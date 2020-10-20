/**
 *    Auth:riozenc
 *    Date:2019年3月12日 下午7:15:22
 *    Title:com.riozenc.cim.webapp.service.ISettlementService.java
 **/
package org.fms.cim.webapp.archives.service;

import com.riozenc.cim.webapp.archives.domain.SettlementDomain;
import com.riozenc.cim.webapp.archives.domain.SettlementMeterRelDomain;
import com.riozenc.titanTool.spring.webapp.service.BaseService;

import java.util.List;

public interface ISettlementMeterRelService extends BaseService<SettlementMeterRelDomain> {

	public List<Long> getSettlementByMeterIds(List<String> meterIds);

	public List<SettlementMeterRelDomain> findSettlementByMeterIds(List<Long> meterIds);

	public int updateList(List<SettlementMeterRelDomain> smruList);

	public List<Long> getMeterIdsBySettlementId(Long settlementId);

	public List<SettlementMeterRelDomain> getMeterIdsBySettlementIds(List<Long> settlementIds);

	public List<Long> getMeterIdsBySettlementInfo(SettlementDomain settlementDomain);

}
