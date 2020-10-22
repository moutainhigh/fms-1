package org.fms.cim.webapp.archives.action;

import java.io.IOException;

import org.fms.cim.web.config.JsonGrid;
import org.fms.cim.webapp.archives.domain.TransformerLossTableParamDomain;
import org.fms.cim.webapp.archives.service.ITransformerLossTableParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.riozenc.titanTool.spring.web.http.HttpResult;

import reactor.core.publisher.Mono;

@ControllerAdvice
@RequestMapping("transformerLossTableParam")
public class TransformerLossTableParamAction {
	@Autowired
	@Qualifier("transformerLossTableParamServiceImpl")
	private ITransformerLossTableParamService transformerLossTableParamService;

	@ResponseBody
	@PostMapping(params = "method=getTransformerLossParamInfo")
	public Mono<JsonGrid> getTransformerLossParamInfo(
			@RequestBody TransformerLossTableParamDomain transformerLossTableParamDomain)
			throws JsonParseException, JsonMappingException, IOException {

		return Mono.just(new JsonGrid(transformerLossTableParamDomain,
				transformerLossTableParamService.findByWhere(transformerLossTableParamDomain)));
	}

	//?????????????????????????????????????/
	@ResponseBody
	@PostMapping(params = "method=getAllTransformerLossParamInfo")
	public Mono<?> getAllTransformerLossParamInfo(
			@RequestBody TransformerLossTableParamDomain transformerLossTableParamDomain)
			throws JsonParseException, JsonMappingException, IOException {

		return Mono.just(transformerLossTableParamService.findByWhere(transformerLossTableParamDomain));
	}

	@ResponseBody
	@PostMapping(params = "method=updateTransformerLossParamInfo")
	public Mono<HttpResult> updateTransformerLossParamInfo(
			@RequestBody TransformerLossTableParamDomain transformerLossTableParamDomain)
			throws JsonParseException, JsonMappingException, IOException {

		int count = transformerLossTableParamService.update(transformerLossTableParamDomain);
		if (count > 0) {
			return Mono.just(new HttpResult(HttpResult.SUCCESS, "更新信息成功"));
		} else {
			return Mono.just(new HttpResult(HttpResult.ERROR, "更新信息失败"));
		}
	}

	@ResponseBody
	@PostMapping(params = "method=addTransformerLossParamInfo")
	public Mono<HttpResult> addTransformerLossParamInfo(
			@RequestBody TransformerLossTableParamDomain transformerLossTableParamDomain)
			throws JsonParseException, JsonMappingException, IOException {

		int count = transformerLossTableParamService.insert(transformerLossTableParamDomain);
		if (count > 0) {
			return Mono.just(new HttpResult(HttpResult.SUCCESS, "新增信息成功"));
		} else {
			return Mono.just(new HttpResult(HttpResult.ERROR, "新增信息失败"));
		}
	}
}
