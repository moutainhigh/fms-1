package org.fms.cim.webapp.archives.service.impl;

import java.util.List;

import org.fms.cim.webapp.archives.dao.ABusDAO;
import org.fms.cim.webapp.archives.domain.ABusDomain;
import org.fms.cim.webapp.archives.service.IAbusService;
import org.springframework.stereotype.Service;

import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.annotation.TransactionService;

@TransactionService
public class AbusServiceImpl implements IAbusService {
	
	@TransactionDAO
	private ABusDAO abusDAO;

	@Override
	public int insert(ABusDomain t) {
		// TODO Auto-generated method stub
		return abusDAO.insert(t);
	}

	@Override
	public int delete(ABusDomain t) {
		// TODO Auto-generated method stub
		return abusDAO.delete(t);
	}

	@Override
	public int update(ABusDomain t) {
		// TODO Auto-generated method stub
		return abusDAO.update(t);
	}

	@Override
	public ABusDomain findByKey(ABusDomain t) {
		// TODO Auto-generated method stub
		return abusDAO.findByKey(t);
	}

	@Override
	public List<ABusDomain> findByWhere(ABusDomain t) {
		// TODO Auto-generated method stub
		return abusDAO.findByWhere(t);
	}

}
