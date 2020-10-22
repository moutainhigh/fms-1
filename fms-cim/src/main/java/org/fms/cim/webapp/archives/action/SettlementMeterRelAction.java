/**
 *    Auth:riozenc
 *    Date:2019年3月14日 上午8:50:19
 *    Title:com.riozenc.cim.webapp.action.MeterAction.java
 **/
package org.fms.cim.webapp.archives.action;

import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.fms.cim.webapp.archives.domain.SettlementDomain;
import org.fms.cim.webapp.archives.domain.SettlementMeterRelDomain;
import org.fms.cim.webapp.archives.service.ISettlementMeterRelService;
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
import com.riozenc.titanTool.common.json.utils.GsonUtils;
import com.riozenc.titanTool.common.json.utils.JSONUtil;

@ControllerAdvice
@RequestMapping("settlementMeterRel")
public class SettlementMeterRelAction {

	@Autowired
	@Qualifier("settlementMeterRelServiceImpl")
	private ISettlementMeterRelService settlementMeterRelService;

	@ResponseBody
	@PostMapping(params = "method=getSettlementByMeterIds")
	public List<Long> getSettlementByMeterIds(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
	
		List<String> meterIds = 
				JSONUtil.readValue(body, new TypeReference<List<String>>() {});
		return settlementMeterRelService.getSettlementByMeterIds(meterIds);
		
//		JsonNode jsonNode = JSONUtil.readValue(body, JsonNode.class);
//
//		if(jsonNode.get("meterIds")!=null&&jsonNode.get("meterIds").isArray()) {
//			List<String> meterIds = 
//					JSONUtil.readValue(jsonNode.get("meterIds").toString(), new TypeReference<List<String>>() {});
//			return settlementMeterRelService.getSettlementByMeterIds(meterIds);
//
//		}else {
//			return null;
//		}

	}

	@ResponseBody
	@PostMapping(params = "method=querySettlementIdsByMeterIds")
	public List<Long> querySettlementIdsByMeterIds(@RequestBody String meterIdsStr)
			throws IOException {
		final char startMeterIdsStrMap = '{';
		final char endMeterIdsStrMap = '}';
		final char startMeterIdsStrList = '[';
		final char endMeterIdsStrList = ']';
		if (meterIdsStr.indexOf(startMeterIdsStrMap) != -1 || meterIdsStr.lastIndexOf(endMeterIdsStrMap) != -1) {
			if (meterIdsStr.indexOf(startMeterIdsStrList) == -1 || meterIdsStr.lastIndexOf(endMeterIdsStrList) == -1) {
				return new ArrayList<>(0);
			}
			String substring = meterIdsStr.substring(meterIdsStr.indexOf(startMeterIdsStrList),
					meterIdsStr.lastIndexOf(endMeterIdsStrList)+1);
			List<String> meterIds =
					GsonUtils.readValueToList(substring, String.class);
			return settlementMeterRelService.getSettlementByMeterIds(meterIds);
		} else {
			List<String> meterIds =
					JSONUtil.readValue(meterIdsStr, new TypeReference<List<String>>() {});
			return settlementMeterRelService.getSettlementByMeterIds(meterIds);
		}


	}

	@ResponseBody
	@PostMapping(params = "method=getMeterIdsBySettlements")
	public List<SettlementMeterRelDomain> getMeterIdsBySettlements(@RequestBody String settlementDomains) {
		HashMap hashMap = GsonUtils.readValue(settlementDomains, HashMap.class);
		List<SettlementDomain> settlements = GsonUtils.readValueToList(GsonUtils.toJson(hashMap.get("settlement")),
				SettlementDomain.class);
		List<Long> settlementIds = settlements.stream().filter(s -> s.getId() != null)
				.map(SettlementDomain::getId).distinct().collect(toList());
		return settlementMeterRelService.getMeterIdsBySettlementIds(settlementIds);
	}

	@ResponseBody
	@PostMapping(params = "method=getMeterIdsBySettlementInfo")
	public List<Long> getMeterIdsBySettlementInfo(@RequestBody String settlementDomains) {
		SettlementDomain settlements = GsonUtils.readValue(settlementDomains,
				SettlementDomain.class);
		return settlementMeterRelService.getMeterIdsBySettlementInfo(settlements);
	}

}
