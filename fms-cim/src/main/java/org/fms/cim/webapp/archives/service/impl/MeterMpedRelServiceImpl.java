/**
 *    Auth:riozenc
 *    Date:2019年3月12日 下午7:16:23
 *    Title:com.riozenc.cim.webapp.service.impl.SettlementServiceImpl.java
 **/
package org.fms.cim.webapp.archives.service.impl;

import java.util.List;

import org.fms.cim.webapp.archives.dao.BankDAO;
import org.fms.cim.webapp.archives.dao.MeterMpedRelDAO;
import org.fms.cim.webapp.archives.domain.BankDomain;
import org.fms.cim.webapp.archives.domain.MeterMpedRelDomain;
import org.fms.cim.webapp.archives.service.IBankService;
import org.fms.cim.webapp.archives.service.IMeterMpedRelService;

import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.annotation.TransactionService;

@TransactionService
public class MeterMpedRelServiceImpl implements IMeterMpedRelService {

	@TransactionDAO
	private MeterMpedRelDAO MeterMpedRelDAO;

	@Override
	public int insert(MeterMpedRelDomain t) {
		return MeterMpedRelDAO.insert(t);
	}

	@Override
	public int delete(MeterMpedRelDomain t) {
		return MeterMpedRelDAO.delete(t);
	}

	@Override
	public int update(MeterMpedRelDomain t) {
		return MeterMpedRelDAO.update(t);
	}

	@Override
	public MeterMpedRelDomain findByKey(MeterMpedRelDomain t) {
		return MeterMpedRelDAO.findByKey(t);
	}

	@Override
	public List<MeterMpedRelDomain> findByWhere(MeterMpedRelDomain t) {
		return MeterMpedRelDAO.findByWhere(t);
	}

}
