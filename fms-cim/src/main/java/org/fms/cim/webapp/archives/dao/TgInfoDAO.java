package org.fms.cim.webapp.archives.dao;

import com.riozenc.cim.api.annotation.SynchronizeTrigger;
import com.riozenc.cim.webapp.archives.domain.TgInfoDomain;
import com.riozenc.titanTool.annotation.PaginationSupport;
import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.spring.webapp.dao.AbstractTransactionDAOSupport;
import com.riozenc.titanTool.spring.webapp.dao.BaseDAO;

import java.util.List;

@TransactionDAO()
public class TgInfoDAO extends AbstractTransactionDAOSupport implements BaseDAO<TgInfoDomain> {
    @Override
	@SynchronizeTrigger
    public int insert(TgInfoDomain t) {
        return getPersistanceManager().insert(getNamespace() + ".insert", t);
    }

    @Override
    public int delete(TgInfoDomain t) {
        return getPersistanceManager().delete(getNamespace() + ".delete", t);
    }

    @Override
	@SynchronizeTrigger
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
