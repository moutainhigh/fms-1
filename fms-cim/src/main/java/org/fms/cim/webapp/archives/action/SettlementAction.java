/**
 *    Auth:riozenc
 *    Date:2019年3月14日 上午8:50:19
 *    Title:com.riozenc.cim.webapp.action.MeterAction.java
 **/
package org.fms.cim.webapp.archives.action;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.riozenc.cim.web.config.JsonGrid;
import com.riozenc.cim.webapp.archives.domain.SettlementDomain;
import com.riozenc.cim.webapp.archives.domain.SettlementMeterRelDomain;
import com.riozenc.cim.webapp.archives.service.ISettlementService;
import com.riozenc.titanTool.common.json.utils.GsonUtils;
import com.riozenc.titanTool.common.json.utils.JSONUtil;
import com.riozenc.titanTool.spring.web.http.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@RequestMapping("settlement")
public class SettlementAction {

	@Autowired
	@Qualifier("settlementServiceImpl")
	private ISettlementService settlementService;

	@ResponseBody
	@PostMapping(params = "method=getSettlement")
	public JsonGrid getSettlement(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		SettlementDomain settlementDomain = GsonUtils.readValue(body, SettlementDomain.class);
		return new JsonGrid(settlementDomain,settlementService.findByWhere(settlementDomain));
	}

	@ResponseBody
	@PostMapping(params = "method=getSettlementById")
	public SettlementDomain getSettlementById(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		SettlementDomain settlementDomain = GsonUtils.readValue(body, SettlementDomain.class);
		return settlementService.findByKey(settlementDomain);
	}
	
	@ResponseBody
	@PostMapping(params = "method=getSettlementsByBusinessPlaceCodeAndInvoiceType")
	public List<SettlementDomain> getSettlementsByBusinessPlaceCodeAndInvoiceType(@RequestBody String body) {
		SettlementDomain settlementDomain = GsonUtils.readValue(body, SettlementDomain.class);
		return settlementService.findSettlementByBusinessPlaceCodeAndInvoiceType(settlementDomain);
	}

	@ResponseBody
	@PostMapping(params = "method=getSettlements")
	public List<SettlementDomain> getSettlements(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		SettlementDomain settlementDomain = GsonUtils.readValue(body, SettlementDomain.class);
		return settlementService.findByWhere(settlementDomain);
	}

	@ResponseBody
	@PostMapping(params = "method=querySettlements")
	public List<SettlementDomain> querySettlements(@RequestBody String settlement) {
		Map<String, Object> map = GsonUtils.readValue(settlement, Map.class);
		SettlementDomain settlementDomain = GsonUtils.readValue(GsonUtils.toJson(map.get("settlement")),
				SettlementDomain.class);
		return settlementService.findByWhere(settlementDomain);
	}

	@ResponseBody
	@PostMapping(params = "method=getSettlementByNo")
	public JsonGrid getSettlementByNo(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		SettlementDomain settlementDomain = GsonUtils.readValue(body, SettlementDomain.class);
		return new JsonGrid(settlementDomain,settlementService.findByNo(settlementDomain));
	}
	
	
	@ResponseBody
	@PostMapping(params = "method=getSettlementMeterRel")
	public JsonGrid getSettlementMeterRel(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		SettlementDomain settlementDomain = GsonUtils.readValue(body, SettlementDomain.class);
		return new JsonGrid(settlementDomain,settlementService.getSettlementMeterRel(settlementDomain));
	}

	@ResponseBody
	@PostMapping(params = "method=getNoSettlementMeter")
	public JsonGrid getNoSettlementMeter(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		SettlementMeterRelDomain t = GsonUtils.readValue(body, SettlementMeterRelDomain.class);
		return new JsonGrid(t,settlementService.getNoSettlementMeter(t));
	}

