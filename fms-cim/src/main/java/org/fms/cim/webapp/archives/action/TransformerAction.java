/**
 * Auth:riozenc
 * Date:2019年3月13日 下午4:43:01
 * Title:com.riozenc.cim.webapp.action.TransformerAction.java
 **/
package org.fms.cim.webapp.archives.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.fms.cim.web.config.JsonGrid;
import org.fms.cim.webapp.archives.domain.CustomerDomain;
import org.fms.cim.webapp.archives.domain.TransformerDomain;
import org.fms.cim.webapp.archives.domain.TransformerLineRelDomain;
import org.fms.cim.webapp.archives.domain.TransformerMeterRelationDomain;
import org.fms.cim.webapp.archives.domain.UserDomain;
import org.fms.cim.webapp.archives.service.ITransformerMeterRelationService;
import org.fms.cim.webapp.archives.service.ITransformerService;
import org.fms.cim.webapp.assets.domain.TransformerAssetsDomain;
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
import com.riozenc.titanTool.common.json.utils.GsonUtils;
import com.riozenc.titanTool.common.json.utils.JSONUtil;
import com.riozenc.titanTool.spring.web.http.HttpResult;
import com.riozenc.titanTool.spring.web.http.HttpResultPagination;

import reactor.core.publisher.Mono;

@ControllerAdvice
@RequestMapping("transformer")
public class TransformerAction {

	@Autowired
	@Qualifier("transformerServiceImpl")
	private ITransformerService transformerService;

	@Autowired
	@Qualifier("transformerMeterRelationServiceImpl")
	private ITransformerMeterRelationService transformerMeterRelationService;

	@ResponseBody
	@PostMapping(params = "method=getTransformerByUser")
	public Mono<JsonGrid> getTransformerByUser(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		UserDomain userDomain = GsonUtils.readValue(body, UserDomain.class);
		return Mono.just(new JsonGrid(userDomain, transformerService.getTransformerByUser(userDomain)));
	}

	@ResponseBody
	@PostMapping(params = "method=getTransformerByCustomer")
	public Object getTransformerByCustomer(@RequestBody String body) {
		TransformerDomain transformerDomain = GsonUtils.readValue(body, TransformerDomain.class);
		CustomerDomain customerDomain = new CustomerDomain();
		customerDomain.setId(transformerDomain.getCustomerId());
		List<TransformerDomain> transformerByCustomer = transformerService.getTransformerByCustomer(customerDomain);

		return new HttpResultPagination<>(transformerDomain,
				transformerService.getTransformerByCustomer(customerDomain));
	}

