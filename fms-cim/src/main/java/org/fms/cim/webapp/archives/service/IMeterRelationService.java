/**
 *    Auth:riozenc
 *    Date:2019年3月8日 下午3:38:03
 *    Title:com.riozenc.cim.webapp.service.ICustomerService.java
 **/
package org.fms.cim.webapp.archives.service;

import java.util.List;

import org.fms.cim.webapp.archives.domain.MeterRelationDomain;

import com.riozenc.titanTool.spring.webapp.service.BaseService;

public interface IMeterRelationService extends BaseService<MeterRelationDomain> {

	public int deleteList(List<MeterRelationDomain> meterRelList);

	public List<MeterRelationDomain> getMeterRelationByWriteSectIds(List<Long> writeSectIds);
	
	public List<MeterRelationDomain> getMeterRelationByMeterIds(List<Long> writeSectIds);

	public int insertList(List<MeterRelationDomain> meterRelList);

	public int updateList(List<MeterRelationDomain> mreluList);

	public List<MeterRelationDomain> cc(MeterRelationDomain t);
	
	
}
