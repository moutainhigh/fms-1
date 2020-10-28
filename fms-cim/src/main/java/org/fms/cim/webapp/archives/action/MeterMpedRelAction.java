/**
 *   qt
 *   计费点与计量点关系
 **/
package org.fms.cim.webapp.archives.action;

import java.io.IOException;
import java.util.List;

import org.fms.cim.web.config.JsonGrid;
import org.fms.cim.webapp.archives.domain.BankDomain;
import org.fms.cim.webapp.archives.domain.MeterMpedRelDomain;
import org.fms.cim.webapp.archives.service.IBankService;
import org.fms.cim.webapp.archives.service.IMeterMpedRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.riozenc.titanTool.common.json.utils.GsonUtils;
import com.riozenc.titanTool.spring.web.http.HttpResult;

import reactor.core.publisher.Mono;

@ControllerAdvice
@RequestMapping("meterMpedRel")
public class MeterMpedRelAction {

	@Autowired
	@Qualifier("meterMpedRelServiceImpl")
	private IMeterMpedRelService meterMpedRelService;

	@ResponseBody
	@PostMapping(params = "method=getMeterMpedRel")
	public JsonGrid getBank(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		MeterMpedRelDomain t = GsonUtils.readValue(body, MeterMpedRelDomain.class);
		return new JsonGrid(t,meterMpedRelService.findByWhere(t));
	}
	
	@ResponseBody
	@PostMapping(params = "method=getMeterMpedRelList")
	public List<MeterMpedRelDomain> getBankList(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		MeterMpedRelDomain t = GsonUtils.readValue(body, MeterMpedRelDomain.class);
		return meterMpedRelService.findByWhere(t);
	}
	
	@ResponseBody
	@PostMapping(params = "method=getMeterMpedRelByKey")
	public MeterMpedRelDomain getBankByKey(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		MeterMpedRelDomain t = GsonUtils.readValue(body, MeterMpedRelDomain.class);
		return meterMpedRelService.findByKey(t);
	}


	@ResponseBody
	@PostMapping(params = "method=addMeterMpedRel")
	public Mono<HttpResult> addBank(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		MeterMpedRelDomain t = GsonUtils.readValue(body, MeterMpedRelDomain.class);
	
		int count = meterMpedRelService.insert(t);
		if(count>0) {
			return Mono.just(new HttpResult(HttpResult.SUCCESS, "新增计费点与计量点关系成功"));
		}
		return Mono.just(new HttpResult(HttpResult.ERROR, "新增计费点与计量点关系失败"));

	}

	@ResponseBody
	@PostMapping(params = "method=updateMeterMpedRel")
	public Mono<HttpResult> updateBank(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		MeterMpedRelDomain t = GsonUtils.readValue(body, MeterMpedRelDomain.class);
		int count = meterMpedRelService.update(t);
		if(count>0) {
			return Mono.just(new HttpResult(HttpResult.SUCCESS, "更新计费点与计量点关系成功"));
		}
		return Mono.just(new HttpResult(HttpResult.ERROR, "更新计费点与计量点关系失败"));
	}

}