	@ResponseBody
	@PostMapping(params = "method=addSettlement")
	public Mono<HttpResult> insertSettlement(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		SettlementDomain t = GsonUtils.readValue(body, SettlementDomain.class);

		SettlementDomain tt = new SettlementDomain();
		tt.setSettlementNo(t.getSettlementNo());
		//结算户编号查重
		//TODO 结算户号生成
		List<SettlementDomain> list = settlementService.findByNo(tt);
		if(list.size()>0) {
			return Mono.just(new HttpResult<>(HttpResult.ERROR, "新增结算户信息失败,结算户编号重复"));

		}

		t.setStatus((byte) 1);
		t.setCreateDate(new Date());
		
		int count = settlementService.insert(t);
		if(count>0) {
			return Mono.just(new HttpResult<>(HttpResult.SUCCESS, "新增结算户信息成功"));
		}
		return Mono.just(new HttpResult<>(HttpResult.ERROR, "新增结算户信息失败"));

	}

	@ResponseBody
	@PostMapping(params = "method=updateSettlement")
	public Mono<HttpResult> updateSettlement(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		SettlementDomain settlementDomain = GsonUtils.readValue(body, SettlementDomain.class);
		int count = settlementService.update(settlementDomain);
		if(count>0) {
			return Mono.just(new HttpResult<>(HttpResult.SUCCESS, "更新结算户信息成功"));
		}
		return Mono.just(new HttpResult<>(HttpResult.ERROR, "更新结算户信息失败"));
	}

