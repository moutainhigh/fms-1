/**
 *    Auth:riozenc
 *    Date:2019年3月12日 下午7:16:23
 *    Title:com.riozenc.cim.webapp.service.impl.SettlementServiceImpl.java
 **/
package org.fms.cim.webapp.archives.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.riozenc.cim.webapp.archives.dao.MeterDAO;
import com.riozenc.cim.webapp.archives.domain.CustomerDomain;
import com.riozenc.cim.webapp.archives.domain.MeterDomain;
import com.riozenc.cim.webapp.archives.domain.SettlementDomain;
import com.riozenc.cim.webapp.archives.domain.UserDomain;
import com.riozenc.cim.webapp.archives.service.IBillService;
import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.annotation.TransactionService;

@TransactionService
public class BillServiceImpl implements IBillService {
	

	@TransactionDAO
	private MeterDAO meterDAO;
	
	@Override
	public List<MeterDomain> getMeters(JsonNode jsonNode) throws JsonParseException, JsonMappingException, IOException{

		//传入的数据的格式
//		{'chargeObject':{1,2,3},'no':{},'name':{}}  1:用电户 2客户  3结算户

		//第一步，数据解析  新方法
		String chargeObject = jsonNode.get("chargeObject").asText();//户 类型。
		String no = jsonNode.get("no").isNull()?null:jsonNode.get("no").asText();//户 类型。
		String name = jsonNode.get("name").isNull()?null:jsonNode.get("name").asText();//户 类型。
		String bankNo = jsonNode.get("bankNo").isNull()?null:
				jsonNode.get("bankNo").asText();
		Long businessPlaceCode = jsonNode.get("businessPlaceCode").isNull()?
				null:
				jsonNode.get("businessPlaceCode").asLong();
		Long chargeModeType = jsonNode.get("chargeModeType").isNull()?
				null:
				jsonNode.get("chargeModeType").asLong();
		List<MeterDomain> meterList = new ArrayList<MeterDomain>();

		switch (chargeObject) {
			case "1" : //用电户 
				UserDomain tempUser = new UserDomain();
				tempUser.setUserNo(no);
				tempUser.setUserName(name);
				tempUser.setPageSize(-1);   //分页控制
				meterList = meterDAO.getMetersByUser(tempUser);
				
				break;
			case "2" :	//客户
				CustomerDomain tempcons = new CustomerDomain();
				tempcons.setCustomerNo(no);
				tempcons.setCustomerName(name);
				tempcons.setPageSize(-1);
				meterList = meterDAO.getMetersByCustomer(tempcons);

				break;
			case "3" :	//结算户
				SettlementDomain tempSettlement = new SettlementDomain();
				tempSettlement.setSettlementName(name);
				tempSettlement.setSettlementNo(no);
				tempSettlement.setBankNo(bankNo);
				tempSettlement.setBusinessPlaceCode(businessPlaceCode);
				tempSettlement.setChargeModeType(chargeModeType==null?null:chargeModeType.byteValue());
				tempSettlement.setPageSize(-1);
				meterList = meterDAO.getMetersBySettlement(tempSettlement);
				
				break;
			default:
			
				return meterList;
		
		}
		
	
		
		
		
		return meterList;
		
	}

	@Override
	public List<MeterDomain> getMetersBySettlement(JsonNode jsonNode) throws JsonParseException, JsonMappingException, IOException{

		//传入的数据的格式
//		{'chargeObject':{1,2,3},'no':{},'name':{}}  1:用电户 2客户  3结算户

		//第一步，数据解析  新方法
		String chargeObject = jsonNode.get("chargeObject").asText();//户 类型。
		String no = jsonNode.get("no").isNull()?null:jsonNode.get("no").asText();//户 类型。
		String name = jsonNode.get("name").isNull()?null:jsonNode.get("name").asText();//户 类型。
		String bankNo = jsonNode.get("bankNo").isNull()?null:
				jsonNode.get("bankNo").asText();
		Long businessPlaceCode = jsonNode.get("businessPlaceCode").isNull()?
				null:
				jsonNode.get("businessPlaceCode").asLong();
		Long chargeModeType = jsonNode.get("chargeModeType").isNull()?
				null:
				jsonNode.get("chargeModeType").asLong();
		List<MeterDomain> meterList = new ArrayList<MeterDomain>();

		switch (chargeObject) {
			case "1" : //用电户
				UserDomain tempUser = new UserDomain();
				tempUser.setUserNo(no);
				tempUser.setUserName(name);
				tempUser.setPageSize(-1);   //分页控制
				meterList = meterDAO.getMetersByUser(tempUser);

				break;
			case "2" :	//客户
				CustomerDomain tempcons = new CustomerDomain();
				tempcons.setCustomerNo(no);
				tempcons.setCustomerName(name);
				tempcons.setPageSize(-1);
				meterList = meterDAO.getMetersByCustomer(tempcons);

				break;
			case "3" :	//结算户
				SettlementDomain tempSettlement = new SettlementDomain();
				tempSettlement.setSettlementName(name);
				tempSettlement.setSettlementNo(no);
				tempSettlement.setBankNo(bankNo);
				tempSettlement.setBusinessPlaceCode(businessPlaceCode);
				tempSettlement.setChargeModeType(chargeModeType==null?null:chargeModeType.byteValue());
				tempSettlement.setPageSize(-1);
				meterList = meterDAO.getMetersBySettlement(tempSettlement);

				break;
			default:

				return meterList;

		}





		return meterList;

	}
	
	
}
