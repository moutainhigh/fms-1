/**
 *    Auth:riozenc
 *    Date:2019年3月8日 下午3:38:03
 *    Title:com.riozenc.cim.webapp.service.ICustomerService.java
 **/
package org.fms.cim.webapp.assets.service;

import com.riozenc.cim.webapp.archives.domain.UserDomain;
import com.riozenc.cim.webapp.assets.domain.MeterAssetsDomain;
import com.riozenc.cim.webapp.assets.domain.MeterAssetsEntity;
import com.riozenc.titanTool.spring.web.http.HttpResult;
import com.riozenc.titanTool.spring.webapp.service.BaseService;

import java.util.List;
import java.util.Map;

public interface IMeterAssetsService extends BaseService<MeterAssetsDomain> {

	public List<MeterAssetsDomain> getMeterAssetsByManager(MeterAssetsDomain meterAssetsDomain);

	public List<Map<String, Object>> getAssetsByUser(UserDomain t);

	public HttpResult addAssetsList(MeterAssetsDomain t);

	public List<MeterAssetsDomain> findByWhereDC(MeterAssetsDomain tt);

	public int updateList(List<MeterAssetsDomain> l);

	public MeterAssetsEntity findMeterEntityByWhere(Long id);

	public List<MeterAssetsDomain> getMeterAssetsByAssetsIds(List<Long> idsList);

	public List<MeterAssetsEntity> getMeterAssetsByFunctionCode(Map ids);
	
	public int insertList(List<MeterAssetsDomain> rl);

	public List<MeterAssetsDomain> getMeterAssetsByNos( List<MeterAssetsDomain> meterAssetsNos);


	}
