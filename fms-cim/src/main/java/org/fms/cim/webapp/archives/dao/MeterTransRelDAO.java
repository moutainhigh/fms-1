package org.fms.cim.webapp.archives.dao;

import com.riozenc.cim.api.annotation.SynchronizeTrigger;
import com.riozenc.cim.webapp.archives.domain.MeterDomain;
import com.riozenc.cim.webapp.archives.domain.TransformerMeterRelationDomain;
import com.riozenc.titanTool.annotation.PaginationSupport;
import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.spring.webapp.dao.AbstractTransactionDAOSupport;
import org.apache.ibatis.session.ExecutorType;

import java.util.ArrayList;
import java.util.List;

@TransactionDAO()
public class MeterTransRelDAO extends AbstractTransactionDAOSupport{
    @PaginationSupport
    public List<MeterDomain> getTransformerByNoMeterRel(MeterDomain t) {
       	if(t.getIdList()==null||"".equals(t.getIdList())) {
    		return new ArrayList<MeterDomain>();
    	}
        return getPersistanceManager().find(getNamespace() + ".getTransformerByNoMeterRel", t);
    }

    @PaginationSupport
    public List<MeterDomain> getTransformerByMeterRel(MeterDomain t) {

    	if(t.getIdList()==null||"".equals(t.getIdList())) {
    		return new ArrayList<MeterDomain>();
    	}
    	return getPersistanceManager().find(getNamespace() + ".getTransformerByMeterRel", t);

    }

	@SynchronizeTrigger
    public int addTransformerByMeterRel(List<TransformerMeterRelationDomain> list) {
        return getPersistanceManager(ExecutorType.BATCH).insertList(getNamespace() + ".addTransformerByMeterRel", list);
    }

    public int deleteTransformerByMeterRel(MeterDomain t) {
        return getPersistanceManager(ExecutorType.BATCH).delete(getNamespace() + ".deleteTransformerByMeterRel", t);
    }

    public List<TransformerMeterRelationDomain> findTransformerGroupNo(TransformerMeterRelationDomain rel) {
        return getPersistanceManager().find(getNamespace() + ".findTransformerGroupNo", rel);
    }

    public List<MeterDomain> getMeterByWriteSectionId(MeterDomain t) {
        return getPersistanceManager().find(getNamespace() + ".getMeterByWriteSectionId", t);
    }

	@SynchronizeTrigger
	public int updateTransLoss(TransformerMeterRelationDomain e) {
        return getPersistanceManager().update(getNamespace() + ".updateTransLoss", e);
	}

    @PaginationSupport
	public List<TransformerMeterRelationDomain> getMeterByTransRel(TransformerMeterRelationDomain e) {
        return getPersistanceManager().find(getNamespace() + ".getMeterByTransRel", e);

	}

}
