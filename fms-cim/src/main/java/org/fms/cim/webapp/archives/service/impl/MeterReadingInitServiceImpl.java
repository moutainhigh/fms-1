/**
 *    Auth:riozenc
 *    Date:2019年3月12日 下午7:16:23
 *    Title:com.riozenc.cim.webapp.service.impl.SettlementServiceImpl.java
 **/
package org.fms.cim.webapp.archives.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.mbs.client.producer.ProducerClient;
import org.mbs.client.producer.ProducerClient.ProducerConfig;
import org.mbs.client.producer.ProducerStarter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.core.type.TypeReference;
import com.riozenc.cim.webapp.archives.domain.DeptMonDomain;
import com.riozenc.cim.webapp.archives.domain.MeterDomain;
import com.riozenc.cim.webapp.archives.domain.UserDomain;
import com.riozenc.cim.webapp.archives.domain.WriteSectDomain;
import com.riozenc.cim.webapp.archives.service.ICustomerService;
import com.riozenc.cim.webapp.archives.service.IMeterInductorAssetsService;
import com.riozenc.cim.webapp.archives.service.IMeterMeterAssetsService;
import com.riozenc.cim.webapp.archives.service.IMeterReadingInitService;
import com.riozenc.cim.webapp.archives.service.IMeterRelationService;
import com.riozenc.cim.webapp.archives.service.IMeterReplaceInfoService;
import com.riozenc.cim.webapp.archives.service.IMeterService;
import com.riozenc.cim.webapp.archives.service.ITransformerMeterRelationService;
import com.riozenc.cim.webapp.archives.service.ITransformerService;
import com.riozenc.cim.webapp.archives.service.IUserService;
import com.riozenc.cim.webapp.archives.service.IWriteSectService;
import com.riozenc.titanTool.annotation.TransactionService;
import com.riozenc.titanTool.common.json.utils.GsonUtils;
import com.riozenc.titanTool.spring.web.client.TitanTemplate;
import com.riozenc.titanTool.spring.web.http.HttpResult;

//原始方法
@TransactionService
public class MeterReadingInitServiceImpl implements IMeterReadingInitService {

	@Autowired
	private TitanTemplate titanTemplate;
	@Autowired
	@Qualifier("meterServiceImpl")
	private IMeterService meterService;
	@Autowired
	@Qualifier("meterRelationServiceImpl")
	private IMeterRelationService meterRelationService;
	@Autowired
	@Qualifier("meterReplaceInfoServiceImpl")
	private IMeterReplaceInfoService meterReplaceInfoService;
	@Autowired
	@Qualifier("transformerServiceImpl")
	private ITransformerService transformerService;
	@Autowired
	@Qualifier("transformerMeterRelationServiceImpl")
	private ITransformerMeterRelationService transformerMeterRelationService;
	@Autowired
	@Qualifier("userServiceImpl")
	private IUserService userService;
	@Autowired
	@Qualifier("customerServiceImpl")
	private ICustomerService customerService;
	@Autowired
	@Qualifier("writeSectServiceImpl")
	private IWriteSectService writeSectService;
	@Autowired
	@Qualifier("meterMeterAssetsServiceImpl")
	private IMeterMeterAssetsService meterMeterAssetsService;
	@Autowired
	@Qualifier("meterInductorAssetsServiceImpl")
	private IMeterInductorAssetsService meterInductorAssetsService;

	private final ExecutorService executorService = Executors.newFixedThreadPool(10);

	// 按客户ID初始化
	@Override
	public HttpResult meterReadingInitByCustomer(List<Long> customerIds, String sn) {

		if (customerIds.size() == 0) {
			return new HttpResult(HttpResult.ERROR, "无所选客户");
		}

		List<UserDomain> userList = userService.getUserByCustomerIds(customerIds);
		if (userList.size() == 0) {
			return new HttpResult(HttpResult.ERROR, "所选客户无用户");
		}
		//userIDS去重
		HashSet<Long> userIdSet = new HashSet<Long>();
		for(UserDomain tempuser :userList) {
			userIdSet.add(tempuser.getId());
		}
		List<Long> userIdList = new ArrayList<Long>(userIdSet);
		// 获取用户获取所属dept

		List<Long> deptIds = userService.getDeptIdsByUserIds(userIdList);
		
		if (deptIds.size() == 0) {
			return new HttpResult(HttpResult.ERROR, "用户无管理单位");
		}
		HashMap<String, Object> remap = getCurrentMon(deptIds);
		if (!(boolean) remap.get("result")) {
			return new HttpResult(HttpResult.ERROR, remap.get("massage").toString());
		}

		List<MeterDomain> meterList = meterService.getMeterByUserIds(userIdList);

		if (meterList.size() == 0) {
			return new HttpResult(HttpResult.ERROR, "所选客户无计量点");
		}

		return cfsPost(userList, meterList, remap.get("mon").toString(), sn);

	}

