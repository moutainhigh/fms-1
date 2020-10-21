/**
 * Auth:riozenc
 * Date:2019年3月8日 下午3:38:49
 * Title:com.riozenc.cim.webapp.service.impl.CustomerServiceImpl.java
 **/
package org.fms.cim.webapp.archives.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.fms.cim.webapp.archives.dao.MeterDAO;
import org.fms.cim.webapp.archives.dao.MeterInductorAssetsRelDAO;
import org.fms.cim.webapp.archives.dao.MeterMeterAssetsRelDAO;
import org.fms.cim.webapp.archives.dao.MeterReplaceInfoDAO;
import org.fms.cim.webapp.archives.dao.SettlementDAO;
import org.fms.cim.webapp.archives.dao.SystemCommonConfigDAO;
import org.fms.cim.webapp.archives.domain.DeptMonDomain;
import org.fms.cim.webapp.archives.domain.LastCodeEntity;
import org.fms.cim.webapp.archives.domain.MeterInductorAssetsRelDomain;
import org.fms.cim.webapp.archives.domain.MeterMeterAssetsRelDomain;
import org.fms.cim.webapp.archives.domain.MeterReplaceDomain;
import org.fms.cim.webapp.archives.domain.SettlementDomain;
import org.fms.cim.webapp.archives.domain.SettlementMeterRelDomain;
import org.fms.cim.webapp.archives.domain.SystemCommonConfigDomain;
import org.fms.cim.webapp.archives.domain.WriteFilesDomain;
import org.fms.cim.webapp.archives.service.IMeterReplaceInfoService;
import org.fms.cim.webapp.assets.dao.InductorAssetsDAO;
import org.fms.cim.webapp.assets.dao.MeterAssetsDAO;
import org.fms.cim.webapp.assets.domain.InductorAssetsDomain;
import org.fms.cim.webapp.assets.domain.MeterAssetsDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.core.type.TypeReference;
import com.riozenc.cim.web.util.CommonUtil;
import com.riozenc.cim.web.util.MonUtils;
import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.annotation.TransactionService;
import com.riozenc.titanTool.common.json.utils.GsonUtils;
import com.riozenc.titanTool.spring.web.client.TitanTemplate;
import com.riozenc.titanTool.spring.web.http.HttpResult;

@TransactionService
public class MeterReplaceInfoServiceImpl implements IMeterReplaceInfoService {

	@Autowired
	private TitanTemplate titanTemplate;
	@TransactionDAO
	private MeterReplaceInfoDAO meterReplaceInfoDAO;
	@TransactionDAO
	private MeterDAO meterDAO;
	@TransactionDAO
	private MeterAssetsDAO meterAssetsDAO;
	@TransactionDAO
	private InductorAssetsDAO inductorAssetsDAO;
	@TransactionDAO
	private SystemCommonConfigDAO systemCommonConfigDAO;
	@TransactionDAO
	private MeterInductorAssetsRelDAO meterInductorAssetsRelDAO;
	@TransactionDAO
	private MeterMeterAssetsRelDAO meterMeterAssetsRelDAO;
	@TransactionDAO
	private SettlementDAO settlementDAO;

	@Override
	public int insert(MeterReplaceDomain t) {
		return meterReplaceInfoDAO.insert(t);
	}

	@Override
	public int delete(MeterReplaceDomain t) {
		return meterReplaceInfoDAO.delete(t);
	}

	@Override
	public int update(MeterReplaceDomain t) {
		return meterReplaceInfoDAO.update(t);
	}

	@Override
	public MeterReplaceDomain findByKey(MeterReplaceDomain t) {
		return meterReplaceInfoDAO.findByKey(t);
	}

	@Override
	public List<MeterReplaceDomain> findByWhere(MeterReplaceDomain t) {
		return meterReplaceInfoDAO.findByWhere(t);
	}

