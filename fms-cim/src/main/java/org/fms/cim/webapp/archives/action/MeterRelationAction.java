/**
 *    Auth:riozenc
 *    Date:2019年3月14日 上午8:50:19
 *    Title:com.riozenc.cim.webapp.action.MeterAction.java
 **/
package org.fms.cim.webapp.archives.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.fms.cim.web.config.JsonGrid;
import org.fms.cim.webapp.archives.domain.MeterRelationDomain;
import org.fms.cim.webapp.archives.service.IMeterRelationService;
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

import reactor.core.publisher.Mono;

@ControllerAdvice
@RequestMapping("meterRelation")
public class MeterRelationAction {

	@Autowired
	@Qualifier("meterRelationServiceImpl")
	private IMeterRelationService meterRelationService;
	
	@ResponseBody
	@PostMapping(params = "method=getMeterRel")
	public JsonGrid getMeterRel(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {		
		MeterRelationDomain t = GsonUtils.readValue(body, MeterRelationDomain.class);
		
		return new JsonGrid(t,meterRelationService.findByWhere(t));
	}

	
	@ResponseBody
	@PostMapping(params = "method=insertMeterRel")
	public Mono<HttpResult>  insertMeterRel(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		MeterRelationDomain t = GsonUtils.readValue(body, MeterRelationDomain.class);
 		t.setStatus(new Byte("1"));
		//判断主副表分时标志，为空、不同、以及分时分摊方式、不允许自己套自己
		HashMap<String, Object> rmap = shareRateCheck(t);
		if(!(boolean)rmap.get("result")) {
			return Mono.just(new HttpResult(HttpResult.ERROR, rmap.get("massage").toString()));
		}
		
		int count = meterRelationService.insert(t);
		if(count>0) {
			return Mono.just(new HttpResult(HttpResult.SUCCESS, "新增套扣关系成功"));
		}
		return Mono.just(new HttpResult(HttpResult.ERROR, "新增套扣关系失败"));

	}
	
	@ResponseBody
	@PostMapping(params = "method=updateMeterRel")
	public Mono<HttpResult> updateMeterRel(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		MeterRelationDomain t = GsonUtils.readValue(body, MeterRelationDomain.class);
		HashMap<String, Object> rmap = shareRateCheck(t);
		if(!(boolean)rmap.get("result")) {
			return Mono.just(new HttpResult(HttpResult.ERROR, rmap.get("massage").toString()));
		}
		
		int count = meterRelationService.update(t);
		if(count>0) {
			return Mono.just(new HttpResult(HttpResult.SUCCESS, "更新套扣关系成功"));
		}
		return Mono.just(new HttpResult(HttpResult.ERROR, "更新套扣关系失败"));
	}
	
	@ResponseBody
	@PostMapping(params = "method=deleteMeterRel")
	public Mono<HttpResult> deleteMeterRel(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		
		JsonNode jsonNode = JSONUtil.readValue(body, JsonNode.class);

		List<MeterRelationDomain> meterRelList = 
				JSONUtil.readValue(jsonNode.get("meterRel").toString(), new TypeReference<List<MeterRelationDomain>>() {});

		
	//	MeterRelationDomain t = GsonUtils.readValue(body, MeterRelationDomain.class);
		int count = meterRelationService.deleteList(meterRelList);
		if(count>0) {
			return Mono.just(new HttpResult<>(HttpResult.SUCCESS, "删除套扣关系成功"));
		}
		return Mono.just(new HttpResult<>(HttpResult.ERROR, "删除套扣关系失败"));
	}
	
	//判断shareRate 是否满足要求
	public HashMap<String, Object> shareRateCheck (MeterRelationDomain t) {
		
		HashMap<String, Object> rmap = new HashMap<String, Object>();
		rmap.put("result", false);
		
		//自己套自己
		if(t.getMeterId() - t.getpMeterId()==0) {
			rmap.put("massage", "不允许自己套自己");
			return rmap;
		}
		
		List<MeterRelationDomain> meterRelList = meterRelationService.cc(t);
		if(meterRelList!=null&&meterRelList.size()>0) {
			rmap.put("massage", "不允许两个计量点有多种套扣关系");
			return rmap;
		}
		
		//判断主副表分时标识，为空，相同
		if(t.getpMeterRateFlag()==null||t.getMeterRateFlag()==null) {
			rmap.put("massage", "主表或副表分时标志为空");
			return rmap;
		}

		//分时拆普通=按比例   普通拆分时=公式或平均
	//	else if(t.getpMeterRateFlag()-0==0 && t.getMeterRateFlag()-1==0 && t.getShareRate()!=null) {
		else if(t.getShareRate()!=null) {
			//分时分摊方式
			String s = t.getShareRate();
			
			//验证格式
			String reg4 = "(\\d+(\\.\\d+)?)[:](\\d+(\\.\\d+)?)[:](\\d+(\\.\\d+)?)[:](\\d+(\\.\\d+)?)"; //4个费率
			String reg3 = "(\\d+(\\.\\d+)?)[:](\\d+(\\.\\d+)?)[:](\\d+(\\.\\d+)?)"; //3个费率
			
			boolean isMatch4 = Pattern.matches(reg4, s);
			boolean isMatch3 = Pattern.matches(reg3, s);
			
			if(!isMatch4&&!isMatch3) {
				rmap.put("massage", "格式不匹配！正确格式为：尖：峰：平：谷 分摊的比例，例如：25:25:25:25 。如果是3个费率则为33:33:34。总和为100");
				return rmap;

			}
			
			//验证数字之和是否为100
			if(isMatch4) {
				Pattern p = Pattern.compile(reg4);  
				Matcher m = p.matcher(s);
				m.find();
				float a1 = Float.parseFloat(m.group(1));
				float a3 = Float.parseFloat(m.group(3));
				float a5 = Float.parseFloat(m.group(5));
				float a7 = Float.parseFloat(m.group(7));
				
				float sum = a1+a3+a5+a7;
				
				if(sum!=100) {
					rmap.put("massage", "各费率占比之和不等于100！");
					return rmap;
				}

			}else if(isMatch3) {
				Pattern p = Pattern.compile(reg3);  
				Matcher m = p.matcher(s);
				m.find();
				float a1 = Float.parseFloat(m.group(1));
				float a3 = Float.parseFloat(m.group(3));
				float a5 = Float.parseFloat(m.group(5));
				
				float sum = a1+a3+a5;
				
				if(sum!=100) {
					rmap.put("massage", "各费率占比之和不等于100！");
					return rmap;
				}
			}
		}
		
		rmap.put("result", true);

		return rmap;
		
	}
	

}
