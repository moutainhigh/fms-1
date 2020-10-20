/**
 *    Auth:riozenc
 *    Date:2019年3月12日 下午7:11:34
 *    Title:com.riozenc.cim.webapp.dao.UserDAO.java
 **/
package org.fms.cim.webapp.archives.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.ExecutorType;

import com.riozenc.cim.webapp.archives.domain.UserDomain;
import com.riozenc.cim.webapp.archives.domain.WriteSectDomain;
import com.riozenc.titanTool.annotation.PaginationSupport;
import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.spring.webapp.dao.AbstractTransactionDAOSupport;
import com.riozenc.titanTool.spring.webapp.dao.BaseDAO;

@TransactionDAO
public class WriteSectDAO extends AbstractTransactionDAOSupport implements BaseDAO<WriteSectDomain> {

	@Override
	public int insert(WriteSectDomain t) {
		return getPersistanceManager().insert(getNamespace() + ".insert", t);
	}

	@Override
	public int delete(WriteSectDomain t) {
		return getPersistanceManager().delete(getNamespace() + ".delete", t);
	}

	@Override
	public int update(WriteSectDomain t) {
		return getPersistanceManager().update(getNamespace() + ".update", t);
	}

	@Override
	public WriteSectDomain findByKey(WriteSectDomain t) {
		return getPersistanceManager().load(getNamespace() + ".findByKey", t);
	}

	@Override
	@PaginationSupport
	public List<WriteSectDomain> findByWhere(WriteSectDomain t) {
		return getPersistanceManager().find(getNamespace() + ".findByWhere", t);
	}

	public List<Long> getDeptIdsByWriteSectIds(List<Long> idsList) {
		if(idsList==null||idsList.size()==0) {
			return new ArrayList<Long>();
		}
		return getPersistanceManager().find(getNamespace() + ".getDeptIdsByWriteSectIds", idsList);
	
	}

	public int insertList(List<WriteSectDomain> wsList) {
		return getPersistanceManager(ExecutorType.BATCH).insertList(getNamespace() + ".insert", wsList);
	}

}
