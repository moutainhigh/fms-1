/**
 *    Auth:riozenc
 *    Date:2019年3月12日 下午7:16:23
 *    Title:com.riozenc.cim.webapp.service.impl.SettlementServiceImpl.java
 **/
package org.fms.cim.webapp.archives.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.riozenc.cim.webapp.archives.dao.*;
import com.riozenc.cim.webapp.archives.domain.*;
import com.riozenc.cim.webapp.archives.service.IBemService;
import com.riozenc.cim.webapp.assets.dao.InductorAssetsDAO;
import com.riozenc.cim.webapp.assets.dao.MeterAssetsDAO;
import com.riozenc.cim.webapp.assets.dao.TransformerAssetsDAO;
import com.riozenc.cim.webapp.assets.domain.InductorAssetsDomain;
import com.riozenc.cim.webapp.assets.domain.MeterAssetsDomain;
import com.riozenc.cim.webapp.assets.domain.TransformerAssetsDomain;
import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.annotation.TransactionService;
import com.riozenc.titanTool.common.json.utils.JSONUtil;

@TransactionService
public class BemServiceImpl implements IBemService {
	
	@TransactionDAO
	private UserDAO userDAO;
	@TransactionDAO
	private CustomerDAO customerDAO;
	@TransactionDAO
	private MeterDAO meterDAO;
	@TransactionDAO
	private MeterAssetsDAO meterAssetsDAO;
	@TransactionDAO
	private InductorAssetsDAO inductorAssetsDAO;
	@TransactionDAO
	private TransformerAssetsDAO transformerAssetsDAO;
	@TransactionDAO
	private TransformerDAO transformerDAO;
	@TransactionDAO
	private UserTransformerRelaDAO userTransformerRelaDAO;
	@TransactionDAO
	private TransformerMeterRelationDAO transformerMeterRelaDAO;
	@TransactionDAO
	private SettlementDAO settlementDAO;
	
