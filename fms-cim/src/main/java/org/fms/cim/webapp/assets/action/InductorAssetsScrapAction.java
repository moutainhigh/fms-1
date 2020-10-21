/**
 *    Auth:riozenc
 *    Date:2019年3月14日 下午8:26:01
 *    Title:com.riozenc.cim.webapp.action.MeterAssetsAction.java
 **/
package org.fms.cim.webapp.assets.action;

import java.util.List;

import org.fms.cim.webapp.assets.domain.InductorAssetsDomain;
import org.fms.cim.webapp.assets.service.IInductorAssetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.riozenc.titanTool.spring.web.http.HttpResult;

/**
 * 互感器资产报废
 *
 * @author yhx
 *
 */
@ControllerAdvice(assignableTypes = InductorAssetsScrapAction.class)
@RequestMapping("inductorAssetsScrap")
public class InductorAssetsScrapAction  {

	@Autowired
	@Qualifier("inductorAssetsServiceImpl")
	private IInductorAssetsService inductorAssetsService;

	/**
	 * 互感器资产退回记录
	 *
	 * @param 
	 * @return
	 */
	@ResponseBody
	@PostMapping(params = "method=addInductorAssetsScrap")
	public Object addInductorAssetsScrap(@RequestBody List<InductorAssetsDomain> l) {

		for(InductorAssetsDomain t : l) {
			t.setStatus((byte) 17);
		}
		
		int i = inductorAssetsService.updateList(l);
		if (i > 0)
			return new HttpResult<>(HttpResult.SUCCESS, "新增成功.");
		else
			return new HttpResult<>(HttpResult.ERROR, "新增失败.");
	}




}
