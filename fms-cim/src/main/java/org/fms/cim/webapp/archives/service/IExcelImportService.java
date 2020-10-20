/**
 *    Auth:riozenc
 *    Date:2019年3月8日 下午3:38:03
 *    Title:com.riozenc.cim.webapp.service.ICustomerService.java
 **/
package org.fms.cim.webapp.archives.service;

import java.util.List;

import com.riozenc.cim.webapp.assets.domain.MeterAssetsDomain;
import com.riozenc.titanTool.spring.web.http.HttpResult;

public interface IExcelImportService {

	HttpResult changeDevForExcel (List<MeterAssetsDomain> meterAssetsDomains) throws Exception;

	
	
}
