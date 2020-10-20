package org.fms.cim.webapp.archives.action;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.riozenc.cim.web.config.JsonGrid;
import com.riozenc.cim.webapp.archives.domain.TgInfoDomain;
import com.riozenc.cim.webapp.archives.service.ITgInfoService;
import com.riozenc.titanTool.common.json.utils.GsonUtils;
import com.riozenc.titanTool.spring.web.http.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 住户
 *
 * @author riozenc
 *
 */

@ControllerAdvice
@RequestMapping("tgInfo")
public class TgInfoAction {

	@Autowired
	@Qualifier("tgInfoServiceImpl")
	private ITgInfoService tgInfoService;

	@ResponseBody
	@PostMapping(params = "method=getTgDropdown")
	public Mono<List<TgInfoDomain>> getTgDropdown() {
		TgInfoDomain tgInfoDomain =  new TgInfoDomain();
		tgInfoDomain.setStatus((byte)1);
		List<TgInfoDomain> tgInfoDomains = tgInfoService.findByWhere(tgInfoDomain);
		return Mono.just(tgInfoDomains);
	}

	@ResponseBody
	@PostMapping(params = "method=getTgInfo")
	public Mono<JsonGrid> getTgInfo(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		TgInfoDomain tgInfoDomain = GsonUtils.readValue(body, TgInfoDomain.class);
		return Mono.just(new JsonGrid(tgInfoDomain, tgInfoService.findByWhere(tgInfoDomain)));
	}

	@ResponseBody
	@RequestMapping(params = "method=updateTgInfo")
	public Mono<HttpResult> updateTgInfo(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		TgInfoDomain tgInfoDomain = GsonUtils.readValue(body, TgInfoDomain.class);
		int count = tgInfoService.update(tgInfoDomain);
		if (count > 0) {
			return Mono.just(new HttpResult<>(HttpResult.SUCCESS, "更新用住宅用户信息成功"));
		} else {
			return Mono.just(new HttpResult<>(HttpResult.ERROR, "更新用住宅用户信息失败"));
		}
	}

	@ResponseBody
	@RequestMapping(params = "method=addTgInfo")
	public Mono<HttpResult> addTgInfo(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		TgInfoDomain tgInfoDomain = GsonUtils.readValue(body, TgInfoDomain.class);
		// 台区编号查重
		TgInfoDomain t = new TgInfoDomain();
		t.setTgNo(tgInfoDomain.getTgNo());
		List<TgInfoDomain> list = tgInfoService.findByNo(t);
		if (list.size() > 0) {
			return Mono.just(new HttpResult<>(HttpResult.ERROR, "新增台区信息失败，台区编号重复"));
		}

		tgInfoDomain.setCreateDate(new Date());
		int count = tgInfoService.insert(tgInfoDomain);
		if (count > 0) {
			return Mono.just(new HttpResult<>(HttpResult.SUCCESS, "新增台区信息成功"));
		} else {
			return Mono.just(new HttpResult<>(HttpResult.ERROR, "新增台区信息失败"));
		}
	}
}
