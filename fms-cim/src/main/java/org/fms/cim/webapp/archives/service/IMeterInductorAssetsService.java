/**
 *    Auth:riozenc
 *    Date:2019年3月8日 下午3:38:03
 *    Title:com.riozenc.cim.webapp.service.ICustomerService.java
 **/
package org.fms.cim.webapp.archives.service;

import java.util.List;

import org.fms.cim.webapp.archives.domain.MeterInductorAssetsRelDomain;

import com.riozenc.titanTool.spring.webapp.service.BaseService;

public interface IMeterInductorAssetsService extends BaseService<MeterInductorAssetsRelDomain> {

    public List<MeterInductorAssetsRelDomain> getMeterInductorByMeterIds(List<Long> s);

    public List<MeterInductorAssetsRelDomain> getInductorEntityByMeterIds(String s);
    public Byte getNextInductorOrder(Long s);

	public List<MeterInductorAssetsRelDomain> getMeterInductorByWriteSectIds(List<Long> writeSectIds);
}
