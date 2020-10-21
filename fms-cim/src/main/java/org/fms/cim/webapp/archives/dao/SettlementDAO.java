/**
 *    Auth:riozenc
 *    Date:2019年3月12日 下午7:06:38
 *    Title:com.riozenc.cim.webapp.dao.SettlementDAO.java
 **/
package org.fms.cim.webapp.archives.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.fms.cim.webapp.archives.domain.MeterDomain;
import org.fms.cim.webapp.archives.domain.SettlementDomain;
import org.fms.cim.webapp.archives.domain.SettlementMeterRelDomain;

import com.riozenc.titanTool.annotation.PaginationSupport;
import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.spring.webapp.dao.AbstractTransactionDAOSupport;
import com.riozenc.titanTool.spring.webapp.dao.BaseDAO;

@TransactionDAO
public class SettlementDAO extends AbstractTransactionDAOSupport implements BaseDAO<SettlementDomain> {

	@Override
	public int insert(SettlementDomain t) {
		return getPersistanceManager().insert(getNamespace() + ".insert", t);
	}

	@Override
	public int delete(SettlementDomain t) {
		return getPersistanceManager().delete(getNamespace() + ".delete", t);
	}

	@Override
	public int update(SettlementDomain t) {
		return getPersistanceManager().update(getNamespace() + ".update", t);
	}

	@Override
	public SettlementDomain findByKey(SettlementDomain t) {
		return getPersistanceManager().load(getNamespace() + ".findByKey", t);
	}

	@Override
	@PaginationSupport
	public List<SettlementDomain> findByWhere(SettlementDomain t) {
		return getPersistanceManager().find(getNamespace() + ".findByWhere", t);
	}

	public List<SettlementDomain> findByBusinessPlaceCodeAndInvoiceType(SettlementDomain settlementDomain) {
		return getPersistanceManager().find(getNamespace() + ".findByBusinessPlaceCodeAndInvoiceType",
				settlementDomain);
	}

	public int addSettlementMeterRelBySettlement(List<SettlementMeterRelDomain> list) {

		return getPersistanceManager(ExecutorType.BATCH).insertList(getNamespace() + ".addSettlementMeterRelBySettlement", list);
	//	return getPersistanceManager().insert(getNamespace() + ".addSettlementMeterRelBySettlement", list);
	}

	public int deleteSettlementMeterRelBySettlement(List<SettlementMeterRelDomain> list) {
		return getPersistanceManager().delete(getNamespace() + ".deleteSettlementMeterRelBySettlement", list);

	}

	public List<SettlementMeterRelDomain> getSettlementMeterRel(SettlementDomain settlementDomain) {
		return getPersistanceManager().find(getNamespace() + ".getSettlementMeterRel", settlementDomain);
	}

	@PaginationSupport
	public List<MeterDomain> getNoSettlementMeter(SettlementMeterRelDomain t) {
		return getPersistanceManager().find(getNamespace() + ".getNoSettlementMeter", t);
	}

    public List<SettlementDomain> findByNo(SettlementDomain t) {
		return getPersistanceManager().find(getNamespace() + ".findByNo", t);
    }

	public List<SettlementDomain> getSettlementbyMeter(SettlementMeterRelDomain smr) {
		return getPersistanceManager().find(getNamespace() + ".getSettlementbyMeter", smr);
	}

	public int addSettlementMeterRel(SettlementMeterRelDomain smr) {
		return getPersistanceManager().insert(getNamespace() + ".addSettlementMeterRel", smr);
	}

	public int insertList(List<SettlementDomain> settlementList) {
		return getPersistanceManager(ExecutorType.BATCH).insertList(getNamespace() + ".insert", settlementList);

	}

	public List<SettlementMeterRelDomain> getSettlementMeterRelByMeterIds(List<Long> lc) {
		return getPersistanceManager().find(getNamespace() + ".getSettlementMeterRelByMeterIds", lc);
	}

	public List<SettlementDomain> findBankSettlement(SettlementDomain t) {
		return getPersistanceManager().find(getNamespace() + ".findBankSettlement", t);
	}

	public List<SettlementDomain> findSettlementByIds(List<Long> ids) {
		if(ids==null||ids.size()==0) {
			return new ArrayList<SettlementDomain>();
		}
		return getPersistanceManager().find(getNamespace() + ".findSettlementByIds", ids);
	}

	public List<SettlementDomain> findBySettlementNos(SettlementDomain t) {
		return getPersistanceManager().find(getNamespace() + ".findBySettlementNos", t);
	}

	public List<SettlementDomain> getSettlementbyMeterIds(List<Long> list) {
		return getPersistanceManager().find(getNamespace() + ".getSettlementbyMeterIds", list);
	}
	
	@PaginationSupport
	public List<Long> findSettlementIdByWhere(SettlementDomain t) {
		return getPersistanceManager().find(getNamespace() + ".findSettlementIdByWhere", t);
	}

	@PaginationSupport
	public List<SettlementDomain> findClearSettlementByWhere(SettlementDomain t) {
		return getPersistanceManager().find(getNamespace() + ".findClearSettlementByWhere", t);
	}
}
