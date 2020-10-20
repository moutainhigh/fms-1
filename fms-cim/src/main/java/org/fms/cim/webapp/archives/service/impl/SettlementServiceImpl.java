/**
 *    Auth:riozenc
 *    Date:2019年3月12日 下午7:16:23
 *    Title:com.riozenc.cim.webapp.service.impl.SettlementServiceImpl.java
 **/
package org.fms.cim.webapp.archives.service.impl;

import com.riozenc.cim.webapp.archives.dao.MeterDAO;
import com.riozenc.cim.webapp.archives.dao.SettlementDAO;
import com.riozenc.cim.webapp.archives.domain.CustomerDomain;
import com.riozenc.cim.webapp.archives.domain.MeterDomain;
import com.riozenc.cim.webapp.archives.domain.SettlementDomain;
import com.riozenc.cim.webapp.archives.domain.SettlementMeterRelDomain;
import com.riozenc.cim.webapp.archives.service.ISettlementService;
import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.annotation.TransactionService;

import java.util.ArrayList;
import java.util.List;

@TransactionService
public class SettlementServiceImpl implements ISettlementService {

	@TransactionDAO
	private SettlementDAO settlementDAO;
	@TransactionDAO
	private MeterDAO meterDAO;
	

	@Override
	public int insert(SettlementDomain t) {
		return settlementDAO.insert(t);
	}

	@Override
	public int delete(SettlementDomain t) {
		return settlementDAO.delete(t);
	}

	@Override
	public int update(SettlementDomain t) {
		return settlementDAO.update(t);
	}

	@Override
	public SettlementDomain findByKey(SettlementDomain t) {
		return settlementDAO.findByKey(t);
	}

	@Override
	public List<SettlementDomain> findByWhere(SettlementDomain t) {
		return settlementDAO.findByWhere(t);
	}

	@Override
	public List<SettlementDomain> findByNo(SettlementDomain t) {
		return settlementDAO.findByNo(t);
	}

	@Override
	public int addSettlementMeterRelBySettlement(List<SettlementMeterRelDomain> list) {
		return settlementDAO.addSettlementMeterRelBySettlement(list);
	}

	@Override
	public int deleteSettlementMeterRelBySettlement(List<SettlementMeterRelDomain> list) {
		return settlementDAO.deleteSettlementMeterRelBySettlement(list);
	}

	@Override
	public List<SettlementMeterRelDomain> getSettlementMeterRel(SettlementDomain settlementDomain) {
		return settlementDAO.getSettlementMeterRel(settlementDomain);
	}

	@Override
	public List<MeterDomain> getNoSettlementMeter(SettlementMeterRelDomain t) {
		//根据客户ID获取用户ids，根据用户获取计量点		
		if(t.getCustomerId()==null) {
			return null;
		}
		CustomerDomain customer = new CustomerDomain();
		customer.setPageSize(-1);
		customer.setId(t.getCustomerId());
		List<MeterDomain> meterList = meterDAO.getMetersByCustomer(customer);
		//根据结算户ID和获取该结算户已关联的计量点ID
		SettlementDomain settlement = new SettlementDomain();
		settlement.setId(t.getSettlementId());
		List<SettlementMeterRelDomain> smrList = settlementDAO.getSettlementMeterRel(settlement);
		//将已关联的计量点剔除
		if(meterList.size()==0) {
			return null;
		}
		for(SettlementMeterRelDomain smr : smrList) {
			for(int i=0;i<meterList.size();i++) {
				MeterDomain m = meterList.get(i);
				if(m.getId()-smr.getMeterId()==0) {
					meterList.remove(m);
					i--;
				}
			}
		}
		return meterList;

	//	return settlementDAO.getNoSettlementMeter(t);
	}

	@Override
	public int addSettlementMeterRel(SettlementMeterRelDomain smr) {
		return settlementDAO.addSettlementMeterRel(smr);

	}

	@Override
	public int insertList(List<SettlementDomain> settlementList) {
		return settlementDAO.insertList(settlementList);

	}

	@Override
	public List<SettlementMeterRelDomain> getSettlementMeterRelByMeterIds(List<Long> lc) {
		
		List<SettlementMeterRelDomain> rList = new ArrayList<SettlementMeterRelDomain>();

		// 处理超过1000个id
		int len = lc.size();
		for (int m = 0; m < len / 999 + 1; m++) {// 遍历次数

			List<Long> tl = lc.subList(m * 999, (m + 1) * 999 > len ? len : (m + 1) * 999);

			List<SettlementMeterRelDomain> tList = settlementDAO.getSettlementMeterRelByMeterIds(lc);

			rList.addAll(tList);

		}

		return rList;

	}

	@Override
	public List<SettlementDomain> findBankSettlement(SettlementDomain smr) {
		return settlementDAO.findBankSettlement(smr);
	}
	@Override
	public List<SettlementDomain> findSettlementByIds(List<Long> ids){
		return settlementDAO.findSettlementByIds(ids);
	}
	@Override
	public List<SettlementDomain> findBySettlementNos(SettlementDomain t){
		return settlementDAO.findBySettlementNos(t);
	}

	@Override
	public List<SettlementDomain> findSettlementByBusinessPlaceCodeAndInvoiceType(SettlementDomain settlementDomain) {
		return settlementDAO.findByBusinessPlaceCodeAndInvoiceType(settlementDomain);
	}
	@Override
	public List<Long> findSettlementIdByWhere(SettlementDomain t) {
		return settlementDAO.findSettlementIdByWhere(t);
	}

	@Override
	public List<SettlementDomain> getSettlementbyMeterIds(List<Long> settlementIdList) {
		return settlementDAO.getSettlementbyMeterIds(settlementIdList);

	}
	@Override
	public List<SettlementDomain> findClearSettlementByWhere(SettlementDomain t) {
		return settlementDAO.findClearSettlementByWhere(t);
	}

}
