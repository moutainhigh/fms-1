/**
 *    Auth:riozenc
 *    Date:2019年3月12日 下午7:16:23
 *    Title:com.riozenc.cim.webapp.service.impl.SettlementServiceImpl.java
 **/
package org.fms.cim.webapp.archives.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.fms.cim.webapp.archives.dao.MeterDAO;
import org.fms.cim.webapp.archives.dao.MeterMeterAssetsRelDAO;
import org.fms.cim.webapp.archives.dao.MeterReplaceInfoDAO;
import org.fms.cim.webapp.archives.domain.MeterDomain;
import org.fms.cim.webapp.archives.domain.MeterMeterAssetsRelDomain;
import org.fms.cim.webapp.archives.domain.MeterReplaceDomain;
import org.fms.cim.webapp.archives.domain.UserDomain;
import org.fms.cim.webapp.archives.service.IExcelImportService;
import org.fms.cim.webapp.assets.dao.MeterAssetsDAO;
import org.fms.cim.webapp.assets.domain.MeterAssetsDomain;

import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.annotation.TransactionService;
import com.riozenc.titanTool.spring.web.http.HttpResult;

@TransactionService
public class ExcelImportServiceImpl implements IExcelImportService {

	@TransactionDAO
	private MeterAssetsDAO meterAssetsDAO;
	@TransactionDAO
	private MeterDAO meterDAO;
	@TransactionDAO
	private MeterMeterAssetsRelDAO meterMeterAssetsDAO;
	@TransactionDAO
	private MeterReplaceInfoDAO meterReplaceInfoDAO;
	
	
	@Override
	public HttpResult changeDevForExcel(List<MeterAssetsDomain> meterAssetsDomains) throws Exception {

		/**
		 * 1.新增资产
		 * 2.生成计量点与电能表关系
		 * 3.生成换表记录*2
		 * 4.换表电量
		 * 
		 * 不支持无功表
		 * 不支持一户多表
		 * 不支持三合一表
		 * */
	//	int i = meterAssetsDAO.insertList(meterAssetsDomains);
		
		//户号-表计资产号
        HashMap<String,String> userNo_meterAssetsNo = new HashMap<String,String>();
		for(MeterAssetsDomain tt : meterAssetsDomains) {
		   	if(userNo_meterAssetsNo.putIfAbsent(tt.getUserNo(), tt.getMeterAssetsNo())!=null) {
        		return new HttpResult<>(HttpResult.ERROR,"该用户下存在多个资产"+tt.getUserNo());
        	}
		}
		
		//资产号-资产ID
        List<MeterAssetsDomain> newMeterAssetsList = meterAssetsDAO.getMeterAssetsByNos(meterAssetsDomains);

        HashMap<String,Long> meterAssetsNo_meterAssetsId = new HashMap<String,Long>();
        for(MeterAssetsDomain tt : newMeterAssetsList) {
        	if(meterAssetsNo_meterAssetsId.putIfAbsent(tt.getMeterAssetsNo(), tt.getId())!=null) {
        		return new HttpResult<>(HttpResult.ERROR,"资产号重复"+tt.getMeterAssetsNo());
        	}
        }
        

        //户号-计量点ID
        List<UserDomain> userList = new ArrayList<UserDomain>();
        for(MeterAssetsDomain tt: meterAssetsDomains) {
        	UserDomain userDomain = new UserDomain();
        	userDomain.setUserNo(tt.getUserNo());
        	userList.add(userDomain);
        }
        List<MeterDomain> meterList = meterDAO.getMeterByUserNos(userList);
        List<Long> meterIds = new ArrayList<>();
        HashMap<String,Long> userNo_mpedId = new HashMap<String,Long>();
        for(MeterDomain tt : meterList) {
        	if(userNo_mpedId.putIfAbsent(tt.getUserNo(), tt.getId())!=null) {
        		return new HttpResult<>(HttpResult.ERROR,"该用户下存在多个计量点"+tt.getUserNo());
        	}
        	meterIds.add(tt.getId());
        }
        if(meterIds!=null && meterIds.size()>1000) {
    		return new HttpResult<>(HttpResult.ERROR,"删除数量超过1000！！！！");
        }
        
        //TODO 拆表操作，生成换表电量
        MeterDomain meter = new MeterDomain();
        meter.setIdList(meterIds.stream().map(String::valueOf).collect(Collectors.joining(",")));
        int oldmmarl = meterMeterAssetsDAO.deleteByMeterIds(meter);
        if(oldmmarl>1000) {
        	throw new Exception("删除数量超过1000！！！！");
        }
        
        
       //装表
        List<MeterMeterAssetsRelDomain> mmarl = new ArrayList<MeterMeterAssetsRelDomain>();
        List<MeterReplaceDomain> mrl = new ArrayList<MeterReplaceDomain>();
        Date now = new Date();
        for(MeterAssetsDomain tt : meterAssetsDomains) {

        	Long meterId = userNo_mpedId.get(tt.getUserNo());
        	Long meterAssetsId = meterAssetsNo_meterAssetsId.get(tt.getMeterAssetsNo());

        	MeterMeterAssetsRelDomain mmar = new MeterMeterAssetsRelDomain();
        	MeterReplaceDomain mr = new MeterReplaceDomain();
        	mmar.setMeterId(meterId);
        	mmar.setMeterAssetsId(meterAssetsId);
        	mmar.setPhaseSeq((byte) 4);
        	mmar.setFunctionCode((long) 1);
        	mmar.setPowerDirection((byte) 1);
        	mmar.setMeterOrder((byte) 1);
        	mmar.setTsFlag((byte) 0);
        	mmar.setCreateDate(now);
        	mmar.setStatus((byte) 1);
        	mmar.setMeterSn(9);
        	
        	mr.setMeterId(meterId);
        	mr.setMeterAssetsId(meterAssetsId);
        	mr.setReplaceDate(now);
        	mr.setReason("Excel批量导入");
        	mr.setEquipmentType((byte) 1);
        	mr.setOperateType((byte) 1);
        	mr.setCreateDate(now);
        	mr.setPhaseSeq((byte) 4);
        	mr.setFunctionCode((long) 1);
        	mr.setPowerDirection((byte) 1);
        	mr.setStatus((byte) 1);
        	
        	mmarl.add(mmar);
        	mrl.add(mr);
        	
        }
        //TODO 装拆表记录入库
        int mmarNum = meterMeterAssetsDAO.insertList(mmarl);
        int mrNum = meterReplaceInfoDAO.insertList(mrl);
        
        System.out.println("数据插入完成");
      
		return null;
	}



}
