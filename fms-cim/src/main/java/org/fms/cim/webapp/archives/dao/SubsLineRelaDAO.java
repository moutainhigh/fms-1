/**
 *    Auth:riozenc
 *    Date:2019年3月8日 下午3:28:46
 *    Title:com.riozenc.cim.webapp.dao.CustomerDAO.java
 **/
package org.fms.cim.webapp.archives.dao;

import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.fms.cim.webapp.archives.domain.SubsLineRelaDomain;

import com.riozenc.titanTool.annotation.PaginationSupport;
import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.spring.webapp.dao.AbstractTransactionDAOSupport;
import com.riozenc.titanTool.spring.webapp.dao.BaseDAO;

@TransactionDAO()
public class SubsLineRelaDAO extends AbstractTransactionDAOSupport implements BaseDAO<SubsLineRelaDomain> {

	@Override
	public int insert(SubsLineRelaDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().insert(getNamespace() + ".insert", t);
	}

	@Override
	public int delete(SubsLineRelaDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().delete(getNamespace() + ".delete", t);
	}

	@Override
	public int update(SubsLineRelaDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().update(getNamespace() + ".update", t);
	}

	@Override
	public SubsLineRelaDomain findByKey(SubsLineRelaDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().load(getNamespace() + ".findByKey", t);
	}

	@Override
	@PaginationSupport
	public List<SubsLineRelaDomain> findByWhere(SubsLineRelaDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().find(getNamespace() + ".findByWhere", t);
	}

	public List<SubsLineRelaDomain> findByLineId(SubsLineRelaDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().find(getNamespace() + ".findByLineId", t);
	}

	public int insertList(List<SubsLineRelaDomain> slrList) {
		return getPersistanceManager(ExecutorType.BATCH).insertList(getNamespace() + ".insert", slrList);
		
	}
	
	
}