	// 按照抄表段id初始化
	@Override
	public HttpResult meterReadingInitByWriteSec(List<Long> writeSectIds, String sn) {

		if (writeSectIds == null || writeSectIds.size() == 0) {
			return new HttpResult(HttpResult.ERROR, "未选择抄表段");
		}
		// 获取抄表段所属dept
		List<Long> deptIds = writeSectService.getDeptIdsByWriteSectIds(writeSectIds);
		if (deptIds == null || deptIds.size() == 0) {
			return new HttpResult(HttpResult.ERROR, "抄表段无管理单位");
		}
		// 电费月份判断
		HashMap<String, Object> remap = getCurrentMon(deptIds);
		if (!(boolean) remap.get("result")) {
			return new HttpResult(HttpResult.ERROR, remap.get("massage").toString());
		}
		
	
		// 根据抄表区段ID获取用户
		List<UserDomain> userList = userService.getUserByWriteSectIds(writeSectIds);
		if (userList.size() == 0) {
			return new HttpResult(HttpResult.ERROR, "无用户");
		}
		HashSet<Long> userIdSet = new HashSet<Long>();
		for(UserDomain tempuser :userList) {
			userIdSet.add(tempuser.getId());
		}
		List<Long> userIdList = new ArrayList<Long>(userIdSet);
		// 获取测量点
		List<MeterDomain> meterList = meterService.getMeterByUserIds(userIdList);
		if (meterList.size() == 0) {
			return new HttpResult(HttpResult.ERROR, "无计量点");
		}

		return cfsPost(userList, meterList, remap.get("mon").toString(), sn);


	}

	// 调用cfs抄表初始化
	private HttpResult cfsPost(List<UserDomain> userList, List<MeterDomain> meterList, String mon, String sn) {

		HashMap<String, Object> cimMap = new HashMap<String, Object>();
		HashSet<Long> meterIdsSet = new HashSet<Long>();
		for(MeterDomain tempuser :meterList) {
			meterIdsSet.add(tempuser.getId());
		}
		List<Long> meterIdsList = new ArrayList<Long>(meterIdsSet);

		// 抄表次数
		cimMap.put("sn", sn);
		// 月份
		cimMap.put("date", mon);
		// 用电户
		cimMap.put("userDomains", userList);
		// 计量点
		cimMap.put("meterDomains", meterList);
		// 计量点关系
		cimMap.put("meterRelationDomains", meterService.getMeterRelByMeterIds(meterIdsList));
		// 换表记录
		cimMap.put("meterReplaceDomains", meterReplaceInfoService.getMeterReplaceByMeterIds(meterIdsList));
		// 测量点与变压器关系
		cimMap.put("transformerMeterRelationDomains",
				transformerMeterRelationService.getTransformerMeterRelationByMeterIds(meterIdsList));
		// 变压器
		cimMap.put("transformerDomains", transformerService.getTransformerByMeterIds(meterIdsList));
		// 计量点与表计的关系
		cimMap.put("meterMeterAssetsRelDomains", meterMeterAssetsService.getMeterAssetsByMeterIds(meterIdsList));
		// 计量点与互感器的关系
		cimMap.put("meterInductorAssetsRelDomains", meterInductorAssetsService.getMeterInductorByMeterIds(meterIdsList));


		try {
			return titanTemplate.post("CFS", "cfs/billingDataInit?method=initializeMany", null, cimMap,
					HttpResult.class);
		} catch (Exception e) {

			return new HttpResult(HttpResult.ERROR, "抄表初始化失败,CFS服务异常");
		}

	}

	// 获取初始化日期
	private HashMap<String, Object> getCurrentMon(List<Long> ids) {

		HashMap<String, Object> tempMap = new HashMap<String, Object>();
		HashMap<String, Object> reMap = new HashMap<String, Object>();
		tempMap.put("ids", ids);
		reMap.put("result", false);
		reMap.put("massage", "开始获取电费月份");

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		try {
			List<DeptMonDomain> deptMonDomains = titanTemplate.post("BILLING-SERVER",
					"billingServer/deptMon?method=getDeptCurrentMonById", httpHeaders, tempMap,
					new TypeReference<List<DeptMonDomain>>() {
					});

			Set<String> set = deptMonDomains.stream().map(m -> m.getMon()).collect(Collectors.toSet());
			String[] mons = new String[1];
			if (set.size() == 1) {
				set.toArray(mons);
				if (mons[0] != null && !"".equals(mons[0])) {
					reMap.put("result", true);
					reMap.put("mon", mons[0]);
					reMap.put("massage", "获取电费月份成功，当前月份为" + mons[0]);

				} else {
					reMap.put("massage", "所选对象的电费月份异常");
				}

			} else if (set.size() == 0) {
				reMap.put("massage", "获取电费月份失败，当前月份为空");
			} else if (set.size() > 1) {
				reMap.put("massage", "所选对象的电费月份不一致");
			} else {
				reMap.put("massage", "所选对象的电费月份异常");

			}

		} catch (Exception e) {

			reMap.put("massage", "获取电费月份失败，调用biling服务失败");
		}

		return reMap;

	}
	
	private <T> T futureGet(Callable<T> callable) throws InterruptedException, ExecutionException {

		Future<T> future = executorService.submit(callable);

		return future.get();
	}

	@Override
	public HttpResult getInitMetersByWriteSect(WriteSectDomain e) {
		// TODO Auto-generated method stub
		return null;
	}

}
