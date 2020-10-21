/**
 *    Auth:riozenc
 *    Date:2019年3月12日 下午7:16:23
 *    Title:com.riozenc.cim.webapp.service.impl.SettlementServiceImpl.java
 **/
package org.fms.cim.webapp.archives.service.impl;

import java.util.Date;
import java.util.List;

import org.fms.cim.webapp.archives.dao.SubsLineRelaDAO;
import org.fms.cim.webapp.archives.domain.SubsLineRelaDomain;
import org.fms.cim.webapp.archives.service.ISubsLineRelaService;

import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.annotation.TransactionService;

@TransactionService
public class SubsLineRelaServiceImpl implements ISubsLineRelaService {
	
	@TransactionDAO
	private SubsLineRelaDAO subsLineRelaDAO;

	Date now = new Date();

	@Override
	public int insert(SubsLineRelaDomain t) {
		// TODO Auto-generated method stub
		return subsLineRelaDAO.insert(t);
	}

	@Override
	public int delete(SubsLineRelaDomain t) {
		// TODO Auto-generated method stub
		return subsLineRelaDAO.delete(t);
	}

	@Override
	public int update(SubsLineRelaDomain t) {
		// TODO Auto-generated method stub
		return subsLineRelaDAO.update(t);
	}

	@Override
	public SubsLineRelaDomain findByKey(SubsLineRelaDomain t) {
		// TODO Auto-generated method stub
		return subsLineRelaDAO.findByKey(t);
	}

	@Override
	public List<SubsLineRelaDomain> findByWhere(SubsLineRelaDomain t) {
		// TODO Auto-generated method stub
		return subsLineRelaDAO.findByWhere(t);
	}
	
}
