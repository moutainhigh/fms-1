/**
 *    Auth:riozenc
 *    Date:2019年3月14日 上午8:50:19
 *    Title:com.riozenc.cim.webapp.action.MeterAction.java
 **/
package org.fms.cim.webapp.archives.action;

import java.io.IOException;
import java.util.List;

import org.fms.cim.web.config.JsonGrid;
import org.fms.cim.webapp.archives.domain.SfPowerBankDomain;
import org.fms.cim.webapp.archives.service.ISfPowerBankService;
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
@RequestMapping("sfPowerBank")
public class SfPowerBankAction {

	@Autowired
	@Qualifier("sfPowerBankServiceImpl")
	private ISfPowerBankService sfPowerBankService;

	@ResponseBody
	@PostMapping(params = "method=getSfPowerBank")
	public JsonGrid getSfPowerBank(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		SfPowerBankDomain t = GsonUtils.readValue(body, SfPowerBankDomain.class);
		return new JsonGrid(t,sfPowerBankService.findByWhere(t));
	}
	
	@ResponseBody
	@PostMapping(params = "method=getSfPowerBankList")
	public List<SfPowerBankDomain> getSfPowerBankList(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		SfPowerBankDomain t = GsonUtils.readValue(body, SfPowerBankDomain.class);
		return sfPowerBankService.findByWhere(t);
	}
	
	@ResponseBody
	@PostMapping(params = "method=getSfPowerBankByKey")
	public SfPowerBankDomain getSfPowerBankByKey(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		SfPowerBankDomain t = GsonUtils.readValue(body, SfPowerBankDomain.class);
		return sfPowerBankService.findByKey(t);
	}


	@ResponseBody
	@PostMapping(params = "method=addSfPowerBank")
	public Mono<HttpResult> addSfPowerBank(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		SfPowerBankDomain t = GsonUtils.readValue(body, SfPowerBankDomain.class);
	
		int count = sfPowerBankService.insert(t);
		if(count>0) {
			return Mono.just(new HttpResult(HttpResult.SUCCESS, "新增供电局开户银行信息成功"));
		}
		return Mono.just(new HttpResult(HttpResult.ERROR, "新增供电局开户银行信息失败"));

	}

	@ResponseBody
	@PostMapping(params = "method=updateSfPowerBank")
	public Mono<HttpResult> updateSfPowerBank(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		SfPowerBankDomain t = GsonUtils.readValue(body, SfPowerBankDomain.class);
		int count = sfPowerBankService.update(t);
		if(count>0) {
			return Mono.just(new HttpResult(HttpResult.SUCCESS, "更新供电局开户银行信息成功"));
		}
		return Mono.just(new HttpResult(HttpResult.ERROR, "更新供电局开户银行信息失败"));
	}

}