	/**
	 * 使用findbywhere 相同的方法
	 */
	@ResponseBody
	@PostMapping(params = "method=getAvaTransformerByWhere")
	public Mono<JsonGrid> getAvaTransformerByWhere(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {

		TransformerDomain t = GsonUtils.readValue(body, TransformerDomain.class);

		return Mono.just(new JsonGrid(t, transformerService.getAvaTransformerByWhere(t)));
	}

	@ResponseBody
	@PostMapping(params = "method=getTransformer")
	public Mono<JsonGrid> getTransformer(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		TransformerDomain t = GsonUtils.readValue(body, TransformerDomain.class);
		return Mono.just(new JsonGrid(t, transformerService.findByWhere(t)));
	}

	/**
	 * 根据变压器资产号查变压器档案
	 */
	@ResponseBody
	@PostMapping(params = "method=getTransformerByAsset")
	public Mono<List<TransformerDomain>> getTransformerByAsset(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		TransformerAssetsDomain t = GsonUtils.readValue(body, TransformerAssetsDomain.class);
		List<TransformerDomain> list = transformerService.getTransformerByAsset(t);
		return Mono.just(list);
	}

	@ResponseBody
	@PostMapping(params = "method=insertTransformer")
	public Mono<HttpResult> insertTransformer(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		TransformerDomain t = GsonUtils.readValue(body, TransformerDomain.class);
		// 变压器编号查重
		TransformerDomain transformerDomain = new TransformerDomain();
		transformerDomain.setTransformerNo(t.getTransformerNo());
		List<TransformerDomain> list = transformerService.findByNo(transformerDomain);
		if (list.size() > 0) {
			return Mono.just(new HttpResult<>(HttpResult.ERROR, "新增变压器信息失败，变压器编号重复"));
		}

		t.setCreateDate(new Date());
		t.setOperator(Long.parseLong(t.getManagerId()));
		t.setStatus((byte) 1);
		int count = transformerService.insert(t);
		if (count > 0) {
			return Mono.just(new HttpResult<>(HttpResult.SUCCESS, "新增变压器信息成功"));
		}
		return Mono.just(new HttpResult<>(HttpResult.ERROR, "新增变压器信息失败"));
	}

	@ResponseBody
	@PostMapping(params = "method=updateTransformer")
	public Mono<HttpResult> updateTransformer(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		TransformerDomain t = GsonUtils.readValue(body, TransformerDomain.class);

		// 变压器编号查重
		TransformerDomain transformerDomain = new TransformerDomain();
		transformerDomain.setTransformerNo(t.getTransformerNo());
		List<TransformerDomain> list = transformerService.findByNoId(transformerDomain);
		if (list.size() > 0) {
			return Mono.just(new HttpResult<>(HttpResult.ERROR, "更新变压器信息失败，变压器编号重复"));
		}
		t.setCreateDate(new Date());

		int count = transformerService.update(t);
		if (count > 0) {
			return Mono.just(new HttpResult<>(HttpResult.SUCCESS, "更新变压器信息成功"));
		}
		return Mono.just(new HttpResult<>(HttpResult.ERROR, "更新变压器信息失败"));
	}

	/*------------------------------抄表初始化-------------------------------*/
	@ResponseBody
	@PostMapping(params = "method=getTransformerByMeterIds")
	public Mono<List<TransformerDomain>> getTransformerByMeterIds(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		JsonNode jsonNode = JSONUtil.readValue(body, JsonNode.class);

		List<Long> ids = JSONUtil.readValue(jsonNode.get("ids").toString(), new TypeReference<List<Long>>() {
		});

		List<TransformerDomain> list = transformerService.getTransformerByMeterIds(ids);
		return Mono.just(list);
	}

	/*------------------------------给zjd写的睿智方法-------------------------------*/
	@ResponseBody
	@PostMapping(params = "method=getTransformerByRel")
	public Mono<List<Map>> getTransformerByRel(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		JsonNode jsonNode = JSONUtil.readValue(body, JsonNode.class);
		List<Long> ids = JSONUtil.readValue(jsonNode.get("ids").toString(), new TypeReference<List<Long>>() {
		});

		List<Map> list = transformerService.getTransformerByRel(ids);
		return Mono.just(list);
	}

	@ResponseBody
	@PostMapping(params = "method=getDistinctTransGroupNo")
	public Mono<List<TransformerMeterRelationDomain>> getDistinctTransGroupNo(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		TransformerMeterRelationDomain transformerMeterRelationDomain = JSONUtil.readValue(body,
				TransformerMeterRelationDomain.class);

		List<TransformerMeterRelationDomain> list = transformerMeterRelationService
				.getDistinctTransGroupNo(transformerMeterRelationDomain);
		return Mono.just(list);
	}

	@ResponseBody
	@PostMapping(params = "method=findRelByTranformIds")
	public List<TransformerLineRelDomain> findRelByTranformIds(@RequestBody String body) {
		TransformerLineRelDomain t = GsonUtils.readValue(body, TransformerLineRelDomain.class);
		return transformerService.findRelByTranformIds(t);
	}

	@ResponseBody
	@PostMapping(params = "method=findRelByLineIds")
	public List<TransformerLineRelDomain> findRelByLineIds(@RequestBody String body) {
		TransformerLineRelDomain t = GsonUtils.readValue(body, TransformerLineRelDomain.class);
		return transformerService.findRelByLineIds(t);
	}

}
