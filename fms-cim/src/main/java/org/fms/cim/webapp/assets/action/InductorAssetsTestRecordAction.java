/**
 *    Auth:riozenc
 *    Date:2019年3月14日 下午8:26:01
 *    Title:com.riozenc.cim.webapp.action.MeterAssetsAction.java
 **/
package org.fms.cim.webapp.assets.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.riozenc.cim.web.config.JsonGrid;
import com.riozenc.cim.webapp.archives.domain.SystemCommonConfigDomain;
import com.riozenc.cim.webapp.archives.service.ISystemCommonConfigService;
import com.riozenc.cim.webapp.assets.domain.InductorAssetsDomain;
import com.riozenc.cim.webapp.assets.domain.InductorAssetsTestRecordDetailDomain;
import com.riozenc.cim.webapp.assets.domain.InductorAssetsTestRecordDomain;
import com.riozenc.cim.webapp.assets.service.IInductorAssetsTestRecordService;
import com.riozenc.titanTool.common.json.utils.GsonUtils;
import com.riozenc.titanTool.common.json.utils.JSONUtil;
import com.riozenc.titanTool.spring.web.http.HttpResult;

import reactor.core.publisher.Mono;

/**
 * 互感器资产检测
 *
 * @author yhx
 *
 */
@ControllerAdvice(assignableTypes = InductorAssetsTestRecordAction.class)
@RequestMapping("inductorAssetsTestRecord")
public class InductorAssetsTestRecordAction  {

	@Autowired
	@Qualifier("inductorAssetsTestRecordServiceImpl")
	private IInductorAssetsTestRecordService inductorAssetsTestRecordService;
	@Autowired
	@Qualifier("systemCommonConfigServiceImpl")
	private ISystemCommonConfigService systemCommonConfigService;

	
	/**
	 * 开始检测
	 *
	 * @param 
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@ResponseBody
	@PostMapping(params = "method=testBegin")
	public HashMap<String, Object> testBegin(@RequestBody String body) throws JsonParseException, JsonMappingException, IOException {
		
		
		List<InductorAssetsDomain> inductorAssetsList =
				JSONUtil.readValue(body, new TypeReference<List<InductorAssetsDomain>>() {});

	//	JsonNode jsonNode = JSONUtil.readValue(body, JsonNode.class);

//		List<InductorAssetsDomain> inductorAssetsList =
//				JSONUtil.readValue(jsonNode.get("inductorAssetsInfo").toString(), new TypeReference<List<InductorAssetsDomain>>() {});

		int count = inductorAssetsList.size();
		
		HashMap<String, Object> rmap = new HashMap<String,Object>();
		rmap.put("result",false );
		//result  message  InductorAssetsTestRecord
		/**
		 * 0、判断数量，1-3个
		 * 1、判断互感器状态是否是入库0、拆回9、待检23
		 * 2、判断pt变比，ct变比
		 * 3、判断制造单位   ---------暂无
		 * 
		 * 1、修改资产状态为在检
		 * 2、存记录
		 * 3、将类型、规格、出场编号、制造单位、等复制到检测记录里面。
		 * */
		//初次判断所选资产是否满足检测条件

		if(count<1||count>3) {
			rmap.put("message", "资产个数不符，目前仅支持同时检测3个以内");
			return rmap;
		}
		
		//返回前台的对象
		InductorAssetsTestRecordDomain iat = new InductorAssetsTestRecordDomain();
		InductorAssetsDomain ia = inductorAssetsList.get(0);
		iat.setInductorAssetsID1(ia.getId());
		String madeNo = ia.getMadeNo();
		
		for(int i = 0;i<count;i++) {
			InductorAssetsDomain ti = inductorAssetsList.get(i);
			if(ti.getStatus()!=0&&ti.getStatus()!=9&&ti.getStatus()!=23) {
				//TODO 如果是在检中，将之前的报告返回到前台。
				//TODO 如果选择的3个资产不是之前在一组的资产          怎么办。
				rmap.put("message", "请确认资产状态为入库、拆回或待检测"); 
				return rmap;
			}
			if(ti.getStatus()==27) {
				rmap.put("message", "资产处于在检状态，请确认！出厂编号为"+ti.getMadeNo()); 
				return rmap;
			}
			if(ti.getRatedPtCode()!=ia.getRatedPtCode()||ti.getRatedCtCode()!=ia.getRatedCtCode()) {
				rmap.put("message", "选择的资产的变比必须一致");
				return rmap;
			}
			if(i == 1) {
				iat.setInductorAssetsID2(inductorAssetsList.get(1).getId());
				madeNo = madeNo + " "+ inductorAssetsList.get(1).getMadeNo();
			}
			else if(i == 2) {
				iat.setInductorAssetsID3(inductorAssetsList.get(2).getId());
				madeNo = madeNo + " "+ inductorAssetsList.get(2).getMadeNo();
			}
		}
		iat.setMadeNo(madeNo);