	@Override
	public HttpResult changeDev(String body, List<LastCodeEntity> lastCodeEntityList) {

		HttpResult httpResult = new HttpResult<>();
		httpResult.setStatusCode(HttpResult.ERROR);

		MeterReplaceDomain e = GsonUtils.readValue(body, MeterReplaceDomain.class);

		e.setCreateDate(new Date());
		e.setOperator(Long.parseLong(e.getManagerId() == null ? "0" : e.getManagerId()));

		// 设备类型 1电能表 2互感器
		int equipmentType = 0;
		if (e.getEquipmentType() == 1 || e.getEquipmentType() == 2) {
			equipmentType = e.getEquipmentType();
		} else {
			httpResult.setMessage("无法识别的设备类型");
			return httpResult;
		}

		// 操作类型 1装 2拆
		int operateType = 0;
		if (e.getOperateType() == 1 || e.getOperateType() == 2) {
			operateType = e.getOperateType();
		} else {
			httpResult.setMessage("无法识别的操作类型");
			return httpResult;
		}

		// 单相表不能分时 因为3单相表不好做套扣
		if (e.getMeterAssetsId() != null && equipmentType == 1 && e.getOperateType() == 1) {
			if (new Long("1").equals(e.getTsFlag()) && (!new Byte("4").equals(e.getPhaseSeq()))) {
				httpResult.setMessage("分时的单相表请选择D相序，若要选择其他相序,只能选择非分时的单相表.");
				return httpResult;
			}
		}

		// 资产档案
		MeterAssetsDomain meterAssets = new MeterAssetsDomain();
		InductorAssetsDomain ptAssets = new InductorAssetsDomain();
		InductorAssetsDomain ctAssets = new InductorAssetsDomain();

		// 判断是拆还是装
		/**
		 * 2.装表 生成综合倍率
		 *
		 */
		if (operateType == 1) { // 装

			// 判断表序号
			int maxMeterSn = getMaxMeterSn(e);
			if (e.getMeterSn() == null || e.getMeterSn() - maxMeterSn <= 0) {
//                rmap.put("massage", "所填表序号小于该用户下最大表序号为，最大表序号为：" + maxMeterSn);
//                return rmap;
				e.setMeterSn(maxMeterSn);
			}

			if (e.getMeterAssetsId() != null && equipmentType == 1) {// 电能表
				MeterMeterAssetsRelDomain meterMeterAssetsRelDomain = GsonUtils.readValue(body,
						MeterMeterAssetsRelDomain.class);
				// 判断计量点下是否存在该相电能表
				MeterMeterAssetsRelDomain testMeterAssetsRel = new MeterMeterAssetsRelDomain();
				testMeterAssetsRel.setMeterId(meterMeterAssetsRelDomain.getMeterId());
				testMeterAssetsRel.setFunctionCode(meterMeterAssetsRelDomain.getFunctionCode());
				testMeterAssetsRel.setPowerDirection(meterMeterAssetsRelDomain.getPowerDirection());
				testMeterAssetsRel.setPhaseSeq(meterMeterAssetsRelDomain.getPhaseSeq());
				List<MeterMeterAssetsRelDomain> testList = meterMeterAssetsRelDAO.findByWhere(testMeterAssetsRel);
				if (null != testList && testList.size() > 0) {
					httpResult.setMessage("该计量点下已经存在该相的电能表");
					return httpResult;
				}
				//判断同一计量点下是否存在虚拟+有功表

				// 插入记录
				meterMeterAssetsRelDomain.setCreateDate(new Date());
				meterMeterAssetsRelDomain.setStatus((byte) 1);
				meterMeterAssetsRelDomain.setMeterOrder(
						meterMeterAssetsRelDAO.getNextMeterOrder(meterMeterAssetsRelDomain.getMeterId()));

				// 如果是装表，把资产状态改为 8、装出
				meterAssets.setId(e.getMeterAssetsId());
				meterAssets.setStatus((byte) 8);
				int ma = meterMeterAssetsRelDAO.insert(meterMeterAssetsRelDomain);

				ma += meterAssetsDAO.update(meterAssets);
				// 获取和电能表同向的pt ct
				if (ma <= 0) {
					httpResult.setMessage("电能表资产档案更新失败");
					return httpResult;
				}

			}

			if (equipmentType == 2) {// 装 互感器 需要强关联电能表
				MeterMeterAssetsRelDomain meterAssetsRelDomain = new MeterMeterAssetsRelDomain();
				meterAssetsRelDomain.setMeterId(e.getMeterId());
				meterAssetsRelDomain.setFunctionCode(1L);
				meterAssetsRelDomain.setPowerDirection((byte) 1);
				meterAssetsRelDomain.setPhaseSeq(e.getPhaseSeq());
				List<MeterMeterAssetsRelDomain> meterAssetsRelDomains = meterMeterAssetsRelDAO
						.findByWhere(meterAssetsRelDomain);
				if (meterAssetsRelDomains.isEmpty()) {
					httpResult.setMessage(e.getPhaseSeq() + "相序下没有对应的电能表，请先安装电能表,再来安装互感器");
					return httpResult;
				} else {
					meterAssetsRelDomain = meterAssetsRelDomains.get(0);
					e.setPowerDirection(meterAssetsRelDomain.getPowerDirection());
					e.setFunctionCode(meterAssetsRelDomain.getFunctionCode());
					e.setMeterAssetsId(meterAssetsRelDomain.getMeterAssetsId());
					e.setStatus((byte) 1);
				}

				if (e.getPtAssetsId() != null) {
					int pta = 0;
					// 判断计量点下是否存在该相互感器
					MeterInductorAssetsRelDomain meterInductorAssetsRelDomain = GsonUtils.readValue(body,
							MeterInductorAssetsRelDomain.class);
					MeterInductorAssetsRelDomain testInductorAssetsRel = new MeterInductorAssetsRelDomain();
					testInductorAssetsRel.setMeterId(meterInductorAssetsRelDomain.getMeterId());
					testInductorAssetsRel.setPhaseSeq(meterInductorAssetsRelDomain.getPhaseSeq());
					testInductorAssetsRel.setInductorType(new Byte("2"));
					List<MeterInductorAssetsRelDomain> testList = meterInductorAssetsRelDAO
							.findByWhere(testInductorAssetsRel);
					if (null != testList && testList.size() > 0) {
						httpResult.setMessage("该计量点下已经存在该相的电压互感器");
						return httpResult;
					}
					// 插入运行pt互感器
					if (!e.getPtAssetsId().equals(e.getCtAssetsId())) {
						meterInductorAssetsRelDomain.setCreateDate(new Date());
						meterInductorAssetsRelDomain.setInductorAssetsId(e.getPtAssetsId());
						meterInductorAssetsRelDomain.setInductorType((byte) 2);
						meterInductorAssetsRelDomain.setInductorOrder(meterInductorAssetsRelDAO
								.getNextInductorOrder(meterInductorAssetsRelDomain.getMeterId()));
						meterInductorAssetsRelDomain.setStatus((byte) 1);
						meterInductorAssetsRelDAO.insert(meterInductorAssetsRelDomain);
						ptAssets.setId(e.getPtAssetsId());
						ptAssets.setStatus((byte) 8);
						pta += inductorAssetsDAO.update(ptAssets);
					}
					if (pta <= 0) {
						httpResult.setMessage("pt互感器资产档案更新失败");
						return httpResult;
					}
				}

				if (e.getCtAssetsId() != null) {
					int cta = 0;
					// 判断计量点下是否存在该相互感器
					MeterInductorAssetsRelDomain meterInductorAssetsRelDomain = GsonUtils.readValue(body,
							MeterInductorAssetsRelDomain.class);
					MeterInductorAssetsRelDomain testInductorAssetsRel = new MeterInductorAssetsRelDomain();
					testInductorAssetsRel.setMeterId(meterInductorAssetsRelDomain.getMeterId());
					testInductorAssetsRel.setPhaseSeq(meterInductorAssetsRelDomain.getPhaseSeq());
					testInductorAssetsRel.setInductorType(new Byte("1"));
					List<MeterInductorAssetsRelDomain> testList = meterInductorAssetsRelDAO
							.findByWhere(testInductorAssetsRel);
					if (null != testList && testList.size() > 0) {
						httpResult.setMessage("该计量点下已经存在该相的电流互感器");
						return httpResult;
					}
					// 插入运行ct互感器
					if (!e.getCtAssetsId().equals(e.getPtAssetsId())) {
						meterInductorAssetsRelDomain.setCreateDate(new Date());
						meterInductorAssetsRelDomain.setInductorAssetsId(e.getCtAssetsId());
						meterInductorAssetsRelDomain.setInductorType(new Byte("1"));
						meterInductorAssetsRelDomain.setInductorOrder(meterInductorAssetsRelDAO
								.getNextInductorOrder(meterInductorAssetsRelDomain.getMeterId()));
						meterInductorAssetsRelDomain.setStatus(new Byte("1"));
						cta += meterInductorAssetsRelDAO.insert(meterInductorAssetsRelDomain);
						ctAssets.setId(e.getCtAssetsId());
						ctAssets.setStatus((byte) 8);
						cta = inductorAssetsDAO.update(ctAssets);
					}
					if (cta <= 0) {
						httpResult.setMessage("ct互感器资产档案更新失败");
						return httpResult;
					}
				}

				// 插入运行组合互感器
				if (e.getCtAssetsId() != null && e.getPtAssetsId() != null
						&& e.getPtAssetsId().equals(e.getCtAssetsId())) {
					MeterInductorAssetsRelDomain meterInductorAssetsRelDomain = GsonUtils.readValue(body,
							MeterInductorAssetsRelDomain.class);
					// 判断是否存咋组合互感器
					MeterInductorAssetsRelDomain testInductorAssetsRel = new MeterInductorAssetsRelDomain();
					testInductorAssetsRel.setMeterId(meterInductorAssetsRelDomain.getMeterId());
					testInductorAssetsRel.setPhaseSeq(meterInductorAssetsRelDomain.getPhaseSeq());
					testInductorAssetsRel.setInductorType(new Byte("3"));
					List<MeterInductorAssetsRelDomain> testList = meterInductorAssetsRelDAO
							.findByWhere(testInductorAssetsRel);
					if (null != testList && testList.size() > 0) {
						httpResult.setMessage("该计量点下已经存在该相的组合互感器");
						return httpResult;
					}
					meterInductorAssetsRelDomain.setCreateDate(new Date());
					meterInductorAssetsRelDomain.setInductorAssetsId(e.getCtAssetsId());
					meterInductorAssetsRelDomain.setInductorType(new Byte("3"));
					meterInductorAssetsRelDomain.setInductorOrder(
							meterInductorAssetsRelDAO.getNextInductorOrder(meterInductorAssetsRelDomain.getMeterId()));
					meterInductorAssetsRelDomain.setStatus(new Byte("1"));
					meterInductorAssetsRelDAO.insert(meterInductorAssetsRelDomain);
				}

			}

		} else if (operateType == 2) { // 拆

			// 如果是拆表，就把状态改为9、拆回
			if (e.getMeterAssetsId() != null && equipmentType == 1) {
				meterAssets.setId(e.getMeterAssetsId());
				meterAssets.setStatus((byte) 9);
				int ma = meterAssetsDAO.update(meterAssets);

				// 拆表时生成换表电量
				// changeMeterPower(e);
				changeMeterPowerByFront(e, lastCodeEntityList);

				// 删除运行电能表
				MeterMeterAssetsRelDomain delMeterAssetsRel = new MeterMeterAssetsRelDomain();
				delMeterAssetsRel.setMeterId(e.getMeterId());
				delMeterAssetsRel.setMeterAssetsId(e.getMeterAssetsId());
				delMeterAssetsRel.setFunctionCode(e.getFunctionCode());
				ma += meterMeterAssetsRelDAO.delete(delMeterAssetsRel);
				if (ma <= 0) {
					httpResult.setMessage("电能表资产档案更新失败");
					return httpResult;
				}
			}

			if (e.getPtAssetsId() != null && equipmentType == 2) {
				ptAssets.setId(e.getPtAssetsId());
				ptAssets.setStatus((byte) 9);
				int cta = inductorAssetsDAO.update(ptAssets);
				// 删除关系
				MeterInductorAssetsRelDomain delIndutorAssetsRel = new MeterInductorAssetsRelDomain();
				delIndutorAssetsRel.setMeterId(e.getMeterId());
				delIndutorAssetsRel.setInductorAssetsId(e.getPtAssetsId());
				cta += meterInductorAssetsRelDAO.delete(delIndutorAssetsRel);
				if (cta <= 0) {
					httpResult.setMessage("pt互感器资产档案更新失败");
					return httpResult;
				}
			}
			if (e.getCtAssetsId() != null && equipmentType == 2) {
				ctAssets.setId(e.getCtAssetsId());
				ctAssets.setStatus((byte) 9);
				int pta = inductorAssetsDAO.update(ctAssets);
				// 删除关系
				MeterInductorAssetsRelDomain delIndutorAssetsRel = new MeterInductorAssetsRelDomain();
				delIndutorAssetsRel.setMeterId(e.getMeterId());
				delIndutorAssetsRel.setInductorAssetsId(e.getCtAssetsId());
				pta += meterInductorAssetsRelDAO.delete(delIndutorAssetsRel);
				if (pta <= 0) {
					httpResult.setMessage("ct互感器资产档案更新失败");
					return httpResult;
				}
			}
		}

		// 更新倍率 根据互感器计量点和相线和功能代码查询对应的电能表
		MeterMeterAssetsRelDomain meterMeterAssetsRelDomain = new MeterMeterAssetsRelDomain();
		meterMeterAssetsRelDomain.setFunctionCode(e.getFunctionCode());
		meterMeterAssetsRelDomain.setMeterId(e.getMeterId());
		meterMeterAssetsRelDomain.setPhaseSeq(e.getPhaseSeq());
		List<MeterMeterAssetsRelDomain> returnMeterMeterAssets = meterMeterAssetsRelDAO
				.findByWhere(meterMeterAssetsRelDomain);
		// 如果有电能表则更新倍率
		if (returnMeterMeterAssets != null && returnMeterMeterAssets.size() >= 1) {
			returnMeterMeterAssets.stream().forEach(t -> {
				// 如果前台填了倍率则使用前台填的
				if (e.getFactorNum() != null && !"".equals(e.getFactorNum())) {
					t.setFactorNum(e.getFactorNum());
				} else {
					t.setFactorNum(getFactorNumByMeterAssets(t));
				}

				meterMeterAssetsRelDAO.update(t);
			});
		}

		int count = meterReplaceInfoDAO.insert(e);
		if (count < 1) {
			httpResult.setMessage("操作记录插入失败");
			return httpResult;
		}

		// 组一串返回信息。
		String massage = (operateType == 1 ? "装" : "拆") + (equipmentType == 1 ? "电能表" : "互感器") + "操作成功";

		httpResult.setStatusCode(HttpResult.SUCCESS);
		httpResult.setMessage(massage);

		return httpResult;

	}