	@Override
	public HashMap<String, Object> addBemInfo(JsonNode jsonNode) throws JsonParseException, JsonMappingException, IOException{

		//传入的数据的格式
//		postData.put("userInfo", appUserInfo);
//	    postData.put("customerInfo", appCustomerInfo);
//	    postData.put("meterInfo", appMeterInfos);
//	    postData.put("meterAssetsInfo", appMeterAssetsInfos);
//	    postData.put("inductorAssetsInfo", appInductorAssetsInfos);
//		postData.put("TransformerInfo", appTransformerInfos);

		HashMap<String,Object> rmap = new HashMap<String,Object>();
		String message = "业扩档案更新开始";

		//第一步，数据解析 老方法
//		UserDomain userInfo = JSONUtil.readValue(jsonNode.get("userInfo").toString(), UserDomain.class);
//		CustomerDomain customerInfo = JSONUtil.readValue(jsonNode.get("customerInfo").toString(), CustomerDomain.class);
//		List<MeterDomain> meterList = JSONUtil.readValue(jsonNode.get("meterInfo").toString(), List.class);
//		List<MeterAssetsDomain> meterAssetsList = JSONUtil.readValue(jsonNode.get("meterAssetsInfo").toString(), List.class);
//		List<InductorAssetsDomain> inductorAssetsList = JSONUtil.readValue(jsonNode.get("inductorAssetsInfo").toString(), List.class);
//		List<TransformerDomain> transformerInfoList = JSONUtil.readValue(jsonNode.get("transformerInfo").toString(), List.class);
//		List<TransformerAssetsDomain> transformerInfoAssetsList = JSONUtil.readValue(jsonNode.get("transformerInfo").toString(), List.class);
	
		//第一步，数据解析  新方法
//		String templateId = jsonNode.get("templateId").toString();//业务类型。
		
		CustomerDomain customerInfo = JSONUtil.readValue(jsonNode.get("customerInfo").toString(), CustomerDomain.class);
		
		UserDomain userInfo = JSONUtil.readValue(jsonNode.get("userInfo").toString(), UserDomain.class);

		SettlementDomain settlementDomain =
				JSONUtil.readValue(jsonNode.get("settlement").toString(),SettlementDomain.class);





		//客户
		HashMap<String,Object> customerMap = addCustomerInfo(customerInfo);
		message += customerMap.get("message").toString();

		if(!(boolean)customerMap.get("result")) {
	
			return customerMap;
		}
		
		//用电户
		if(userInfo.getCustomerNo()==null) {
			userInfo.setCustomerNo(customerInfo.getCustomerNo());
		}
		userInfo.setCustomerId(customerInfo.getId());
		HashMap<String,Object> userMap = addUserInfo(userInfo);
		message += userMap.get("message").toString();

		if(!(boolean)userMap.get("result")) {
	
			return userMap;
		}

		settlementDomain.setCustomerId(customerInfo.getId());
		HashMap<String,Object> settlementrMap =
				addSettlementInfo(settlementDomain);
		message += settlementrMap.get("message").toString();

		if(!(boolean)settlementrMap.get("result")) {

			return settlementrMap;
		}
		

		
		if(jsonNode.get("transformerInfo")!=null) {
			List<TransformerDomain> transformerInfoList = 
					JSONUtil.readValue(jsonNode.get("transformerInfo").toString(), new TypeReference<List<TransformerDomain>>() {});
		
			List<TransformerAssetsDomain> transformerInfoAssetsList = 
					JSONUtil.readValue(jsonNode.get("transformerInfo").toString(), new TypeReference<List<TransformerAssetsDomain>>() {});
			
			//变压器资产 
			HashMap<String,Object> transformerAssetsInfoMap = addTransformerAssetsInfo(transformerInfoAssetsList, transformerInfoList);
			message += transformerAssetsInfoMap.get("message").toString();
			if(!(boolean)transformerAssetsInfoMap.get("result")) {
				
				return transformerAssetsInfoMap;
			}
			
			//变压器档案
			HashMap<String,Object> transformerInfoMap = addTransformerInfo(transformerInfoList);
			message += transformerInfoMap.get("message").toString();
			if(!(boolean)transformerInfoMap.get("result")) {
				
				return transformerInfoMap;
			}
			
			//变压器 用户关系
			//原始list
		/*	List<UserTransformerRelaDomain> userTransRelaList = userTransformerRelaDAO.getUserTransformerRelaByUser(userInfo);
			//之前不存在的入库
			boolean isexist = false;
			for(TransformerDomain t : transformerInfoList) {
				isexist = false;
				for(UserTransformerRelaDomain utrt:userTransRelaList) {
					if(t.getId()==utrt.getTransformerId()) {//存在关系
						isexist = true;
						break; 
					}
				}
				if(!isexist) {
					UserTransformerRelaDomain tt = new UserTransformerRelaDomain();
					tt.setUserId(userInfo.getId());
					tt.setTransformerId(t.getId());
					tt.setCreateDate(new Date());
					tt.setStatus((byte)1);//状态，有效1  无效0
					
					userTransformerRelaDAO.saveUserTransformerRela(tt);
				}
			}
			//之前存在的修改为无效
			for(UserTransformerRelaDomain utrt : userTransRelaList) {
				isexist = false;
				for(TransformerDomain t : transformerInfoList) {
					if(t.getId()==utrt.getTransformerId()) {
						isexist = true;
						break;
					}
				}
				if(!isexist) {
					utrt.setStatus((byte)0);//状态，有效1  无效0
					userTransformerRelaDAO.update(utrt);
				}
			}*/
				
//		//变压器 计量点关系  
//		//将计量点档案中的变压器NO转换成变压器ID
//		TransformerDomain ttd = new TransformerDomain();
//		TransformerMeterRelaDomain ttmr = new TransformerMeterRelaDomain();
//		
//		for(MeterDomain tm : meterList) {
//			ttd.setTransformerNo(tm.getTransformerNo());
//			List<TransformerDomain> ttlist = transformerDAO.findByWhere(ttd);
//			ttmr.setTransformerId(ttlist.get(0).getId());
//			ttmr.setMeterId(tm.getId());
//			ttmr.setCreateDate(new Date());
//			ttmr.setStatus((byte)1);
//			transformerMeterRelaDAO.insert(ttmr);
//		}
		
		}
		//变压器关系更新
		if(jsonNode.get("transformerRel")!=null){
			List<TransformerMeterRelationDomain> transformerMeterRelationList=
					JSONUtil.readValue(jsonNode.get("transformerRel").toString(), new TypeReference<List<TransformerMeterRelationDomain>>() {});

			HashMap<String,Object> tansformerRelMap=
					addTransformerRel(transformerMeterRelationList);

			if(!(boolean)tansformerRelMap.get("result")) {

				return tansformerRelMap;
			}
		}


		if(jsonNode.get("meterInfo")!=null){
			List<MeterDomain> meterList = 
					JSONUtil.readValue(jsonNode.get("meterInfo").toString(), new TypeReference<List<MeterDomain>>() {});

			if(jsonNode.get("meterAssetsInfo")!=null) {
				//档案解析
				List<MeterAssetsDomain> meterAssetsList = 
						JSONUtil.readValue(jsonNode.get("meterAssetsInfo").toString(), new TypeReference<List<MeterAssetsDomain>>() {});
		
				//电表资产
				HashMap<String,Object> meterAssetsMap = addMeterAssetsInfo(meterAssetsList, meterList);
				message += meterAssetsMap.get("message").toString();
		
				if(!(boolean)meterAssetsMap.get("result")) {
					
					return meterAssetsMap;
				}
			}
	
			if(jsonNode.get("inductorAssetsInfo")!=null) {
				List<InductorAssetsDomain> inductorAssetsList =
						JSONUtil.readValue(jsonNode.get("inductorAssetsInfo").toString(), new TypeReference<List<InductorAssetsDomain>>() {});
		
				//互感器资产
				HashMap<String,Object> inductorAssetsMap = addInductorAssetsInfo(inductorAssetsList, meterList);
				message += inductorAssetsMap.get("message").toString();
				if(!(boolean)inductorAssetsMap.get("result")) {
					
					return inductorAssetsMap;
				}
			}
			
			//计量点
			MeterDomain tempMeter = new MeterDomain();
			for(MeterDomain meterInfo:meterList) {
				tempMeter.setMeterNo(meterInfo.getMeterNo());			
				List<MeterDomain> rmeterList = meterDAO.findByWhere(tempMeter);
				if(rmeterList.size()==0) {//新增
	//				//userNo转ID
	//				meterInfo.setUserId(userInfo.getId());
	//				//transNo转ID
	//				for(TransformerDomain t : transformerInfoList) {
	//					if(t.getTransformerNo()==meterInfo.getTransformerNo()) {
	//						meterInfo.setTransformerId(t.getId());
	//					}
	//				}
					
					int meterAddCount = meterDAO.insert(meterInfo);
					if(meterAddCount!=1) {
						rmap.put("result", false);
						rmap.put("message", "计量点新增失败！");
						return rmap;
					}
					message+="计量点新增成功！计量点号为："+meterInfo.getMeterNo();
	
				}else if(rmeterList.size()==1) {//更新
					meterInfo.setId(rmeterList.get(0).getId());
					int meterUpateCount = meterDAO.update(meterInfo);
					if(meterUpateCount!=1) {
						rmap.put("result", false);
						rmap.put("message", "计量点信息修改失败！");
						return rmap;
					}
					message+="计量点信息修改成功！计量点号为："+meterInfo.getMeterNo();
	
				}else if(rmeterList.size()>1) {//垃圾档案
					rmap.put("result", false);
					rmap.put("message", "计量点号重复！");
					return rmap;
				}
			}
		}
		message += "业扩档案更新完成！！";
		rmap.put("message",message);
		rmap.put("result", true);
		return rmap;
	}

