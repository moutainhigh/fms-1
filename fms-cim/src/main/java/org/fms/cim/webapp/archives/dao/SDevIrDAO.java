package org.fms.cim.webapp.archives.dao;

import java.util.List;

import org.fms.cim.webapp.archives.domain.ABusDomain;
import org.fms.cim.webapp.archives.domain.SDevIrDomain;

import com.riozenc.titanTool.annotation.PaginationSupport;
import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.spring.webapp.dao.AbstractTransactionDAOSupport;
import com.riozenc.titanTool.spring.webapp.dao.BaseDAO;

@TransactionDAO("read")
public class SDevIrDAO extends AbstractTransactionDAOSupport implements BaseDAO<SDevIrDomain>{

	@Override
	public int insert(SDevIrDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().insert(getNamespace() + ".insert", t);
	}

	@Override
	public int delete(SDevIrDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().delete(getNamespace() + ".delete", t);
	}

	@Override
	public int update(SDevIrDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().update(getNamespace() + ".update", t);
	}

	@Override
	public SDevIrDomain findByKey(SDevIrDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().load(getNamespace() + ".findByKey", t);
	}

	@Override
	@PaginationSupport
	public List<SDevIrDomain> findByWhere(SDevIrDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().find(getNamespace() + ".findByWhere", t);
	}


}