	public HttpResult validityJudgment(MeterMeterAssetsRelDomain meterMeterAssetsRelDomain) {
		HttpResult httpResult = new HttpResult<>();
		httpResult.setStatusCode(HttpResult.ERROR);
		
		MeterMeterAssetsRelDomain testMeterAssetsRel = new MeterMeterAssetsRelDomain();
		testMeterAssetsRel.setMeterId(meterMeterAssetsRelDomain.getMeterId());
	
		List<MeterMeterAssetsRelDomain> testList = meterMeterAssetsRelDAO.findByWhere(testMeterAssetsRel);
		int a = 0; //A相
		int b = 0; //B相
		int c = 0; //C相
		int d = 0; //D相
		int v = 0; //虚拟表
		int w = 0; //无功表
		
		/**
		 * 1、虚拟表只能有一块
		 * 2、无功表只能有一块，且为D相
		 * 3、同相有功表只能有一块
		 * 4、A/B/C和D有功不能共存
		 * 
		 * */
		for(MeterMeterAssetsRelDomain tt : testList) {
			
		}
		/*
		 * if() {
		 * 
		 * }
		 */
		
		
		
		if (null != testList && testList.size() > 0) {
			httpResult.setMessage("该计量点下已经存在该相的电能表");
			return httpResult;
		}
		//判断同一计量点下是否存在虚拟+有功表
		
		
		
		return httpResult;
	}


	
	
	
	/**
	 * 根据资产获得倍率 cjd
	 */
	public Double getFactorNumByMeterAssets(MeterMeterAssetsRelDomain receiveDomain) {
		List<MeterInductorAssetsRelDomain> ptMeterInductorRels = new ArrayList<>();
		List<MeterInductorAssetsRelDomain> ctMeterInductorRels = new ArrayList<>();
		MeterInductorAssetsRelDomain meterInductorAssetsRelDomain = new MeterInductorAssetsRelDomain();
		meterInductorAssetsRelDomain.setMeterId(receiveDomain.getMeterId());
		// 无功表
		if (2 == receiveDomain.getFunctionCode()) {
			// pt 无功表先取ptA相 若无再取ptd相
			meterInductorAssetsRelDomain.setPhaseSeq(new Byte("1"));
			meterInductorAssetsRelDomain.setInductorType(new Byte("2"));
			ptMeterInductorRels = meterInductorAssetsRelDAO.findByWhere(meterInductorAssetsRelDomain);
			if (ptMeterInductorRels == null || ptMeterInductorRels.size() < 1) {
				meterInductorAssetsRelDomain.setPhaseSeq(new Byte("4"));
				ptMeterInductorRels = meterInductorAssetsRelDAO.findByWhere(meterInductorAssetsRelDomain);
			}
			// ct 无功表先取ptA相 若无再取ptd相
			meterInductorAssetsRelDomain.setPhaseSeq(new Byte("1"));
			meterInductorAssetsRelDomain.setInductorType(new Byte("1"));
			ctMeterInductorRels = meterInductorAssetsRelDAO.findByWhere(meterInductorAssetsRelDomain);
			if (ctMeterInductorRels == null || ctMeterInductorRels.size() < 1) {
				meterInductorAssetsRelDomain.setPhaseSeq(new Byte("4"));
				ctMeterInductorRels = meterInductorAssetsRelDAO.findByWhere(meterInductorAssetsRelDomain);
			}
			// 若没有值 则取组合互感器
			if ((ptMeterInductorRels == null && ctMeterInductorRels == null)
					|| (ptMeterInductorRels.size() == 0 && ctMeterInductorRels.size() == 0)) {
				meterInductorAssetsRelDomain.setPhaseSeq(new Byte("1"));
				meterInductorAssetsRelDomain.setInductorType(new Byte("3"));
				ctMeterInductorRels = meterInductorAssetsRelDAO.findByWhere(meterInductorAssetsRelDomain);
				ptMeterInductorRels = meterInductorAssetsRelDAO.findByWhere(meterInductorAssetsRelDomain);

				if (ctMeterInductorRels == null || ctMeterInductorRels.size() < 1) {
					meterInductorAssetsRelDomain.setPhaseSeq(new Byte("4"));
					ctMeterInductorRels = meterInductorAssetsRelDAO.findByWhere(meterInductorAssetsRelDomain);
					ptMeterInductorRels = meterInductorAssetsRelDAO.findByWhere(meterInductorAssetsRelDomain);
				}
			}
			List<Long> ctAssetsIds = ctMeterInductorRels.stream().map(t -> t.getInductorAssetsId())
					.collect(Collectors.toList());
			List<Long> ptAssetsIds = ptMeterInductorRels.stream().map(t -> t.getInductorAssetsId())
					.collect(Collectors.toList());

			return calFactorNum(ctAssetsIds, ptAssetsIds);

		} else {
			// pt 无功表先取ptA相 若无再取ptd相
			meterInductorAssetsRelDomain.setPhaseSeq(receiveDomain.getPhaseSeq());
			meterInductorAssetsRelDomain.setInductorType(new Byte("2"));
			ptMeterInductorRels = meterInductorAssetsRelDAO.findByWhere(meterInductorAssetsRelDomain);
			// ct 无功表先取ptA相 若无再取ptd相
			meterInductorAssetsRelDomain.setInductorType(new Byte("1"));
			ctMeterInductorRels = meterInductorAssetsRelDAO.findByWhere(meterInductorAssetsRelDomain);
			if ((ptMeterInductorRels == null && ctMeterInductorRels == null)
					|| (ptMeterInductorRels.size() == 0 && ctMeterInductorRels.size() == 0)) {
				meterInductorAssetsRelDomain.setInductorType(new Byte("3"));
				ctMeterInductorRels = meterInductorAssetsRelDAO.findByWhere(meterInductorAssetsRelDomain);
				ptMeterInductorRels = meterInductorAssetsRelDAO.findByWhere(meterInductorAssetsRelDomain);
			}
			List<Long> ctAssetsIds = ctMeterInductorRels.stream().map(t -> t.getInductorAssetsId())
					.collect(Collectors.toList());
			List<Long> ptAssetsIds = ptMeterInductorRels.stream().map(t -> t.getInductorAssetsId())
					.collect(Collectors.toList());

			return calFactorNum(ctAssetsIds, ptAssetsIds);
		}

	}