	//添加客户档案
	public HashMap<String, Object> addCustomerInfo(CustomerDomain customerInfo) {
		
		HashMap<String,Object> rmap = new HashMap<String,Object>();
		rmap.put("result", false);
		rmap.put("message", "客户新增开始！");

		customerInfo.setStatus((byte)1);
		customerInfo.setId(null);
		CustomerDomain tempCustomer = new CustomerDomain();
		tempCustomer.setCustomerNo(customerInfo.getCustomerNo());
		List<CustomerDomain> customerList= customerDAO.getCustomerByNo(tempCustomer);
		if(customerList.size()==0) {//新增
			int customerAddCount = customerDAO.insert(customerInfo);
			if(customerAddCount!=1) {
				rmap.put("message", "客户新增失败！");
				return rmap;
			}
			rmap.put("result", true);
			rmap.put("message", "客户新增成功！");

		}else if(customerList.size()==1) {//更新
			customerInfo.setId(customerList.get(0).getId());
			int customerUpateCount = customerDAO.update(customerInfo);
			if(customerUpateCount!=1) {
				rmap.put("message", "客户信息修改失败！");
				return rmap;
			}
			rmap.put("result", true);
			rmap.put("message", "客户信息修改成功！");

		}else {//垃圾档案

			rmap.put("message", "客户户号重复！");
			return rmap;
		}
		return rmap;

	}
	
