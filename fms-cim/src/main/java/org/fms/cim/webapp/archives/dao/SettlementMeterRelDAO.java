/**
 *    Auth:riozenc
 *    Date:2019年3月12日 下午7:06:38
 *    Title:com.riozenc.cim.webapp.dao.SettlementDAO.java
 **/
package org.fms.cim.webapp.archives.dao;

import com.riozenc.cim.webapp.archives.domain.SettlementDomain;
import com.riozenc.cim.webapp.archives.domain.SettlementMeterRelDomain;
import com.riozenc.titanTool.annotation.PaginationSupport;
import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.spring.webapp.dao.AbstractTransactionDAOSupport;
import com.riozenc.titanTool.spring.webapp.dao.BaseDAO;
import org.apache.ibatis.session.ExecutorType;

import java.util.List;

@TransactionDAO
public class SettlementMeterRelDAO extends AbstractTransactionDAOSupport implements BaseDAO<SettlementMeterRelDomain> {

	@Override
	public int insert(SettlementMeterRelDomain t) {
		return getPersistanceManager().insert(getNamespace() + ".insert", t);
	}

	@Override
	public int delete(SettlementMeterRelDomain t) {
		return getPersistanceManager().delete(getNamespace() + ".delete", t);
	}

	@Override
	public int update(SettlementMeterRelDomain t) {
		return getPersistanceManager().update(getNamespace() + ".update", t);
	}

	@Override
	public SettlementMeterRelDomain findByKey(SettlementMeterRelDomain t) {
		return getPersistanceManager().load(getNamespace() + ".findByKey", t);
	}

	@Override
	@PaginationSupport
	public List<SettlementMeterRelDomain> findByWhere(SettlementMeterRelDomain t) {
		return getPersistanceManager().find(getNamespace() + ".findByWhere", t);
	}

	public int insertList(List<SettlementMeterRelDomain> settlementList) {
		return getPersistanceManager(ExecutorType.BATCH).insertList(getNamespace() + ".insert", settlementList);

	}

	public List<Long> getSettlementByMeterIds(List<String> meterIds) {
		return getPersistanceManager().find(getNamespace() + ".getSettlementByMeterIds", meterIds);

	}

	public List<SettlementMeterRelDomain> findSettlementByMeterIds(List<Long> meterIds) {
		return getPersistanceManager().find(getNamespace() + ".findSettlementByMeterIds", meterIds);
	}

	public int updateList(List<SettlementMeterRelDomain> smruList) {
		return getPersistanceManager(ExecutorType.BATCH).updateList(getNamespace() + ".update", smruList);

	}

	public List<Long> getMeterIdsBySettlementId(Long settlementId) {
		return getPersistanceManager().find(getNamespace() + ".getMeterIdsBySettlementId", settlementId);
	}

	public List<SettlementMeterRelDomain> getMeterIdsBySettlementIds(List<Long> settlementIds) {
		return getPersistanceManager().find(getNamespace() + ".getMeterIdsBySettlementIds", settlementIds);
	}

	public List<Long> getMeterIdsBySettlementInfo(SettlementDomain settlementDomain) {
		return getPersistanceManager().find(getNamespace() + ".getMeterIdsBySettlementInfo", settlementDomain);
	}
}