	/**
	 * 计算倍率 cjd 根据CT资产号 PT资产号 计算倍率
	 */
	public Double calFactorNum(List<Long> ctAssetsIds, List<Long> ptAssetsIds) {

		Double ctValue = ctAssetsIds.stream().mapToDouble(ctAssetsId -> {
			// 获取ct 变比
			InductorAssetsDomain ctIductorAssets = new InductorAssetsDomain();
			ctIductorAssets.setId(ctAssetsId);
			InductorAssetsDomain returnCtIductorAssets = inductorAssetsDAO.findByKey(ctIductorAssets);
			// 获取下拉对应的ct 值
			SystemCommonConfigDomain ctCommonConfig = systemCommonConfigDAO.findByKeyValue("RATED_CT_CODE",
					returnCtIductorAssets.getRatedCtCode());
			return new BigDecimal(null == ctCommonConfig.getRemark1() || "".equals(ctCommonConfig.getRemark1()) ? "1"
					: ctCommonConfig.getRemark1()).doubleValue();
		}).sum();

		Double ptValue = ptAssetsIds.stream().mapToDouble(ptAssetsId -> {
			// 获取pt 变比
			InductorAssetsDomain ptIductorAssets = new InductorAssetsDomain();
			ptIductorAssets.setId(ptAssetsId);
			InductorAssetsDomain returnPtIductorAssets = inductorAssetsDAO.findByKey(ptIductorAssets);
			// 获取pt 变比
			SystemCommonConfigDomain ptCommonConfig = systemCommonConfigDAO.findByKeyValue("RATED_PT_CODE",
					returnPtIductorAssets.getRatedPtCode());
			return new BigDecimal(null == ptCommonConfig.getRemark1() || "".equals(ptCommonConfig.getRemark1()) ? "1"
					: ptCommonConfig.getRemark1()).doubleValue();
		}).sum();
		if (ctValue == null || ctValue == 0) {
			ctValue = new Double("1");
		}
		if (ptValue == null || ptValue == 0) {
			ptValue = new Double("1");
		}
		return new BigDecimal(ctValue).multiply(new BigDecimal(ptValue)).doubleValue();

	}

