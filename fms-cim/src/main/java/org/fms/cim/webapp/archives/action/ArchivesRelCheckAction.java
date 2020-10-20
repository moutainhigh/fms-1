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
import com.riozenc.cim.webapp.archives.domain.MeterDomain;
import com.riozenc.cim.webapp.archives.service.IArchivesRelCheckService;
import com.riozenc.titanTool.common.json.utils.JSONUtil;
import com.riozenc.titanTool.spring.web.http.HttpResult;

@ControllerAdvice
@RequestMapping("archivesRelCheck")
public class ArchivesRelCheckAction {
	
	@Autowired
	@Qualifier("archivesRelCheckServiceImpl")
	private IArchivesRelCheckService archivesRelCheckService;
	

	@ResponseBody
	@PostMapping(params = "method=meterCheck")
	public HttpResult meterCheck(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		JsonNode jsonNode = JSONUtil.readValue(body, JsonNode.class);

		List<MeterDomain> meterList =
				JSONUtil.readValue(jsonNode.get("meterList").toString(), new TypeReference<List<MeterDomain>>() {});
		if(meterList.size()>0) {
			return archivesRelCheckService.meterCheck(meterList);
		}
		
		return new HttpResult(HttpResult.ERROR,"执行异常，请规范操作");
	}
	
	
	
	
}
