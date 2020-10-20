/**
 *    Auth:riozenc
 *    Date:2019年3月8日 下午3:38:03
 *    Title:com.riozenc.cim.webapp.service.ICustomerService.java
 **/
package org.fms.cim.webapp.assets.service;

import java.util.List;

import com.riozenc.cim.webapp.assets.domain.MeterAssetsDomain;
import com.riozenc.cim.webapp.assets.domain.MeterAssetsUseRecordDomain;
import com.riozenc.titanTool.spring.webapp.service.BaseService;

public interface IMeterAssetsUseRecordService extends BaseService<MeterAssetsUseRecordDomain> {

	int addMeterAssetsUseRecord(List<MeterAssetsDomain> meterAssetsList,
			MeterAssetsUseRecordDomain meterAssetsUseRecord);
	
}