	// 换表电量，从前台获取数据
	public void changeMeterPowerByFront(MeterReplaceDomain mr, List<LastCodeEntity> lastCodeEntityList) {

		HashMap<String, Object> rMap = new HashMap<String, Object>();
		rMap.put("result", false);

		BigDecimal rate = new BigDecimal(0);
		// 获取综合倍率。和电能表资产，没有则直接跳出方法
		MeterMeterAssetsRelDomain tmmar = new MeterMeterAssetsRelDomain();
		tmmar.setMeterId(mr.getMeterId());
		tmmar.setPhaseSeq(mr.getPhaseSeq());

		List<MeterMeterAssetsRelDomain> returnMeterMeterAssets = meterMeterAssetsRelDAO.findByWhere(tmmar);
		if (returnMeterMeterAssets.size() == 0 || returnMeterMeterAssets.get(0).getFactorNum() == null) {
			return; // 未装电能表，或电能表综合倍率为空的。直接取消计算
		}
		rate = BigDecimal.valueOf(returnMeterMeterAssets.get(0).getFactorNum());

//		//上次的结算月份，对于未计费过的新户，直接使用装表记录。
//		HashMap<String, Object> monMap = getCurrentMon(mr);
//		String mon = "201910";
//		if ((boolean) monMap.get("result")) {
//			mon = monMap.get("mon").toString();
//		}
//		
//		//上个月表码
//		WriteFilesDomain writeFiles = new WriteFilesDomain();
//		writeFiles.setMeterId(mr.getMeterId());
//		writeFiles.setMon(Integer.parseInt(mon));
//		writeFiles.setMeterAssetsId(returnMeterMeterAssets.get(0).getMeterAssetsId());
//		writeFiles.setFunctionCode(mr.getFunctionCode().byteValue());    //有功
//		writeFiles.setPowerDirection(mr.getPowerDirection().byteValue()); //正向
//		List<WriteFilesDomain> writeFileList = getCodeByMeter(writeFiles);
//		
//		//上次的装表记录
//		MeterReplaceDomain lmr = getLastMeterReplaceInfo(mr);
//		if (lmr == null) {
//		    //无装表记录，报错。一般不会
//			return;
//		}

		// 计算换表电量并赋值到mr
		calcReplacePower(mr, lastCodeEntityList, rate);

	}