	//添加用电户档案
	public HashMap<String, Object> addUserInfo(UserDomain userInfo) {
		HashMap<String,Object> rmap = new HashMap<String,Object>();
		rmap.put("result", false);
		rmap.put("message", "用电户新增开始！");
		
		userInfo.setStatus((byte)1);
		userInfo.setId(null);
		UserDomain tempUser = new UserDomain();
		tempUser.setUserNo(userInfo.getUserNo());
		List<UserDomain> userList= userDAO.findByNo(tempUser);
		if(userList.size()==0) {//新增
			int userAddCount = userDAO.insert(userInfo);
			if(userAddCount!=1) {
				rmap.put("message", "用电户新增失败！");
				return rmap;
			}
			rmap.put("result", true);
			rmap.put("message", "用电户新增成功！");

		}else if(userList.size()==1) {//更新
			userInfo.setId(userList.get(0).getId());
			int userUpateCount = userDAO.update(userInfo);
			if(userUpateCount!=1) {
				rmap.put("message", "用电户信息修改失败！");
				return rmap;
			}
			rmap.put("result", true);
			rmap.put("message", "用电户信息修改成功！");

		}else {//垃圾档案
			rmap.put("message", "用电户户号重复！");
			return rmap;
		}
		
		return rmap;
			
	}

	//添加结算户档案
	public HashMap<String, Object> addSettlementInfo(SettlementDomain settlementDomain) {
		HashMap<String,Object> rmap = new HashMap<String,Object>();
		rmap.put("result", false);
		rmap.put("message", "结算户新增开始！");

		settlementDomain.setId(null);
		SettlementDomain tempSettlement = new SettlementDomain();
		tempSettlement.setSettlementNo(settlementDomain.getSettlementNo());
		List<SettlementDomain> settlementList=settlementDAO.findByNo(tempSettlement);
		if(settlementList.size()==0) {//新增
			int settlementAddCount = settlementDAO.insert(settlementDomain);
			if(settlementAddCount!=1) {
				rmap.put("message", "结算户新增失败！");
				return rmap;
			}
			rmap.put("result", true);
			rmap.put("message", "结算户新增成功！");

		}else if(settlementList.size()==1) {//更新
			tempSettlement.setId(settlementList.get(0).getId());
			int settlementUpdateCount = settlementDAO.update(settlementDomain);
			if(settlementUpdateCount!=1) {
				rmap.put("message", "结算户信息修改失败！");
				return rmap;
			}
			rmap.put("result", true);
			rmap.put("message", "结算户信息修改成功！");

		}else {//垃圾档案
			rmap.put("message", "结算户户号重复！");
			return rmap;
		}

		return rmap;

	}

