/**
 *    Auth:riozenc
 *    Date:2019年3月14日 上午8:50:19
 *    Title:com.riozenc.cim.webapp.action.MeterAction.java
 **/
package org.fms.cim.webapp.archives.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.fms.cim.web.config.JsonGrid;
import org.fms.cim.webapp.archives.domain.LineDomain;
import org.fms.cim.webapp.archives.domain.SubsLineRelaDomain;
import org.fms.cim.webapp.archives.service.ILineService;
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
@RequestMapping("line")
public class LineAction {

	@Autowired
	@Qualifier("lineServiceImpl")
	private ILineService lineService;

	//通过findByWhere获取全部线路信息（仅通过线路档案）---------------------可能业扩要用
	@ResponseBody
	@PostMapping(params = "method=getLine")
	public JsonGrid getAllLineInfo(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		LineDomain t = GsonUtils.readValue(body, LineDomain.class);

		return new JsonGrid(t,lineService.findByWhere(t));
	}
	
	@ResponseBody
	@PostMapping(params = "method=getLineByKey")
	public LineDomain getLineByKey(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		LineDomain t = GsonUtils.readValue(body, LineDomain.class);

		return lineService.findByKey(t);
	}

	//添加线路和线路与厂站关系档案
	@ResponseBody
	@PostMapping(params = "method=addLine")
	public Mono<HttpResult> addLine(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		SubsLineRelaDomain subsLineRelaDomain = GsonUtils.readValue(body, SubsLineRelaDomain.class);
		LineDomain line = GsonUtils.readValue(body, LineDomain.class);

		//查重
		LineDomain e = new LineDomain();
		e.setLineCode(line.getLineCode());
		List<LineDomain> list = lineService.findByLineCode(e);
		if(list.size()>0) {
			return Mono.just(new HttpResult(HttpResult.ERROR, "新增线路信息失败，线路编号重复"));
		}
		if(subsLineRelaDomain.getBeginSubsId()==null) {
			return Mono.just(new HttpResult(HttpResult.ERROR, "新增线路信息失败，所属厂站不能为空"));

		}

		subsLineRelaDomain.setCreateDate(new Date());
		boolean result = lineService.addLineAndSubsLineRela(line,subsLineRelaDomain);

		if(result) {
			return Mono.just(new HttpResult(HttpResult.SUCCESS, "新增线路信息成功"));
		}
		return Mono.just(new HttpResult(HttpResult.ERROR, "新增线路信息失败"));

	}

	@ResponseBody
	@PostMapping(params = "method=updateLine")
	public Mono<HttpResult> updateLine(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		SubsLineRelaDomain subsLineRelaDomain = GsonUtils.readValue(body, SubsLineRelaDomain.class);
		LineDomain line = GsonUtils.readValue(body, LineDomain.class);

		subsLineRelaDomain.setLineId(line.getId());
		boolean result = lineService.updateLineAndSubsLineRela(line,subsLineRelaDomain);
		if(result) {
			return Mono.just(new HttpResult(HttpResult.SUCCESS, "更新线路信息成功"));
		}
		return Mono.just(new HttpResult(HttpResult.ERROR, "更新线路信息失败"));
	}

	@ResponseBody
	@PostMapping(params = "method=findByLineIds")
	public List<LineDomain> findByLineIds(@RequestBody String body) {
		LineDomain lineDomain= GsonUtils.readValue(body, LineDomain.class);
		return lineService.findByLineIds(lineDomain);
	}


}