	/**
	 * 生成换表电量 拆表时获取拆表底码和上次结算止码
	 */
	public void changeMeterPower(MeterReplaceDomain mr) {

		HashMap<String, Object> rMap = new HashMap<String, Object>();
		rMap.put("result", false);

		/**
		 * 换标记录添加当前电费月份，处理月份
		 *
		 * 根据计量点找结算户 根据结算户的营业区域获取电费月份 根据电费月份和计量点ID获取止码和止码抄表时间
		 *
		 * 用meterid、相序 获取综合倍率
		 *
		 * 判断装表和上次表码的时间先后
		 *
		 * 根据起止码和综合倍率计算分费率电量
		 *
		 * 将换表电量放在P5开头的字段
		 *
		 */
		BigDecimal rate = new BigDecimal(0);
		// 获取综合倍率。和电能表资产，没有则直接跳出方法
		MeterMeterAssetsRelDomain tmmar = new MeterMeterAssetsRelDomain();
		tmmar.setMeterId(mr.getMeterId());
		tmmar.setPhaseSeq(mr.getPhaseSeq());

		List<MeterMeterAssetsRelDomain> returnMeterMeterAssets = meterMeterAssetsRelDAO.findByWhere(tmmar);
		if (returnMeterMeterAssets.size() == 0 || returnMeterMeterAssets.get(0).getFactorNum() == null) {
			return; // 未装电能表，或电能表综合倍率为空的。直接取消计算
		}
		rate = BigDecimal.valueOf(returnMeterMeterAssets.get(0).getFactorNum());

		// 上次的结算月份，对于未计费过的新户，直接使用装表记录。
		HashMap<String, Object> monMap = getCurrentMon(mr);
		String mon = "201910";
		if ((boolean) monMap.get("result")) {
			mon = monMap.get("mon").toString();
		}

		// 上个月表码
		WriteFilesDomain writeFiles = new WriteFilesDomain();
		writeFiles.setMeterId(mr.getMeterId());
		writeFiles.setMon(Integer.parseInt(mon));
		writeFiles.setMeterAssetsId(returnMeterMeterAssets.get(0).getMeterAssetsId());
		writeFiles.setFunctionCode(mr.getFunctionCode().byteValue()); // 有功
		writeFiles.setPowerDirection(mr.getPowerDirection().byteValue()); // 正向
		List<WriteFilesDomain> writeFileList = getCodeByMeter(writeFiles);

		// 上次的装表记录
		MeterReplaceDomain lmr = getLastMeterReplaceInfo(mr);
		if (lmr == null) {
			// 无装表记录，报错。一般不会
			return;
		}

		// 计算换表电量并赋值到mr
		if (writeFileList.size() == 0 || lmr.getReplaceDate().after(writeFileList.get(0).getWriteDate())) {// 首次算费
			calcReplacePower(null, lmr, rate, mr);
		} else {
			calcReplacePower(writeFileList, null, rate, mr);
		}
	}

	// 获取计量点结算户的当前电费月份
	private HashMap<String, Object> getCurrentMon(MeterReplaceDomain mr) {

		HashMap<String, Object> rMap = new HashMap<String, Object>();
		rMap.put("result", false);

		SettlementMeterRelDomain smr = new SettlementMeterRelDomain();
		smr.setMeterId(mr.getMeterId());
		List<SettlementDomain> sl = settlementDAO.getSettlementbyMeter(smr);
		if (sl.size() == 0) {
			rMap.put("massage", "计量点未关联结算户");
			return rMap;
		}
		Long deptId = sl.get(0).getBusinessPlaceCode();

		HashMap<String, Object> tempMap = new HashMap<String, Object>();
//        tempMap.put("ids", "[" + deptId + "]");
		tempMap.put("ids", Arrays.asList(deptId));

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		Calendar cal = Calendar.getInstance();

		try {
			List<DeptMonDomain> deptMonDomains = titanTemplate.post("BILLING-SERVER",
					"billingServer/deptMon?method=getDeptCurrentMonById", httpHeaders, tempMap,
					new TypeReference<List<DeptMonDomain>>() {
					});

			if (deptMonDomains.size() == 0 || deptMonDomains.get(0).getMon() == null) {
				rMap.put("result", true);
				rMap.put("mon", cal.get(Calendar.YEAR) + String.format("%02d", cal.get(Calendar.MONTH)));
				return rMap;
			}

			int mon = Integer.parseInt(deptMonDomains.get(0).getMon());
			cal.set(mon / 100, mon % 100, 3);// 这个3 没有特殊意义,可以删除。
			rMap.put("mon", cal.get(Calendar.YEAR) + String.format("%02d", cal.get(Calendar.MONTH)));
			rMap.put("result", true);
			return rMap;
		} catch (Exception e) {
			rMap.put("massage", "调用billingServer失败");
			return rMap;
		}

	}

	// 获取计量点ID和电费月份获取抄表止码和时间
	private List<WriteFilesDomain> getCodeByMeter(WriteFilesDomain writeFiles) {

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		List<WriteFilesDomain> writeFileList = new ArrayList<WriteFilesDomain>();

		try {
			writeFileList = titanTemplate.post("BILLING-SERVER", "billingServer/WriteFiles/getWriteFiles", httpHeaders,
					writeFiles, new TypeReference<List<WriteFilesDomain>>() {
					});

		} catch (Exception e) {
			// TODO 待添加返回信息或处理方法
		}
		return writeFileList;

	}

	// 根据meterid和电能表资产或互感器资产获取上次的装表记录
	private MeterReplaceDomain getLastMeterReplaceInfo(MeterReplaceDomain mr) {
		MeterReplaceDomain tmr = new MeterReplaceDomain();
		tmr.setMeterId(mr.getMeterId());
		tmr.setMeterAssetsId(mr.getMeterAssetsId());
		tmr.setCtAssetsId(mr.getCtAssetsId());
		tmr.setPtAssetsId(mr.getPtAssetsId());
		tmr.setOperateType((byte) 1);
		List<MeterReplaceDomain> tl = meterReplaceInfoDAO.findByWhere(tmr);

		if (tl == null || tl.size() == 0) {

			return new MeterReplaceDomain();
		}
		return tl.get(0);

	}

