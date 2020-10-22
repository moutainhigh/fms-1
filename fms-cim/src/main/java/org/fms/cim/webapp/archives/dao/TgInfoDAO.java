package org.fms.cim.webapp.archives.dao;

import java.util.List;

import org.fms.cim.webapp.archives.domain.TgInfoDomain;

import com.riozenc.titanTool.annotation.PaginationSupport;
import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.spring.webapp.dao.AbstractTransactionDAOSupport;
import com.riozenc.titanTool.spring.webapp.dao.BaseDAO;

@TransactionDAO()
public class TgInfoDAO extends AbstractTransactionDAOSupport implements BaseDAO<TgInfoDomain> {
    @Override
    public int insert(TgInfoDomain t) {
        return getPersistanceManager().insert(getNamespace() + ".insert", t);
    }

    @Override
    public int delete(TgInfoDomain t) {
        return getPersistanceManager().delete(getNamespace() + ".delete", t);
    }

    @Override
    public int update(TgInfoDomain t) {
        return getPersistanceManager().update(getNamespace() + ".update", t);
    }

    @Override
    public TgInfoDomain findByKey(TgInfoDomain t) {
        return getPersistanceManager().load(getNamespace() + ".findByKey", t);
    }

    @Override
	@PaginationSupport
    public List<TgInfoDomain> findByWhere(TgInfoDomain t) {
        return getPersistanceManager().find(getNamespace() + ".findByWhere", t);
    }

    public List<TgInfoDomain> findByNo(TgInfoDomain t) {
        return getPersistanceManager().find(getNamespace() + ".findByNo", t);
    }
}