	@ResponseBody
	@PostMapping(params = "method=addSettlementMeterRelBySettlement")
	public Mono<HttpResult> addSettlementMeterRelBySettlement(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {

		JsonNode jsonNode = JSONUtil.readValue(body, JsonNode.class);

		List<SettlementMeterRelDomain> list =
				JSONUtil.readValue(jsonNode.get("rel").toString(), new TypeReference<List<SettlementMeterRelDomain>>() {});

		for(SettlementMeterRelDomain t : list) {
			t.setCreateDate(new Date());
			t.setStatus((byte)1);
		}
		int count = settlementService.addSettlementMeterRelBySettlement(list);
		if(count>0) {
			return Mono.just(new HttpResult<>(HttpResult.SUCCESS, "添加关系成功"));
		}
		return Mono.just(new HttpResult<>(HttpResult.ERROR, "添加关系失败"));
	}

	@ResponseBody
	@PostMapping(params = "method=deleteSettlementMeterRelBySettlement")
	public Mono<HttpResult> deleteSettlementMeterRelBySettlement(@RequestBody List<SettlementMeterRelDomain> list)
			throws JsonParseException, JsonMappingException, IOException {
//		JsonNode jsonNode = JSONUtil.readValue(body, JsonNode.class);
//
//		List<SettlementMeterRelDomain> list =
//				JSONUtil.readValue(jsonNode.get("rel").toString(), new TypeReference<List<SettlementMeterRelDomain>>() {});
		for(SettlementMeterRelDomain t : list) {
			if (t.getId()==null) {
				return Mono.just(new HttpResult<>(HttpResult.ERROR, "删除关系失败,关系ID为空"));
			}
		}
		int count = settlementService.deleteSettlementMeterRelBySettlement(list);
		if(count>0) {
			return Mono.just(new HttpResult<>(HttpResult.SUCCESS, "删除关系成功"));
		}
		return Mono.just(new HttpResult<>(HttpResult.ERROR, "删除关系失败"));
	}

	@ResponseBody
	@PostMapping(params = "method=updateDeductionOrder")
	public Mono<HttpResult> updateDeductionOrder(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		SettlementDomain settlementDomain = GsonUtils.readValue(body, SettlementDomain.class);
		int count = settlementService.update(settlementDomain);
		if(count>0) {
			return Mono.just(new HttpResult<>(HttpResult.SUCCESS, "更新结算户信息成功"));
		}
		return Mono.just(new HttpResult<>(HttpResult.ERROR, "更新结算户信息失败"));
	}
	
	@ResponseBody
	@PostMapping(params = "method=getSettlementByIds")
	public Mono<HttpResult> getSettlementByIds(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		
		List<Long> settlementIdList = JSONUtil.readValue(body, new TypeReference<List<Long>>() {});

		List<SettlementDomain> settlementList = settlementService.findSettlementByIds(settlementIdList);
	
		return Mono.just(new HttpResult<>(HttpResult.SUCCESS, "获取结算户信息成功",settlementList));
	
	}

	@ResponseBody
	@PostMapping(params = "method=getSettlementbyMeterIds")
	public HttpResult getSettlementbyMeterIds(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		
//		JsonNode jsonNode = JSONUtil.readValue(body, JsonNode.class);
		
		List<Long> settlementIdList = JSONUtil.readValue(body, new TypeReference<List<Long>>() {});

		List<SettlementDomain> settlementList = settlementService.getSettlementbyMeterIds(settlementIdList);
	
		return new HttpResult<>(HttpResult.SUCCESS, "获取结算户信息成功",settlementList);
	
	}

	// 获取短信用户和电话号码
	@ResponseBody
	@PostMapping(params = "method=findSettlementsByMeterIds")
	public List<SettlementDomain> findSettlementsByMeterIds(@RequestBody String settlementDomain) {
		Map map = GsonUtils.readValue(settlementDomain, Map.class);
		SettlementDomain settlement = GsonUtils.readValue(GsonUtils.toJson(map.get("settlement")), SettlementDomain.class);
		return settlementService
				.getSettlementbyMeterIds(settlement.getMeterIds());
	}
	
	@ResponseBody
	@PostMapping(params = "method=querySettlementsByIds")
	public List<SettlementDomain> querySettlementsByIds(@RequestBody String body) {
		final char startBodyStrMap = '{';
		final char endBodyStrMap = '}';
		final char startBodyStrList = '[';
		final char endBodyStrList = ']';
		if (body.indexOf(startBodyStrMap) != -1 || body.lastIndexOf(endBodyStrMap) != -1) {
			if (body.indexOf(startBodyStrList) == -1 || body.lastIndexOf(endBodyStrList) == -1) {
				return new ArrayList<>(0);
			}
			String substring = body.substring(body.indexOf(startBodyStrList),
					body.lastIndexOf(endBodyStrList)+1);
			List<Long> settlementIds =
					GsonUtils.readValueToList(substring, Long.class);
			return settlementService.findSettlementByIds(settlementIds);
		} else {
			List<Long> settlementIds =
					GsonUtils.readValueToList(body, Long.class);
			return settlementService.findSettlementByIds(settlementIds);
		}

	}



	//根据结算户ids 查结算户信息
	@ResponseBody
	@PostMapping(params = "method=findBySettlementNos")
	public List<SettlementDomain> findBySettlementNos(@RequestBody String body){
		SettlementDomain settlementDomain = GsonUtils.readValue(body, SettlementDomain.class);
		return settlementService.findBySettlementNos(settlementDomain);
	}

	//根据计量点id查计量点与结算户关系
	@ResponseBody
	@PostMapping(params = "method=getSettlementMeterRelByMeterIds")
	public List<SettlementMeterRelDomain> getSettlementMeterRelByMeterIds(@RequestBody String body){
		List<Long> meterIds =
				GsonUtils.readValueToList(body,Long.class);
		return settlementService.getSettlementMeterRelByMeterIds(meterIds);
	}

	@ResponseBody
	@PostMapping(params = "method=findSettlementIdByWhere")
	public List<Long> findSettlementIdByWhere(@RequestBody String body) {
		SettlementDomain settlementDomain = GsonUtils.readValue(body, SettlementDomain.class);
		return settlementService.findSettlementIdByWhere(settlementDomain);
	}

    @ResponseBody
    @PostMapping(params = "method=findClearSettlementByWhere")
    public JsonGrid findClearSettlementByWhere(@RequestBody String body)
            throws JsonParseException, JsonMappingException, IOException {
        SettlementDomain settlementDomain = GsonUtils.readValue(body, SettlementDomain.class);
        return new JsonGrid(settlementDomain,settlementService.findClearSettlementByWhere(settlementDomain));
    }
}
