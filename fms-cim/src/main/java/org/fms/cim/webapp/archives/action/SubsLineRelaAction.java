/**
 *    Auth:riozenc
 *    Date:2019年3月14日 上午8:50:19
 *    Title:com.riozenc.cim.webapp.action.MeterAction.java
 **/
package org.fms.cim.webapp.archives.action;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.riozenc.cim.web.config.JsonGrid;
import com.riozenc.cim.webapp.archives.domain.SubsDomain;
import com.riozenc.cim.webapp.archives.domain.SubsLineRelaDomain;
import com.riozenc.cim.webapp.archives.service.ISubsLineRelaService;
import com.riozenc.cim.webapp.archives.service.ISubsService;
import com.riozenc.titanTool.common.json.utils.GsonUtils;
import com.riozenc.titanTool.spring.web.http.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@ControllerAdvice
@RequestMapping("subsLineRela")
public class SubsLineRelaAction {

	@Autowired
	@Qualifier("subsLineRelaServiceImpl")
	private ISubsLineRelaService subsLineRelaService;

	@ResponseBody
	@PostMapping(params = "method=getsubsLineRela")
	public JsonGrid getsubsLineRela(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		SubsLineRelaDomain subsLineRelaDomain = GsonUtils.readValue(body,
				SubsLineRelaDomain.class);
		return new JsonGrid(subsLineRelaDomain,subsLineRelaService.findByWhere(subsLineRelaDomain));
	}
	


}
