/**
 *    Auth:riozenc
 *    Date:2019年3月13日 上午9:33:24
 *    Title:com.riozenc.cim.webapp.action.UserAction.java
 **/
package org.fms.cim.webapp.archives.action;
/**
 * 用电户
 * @author riozenc
 *
 */

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.riozenc.cim.web.config.JsonGrid;
import com.riozenc.cim.web.strategy.SequenceEnvironment;
import com.riozenc.cim.web.strategy.SequenceStrategy;
import com.riozenc.cim.webapp.archives.domain.CustomerDomain;
import com.riozenc.cim.webapp.archives.domain.MeterDomain;
import com.riozenc.cim.webapp.archives.domain.UserDomain;
import com.riozenc.cim.webapp.archives.service.ICustomerService;
import com.riozenc.cim.webapp.archives.service.IMeterService;
import com.riozenc.cim.webapp.archives.service.IUserService;
import com.riozenc.titanTool.common.json.utils.GsonUtils;
import com.riozenc.titanTool.common.json.utils.JSONUtil;
import com.riozenc.titanTool.spring.web.http.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import reactor.core.publisher.Mono;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@ControllerAdvice
@RequestMapping("user")
public class UserAction {

	@Autowired
	@Qualifier("userServiceImpl")
	private IUserService userService;
	@Autowired
	@Qualifier("customerServiceImpl")
	private ICustomerService customerService;
    @Autowired
    @Qualifier("meterServiceImpl")
    private IMeterService meterService;
	@Autowired
    @Qualifier("appNoSequenceStrategy")
    private SequenceStrategy appNoSequenceStrategy;
	
    @Autowired
    private RestTemplate restTemplate;
    

	@ResponseBody
	@PostMapping(params = "method=getUserInfo")
	public Mono<JsonGrid> getUserInfo(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		UserDomain userDomain = GsonUtils.readValue(body, UserDomain.class);
		return Mono.just(new JsonGrid(userDomain, userService.findByWhere(userDomain)));
	}

	@RequestMapping(params = "method=getUserByKey")
	@ResponseBody
	public Mono<UserDomain> getWriteSectByKey(@RequestBody UserDomain e) {
		return Mono.just(userService.findByKey(e));
	}

	/**
	 * 保存客户和用电户信息
	 */
	@ResponseBody
	@RequestMapping(params = "method=updateUserCustomer")
	public Mono<HttpResult> updateUserCustomer(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		JsonNode jsonNode = JSONUtil.readValue(body, JsonNode.class);
		// 前台传2个domain时的处理方法
		UserDomain userDomain = GsonUtils.readValue(jsonNode.get("user").toString(), UserDomain.class);
		CustomerDomain customerDomain = GsonUtils.readValue(jsonNode.get("customer").toString(), CustomerDomain.class);
		// 判断user_no是否重复
		List<UserDomain> userList = userService.userNoDC(userDomain);
		if (userList.size() > 0) {
			return Mono.just(new HttpResult(HttpResult.ERROR, "修改用电户信息失败，户号重复"));
		}
		List<CustomerDomain> customerList = customerService.customerNoDC(customerDomain);
		if (customerList.size() > 0) {
			return Mono.just(new HttpResult(HttpResult.ERROR, "修改用电户信息失败，客户户号重复"));
		}

		if (userService.updateUserCustomer(userDomain, customerDomain)) {
			return Mono.just(new HttpResult(HttpResult.SUCCESS, "更新用户信息成功"));
		} else {
			return Mono.just(new HttpResult(HttpResult.ERROR, "更新用户信息失败"));
		}
	}

	@ResponseBody
	@RequestMapping(params = "method=updateUser")
	public Mono<HttpResult> updateUser(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		UserDomain userDomain = GsonUtils.readValue(body, UserDomain.class);
		// 判断user_no是否重复
		List<UserDomain> userList = userService.userNoDC(userDomain);
		if (userList.size() > 0) {
			return Mono.just(new HttpResult(HttpResult.ERROR, "新增用电户信息失败，户号重复"));
		}
		int count = userService.update(userDomain);
		
		MeterDomain meterDomain = new MeterDomain();
		meterDomain.setUserId(userDomain.getId());
		meterDomain.setWriteSectionId(userDomain.getWriteSectId());
		meterDomain.setStatus(userDomain.getStatus());
		meterService.updateWriteSectIdByUserId(meterDomain);
		
		if (count > 0) {
			return Mono.just(new HttpResult(HttpResult.SUCCESS, "更新用电户信息成功"));
		} else {
			return Mono.just(new HttpResult(HttpResult.ERROR, "更新用电户信息失败"));
		}
	}

