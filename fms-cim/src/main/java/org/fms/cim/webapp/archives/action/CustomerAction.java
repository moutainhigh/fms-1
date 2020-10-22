/**
 *    Auth:riozenc
 *    Date:2019年3月8日 下午3:37:16
 *    Title:com.riozenc.cim.webapp.action.CustomerAction.java
 **/
package org.fms.cim.webapp.archives.action;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.fms.cim.common.strategy.no.SequenceEnvironment;
import org.fms.cim.common.strategy.no.SequenceStrategy;
import org.fms.cim.web.config.JsonGrid;
import org.fms.cim.webapp.archives.domain.CustomerDomain;
import org.fms.cim.webapp.archives.domain.SysSequenceNoDomain;
import org.fms.cim.webapp.archives.service.ICustomerService;
import org.fms.cim.webapp.archives.service.ISysSequenceNoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.riozenc.titanTool.common.json.utils.GsonUtils;
import com.riozenc.titanTool.common.json.utils.JSONUtil;
import com.riozenc.titanTool.spring.web.http.HttpResult;

import reactor.core.publisher.Mono;

@ControllerAdvice
@RequestMapping("customer")
public class CustomerAction {

	@Autowired
	@Qualifier("customerServiceImpl")
	private ICustomerService customerService;
	
	@Autowired
	@Qualifier("sysSequenceNoServiceImpl")
	private ISysSequenceNoService sysSequenceNoService;
	
	@Autowired
    @Qualifier("appNoSequenceStrategy")
    private SequenceStrategy appNoSequenceStrategy;
	
    @Autowired
    private RestTemplate restTemplate;
    

	@RequestMapping(params = "method=addCustomerInfo")
	@ResponseBody
	public Mono<HttpResult> addCustomerInfo(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		CustomerDomain customerDomain = GsonUtils.readValue(body, CustomerDomain.class);

		//如果户号空，生成户号
		if(customerDomain.getCustomerNo()==null) {
			
//			HashMap<String,Object> customerNoMap = newCustomerNo();
//			if((boolean)customerNoMap.get("result")) {

				String deptId = getDept(customerDomain.getBusinessPlaceCode());
				if(deptId.length()!=4) {
					return Mono.just(new HttpResult(HttpResult.ERROR, "所选营业区域不符合要求，请选择最下级营业区域"));
				}
				//获取户号
		        SequenceEnvironment appNoEnvironment=new SequenceEnvironment(appNoSequenceStrategy);

		        customerDomain.setCustomerNo(appNoEnvironment.generateSequenceNo(deptId));


//			}else {
//				return Mono.just(new HttpResult(HttpResult.ERROR, customerNoMap.get("customerNo").toString()));
//
//			}
		}

		// 判断customer_no是否重复
		CustomerDomain tt = new CustomerDomain();
		tt.setCustomerNo(customerDomain.getCustomerNo());
		List<CustomerDomain> customerList = customerService.getCustomerByNo(tt);
		if (customerList.size() > 0) {
			return Mono.just(new HttpResult(HttpResult.ERROR, "新增客户信息失败，户号重复"));
		}
		customerDomain.setCreateDate(new Date());
		customerDomain.setStatus((byte) 1);
		int i = customerService.insert(customerDomain);

		if (i > 0) {
			return Mono.just(new HttpResult(HttpResult.SUCCESS, "新增客户信息成功."));
		}
		return Mono.just(new HttpResult(HttpResult.ERROR, "新增客户信息失败."));
	}

	@ResponseBody
	@PostMapping(params = "method=getCustomer")
	public Mono<JsonGrid> getCustomer(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		CustomerDomain customerDomain = GsonUtils.readValue(body, CustomerDomain.class);
//		customerDomain.setStatus((byte)1);
		return Mono.just(new JsonGrid(customerDomain, customerService.findByWhere(customerDomain)));

	}

	//根据户号和户名精确查询
	@ResponseBody
	@PostMapping(params = "method=getCustomerByNo")
	public Mono<JsonGrid> getCustomerByNo(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		CustomerDomain customerDomain = GsonUtils.readValue(body, CustomerDomain.class);
		return Mono.just(new JsonGrid(customerDomain, customerService.getCustomerByNo(customerDomain)));

	}
	
