/**
 *    Auth:riozenc
 *    Date:2019年3月18日 下午2:00:40
 *    Title:com.riozenc.cim.webapp.assets.action.InductorAssetsAction.java
 **/
package org.fms.cim.webapp.assets.action;

import java.io.IOException;
import java.util.List;
import com.riozenc.cim.web.strategy.SequenceStrategy;
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
import com.riozenc.cim.web.strategy.SequenceEnvironment;
import com.riozenc.cim.webapp.archives.domain.UserDomain;
import com.riozenc.cim.webapp.assets.domain.InductorAssetsDomain;
import com.riozenc.cim.webapp.assets.service.IInductorAssetsService;
import com.riozenc.titanTool.common.json.utils.GsonUtils;
import com.riozenc.titanTool.spring.web.http.HttpResult;

import reactor.core.publisher.Mono;

/**
 * 互感器资产
 *
 * @author yhx
 *
 */
@ControllerAdvice(assignableTypes = InductorAssetsAction.class)
@RequestMapping("inductorAssets")
public class InductorAssetsAction  {

	@Autowired
	@Qualifier("inductorAssetsServiceImpl")
	private IInductorAssetsService inductorAssetsService;

	@Autowired
    @Qualifier("inductorSequenceStrategy")
	private SequenceStrategy inductorSequenceStrategy;
	/**
	 * 新增资产
	 */
	@ResponseBody
	@PostMapping(params = "method=addAssets")
	public Object addAssets(@RequestBody String body) {
		InductorAssetsDomain t = GsonUtils.readValue(body, InductorAssetsDomain.class);
		SequenceEnvironment inductorEnvironment=new SequenceEnvironment(inductorSequenceStrategy);
		t.setInductorAssetsNo(inductorEnvironment.generateSequenceNo(null));
		//资产号查重
		InductorAssetsDomain tt = new InductorAssetsDomain();
		tt.setInductorAssetsNo(t.getInductorAssetsNo());
		List<InductorAssetsDomain> list = inductorAssetsService.findByWhere(tt);
		if(list.size()>0) {
			return new HttpResult<>(HttpResult.ERROR, "新增失败,资产编号重复.");
		}
		if(t.getStatus()==null) {
			t.setStatus((byte) 0);
		}
		int i = inductorAssetsService.insert(t);
		if (i > 0)
			return new HttpResult<>(HttpResult.SUCCESS, "新增成功.");
		else
			return new HttpResult<>(HttpResult.ERROR, "新增失败.");
	}

	/**
	 * 查询资产，关联登录人信息
	 */
	@ResponseBody
	@PostMapping(params = "method=getInductorAssetsByManager")
	public Mono<JsonGrid> getAssetsByManager(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		InductorAssetsDomain t = GsonUtils.readValue(body, InductorAssetsDomain.class);
		return Mono.just(new JsonGrid(t,inductorAssetsService.getInductorAssetsByManager(t)));
	}

	/**
	 * 查询资产 findbywhere  分页
	 */
	@ResponseBody
	@PostMapping(params = "method=getInductorAssets")
	public Mono<JsonGrid> getInductorAssets(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		InductorAssetsDomain t = GsonUtils.readValue(body, InductorAssetsDomain.class);
		return Mono.just(new JsonGrid(t,inductorAssetsService.findByWhere(t)));
	}

	/**
	 * 根据用电户查询资产
	 */
	@ResponseBody
	@PostMapping(params = "method=getInductorAssetsByUser")
	public Mono<List<InductorAssetsDomain>> getInductorAssetsByUser(@RequestBody UserDomain t) {
		// TODO Auto-generated method stub
		List<InductorAssetsDomain> list = inductorAssetsService.getInductorAssetsByUser(t);
		return Mono.just(list);
	}


	/**
	 * 更新资产信息（所有属性）
	 *
	 * @param inductorAssetsDomain
	 * @return
	 */
	@ResponseBody
	@PostMapping(params = "method=update")
	public HttpResult update(@RequestBody String body) 	throws JsonParseException, JsonMappingException, IOException {

		InductorAssetsDomain t = GsonUtils.readValue(body, InductorAssetsDomain.class);

		List<InductorAssetsDomain> list = inductorAssetsService.assetsNoDC(t);
		if(list.size()>0) {
			return new HttpResult(HttpResult.ERROR, "更新失败.资产号重复");

		}

		int i = inductorAssetsService.update(t);
		if (i > 0)
			return new HttpResult(HttpResult.SUCCESS, "更新成功.");
		else
			return new HttpResult(HttpResult.ERROR, "更新失败.");
	}


}