		//1、2次电流   由于下拉中维护不规范，无法获取
		//1、2次电压
		SystemCommonConfigDomain scc = new SystemCommonConfigDomain();
		scc.setStatus((byte)1);
//		//互感器类型
//		scc.setType("INDUCTOR_TYPE");
//		List<SystemCommonConfigDomain> inductorTypeList = systemCommonConfigDAO.findByWhere(scc);
		//pt变比
		scc.setType("RATED_PT_CODE");
		List<SystemCommonConfigDomain> ptList = systemCommonConfigService.findByWhere(scc);
		//ct变比
		scc.setType("RATED_CT_CODE");
		List<SystemCommonConfigDomain> ctList = systemCommonConfigService.findByWhere(scc);
		
		//名称
		if(ia.getInductorType()==1) {
			iat.setDeviceName("电流互感器");
			for (SystemCommonConfigDomain ct : ctList) {
				if(ia.getRatedCtCode()==ct.getParamKey()) {
					iat.setSpecs(ct.getParamValue());
				}
			}
		}else if(ia.getInductorType()==2) {
			iat.setDeviceName("电压互感器");
			for (SystemCommonConfigDomain pt : ptList) {
				if(ia.getRatedPtCode()==pt.getParamKey()) {
					iat.setSpecs(pt.getParamValue());
				}
			}
		}else if(ia.getInductorType()==3) {
			iat.setDeviceName("计量箱");
		}else {
			iat.setDeviceName("未知类型");
		}
		
		rmap.put("result",true );
		rmap.put("message","开始生成检测报告" );
		rmap.put("inductorAssetsTestRecordDomain", iat);

		return rmap;

	}
	
	/**
	 * 新增互感器资产检定记录
	 *
	 * @param 
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@ResponseBody
	@PostMapping(params = "method=addInductorAssetsTestRecord")
	public HttpResult addInductorAssetsTestRecord(@RequestBody String body) throws JsonParseException, JsonMappingException, IOException {
		
		JsonNode jsonNode = JSONUtil.readValue(body, JsonNode.class);
		
		InductorAssetsTestRecordDomain iatr = 
				JSONUtil.readValue(jsonNode.get("inductorAssetsTestRecord").toString(), InductorAssetsTestRecordDomain.class);
		
		List<InductorAssetsTestRecordDetailDomain> iatrdList = 
				JSONUtil.readValue(jsonNode.get("inductorAssetsTestRecordDetailList").toString(), new TypeReference<List<InductorAssetsTestRecordDetailDomain>>() {});

		if(iatr==null || iatrdList==null) {
			return new HttpResult(HttpResult.ERROR, "检测记录或检测记录明细为空.");
		}

		return inductorAssetsTestRecordService.addInductorAssetsUseRecord(iatr,iatrdList);

	}


	/**
	 * 查询互感器资产信息领用记录
	 *
	 * @param InductorAssetsTestRecordDomain
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@ResponseBody
	@PostMapping(params = "method=getInductorAssetsUseRecordByKey")
	public Mono<InductorAssetsTestRecordDomain> getInductorAssetsUseRecordByKey(@RequestBody InductorAssetsTestRecordDomain t) {
		// TODO Auto-generated method stub
		InductorAssetsTestRecordDomain rt = inductorAssetsTestRecordService.findByKey(t);
		return Mono.just(rt);
	}

	/**
	 * 查询互感器资产
	 * */
	@ResponseBody
	@PostMapping(params = "method=getInductorAssetsUseRecordByWhere")
	public Mono<JsonGrid> getInductorAssetsUseRecordByWhere(@RequestBody String body) {
		InductorAssetsTestRecordDomain t = GsonUtils.readValue(body, InductorAssetsTestRecordDomain.class);

		List<InductorAssetsTestRecordDomain> list = inductorAssetsTestRecordService.findByWhere(t);

		JsonGrid grid = new JsonGrid(t, list);

		return Mono.just(grid);
	}

	/**
	 * 更新
	 *
	 * @param InductorAssetsTestRecordDomain
	 * @return
	 */
	@ResponseBody
	@PostMapping(params = "method=update")
	 Object update(@RequestBody String body) {

		InductorAssetsTestRecordDomain t = GsonUtils.readValue(body, InductorAssetsTestRecordDomain.class);

		int i = inductorAssetsTestRecordService.update(t);
		if (i > 0)
			return new HttpResult(HttpResult.SUCCESS, "更新成功.");
		else
			return new HttpResult(HttpResult.ERROR, "更新失败.");
	}


}
