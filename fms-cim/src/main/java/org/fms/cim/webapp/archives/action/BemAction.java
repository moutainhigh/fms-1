package org.fms.cim.webapp.archives.action;

import java.io.IOException;
import java.util.HashMap;

import org.fms.cim.webapp.archives.service.IBemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.riozenc.titanTool.common.json.utils.JSONUtil;
import com.riozenc.titanTool.spring.web.http.HttpResult;

import reactor.core.publisher.Mono;

@ControllerAdvice
@RequestMapping("bem")
public class BemAction {

	@Autowired
	@Qualifier("bemServiceImpl")
	private IBemService bemService;

	/**
	 * 保存由业扩传过来的客户、用电户、计量点、电表资产、互感器资产信息
	 */
	@ResponseBody
	@RequestMapping(params = "method=bemAddReceive")
	public Mono<HttpResult> bemAddReceive(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		JsonNode jsonNode = JSONUtil.readValue(body, JsonNode.class);
		HashMap<String, Object> rmap = bemService.addBemInfo(jsonNode);
		boolean result = (boolean) rmap.get("result");
		String message = rmap.get("message").toString();
		if (result) {
			return Mono.just(new HttpResult<>(HttpResult.SUCCESS, message));
		} else {
			return Mono.just(new HttpResult<>(HttpResult.ERROR, message));
		}
	}

}
