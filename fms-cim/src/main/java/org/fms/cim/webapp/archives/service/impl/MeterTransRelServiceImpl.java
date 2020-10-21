package org.fms.cim.webapp.archives.service.impl;

import java.util.List;

import org.fms.cim.webapp.archives.dao.MeterTransRelDAO;
import org.fms.cim.webapp.archives.domain.MeterDomain;
import org.fms.cim.webapp.archives.domain.TransformerMeterRelationDomain;
import org.fms.cim.webapp.archives.service.IMeterTransRelService;

import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.annotation.TransactionService;

@TransactionService
public class MeterTransRelServiceImpl implements IMeterTransRelService {
    @TransactionDAO
    private MeterTransRelDAO meterTransRelDAO;
    @Override
    public List<MeterDomain> getTransformerByNoMeterRel(MeterDomain t) {
        return meterTransRelDAO.getTransformerByNoMeterRel(t);
    }

    @Override
    public List<MeterDomain> getTransformerByMeterRel(MeterDomain t) {
        return meterTransRelDAO.getTransformerByMeterRel(t);
    }

    @Override
    public List<TransformerMeterRelationDomain> findTransformerGroupNo(TransformerMeterRelationDomain rel) {
        return meterTransRelDAO.findTransformerGroupNo(rel);
    }

    @Override
    public int addTransformerByMeterRel(List<TransformerMeterRelationDomain> list, MeterDomain deleteList) {
        meterTransRelDAO.deleteTransformerByMeterRel(deleteList);
        if(list!=null&&list.size()>0) {
            for(TransformerMeterRelationDomain tm:list) {
            	if(tm.getMsType()!=null && tm.getMsType()-2==0) {
                	tm.setTransLostType((byte) 2);
            	}else {
                	tm.setTransLostType((byte) 0);
            	}
            	tm.setStatus((byte) 1);
            }
        }
        return meterTransRelDAO.addTransformerByMeterRel(list);
    }

    @Override
    public List<MeterDomain> getMeterByWriteSectionId(MeterDomain t) {
        return meterTransRelDAO.getMeterByWriteSectionId(t);
    }

	@Override
	public int updateTransLoss(TransformerMeterRelationDomain e) {
        return meterTransRelDAO.updateTransLoss(e);

	}

	@Override
	public List<TransformerMeterRelationDomain> getMeterByTransRel(TransformerMeterRelationDomain e) {
        return meterTransRelDAO.getMeterByTransRel(e);

	}
}
