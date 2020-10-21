/**
 *    Auth:riozenc
 *    Date:2019年3月12日 下午7:16:23
 *    Title:com.riozenc.cim.webapp.service.impl.SettlementServiceImpl.java
 **/
package org.fms.cim.webapp.archives.service.impl;

import java.util.List;

import org.fms.cim.webapp.archives.dao.MeterDAO;
import org.fms.cim.webapp.archives.dao.SettlementMeterRelDAO;
import org.fms.cim.webapp.archives.domain.SettlementDomain;
import org.fms.cim.webapp.archives.domain.SettlementMeterRelDomain;
import org.fms.cim.webapp.archives.service.ISettlementMeterRelService;

import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.annotation.TransactionService;

@TransactionService
public class SettlementMeterRelServiceImpl implements ISettlementMeterRelService {

	@TransactionDAO
	private SettlementMeterRelDAO settlementMeterRelDAO;
	@TransactionDAO
	private MeterDAO meterDAO;
	

	@Override
	public int insert(SettlementMeterRelDomain t) {
		return settlementMeterRelDAO.insert(t);
	}

	@Override
	public int delete(SettlementMeterRelDomain t) {
		return settlementMeterRelDAO.delete(t);
	}

	@Override
	public int update(SettlementMeterRelDomain t) {
		return settlementMeterRelDAO.update(t);
	}

	@Override
	public SettlementMeterRelDomain findByKey(SettlementMeterRelDomain t) {
		return settlementMeterRelDAO.findByKey(t);
	}

	@Override
	public List<SettlementMeterRelDomain> findByWhere(SettlementMeterRelDomain t) {
		return settlementMeterRelDAO.findByWhere(t);
	}


	@Override
	public List<Long> getSettlementByMeterIds(List<String> meterIds) {
		return settlementMeterRelDAO.getSettlementByMeterIds(meterIds);

	}

	@Override
	public List<SettlementMeterRelDomain> findSettlementByMeterIds(List<Long> meterIds) {
		return settlementMeterRelDAO.findSettlementByMeterIds(meterIds);
	}

	@Override
	public int updateList(List<SettlementMeterRelDomain> smruList) {
		return settlementMeterRelDAO.updateList(smruList);

	}

	@Override
	public List<Long> getMeterIdsBySettlementId(Long settlementId) {
		return settlementMeterRelDAO.getMeterIdsBySettlementId(settlementId);
	}

	@Override
	public List<SettlementMeterRelDomain> getMeterIdsBySettlementIds(List<Long> settlementIds) {
		return settlementMeterRelDAO.getMeterIdsBySettlementIds(settlementIds);
	}

	@Override
	public List<Long> getMeterIdsBySettlementInfo(SettlementDomain settlementDomain) {
		return settlementMeterRelDAO.getMeterIdsBySettlementInfo(settlementDomain);
	}

}
