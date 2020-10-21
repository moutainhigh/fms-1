/**
 *    Auth:riozenc
 *    Date:2019年3月12日 下午7:15:22
 *    Title:com.riozenc.cim.webapp.service.ISettlementService.java
 **/
package org.fms.cim.webapp.archives.service;

import java.util.List;

import org.fms.cim.webapp.archives.domain.MeterDomain;
import org.fms.cim.webapp.archives.domain.SettlementDomain;
import org.fms.cim.webapp.archives.domain.SettlementMeterRelDomain;

import com.riozenc.titanTool.spring.webapp.service.BaseService;

public interface ISettlementService extends BaseService<SettlementDomain>{

	public int addSettlementMeterRelBySettlement(List<SettlementMeterRelDomain> list);

	public int deleteSettlementMeterRelBySettlement(List<SettlementMeterRelDomain> list);

	public List<SettlementMeterRelDomain> getSettlementMeterRel(SettlementDomain settlementDomain);

	public List<MeterDomain> getNoSettlementMeter(SettlementMeterRelDomain t);

    public List<SettlementDomain> findByNo(SettlementDomain settlementDomain);

	public int addSettlementMeterRel(SettlementMeterRelDomain smr);

	public int insertList(List<SettlementDomain> settlementList);

	public List<SettlementMeterRelDomain> getSettlementMeterRelByMeterIds(List<Long> lc);

	public List<SettlementDomain> findBankSettlement(SettlementDomain smr);

	public List<SettlementDomain> findSettlementByIds(List<Long> ids);

	public List<SettlementDomain> findBySettlementNos(SettlementDomain t);

	List<SettlementDomain> findSettlementByBusinessPlaceCodeAndInvoiceType(SettlementDomain settlementDomain);
	List<Long> findSettlementIdByWhere(SettlementDomain t);

	public List<SettlementDomain> getSettlementbyMeterIds(List<Long> settlementIdList);

	public List<SettlementDomain> findClearSettlementByWhere(SettlementDomain t);

}
