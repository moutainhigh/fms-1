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
 * 互感器资产退回
 *
 * @author yhx
 *
 */
@ControllerAdvice(assignableTypes = InductorAssetsReturnAction.class)
@RequestMapping("inductorAssetsReturn")
public class InductorAssetsReturnAction  {

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
	@PostMapping(params = "method=inductorAssetsReturn")
	public Object inductorAssetsReturn(@RequestBody List<InductorAssetsDomain> l) {

		for(InductorAssetsDomain t : l) {
			t.setDeptId(null);
			t.setStatus((byte) 5);
		}
		
		int i = inductorAssetsService.updateList(l);
		if (i > 0)
			return new HttpResult(HttpResult.SUCCESS, "新增成功.");
		else
			return new HttpResult(HttpResult.ERROR, "新增失败.");
	}




}
