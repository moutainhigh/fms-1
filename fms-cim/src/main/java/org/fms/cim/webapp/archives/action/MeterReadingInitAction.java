/**
 *    Auth:riozenc
 *    Date:2019年3月8日 下午3:37:16
 *    Title:com.riozenc.cim.webapp.action.MeterReadingInitAction.java
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
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.riozenc.cim.web.config.JsonGrid;
import com.riozenc.cim.webapp.archives.domain.WriteSectDomain;
import com.riozenc.cim.webapp.archives.service.IMeterReadingInitService;
import com.riozenc.cim.webapp.archives.service.IWriteSectService;
import com.riozenc.titanTool.common.json.utils.JSONUtil;
import com.riozenc.titanTool.spring.web.http.HttpResult;

import reactor.core.publisher.Mono;

@ControllerAdvice
@RequestMapping("meterReadingInit")
public class MeterReadingInitAction {

	@Autowired
	@Qualifier("meterReadingInitServiceImpl2")
	private IMeterReadingInitService meterReadingInitService;
	@Autowired
	@Qualifier("writeSectServiceImpl")
	private IWriteSectService writeSectService;
	
	
	// 根据客户抄表初始化
	@ResponseBody
	@PostMapping(params = "method=meterReadingInitByCustomer")
	public HttpResult meterReadingInitByCustomer(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		JsonNode jsonNode = JSONUtil.readValue(body, JsonNode.class);

		if (jsonNode.get("ids") != null && jsonNode.get("ids").isArray()) {
			List<Long> ids = JSONUtil.readValue(jsonNode.get("ids").toString(), new TypeReference<List<Long>>() {
			});

			String sn = jsonNode.get("sn").toString();

			return meterReadingInitService.meterReadingInitByCustomer(ids, sn);
		} else {
			return new HttpResult<>(HttpResult.ERROR, "未选择客户");
		}

	}

	// 根据抄表段抄表初始化
	@ResponseBody
	@PostMapping(params = "method=meterReadingInitByWriteSec")
	public HttpResult meterReadingInitByWriteSec(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
//		Long btime = System.currentTimeMillis();
//		Long etime = System.currentTimeMillis();
		JsonNode jsonNode = JSONUtil.readValue(body, JsonNode.class);

		if (jsonNode.get("ids") != null && jsonNode.get("ids").isArray()) {
			List<Long> ids = JSONUtil.readValue(jsonNode.get("ids").toString(), new TypeReference<List<Long>>() {
			});
			String sn = jsonNode.get("sn").toString();

			return meterReadingInitService.meterReadingInitByWriteSec(ids, sn);

		} else {
			return new HttpResult<>(HttpResult.ERROR, "未选择抄表段");
		}

	}
	
	
	
	//获取某一抄表段中的已抄计量点和未抄计量点
	@RequestMapping(params = "method=getInitMetersByWriteSect")
	@ResponseBody
	public HttpResult getInitMetersByWriteSect(@RequestBody WriteSectDomain e) {

		return meterReadingInitService.getInitMetersByWriteSect(e);
		
	}

}