	@ResponseBody
	@PostMapping(params = "method=getCustomerInfo")
	public Mono<CustomerDomain> getCustomerInfo(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		CustomerDomain customerDomain = GsonUtils.readValue(body, CustomerDomain.class);
		return Mono.just(customerService.findByKey(customerDomain));
	}

	@ResponseBody
	@PostMapping(params = "method=updateCustomerInfo")
	public Mono<HttpResult> updateCustomerInfo(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		CustomerDomain customerDomain = GsonUtils.readValue(body, CustomerDomain.class);
		
		// 判断customer_no是否重复
		List<CustomerDomain> customerList = customerService.customerNoDC(customerDomain);
		if (customerList.size() > 0) {
			return Mono.just(new HttpResult(HttpResult.ERROR, "新增客户信息失败，户号重复"));
		}
		
		int count = customerService.update(customerDomain);

		if (count > 0) {
			return Mono.just(new HttpResult(HttpResult.SUCCESS, "修改客户信息成功"));
		} else {
			return Mono.just(new HttpResult(HttpResult.ERROR, "修改客户信息失败"));
		}
	}

	/*------------------------------抄表初始化-------------------------------*/
	@ResponseBody
	@PostMapping(params = "method=getCustomerByIds")
	public Mono<List<CustomerDomain>> getCustomerByIds(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		JsonNode jsonNode = JSONUtil.readValue(body, JsonNode.class);
		String tmapStr = jsonNode.get("ids").toString();
		return Mono.just(customerService.getCustomerByIds(tmapStr));
	}

	/*------------------------------生成户号guidong-------------------------------*/
	public synchronized HashMap<String,Object> newCustomerNo() {

		HashMap<String,Object> reMap = new HashMap<String,Object>();
		reMap.put("result", false);
		reMap.put("customerNo", "自动生成户号失败");

		SysSequenceNoDomain seq = new SysSequenceNoDomain();
		seq.setCode("CUSTOMER_NO");
		//seq.setId((long) 1);
		List<SysSequenceNoDomain> listSeq = sysSequenceNoService.findByWhere(seq);
		
		//多条序列记录
		if(listSeq.size()>1) {
			
			reMap.put("customerNo", "SYS_SEQUENCE_NO表中存在多条CUSTOMER_NO序列，请联系系统管理员");
			return reMap;
			
		}else if(listSeq.size()==1) { //1条记录
			seq = listSeq.get(0);
			//最大值为空
			if(seq.getMaxNo() == null) {
				reMap.put("customerNo", "SYS_SEQUENCE_NO表中CUSTOMER_NO序列的最大值为空，请联系系统管理员");
				return reMap;
			}
			//正则为空
			if(seq.getRegex() == null) {
				reMap.put("customerNo", "SYS_SEQUENCE_NO表中CUSTOMER_NO序列的格式化字符串为空，请联系系统管理员");
				return reMap;
			}
			
			int maxNo = seq.getMaxNo();

			seq.setMaxNo(maxNo + 1);
			if (sysSequenceNoService.update(seq) == 1) {
				String customerNo = String.format(seq.getRegex(), maxNo);
				reMap.put("customerNo",customerNo);
				reMap.put("result", true);

			}else {
				//更新失败
				reMap.put("customerNo", "SYS_SEQUENCE_NO表中CUSTOMER_NO序列更新失败，请联系系统管理员");
				return reMap;
			}
			
		}else {//没有记录
			reMap.put("customerNo", "SYS_SEQUENCE_NO表中无CUSTOMER_NO序列，请联系系统管理员");
			return reMap;
			
		}

		return reMap;

	}
	
	public String getDept(Long businessPlaceCode) throws JsonParseException, JsonMappingException, IOException{
        String resultJson = restTemplate.getForObject("http://AUTH-CENTER/auth/dept/getDeptById/" + businessPlaceCode,
                String.class);
        HttpResult<HashMap<String,Object>> httpResult2 = JSONUtil.readValue(resultJson,
                new TypeReference<HttpResult<HashMap<String,Object>>>() {});		
        return httpResult2.getResultData().get("deptId").toString();
        
	}

}