	@ResponseBody
	@RequestMapping(params = "method=addUserInfo")
	public Mono<HttpResult> addUserInfo(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		UserDomain userDomain = GsonUtils.readValue(body, UserDomain.class);
		
		// 判断user_no是否重复
		UserDomain tt = new UserDomain();
		tt.setUserNo(userDomain.getUserNo());
		List<UserDomain> userList = userService.findByNo(tt);
		//户号已存在则生成
		if (userList.size() > 0) {
			//生成用户编号
			String deptId = getDept(userDomain.getBusinessPlaceCode());
			//获取户号
	        SequenceEnvironment appNoEnvironment=new SequenceEnvironment(appNoSequenceStrategy);
	        String userNo = appNoEnvironment.generateSequenceNo(deptId);
	        userDomain.setUserNo(userNo);
			tt.setUserNo(userNo);
			// 判断user_no是否重复
			userList = userService.findByNo(tt);
			if (userList.size() > 0) {
				return Mono.just(new HttpResult(HttpResult.ERROR, "新增用电户信息失败，用户编号重复"));
			}

		}

		userDomain.setStatus((byte) 1);
		userDomain.setCreateDate(new Date());

		int count = userService.insert(userDomain);
		if (count > 0) {
			return Mono.just(new HttpResult(HttpResult.SUCCESS, "新增用电户信息成功"));
		} else {
			return Mono.just(new HttpResult(HttpResult.ERROR, "新增用电户信息失败"));
		}
	}

	// 鹤岗用户编号生成器
	public String newUserNo(UserDomain e) {

		// 站号
		String stationNo = e.getBusinessPlaceCode() == null ? "" : e.getBusinessPlaceCode().toString();
		// 年月
		Date tempDate = new Date();
		DateFormat format1 = new SimpleDateFormat("yyMM");
		String yymm = format1.format(tempDate);
		// 流水号

		return null;
	}

	public String getDept(Long businessPlaceCode) throws JsonParseException, JsonMappingException, IOException{
        String resultJson = restTemplate.getForObject("http://AUTH-CENTER/auth/dept/getDeptById/" + businessPlaceCode,
                String.class);
        HttpResult<HashMap<String,Object>> httpResult2 = JSONUtil.readValue(resultJson,
                new TypeReference<HttpResult<HashMap<String,Object>>>() {});		
        return httpResult2.getResultData().get("deptId").toString();
        
	}
	
	/*------------------------------抄表初始化-------------------------------*/
//
//	@ResponseBody
//	@PostMapping(params = "method=getUserByIds")
//	public Mono<List<UserDomain>> getUserByIds(@RequestBody String body)
//			throws JsonParseException, JsonMappingException, IOException {
//		JsonNode jsonNode = JSONUtil.readValue(body, JsonNode.class);
//		String s = jsonNode.get("ids").toString();
//		// return Mono.just(userService.getUserByIds(s));
//		return null;
//	}

	@ResponseBody
	@PostMapping(params = "method=getUsersByCustomerNo")
	public JsonGrid getUsersByCustomerNo(@RequestBody String customer)
			throws JsonParseException, JsonMappingException, IOException {
		CustomerDomain customerDomain = GsonUtils.readValue(customer, CustomerDomain.class);
		UserDomain userDomain = new UserDomain();
		userDomain.setCustomerId(customerDomain.getId());
		return new JsonGrid(userDomain, userService.getUsersByCustomerNo(userDomain));
	}

	@ResponseBody
	@PostMapping(params = "method=queryUsersByCustomer")
	public List<UserDomain> queryUsersByCustomer(@RequestBody String customer) {
		HashMap<String,Object> map=  GsonUtils.readValue(customer, HashMap.class);
		CustomerDomain customerDomain = GsonUtils.readValue(GsonUtils.toJson(map.get("customer")),
				CustomerDomain.class);
		UserDomain userDomain = new UserDomain();
		userDomain.setCustomerId(customerDomain.getId());
		userDomain.setCustomerIds(customerDomain.getCustomerIds());
		return userService.getUsersByCustomerNo(userDomain);
	}

	@ResponseBody
	@PostMapping(params = "method=getUserByIds")
	public List<UserDomain> getUserByIds(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		List<Long> userIds = GsonUtils.readValueToList(body, Long.class);
		return userService.getUserByIds(userIds);
	}

	@ResponseBody
	@PostMapping(params = "method=queryUserByIds")
	public List<UserDomain> queryUserByIds(@RequestBody String body) {
		HashMap<String,Object> map = GsonUtils.readValue(body, HashMap.class);
		List<Long> userIds = GsonUtils.readValueToList(GsonUtils.toJson(map.get("userIds")), Long.class);
		return userService.getUserByIds(userIds);
	}

}
