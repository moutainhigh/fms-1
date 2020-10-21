/**
 *    Auth:riozenc
 *    Date:2019年3月8日 下午3:28:46
 *    Title:com.riozenc.cim.webapp.dao.CustomerDAO.java
 **/
package org.fms.cim.webapp.archives.dao;

import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.fms.cim.webapp.archives.domain.LineDomain;

import com.riozenc.cim.api.annotation.SynchronizeTrigger;
import com.riozenc.titanTool.annotation.PaginationSupport;
import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.spring.webapp.dao.AbstractTransactionDAOSupport;
import com.riozenc.titanTool.spring.webapp.dao.BaseDAO;

@TransactionDAO()
public class LineDAO extends AbstractTransactionDAOSupport implements BaseDAO<LineDomain> {

	@Override
	@SynchronizeTrigger
	public int insert(LineDomain t) {
		return getPersistanceManager().insert(getNamespace() + ".insert", t);
	}

	@Override
	public int delete(LineDomain t) {
		return getPersistanceManager().delete(getNamespace() + ".delete", t);
	}

	@Override
	@SynchronizeTrigger
	public int update(LineDomain t) {
		return getPersistanceManager().update(getNamespace() + ".update", t);
	}

	@Override
	public LineDomain findByKey(LineDomain t) {
		return getPersistanceManager().load(getNamespace() + ".findByKey", t);
	}

	@Override
	@PaginationSupport
	public List<LineDomain> findByWhere(LineDomain t) {
		return getPersistanceManager().find(getNamespace() + ".findByWhere", t);
	}

    public List<LineDomain> findByLineCode(LineDomain t) {
		return getPersistanceManager().find(getNamespace() + ".findByLineCode", t);
    }

	public int insertList(List<LineDomain> lineList) {
		return getPersistanceManager(ExecutorType.BATCH).insertList(getNamespace() + ".insert", lineList);
	}

	public List<LineDomain> findByLineIds(LineDomain t) {
		return getPersistanceManager().find(getNamespace() + ".findByLineIds", t);
	}

}
