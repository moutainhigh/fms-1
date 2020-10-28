/**
 *    Auth:riozenc
 *    Date:2019年3月12日 下午7:16:23
 *    Title:com.riozenc.cim.webapp.service.impl.SettlementServiceImpl.java
 **/
package org.fms.cim.webapp.archives.service.impl;

import java.util.List;

import org.fms.cim.webapp.archives.dao.LineDAO;
import org.fms.cim.webapp.archives.dao.PMpedDAO;
import org.fms.cim.webapp.archives.dao.SubsLineRelaDAO;
import org.fms.cim.webapp.archives.domain.LineDomain;
import org.fms.cim.webapp.archives.domain.PMpedDomain;
import org.fms.cim.webapp.archives.domain.SubsLineRelaDomain;
import org.fms.cim.webapp.archives.service.ILineService;
import org.fms.cim.webapp.archives.service.IPmpedService;

import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.annotation.TransactionService;

@TransactionService
public class PmpedServiceImpl implements IPmpedService {

	@TransactionDAO
	private PMpedDAO pmpedDAO;

	@Override
	public int insert(PMpedDomain t) {
		// TODO Auto-generated method stub
		return pmpedDAO.insert(t);
	}

	@Override
	public int delete(PMpedDomain t) {
		// TODO Auto-generated method stub
		return pmpedDAO.delete(t);
	}

	@Override
	public int update(PMpedDomain t) {
		// TODO Auto-generated method stub
		return pmpedDAO.update(t);
	}

	@Override
	public PMpedDomain findByKey(PMpedDomain t) {
		// TODO Auto-generated method stub
		return pmpedDAO.findByKey(t);
	}

	@Override
	public List<PMpedDomain> findByWhere(PMpedDomain t) {
		// TODO Auto-generated method stub
		return pmpedDAO.findByWhere(t);
	}
	
}
