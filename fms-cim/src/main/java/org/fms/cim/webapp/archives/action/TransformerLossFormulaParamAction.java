package org.fms.cim.webapp.archives.action;

import java.io.IOException;
import java.util.List;

import org.fms.cim.web.config.JsonGrid;
import org.fms.cim.webapp.archives.domain.TransformerLossFormulaParamDomain;
import org.fms.cim.webapp.archives.service.ITransformerLossFormulaParamService;
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
@RequestMapping("transformerLossFormulaParam")
public class TransformerLossFormulaParamAction {
	@Autowired
	@Qualifier("transformerLossFormulaParamServiceImpl")
	private ITransformerLossFormulaParamService transformerLossFormulaParamService;

	@ResponseBody
	@PostMapping(params = "method=getTransformerLossParamInfo")
	public Mono<JsonGrid> getTransformerLossParamInfo(
			@RequestBody TransformerLossFormulaParamDomain transformerLossFormulaParamDomain)
			throws JsonParseException, JsonMappingException, IOException {
		List<TransformerLossFormulaParamDomain> l = transformerLossFormulaParamService.findByWhere(transformerLossFormulaParamDomain);
		return Mono.just(new JsonGrid(transformerLossFormulaParamDomain,
				transformerLossFormulaParamService.findByWhere(transformerLossFormulaParamDomain)));
	}

	@ResponseBody
	@PostMapping(params = "method=getAllTransformerLossParamInfo")
	public Mono<?> getAllTransformerLossParamInfo(@RequestBody TransformerLossFormulaParamDomain transformerLossFormulaParamDomain)
			throws JsonParseException, JsonMappingException, IOException {

		return Mono.just(transformerLossFormulaParamService.findByWhere(transformerLossFormulaParamDomain));
	}

	@ResponseBody
	@PostMapping(params = "method=updateTransformerLossParamInfo")
	public Mono<HttpResult> updateTransformerLossParamInfo(@RequestBody TransformerLossFormulaParamDomain transformerLossFormulaParamDomain)
			throws JsonParseException, JsonMappingException, IOException {
		
		int count = transformerLossFormulaParamService.update(transformerLossFormulaParamDomain);
		if (count > 0) {
			return Mono.just(new HttpResult(HttpResult.SUCCESS, "更新信息成功"));
		} else {
			return Mono.just(new HttpResult(HttpResult.ERROR, "更新信息失败"));
		}
	}

	@ResponseBody
	@PostMapping(params = "method=addTransformerLossParamInfo")
	public Mono<HttpResult> addTransformerLossParamInfo(@RequestBody TransformerLossFormulaParamDomain transformerLossFormulaParamDomain)
			throws JsonParseException, JsonMappingException, IOException {

		int count = transformerLossFormulaParamService.insert(transformerLossFormulaParamDomain);
		if (count > 0) {
			return Mono.just(new HttpResult(HttpResult.SUCCESS, "新增信息成功"));
		} else {
			return Mono.just(new HttpResult(HttpResult.ERROR, "新增信息失败"));
		}
	}
}