	//电表资产
	public HashMap<String, Object> addMeterAssetsInfo(List<MeterAssetsDomain> meterAssetsList, List<MeterDomain> meterList) {
		HashMap<String,Object> rmap = new HashMap<String,Object>();
		rmap.put("result", false);
		rmap.put("message", "电表资产新增开始！");

		//用于标志资产是否匹配上档案
		boolean isMatch = false;
	
		MeterAssetsDomain tempMeterAssets = new MeterAssetsDomain();
		for(MeterAssetsDomain meterAssetsInfo:meterAssetsList) {
			isMatch = false;
			tempMeterAssets.setMeterAssetsNo(meterAssetsInfo.getMeterAssetsNo());
			tempMeterAssets.setStatus((byte)1);
			List<MeterAssetsDomain> rmeterAssetsList = meterAssetsDAO.findByWhere(tempMeterAssets);
			if(rmeterAssetsList.size()==0) {//资产为空
				rmap.put("message", "电表资产不存在！");
				return rmap;
				
			}else if(rmeterAssetsList.size()==1) {//更新
				for(MeterDomain meterInfo:meterList) {//替换计量点中的meterAssetsId
					if(meterInfo.getMeterAssetsId()==meterAssetsInfo.getId()) {
						meterInfo.setMeterAssetsId(rmeterAssetsList.get(0).getId());
						isMatch = true;
					}
				}
				if(!isMatch) {
					rmap.put("message", "电表资产没有关联测量点！");
					return rmap;
				}
				//资产档案更新
				meterAssetsInfo.setStatus((byte)2);
				meterAssetsInfo.setId(rmeterAssetsList.get(0).getId());
				int meterUpateCount = meterAssetsDAO.update(meterAssetsInfo);
				if(meterUpateCount!=1) {

					rmap.put("message", "电表资产修改失败！");
					return rmap;
				}
				rmap.put("result", true);
				rmap.put("message","电表资产修改成功！计量点号为："+meterAssetsInfo.getMeterAssetsNo());

			}else {//垃圾档案
				rmap.put("result", false);
				rmap.put("message", "电表资产号重复！");
				return rmap;
			}
		}
		
		return rmap;
	}
	
	//互感器资产
	public HashMap<String, Object> addInductorAssetsInfo(List<InductorAssetsDomain> inductorAssetsList, List<MeterDomain> meterList) {
		HashMap<String,Object> rmap = new HashMap<String,Object>();
		rmap.put("result", false);
		rmap.put("message", "用电户新增开始！");
		//用于标志资产是否匹配上档案
		boolean isMatch = false;
		
		InductorAssetsDomain tempInductorAssets = new InductorAssetsDomain();
		for (InductorAssetsDomain ta : inductorAssetsList) {
			isMatch = false;
			tempInductorAssets.setInductorAssetsNo(ta.getInductorAssetsNo());
			tempInductorAssets.setStatus((byte)1);
			List<InductorAssetsDomain> tl = inductorAssetsDAO.findByWhere(tempInductorAssets);
			if(tl.size()==0) {//资产为空

				rmap.put("message", "互感器资产不存在！");
				return rmap;
			}else if(tl.size()==1) {
				for(MeterDomain meterInfo:meterList) {//替换计量点中的meterAssetsId
					/*
					 * InductorType 
					 * 1:电流互感器
					 * 2:电压互感器
					 * 3:组合互感器
					 * */
					if(ta.getInductorType()==1 && meterInfo.getCtAssetsId() == ta.getId()) {
						
						meterInfo.setCtAssetsId(tl.get(0).getId());
						isMatch = true;
					}else if (ta.getInductorType()==2 && meterInfo.getPtAssetsId() == ta.getId()) {
						
						meterInfo.setPtAssetsId(tl.get(0).getId());
						isMatch = true;
					}else if (ta.getInductorType()==3 && meterInfo.getPtAssetsId() == ta.getId()) {

						meterInfo.setCtAssetsId(tl.get(0).getId());
						meterInfo.setPtAssetsId(tl.get(0).getId());
						isMatch = true;
					}else {
						rmap.put("message", "互感器资产类型不正确！");
						return rmap;
					}
				}if(!isMatch) {

					rmap.put("message", "互感器资产没有关联测量点！");
					return rmap;
				}
				//修改资产信息
				ta.setStatus((byte)2);
				ta.setId(tl.get(0).getId());
				int count = inductorAssetsDAO.update(ta);
				if(count!=1) {

					rmap.put("message", "互感器资产修改失败！");
					return rmap;
				}
				rmap.put("result", true);
				rmap.put("message", "互感器资产修改成功！资产号为：" +  ta.getInductorAssetsNo());

			}else {
				rmap.put("message", "互感器资产号重复！");
				return rmap;
			}
		}
		
		return rmap;	
	}
	
