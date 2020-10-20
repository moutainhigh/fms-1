/**
 *    Auth:riozenc
 *    Date:2019年3月12日 下午7:16:23
 *    Title:com.riozenc.cim.webapp.service.impl.SettlementServiceImpl.java
 **/
package org.fms.cim.webapp.archives.service.impl;

import java.util.List;

import com.riozenc.cim.webapp.archives.dao.SfPowerBankDAO;
import com.riozenc.cim.webapp.archives.domain.SfPowerBankDomain;
import com.riozenc.cim.webapp.archives.service.ISfPowerBankService;
import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.annotation.TransactionService;

@TransactionService
public class SfPowerBankServiceImpl implements ISfPowerBankService {

	@TransactionDAO
	private SfPowerBankDAO sfPowerBankDAO;

	@Override
	public int insert(SfPowerBankDomain t) {
		return sfPowerBankDAO.insert(t);
	}

	@Override
	public int delete(SfPowerBankDomain t) {
		return sfPowerBankDAO.delete(t);
	}

	@Override
	public int update(SfPowerBankDomain t) {
		return sfPowerBankDAO.update(t);
	}

	@Override
	public SfPowerBankDomain findByKey(SfPowerBankDomain t) {
		return sfPowerBankDAO.findByKey(t);
	}

	@Override
	public List<SfPowerBankDomain> findByWhere(SfPowerBankDomain t) {
		return sfPowerBankDAO.findByWhere(t);
	}

}
