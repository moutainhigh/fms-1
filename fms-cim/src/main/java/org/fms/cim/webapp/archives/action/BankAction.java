/**
 *    Auth:riozenc
 *    Date:2019年3月14日 上午8:50:19
 *    Title:com.riozenc.cim.webapp.action.MeterAction.java
 **/
package org.fms.cim.webapp.archives.action;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.riozenc.cim.web.config.JsonGrid;
import com.riozenc.cim.webapp.archives.domain.BankDomain;
import com.riozenc.cim.webapp.archives.service.IBankService;
import com.riozenc.titanTool.common.json.utils.GsonUtils;
import com.riozenc.titanTool.spring.web.http.HttpResult;

import reactor.core.publisher.Mono;

@ControllerAdvice
@RequestMapping("bank")
public class BankAction {

	@Autowired
	@Qualifier("bankServiceImpl")
	private IBankService bankService;

	@ResponseBody
	@PostMapping(params = "method=getBank")
	public JsonGrid getBank(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		BankDomain t = GsonUtils.readValue(body, BankDomain.class);
		return new JsonGrid(t,bankService.findByWhere(t));
	}
	
	@ResponseBody
	@PostMapping(params = "method=getBankList")
	public List<BankDomain> getBankList(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		BankDomain t = GsonUtils.readValue(body, BankDomain.class);
		return bankService.findByWhere(t);
	}
	
	@ResponseBody
	@PostMapping(params = "method=getBankByKey")
	public BankDomain getBankByKey(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		BankDomain t = GsonUtils.readValue(body, BankDomain.class);
		return bankService.findByKey(t);
	}


	@ResponseBody
	@PostMapping(params = "method=addBank")
	public Mono<HttpResult> addBank(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		BankDomain t = GsonUtils.readValue(body, BankDomain.class);
	
		int count = bankService.insert(t);
		if(count>0) {
			return Mono.just(new HttpResult(HttpResult.SUCCESS, "新增银行信息成功"));
		}
		return Mono.just(new HttpResult(HttpResult.ERROR, "新增银行信息失败"));

	}

	@ResponseBody
	@PostMapping(params = "method=updateBank")
	public Mono<HttpResult> updateBank(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		BankDomain t = GsonUtils.readValue(body, BankDomain.class);
		int count = bankService.update(t);
		if(count>0) {
			return Mono.just(new HttpResult(HttpResult.SUCCESS, "更新银行信息成功"));
		}
		return Mono.just(new HttpResult(HttpResult.ERROR, "更新银行信息失败"));
	}

}
