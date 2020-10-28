package org.fms.cim.webapp.archives.action;

import java.io.IOException;

import org.fms.cim.web.config.JsonGrid;
import org.fms.cim.webapp.archives.domain.ABusDomain;
import org.fms.cim.webapp.archives.domain.BankDomain;
import org.fms.cim.webapp.archives.domain.LineDomain;
import org.fms.cim.webapp.archives.domain.SDevIrDomain;
import org.fms.cim.webapp.archives.service.IAbusService;
import org.fms.cim.webapp.archives.service.ISdevIrService;
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
@RequestMapping("sdevir")
public class SdevIrAction {
	
	@Autowired
	@Qualifier("sdevIrServiceImpl")
	private ISdevIrService sdevIrService;
	
	@ResponseBody
	@PostMapping(params = "method=getSDevIr")
	public JsonGrid getAllLineSDevIr(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		SDevIrDomain t = GsonUtils.readValue(body, SDevIrDomain.class);

		return new JsonGrid(t,sdevIrService.findByWhere(t));
	}
	
	@ResponseBody
	@PostMapping(params = "method=getSDevIrByKey")
	public SDevIrDomain getSDevIrByKey(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		SDevIrDomain t = GsonUtils.readValue(body, SDevIrDomain.class);

		return sdevIrService.findByKey(t);
	}
	
	@ResponseBody
	@PostMapping(params = "method=addSDevIr")
	public Mono<HttpResult> addSDevIr(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		SDevIrDomain t = GsonUtils.readValue(body, SDevIrDomain.class);
	
		int count = sdevIrService.insert(t);
		if(count>0) {
			return Mono.just(new HttpResult(HttpResult.SUCCESS, "新增母线信息成功"));
		}
		return Mono.just(new HttpResult(HttpResult.ERROR, "新增母线信息失败"));

	}
	
	@ResponseBody
	@PostMapping(params = "method=updateSDevIr")
	public Mono<HttpResult> updateSDevIr(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		SDevIrDomain t = GsonUtils.readValue(body, SDevIrDomain.class);
		int count = sdevIrService.update(t);
		if(count>0) {
			return Mono.just(new HttpResult(HttpResult.SUCCESS, "更新母线信息成功"));
		}
		return Mono.just(new HttpResult(HttpResult.ERROR, "更新母线信息失败"));
	}
}