	// 计算换表电量，从前台获取上次抄表
	private void calcReplacePower(MeterReplaceDomain mr, List<LastCodeEntity> lastCodeEntityList, BigDecimal rate) {

		for (LastCodeEntity tl : lastCodeEntityList) {
			String type = tl.getType();
			switch (type) {
			case "总": // 0
				mr.setP5r0(calcPower(tl.getOldP1(), mr.getP1r0(), rate)); // 正有
				mr.setP6r0(calcPower(tl.getOldP2(), mr.getP2r0(), rate)); // 反有
				mr.setP7r0(calcPower(tl.getOldP3(), mr.getP3r0(), rate)); // 正无
				mr.setP8r0(calcPower(tl.getOldP4(), mr.getP4r0(), rate)); // 反无
				break;
			case "峰":// 1
				mr.setP5r1(calcPower(tl.getOldP1(), mr.getP1r1(), rate)); // 正有
				mr.setP6r1(calcPower(tl.getOldP2(), mr.getP2r1(), rate)); // 反有
				mr.setP7r1(calcPower(tl.getOldP3(), mr.getP3r1(), rate)); // 正无
				mr.setP8r1(calcPower(tl.getOldP4(), mr.getP4r1(), rate)); // 反无
				break;
			case "平":// 2
				mr.setP5r2(calcPower(tl.getOldP1(), mr.getP1r2(), rate)); // 正有
				mr.setP6r2(calcPower(tl.getOldP2(), mr.getP2r2(), rate)); // 反有
				mr.setP7r2(calcPower(tl.getOldP3(), mr.getP3r2(), rate)); // 正无
				mr.setP8r2(calcPower(tl.getOldP4(), mr.getP4r2(), rate)); // 反无
				break;
			case "谷":// 3
				mr.setP5r3(calcPower(tl.getOldP1(), mr.getP1r3(), rate)); // 正有
				mr.setP6r3(calcPower(tl.getOldP2(), mr.getP2r3(), rate)); // 反有
				mr.setP7r3(calcPower(tl.getOldP3(), mr.getP3r3(), rate)); // 正无
				mr.setP8r3(calcPower(tl.getOldP4(), mr.getP4r3(), rate)); // 反无
				break;
			case "尖":// 4
				mr.setP5r4(calcPower(tl.getOldP1(), mr.getP1r4(), rate)); // 正有
				mr.setP6r4(calcPower(tl.getOldP2(), mr.getP2r4(), rate)); // 反有
				mr.setP7r4(calcPower(tl.getOldP3(), mr.getP3r4(), rate)); // 正无
				mr.setP8r4(calcPower(tl.getOldP4(), mr.getP4r4(), rate)); // 反无
				break;
			}
		}

	}

	// jiasuan
	private BigDecimal calcPower(BigDecimal o, BigDecimal n, BigDecimal rate) {
		BigDecimal to = o == null ? new BigDecimal(0) : o;
		BigDecimal tn = n == null ? new BigDecimal(0) : n;

		BigDecimal tr = new BigDecimal(0);
		// 新大于等于旧
		if (tn.compareTo(to) > -1) {
			tr = tn.subtract(to).multiply(rate);
		} else {// 新小于旧，考虑满码
			int i = String.valueOf(to.setScale(0, to.ROUND_DOWN)).length();

			BigDecimal full = BigDecimal.valueOf(Math.pow(10, i));

			tr = full.subtract(to).multiply(rate).add(tn.multiply(rate));

		}

		return tr;
	}

	// 计算换表电量
	private void calcReplacePower(List<WriteFilesDomain> writeFileList, MeterReplaceDomain lmr, BigDecimal rate,
			MeterReplaceDomain mr) {

		BigDecimal p1r0 = new BigDecimal(0);
		BigDecimal p1r1 = new BigDecimal(0);
		BigDecimal p1r2 = new BigDecimal(0);
		BigDecimal p1r3 = new BigDecimal(0);
		BigDecimal p1r4 = new BigDecimal(0);

		if (writeFileList == null) {// 上次装表在本抄表周期内，使用上次装表记录计算
			p1r0 = mr.getP1r0() == null ? new BigDecimal(0)
					: mr.getP1r0().subtract(lmr.getP1r0() == null ? new BigDecimal(0) : lmr.getP1r0()).multiply(rate);
			if (mr.getP1r1() != null && lmr.getP1r1() != null) {
				p1r1 = mr.getP1r1().subtract(lmr.getP1r1()).multiply(rate);// 尖
			}
			if (mr.getP1r2() != null && lmr.getP1r2() != null) {
				p1r2 = mr.getP1r2().subtract(lmr.getP1r2()).multiply(rate);// 峰
			}
			if (mr.getP1r3() != null && lmr.getP1r3() != null) {
				p1r3 = mr.getP1r3().subtract(lmr.getP1r3()).multiply(rate);// 平
			}
			if (mr.getP1r4() != null && lmr.getP1r4() != null) {
				p1r4 = mr.getP1r4().subtract(lmr.getP1r4()).multiply(rate);// 谷
			}
		} else {// 上次装表不在本抄表周期内，使用上月止码计算
			for (WriteFilesDomain wf : writeFileList) {
				switch (wf.getTimeSeg()) {
				case 0:
					if (mr.getP1r0() != null) {
						p1r0 = mr.getP1r0().subtract(wf.getEndNum()).multiply(rate);
					}
					break;
				case 1: // 峰
					if (mr.getP1r2() != null) {
						p1r2 = mr.getP1r2().subtract(wf.getEndNum()).multiply(rate);
					}
					break;
				case 2: // 平
					if (mr.getP1r3() != null) {
						p1r3 = mr.getP1r3().subtract(wf.getEndNum()).multiply(rate);
					}
					break;
				case 3: // 谷
					if (mr.getP1r4() != null) {
						p1r4 = mr.getP1r4().subtract(wf.getEndNum()).multiply(rate);
					}
					break;
				case 4: // 尖
					if (mr.getP1r1() != null) {
						p1r1 = mr.getP1r1().subtract(wf.getEndNum()).multiply(rate);
					}
					break;
				}
			}
		}
		mr.setP5r0(p1r0);
		mr.setP5r1(p1r1);
		mr.setP5r2(p1r2);
		mr.setP5r3(p1r3);
		mr.setP5r4(p1r4);

	}

	@Override
	public List<MeterReplaceDomain> findByMeter(MeterReplaceDomain mr) {
		return meterReplaceInfoDAO.findByMeter(mr);
	}

	@Override
	public List<MeterReplaceDomain> getMeterReplaceByWriteSectIds(List<Long> writeSectIds) {
		return meterReplaceInfoDAO.getMeterReplaceByWriteSectIds(writeSectIds);
	}

	@Override
	public List<MeterReplaceDomain> getMeterReplaceByMeterIds(List<Long> meterIds) {

		List<MeterReplaceDomain> rList = new ArrayList<MeterReplaceDomain>();
		// 处理超过1000个id
		int len = meterIds.size();
		for (int m = 0; m < len / 999 + 1; m++) {// 遍历次数

			List<Long> tl = meterIds.subList(m * 999, (m + 1) * 999 > len ? len : (m + 1) * 999);

			List<MeterReplaceDomain> tList = meterReplaceInfoDAO.getMeterReplaceByMeterIds(tl);
			rList.addAll(tList);
		}
		// 去重
		HashSet<MeterReplaceDomain> rSet = new HashSet<MeterReplaceDomain>(rList);

		return new ArrayList<MeterReplaceDomain>(rSet);

	}

