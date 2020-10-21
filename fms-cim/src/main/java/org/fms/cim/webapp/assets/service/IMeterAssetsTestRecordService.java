package org.fms.cim.webapp.assets.service;

import java.util.List;

import org.fms.cim.webapp.assets.domain.MeterAssetsDomain;
import org.fms.cim.webapp.assets.domain.MeterAssetsTestRecordDetailTpDomain;
import org.fms.cim.webapp.assets.domain.MeterAssetsTestRecordDetailUpDomain;
import org.fms.cim.webapp.assets.domain.MeterAssetsTestRecordDomain;

import com.riozenc.titanTool.spring.web.http.HttpResult;
import com.riozenc.titanTool.spring.webapp.service.BaseService;

public interface IMeterAssetsTestRecordService extends BaseService<MeterAssetsTestRecordDomain> {

	HttpResult addMeterAssetsUseRecord(MeterAssetsTestRecordDomain matr, MeterAssetsTestRecordDetailUpDomain matrdu,
			MeterAssetsTestRecordDetailTpDomain matrdt);

	int testTemp(List<MeterAssetsDomain> meterAssetsList);

}
