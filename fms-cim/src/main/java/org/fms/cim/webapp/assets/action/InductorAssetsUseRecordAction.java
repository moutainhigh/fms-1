/**
 *    Auth:riozenc
 *    Date:2019年3月14日 下午8:26:01
 *    Title:com.riozenc.cim.webapp.action.MeterAssetsAction.java
 **/
package org.fms.cim.webapp.assets.action;

import java.io.IOException;
import java.util.List;

import org.fms.cim.web.config.JsonGrid;
import org.fms.cim.webapp.assets.domain.InductorAssetsUseRecordDomain;
import org.fms.cim.webapp.assets.service.IInductorAssetsUseRecordService;
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

/**
 * 互感器资产领用
 *
 * @author yhx
 *
 */
@ControllerAdvice(assignableTypes = InductorAssetsUseRecordAction.class)
@RequestMapping("inductorAssetsUseRecord")
public class InductorAssetsUseRecordAction  {

	@Autowired
	@Qualifier("inductorAssetsUseRecordServiceImpl")
	private IInductorAssetsUseRecordService inductorAssetsUseRecordService;

	/**
	 * 新增互感器资产领用记录
	 *
	 * @param 
	 * @return
	 */
	@ResponseBody
	@PostMapping(params = "method=addInductorAssetsUseRecord")
	public Object addInductorAssetsUseRecord(@RequestBody String body) {
		InductorAssetsUseRecordDomain t = GsonUtils.readValue(body, InductorAssetsUseRecordDomain.class);

		int i = inductorAssetsUseRecordService.insert(t);
		if (i > 0)
			return new HttpResult<>(HttpResult.SUCCESS, "新增成功.");
		else
			return new HttpResult<>(HttpResult.ERROR, "新增失败.");
	}


	/**
	 * 查询互感器资产信息领用记录
	 *
	 * @param InductorAssetsUseRecordDomain
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@ResponseBody
	@PostMapping(params = "method=getInductorAssetsUseRecordByKey")
	public Mono<InductorAssetsUseRecordDomain> getInductorAssetsUseRecordByKey(@RequestBody InductorAssetsUseRecordDomain t) {
		// TODO Auto-generated method stub
		InductorAssetsUseRecordDomain rt = inductorAssetsUseRecordService.findByKey(t);
		return Mono.just(rt);
	}

	/**
	 * 查询互感器资产
	 * */
	@ResponseBody
	@PostMapping(params = "method=getInductorAssetsUseRecordByWhere")
	public Mono<JsonGrid> getInductorAssetsUseRecordByWhere(@RequestBody String body) {
		InductorAssetsUseRecordDomain t = GsonUtils.readValue(body, InductorAssetsUseRecordDomain.class);

		List<InductorAssetsUseRecordDomain> list = inductorAssetsUseRecordService.findByWhere(t);

		JsonGrid grid = new JsonGrid(t, list);

		return Mono.just(grid);
	}

	/**
	 * 更新
	 *
	 * @param InductorAssetsUseRecordDomain
	 * @return
	 */
	@ResponseBody
	@PostMapping(params = "method=update")
	public Object update(@RequestBody String body) {

		InductorAssetsUseRecordDomain t = GsonUtils.readValue(body, InductorAssetsUseRecordDomain.class);

		int i = inductorAssetsUseRecordService.update(t);
		if (i > 0)
			return new HttpResult<>(HttpResult.SUCCESS, "更新成功.");
		else
			return new HttpResult<>(HttpResult.ERROR, "更新失败.");
	}


}
