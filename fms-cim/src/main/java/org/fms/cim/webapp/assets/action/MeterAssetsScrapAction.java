/**
 *    Auth:riozenc
 *    Date:2019年3月14日 下午8:26:01
 *    Title:com.riozenc.cim.webapp.action.MeterAssetsAction.java
 **/
package org.fms.cim.webapp.assets.action;

import java.io.IOException;
import java.util.List;

import org.fms.cim.webapp.assets.domain.MeterAssetsDomain;
import org.fms.cim.webapp.assets.service.IMeterAssetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.riozenc.titanTool.spring.web.http.HttpResult;

/**
 * 电能表资产退回
 *
 * @author yhx 
 *
 */
@ControllerAdvice(assignableTypes = MeterAssetsScrapAction.class)
@RequestMapping("meterAssetsScrap")
public class MeterAssetsScrapAction  {

	@Autowired
	@Qualifier("meterAssetsServiceImpl")
	private IMeterAssetsService meterAssetsService;

	/**
	 * 电表资产退回
	 *
	 * @param meterAssetsScrap
	 * @return
	 */
	@ResponseBody
	@PostMapping(params = "method=meterAssetsScrap")
	public HttpResult meterAssetsScrap(@RequestBody List<MeterAssetsDomain> l) 
			throws JsonParseException, JsonMappingException, IOException {
		for(MeterAssetsDomain e : l) {
			e.setStatus((byte) 17);
		}
		int i = meterAssetsService.updateList(l);
		if (i > 0)
			return new HttpResult<>(HttpResult.SUCCESS, "报废成功.");
		else
			return new HttpResult<>(HttpResult.ERROR, "报废失败.");
	}

}
