package org.fms.cim.webapp.archives.service;

import com.riozenc.cim.webapp.archives.domain.MeterDomain;
import com.riozenc.cim.webapp.archives.domain.TransformerMeterRelationDomain;

import java.util.List;

public interface IMeterTransRelService {

    public List<MeterDomain> getTransformerByNoMeterRel(MeterDomain t);

    public List<MeterDomain> getTransformerByMeterRel(MeterDomain t);

    public int addTransformerByMeterRel(List<TransformerMeterRelationDomain> list, MeterDomain deleteList);

    List<TransformerMeterRelationDomain> findTransformerGroupNo(TransformerMeterRelationDomain rel);

    public List<MeterDomain> getMeterByWriteSectionId(MeterDomain e);

	public int updateTransLoss(TransformerMeterRelationDomain e);

	public List<TransformerMeterRelationDomain> getMeterByTransRel(TransformerMeterRelationDomain e);
}
