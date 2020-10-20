/**
 * Author : czy
 * Date : 2019年4月26日 下午3:39:49
 * Title : com.riozenc.cim.webapp.archives.action.WriteSectAction.java
 *
**/
package org.fms.cim.webapp.archives.action;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.riozenc.cim.web.config.JsonGrid;
import com.riozenc.cim.web.strategy.SequenceEnvironment;
import com.riozenc.cim.web.strategy.SequenceStrategy;
import com.riozenc.cim.webapp.archives.domain.WriteSectDomain;
import com.riozenc.cim.webapp.archives.service.IWriteSectService;
import com.riozenc.titanTool.common.json.utils.GsonUtils;
import com.riozenc.titanTool.common.json.utils.JSONUtil;
import com.riozenc.titanTool.spring.web.http.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 抄表区段管理
 * 
 * @author czy
 *
 */
@ControllerAdvice
@RequestMapping("writeSect")
public class WriteSectAction {

	@Autowired
	@Qualifier("writeSectServiceImpl")
	private IWriteSectService writeSectService;

	@Autowired
	@Qualifier("writeSectNoSequenceStrategy")
	private SequenceStrategy writeSectNoSequenceStrategy;

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(params = "method=insert")
	@ResponseBody
	public Object insert(@RequestBody WriteSectDomain writeSectDomain) throws IOException {
		writeSectDomain.setCreateDate(new Date());

		String writeSectNo =newWriteSectNo(writeSectDomain);

		writeSectDomain.setWriteSectNo(writeSectNo);

		int i = writeSectService.insert(writeSectDomain);
		if (i == 1) {
			return new HttpResult<>(HttpResult.SUCCESS, "新增抄表区段成功");
		} else {
			return new HttpResult<>(HttpResult.ERROR, "新增抄表区段失败");
		}
	}

	public String newWriteSectNo(WriteSectDomain writeSectDomain) 
			throws JsonParseException, JsonMappingException, IOException {

		// 获得所选营业区域
		String businessNo = restTemplate.getForObject(
				"http://AUTH-CENTER/auth/dept/getDeptById/" + writeSectDomain.getBusinessPlaceCode(), String.class);
		// 获取营业区域号
		JsonNode jsonNode = JSONUtil.readValue(businessNo, JsonNode.class);
		JsonNode dataNode = JSONUtil.readValue(jsonNode.get("resultData").toString(), JsonNode.class);
		SequenceEnvironment inductorEnvironment = new SequenceEnvironment(writeSectNoSequenceStrategy);
		
		String writeSectNo = "";

		List<WriteSectDomain> tl = new ArrayList<WriteSectDomain>();
		
		do{
			//生成抄表区段号
			writeSectNo = inductorEnvironment.generateSequenceNo(dataNode.get("deptId").asText());
			//抄表区段号查重
			WriteSectDomain checkWriteSectDomain = new WriteSectDomain();
			checkWriteSectDomain.setWriteSectNo(writeSectNo);
			tl = writeSectService.findByWhere(checkWriteSectDomain);
			
		}while (tl!=null&&tl.size()>0);

		return writeSectNo;
	}
	
	
	@RequestMapping(params = "method=delete")
	@ResponseBody
	public Object delete(@RequestBody WriteSectDomain writeSectDomain) {
		int i = writeSectService.delete(writeSectDomain);
		if (i == 1) {
			return new HttpResult<>(HttpResult.SUCCESS, "删除抄表区段成功");
		} else {
			return new HttpResult<>(HttpResult.ERROR, "删除抄表区段失败");
		}
	}

	@RequestMapping(params = "method=update")
	@ResponseBody
	public Object update(@RequestBody WriteSectDomain writeSectDomain) {
		int i = writeSectService.update(writeSectDomain);
		if (i == 1) {
			return new HttpResult<>(HttpResult.SUCCESS, "更新抄表区段成功");
		} else {
			return new HttpResult<>(HttpResult.ERROR, "更新抄表区段失败");
		}
	}

	@RequestMapping(params = "method=getAllWriteSect")
	@ResponseBody
	public Mono<?> getAllWriteSect(@RequestBody WriteSectDomain e) {
		return Mono.just(writeSectService.findByWhere(e));
	}

	@RequestMapping(params = "method=getWriteSect")
	@ResponseBody
	public Mono<JsonGrid> getWriteSect(@RequestBody WriteSectDomain e) {
		return Mono.just(new JsonGrid(e, writeSectService.findByWhere(e)));
	}
	

	@RequestMapping(params = "method=getWriteSectAndInitNum")
	@ResponseBody
	public Mono<JsonGrid> getWriteSectAndInitNum(@RequestBody WriteSectDomain e) {
		//前台不干，后台加的。使用该方法时注意条件。
		e.setStatus((byte) 1);
		List<WriteSectDomain> writeSectList = writeSectService.findByWhere(e);
		HttpResult rh = writeSectService.getMeterInitSituation(writeSectList,e.getMon());
		
		return Mono.just(new JsonGrid(e, rh.getResultData()));
	}
	
	@RequestMapping(params = "method=getWriteSectByKey")
	@ResponseBody
	public Mono<WriteSectDomain> getWriteSectByKey(@RequestBody WriteSectDomain e) {
		return Mono.just(writeSectService.findByKey(e));
	}

	@RequestMapping(params = "method=findByWhere")
	@ResponseBody
	public List<WriteSectDomain> findByWhere(@RequestBody(required = false) String writeSectDomainJson) {
		WriteSectDomain writeSectDomain = GsonUtils.readValue(writeSectDomainJson,
				WriteSectDomain.class);
		return writeSectService.findByWhere(writeSectDomain);
	}

	@RequestMapping(params = "method=queryByWhere")
	@ResponseBody
	public List<WriteSectDomain> queryByWhere(@RequestBody String writeSectDomainJson) {
		HashMap hashMap = GsonUtils.readValue(writeSectDomainJson, HashMap.class);
		WriteSectDomain writeSect = GsonUtils.readValue(GsonUtils.toJson(hashMap.get("writeSect")),
				WriteSectDomain.class);
		return writeSectService.findByWhere(writeSect);
	}

}