	//变压器资产 
	public HashMap<String, Object> addTransformerAssetsInfo(List<TransformerAssetsDomain> transformerInfoAssetsList, List<TransformerDomain> transformerInfoList) {
		HashMap<String,Object> rmap = new HashMap<String,Object>();
		rmap.put("result", false);
		rmap.put("message", "用电户新增开始！");
		//用于标志资产是否匹配上档案
		boolean isMatch = false;
		
		TransformerAssetsDomain tta = new TransformerAssetsDomain();
		for(TransformerAssetsDomain ta : transformerInfoAssetsList) {
			isMatch = false;
			tta.setTransformerAssetsNo(ta.getTransformerAssetsNo());
			tta.setStatus((byte)1);
			List<TransformerAssetsDomain> ltat = transformerAssetsDAO.findByWhere(tta);
			if(ltat.size()==0) {//资产为空
				rmap.put("message", "变压器资产不存在！");
				return rmap;
				
			}else if(ltat.size()==1) {//更新
				for(TransformerDomain tt : transformerInfoList) {
					if(tt.getTransformerAssetsId()==ta.getId()) {
						tt.setTransformerAssetsId(ltat.get(0).getId());
						isMatch = true;
					}
				}
				if(!isMatch) {
					rmap.put("message", "变压器资产没有关联变压器档案！");
					return rmap;
				}
				//修改资产信息
				ta.setStatus((byte)2);
				ta.setId(ltat.get(0).getId());
				int count = transformerAssetsDAO.update(ta);
				if(count!=1) {
					rmap.put("message", "变压器资产修改失败！");
					return rmap;
				}
				rmap.put("result", true);
				rmap.put("message", "变压器资产修改成功！资产号为：" +  ta.getTransformerAssetsNo());

			}else {//垃圾档案
				rmap.put("message", "变压器资产号重复！");
				return rmap;
			}
		}
		
		return rmap;	
	}	
	
	//变压器档案
	private HashMap<String, Object> addTransformerInfo(List<TransformerDomain> transformerInfoList) {
		HashMap<String,Object> rmap = new HashMap<String,Object>();
		rmap.put("result", false);
		rmap.put("message", "变压器档案新增开始！");

		TransformerDomain transformer = new TransformerDomain();
		for(TransformerDomain t : transformerInfoList) {
			transformer.setTransformerNo(t.getTransformerNo());
			List<TransformerDomain> ttList = transformerDAO.findByWhere(transformer);
			if(ttList.size()==0) {
				int count = transformerDAO.insert(t);
				if(count!=1) {
					
					rmap.put("message", "变压器档案新增失败！");
					return rmap;
				}
				rmap.put("result", true);
				rmap.put("message", "变压器档案新增成功！变压器编号为："+ t.getTransformerNo());

			}else if(ttList.size()==1) {
				t.setId(ttList.get(0).getId());
				int count = transformerDAO.update(t);
				if(count!=1) {

					rmap.put("message", "变压器档案修改失败！");
					return rmap;
				}
				rmap.put("result", true);
				rmap.put("message","变压器档案修改成功！变压器编号为："+ t.getTransformerNo());

			}else {

				rmap.put("message", "变压器编号重复！");
				return rmap;
			}
		}
		return rmap;
	}
	
	//变压器 计量点关系
	//添加客户档案
	public HashMap<String, Object> addTransformerRel(List<TransformerMeterRelationDomain> transformerMeterRels) {

		HashMap<String,Object> rmap = new HashMap<String,Object>();
		rmap.put("result", false);
		rmap.put("message", "变压器关系新增开始！");

		try {
			transformerMeterRels.forEach(t->{
				//拆除删掉关系
				if(Objects.equals(t.getLoadChangeSign(),1)){
					transformerMeterRelaDAO.delete(t);
				}else{
					//没有则插入 有则更新
					TransformerMeterRelationDomain transformerMeterRelationDomain
							=transformerMeterRelaDAO.findByKey(t);

					if(transformerMeterRelationDomain==null){
						transformerMeterRelaDAO.insert(t);
					}else{
						transformerMeterRelaDAO.update(t);
					}
				}

				rmap.put("result", true);
				rmap.put("message", "变压器关系更新完成！");
			});
		}catch(Exception e){
			rmap.put("result", false);
			rmap.put("message", "变压器关系更新失败！");
		}
		return rmap;
	}
	//变压器 用户关系
	//计量点
	
	
	
}