	// 拆表时查询上月表码
	@Override
	public List<LastCodeEntity> getLastCodeByMeter(MeterReplaceDomain meterReplaceDomain) {
		// 上个月表码

		HashMap<String, Object> monMap = getCurrentMon(meterReplaceDomain);
		// 当前月份
		String mon = CommonUtil.getYMD().get(0) + CommonUtil.getYMD().get(1);
		if ((boolean) monMap.get("result")) {
			mon = monMap.get("mon").toString();
		}
		String lastMon = MonUtils.getLastMon(mon);
		WriteFilesDomain writeFiles = new WriteFilesDomain();
		writeFiles.setMon(Integer.parseInt(lastMon));
		writeFiles.setFunctionCode(meterReplaceDomain.getFunctionCode().byteValue());
		writeFiles.setMeterId(meterReplaceDomain.getMeterId());
		writeFiles.setPowerDirection(meterReplaceDomain.getPowerDirection());
		writeFiles.setMeterAssetsId(meterReplaceDomain.getMeterAssetsId());
		// 上月表码
		List<WriteFilesDomain> writeFileList = getCodeByMeter(writeFiles);

		Date writeDate = new Date(0);
		if (writeFileList != null && writeFileList.size() > 0) {
			Optional<WriteFilesDomain> userOp = writeFileList.stream().filter(Objects::nonNull)
					.max(Comparator.comparing(WriteFilesDomain::getWriteDate));
			writeDate = userOp.orElse(new WriteFilesDomain()).getWriteDate();
		}

		// 上次装表记录
		Date replaceDate = new Date(0);
		MeterReplaceDomain meterReplaceLastCode = new MeterReplaceDomain();
		List<MeterReplaceDomain> meterReplaceDomains = meterReplaceInfoDAO.findByWhere(meterReplaceDomain);
		List<WriteFilesDomain> replaceWriteFiles = new ArrayList<>();
		if (meterReplaceDomains != null && meterReplaceDomains.size() > 0) {
			Optional<MeterReplaceDomain> userOp = meterReplaceDomains.stream().filter(Objects::nonNull)
					.max(Comparator.comparing(MeterReplaceDomain::getReplaceDate));
			meterReplaceLastCode = userOp.orElse(new MeterReplaceDomain());
			replaceDate = meterReplaceLastCode.getReplaceDate();
			// 组成writefiles结构
		}

		// 换表日期 大于上次抄表日期
		if (replaceDate.after(writeDate)) {
			writeFileList = CommonUtil.transCjToMis(meterReplaceLastCode);
		}

		// 返回实体
		List<LastCodeEntity> lastCodeEntities = new ArrayList<>();
		writeFileList.stream().forEach(t -> {
			switch (t.getTimeSeg()) {
			case 0: {// 总段
				LastCodeEntity lastCodeEntity = analysisMeterCOde(t);
				lastCodeEntity.setTsFlag(new Long("0"));
				lastCodeEntities.add(lastCodeEntity);
				break;
			}
			case 1: {// 峰段
				LastCodeEntity lastCodeEntity = analysisMeterCOde(t);
				lastCodeEntity.setTsFlag(new Long("1"));
				lastCodeEntities.add(lastCodeEntity);
				break;
			}
			case 2: {// 平段
				LastCodeEntity lastCodeEntity = analysisMeterCOde(t);
				lastCodeEntity.setTsFlag(new Long("2"));
				lastCodeEntities.add(lastCodeEntity);
				break;
			}
			case 3: {// 谷段
				LastCodeEntity lastCodeEntity = analysisMeterCOde(t);
				lastCodeEntity.setTsFlag(new Long("3"));
				lastCodeEntities.add(lastCodeEntity);
				break;
			}
			case 4: {// 尖段
				LastCodeEntity lastCodeEntity = analysisMeterCOde(t);
				lastCodeEntity.setTsFlag(new Long("4"));
				lastCodeEntities.add(lastCodeEntity);
				break;
			}
			}
		});
		return lastCodeEntities;

	}

	// 上月表码实体
	/*
	 * 上余表码数据结构 list: [ 0: { // 总 oldP1: null, oldP2: null, oldP3: null, oldP4:
	 * null,}, 1: { // 尖 oldP1: null, oldP2: null, oldP3: null, oldP4: null,}, 2: {
	 * // 峰 oldP1: null, oldP2: null, oldP3: null, oldP4: null,}, 3: { // 平 oldP1:
	 * null, oldP2: null, oldP3: null, oldP4: null,}, 4: { // 谷 oldP1: null, oldP2:
	 * null, oldP3: null, oldP4: null,}, ]
	 */

	// 解析writefiles
	public LastCodeEntity analysisMeterCOde(WriteFilesDomain t) {
		LastCodeEntity lastCodeEntity = new LastCodeEntity();
		// 正有
		if ((new Byte("1")).equals(t.getPowerDirection()) && (new Byte("1")).equals(t.getFunctionCode())) {
			lastCodeEntity.setOldP1(t.getEndNum());
		}
		// 反有
		if ((new Byte("2")).equals(t.getPowerDirection()) && (new Byte("1")).equals(t.getFunctionCode())) {
			lastCodeEntity.setOldP2(t.getEndNum());
		}
		// 正无
		if ((new Byte("1")).equals(t.getPowerDirection()) && (new Byte("2")).equals(t.getFunctionCode())) {
			lastCodeEntity.setOldP3(t.getEndNum());
		}
		// 反无
		if ((new Byte("2")).equals(t.getPowerDirection()) && (new Byte("2")).equals(t.getFunctionCode())) {
			lastCodeEntity.setOldP4(t.getEndNum());
		}
		return lastCodeEntity;
	}

	@Override
	public int insertList(List<MeterReplaceDomain> mrList) {
		return meterReplaceInfoDAO.insertList(mrList);

	}

	@Override
	public int updateList(List<MeterReplaceDomain> mruList) {
		return meterReplaceInfoDAO.updateList(mruList);

	}

	// 根据计量点获取该用户下最大的表序号
	public int getMaxMeterSn(MeterReplaceDomain m) {

		int maxMeterSn = meterReplaceInfoDAO.getMaxMeterSn(m);

		return maxMeterSn;

	}
}
