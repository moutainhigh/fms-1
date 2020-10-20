/**
 *    Auth:riozenc
 *    Date:2019年3月14日 上午8:50:19
 *    Title:com.riozenc.cim.webapp.action.MeterAction.java
 **/
package org.fms.cim.webapp.archives.action;

import java.io.IOException;
import java.util.Date;
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
import com.riozenc.cim.webapp.archives.domain.SubsDomain;
import com.riozenc.cim.webapp.archives.service.ISubsService;
import com.riozenc.titanTool.common.json.utils.GsonUtils;
import com.riozenc.titanTool.spring.web.http.HttpResult;

import reactor.core.publisher.Mono;

@ControllerAdvice
@RequestMapping("subs")
public class SubsAction {

	@Autowired
	@Qualifier("subsServiceImpl")
	private ISubsService subsService;

	@ResponseBody
	@PostMapping(params = "method=getSubs")
	public JsonGrid getSubs(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		SubsDomain subsDomain = GsonUtils.readValue(body, SubsDomain.class);
		return new JsonGrid(subsDomain,subsService.findByWhere(subsDomain));
	}
	
	@ResponseBody
	@PostMapping(params = "method=getSubsByKey")
	public SubsDomain getSubsByKey(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		SubsDomain subsDomain = GsonUtils.readValue(body, SubsDomain.class);
		return subsService.findByKey(subsDomain);
	}


	@ResponseBody
	@PostMapping(params = "method=addSubs")
	public Mono<HttpResult> insertSubs(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		SubsDomain t = GsonUtils.readValue(body, SubsDomain.class);

		SubsDomain subsDomain = new SubsDomain();
		subsDomain.setSubsNo(t.getSubsNo());
		subsDomain.setCreateDate(new Date());
		//厂站编号查重
		List<SubsDomain> subsDomainList = subsService.findByNo(subsDomain);
		if (subsDomainList.size() > 0) {
			return Mono.just(new HttpResult(HttpResult.ERROR, "新增厂站信息失败，厂站编号重复"));
		}
		int count = subsService.insert(t);
		if(count>0) {
			return Mono.just(new HttpResult(HttpResult.SUCCESS, "新增厂站信息成功"));
		}
		return Mono.just(new HttpResult(HttpResult.ERROR, "新增厂站信息失败"));

	}

	@ResponseBody
	@PostMapping(params = "method=updateSubs")
	public Mono<HttpResult> updateSubs(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		SubsDomain subsDomain = GsonUtils.readValue(body, SubsDomain.class);
		int count = subsService.update(subsDomain);
		if(count>0) {
			return Mono.just(new HttpResult(HttpResult.SUCCESS, "更新厂站信息成功"));
		}
		return Mono.just(new HttpResult(HttpResult.ERROR, "更新厂站信息失败"));
	}

//	@ResponseBody
//	@PostMapping(params = "method=deleteSubs")
//	public Mono<HttpResult> deleteMeterRelList(@RequestBody List<SubsDomain> list)
//			throws JsonParseException, JsonMappingException, IOException {
////		SubsDomain meterRelaDomain = GsonUtils.readValue(list, SubsDomain.class);
////		meterRelaDomain.setUpdateDate(now);
////		meterRelaDomain.setStatus((byte) 0);
//		int count = subsService.deleteSubs(list);
//		if(count>0) {
//			return Mono.just(new HttpResult(HttpResult.SUCCESS, "删除套扣关系成功"));
//		}
//		return Mono.just(new HttpResult(HttpResult.ERROR, "删除套扣关系失败"));
//	}

}
