/**
 *    Auth:riozenc
 *    Date:2019年3月14日 下午8:26:01
 *    Title:com.riozenc.cim.webapp.action.MeterAssetsAction.java
 **/
package org.fms.cim.webapp.assets.action;

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
import com.riozenc.cim.webapp.assets.domain.MeterAssetsDomain;
import com.riozenc.cim.webapp.assets.domain.MeterAssetsUseRecordDomain;
import com.riozenc.cim.webapp.assets.service.IMeterAssetsUseRecordService;
import com.riozenc.titanTool.common.json.utils.GsonUtils;
import com.riozenc.titanTool.common.json.utils.JSONUtil;
import com.riozenc.titanTool.spring.web.http.HttpResult;

import reactor.core.publisher.Mono;

/**
 * 电能表资产领用记录
 *
 * @author yhx 
 *
 */
@ControllerAdvice(assignableTypes = MeterAssetsUseRecordAction.class)
@RequestMapping("meterAssetsUseRecord")
public class MeterAssetsUseRecordAction  {

	@Autowired
	@Qualifier("meterAssetsUseRecordServiceImpl")
	private IMeterAssetsUseRecordService meterAssetsUseRecordService;

	/**
	 * 新增电表资产领用记录
	 *
	 * @param MeterAssetsUseRecordDomain
	 * @return
	 */
	@ResponseBody
	@PostMapping(params = "method=addMeterAssetsUseRecord")
	public HttpResult addMeterAssetsUseRecord(@RequestBody String body) 
			throws JsonParseException, JsonMappingException, IOException {
		
		JsonNode jsonNode = JSONUtil.readValue(body, JsonNode.class);
		
		List<MeterAssetsDomain> meterAssetsList = 
				JSONUtil.readValue(jsonNode.get("meterAssetsList").toString(), new TypeReference<List<MeterAssetsDomain>>() {});
		
		MeterAssetsUseRecordDomain meterAssetsUseRecord = JSONUtil.readValue(jsonNode.get("meterAssetsUseRecord").toString(), MeterAssetsUseRecordDomain.class);

		int i = meterAssetsUseRecordService.addMeterAssetsUseRecord(meterAssetsList,meterAssetsUseRecord);
		if (i == 1)
			return new HttpResult(HttpResult.SUCCESS, "新增成功.");
		else
			return new HttpResult(HttpResult.ERROR, "新增失败.");
	}


	/**
	 * 查询电能表资产findByWhere 
	 * */
	@ResponseBody
	@PostMapping(params = "method=getMeterAssetsUseRecord")
	public Mono<JsonGrid> getMeterAssetsUseRecord(@RequestBody String body) {
		MeterAssetsUseRecordDomain e = GsonUtils.readValue(body, MeterAssetsUseRecordDomain.class);

		List<MeterAssetsUseRecordDomain> list = meterAssetsUseRecordService.findByWhere(e);

		JsonGrid grid = new JsonGrid(e, list);

		return Mono.just(grid);
	}


	/**
	 * 更新资产领用记录信息
	 *
	 * @param MeterAssetsUseRecordDomain
	 * @return
	 */
	@ResponseBody
	@PostMapping(params = "method=update")
	public Object update(@RequestBody String body) {

		MeterAssetsUseRecordDomain e = GsonUtils.readValue(body, MeterAssetsUseRecordDomain.class);

		int i = meterAssetsUseRecordService.update(e);
		if (i > 0)
			return new HttpResult(HttpResult.SUCCESS, "更新成功.");
		else
			return new HttpResult(HttpResult.ERROR, "更新失败.");
	}


}
