/**
 *    Auth:riozenc
 *    Date:2019年3月12日 下午7:16:23
 *    Title:com.riozenc.cim.webapp.service.impl.SettlementServiceImpl.java
 **/
package org.fms.cim.webapp.archives.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.riozenc.cim.webapp.archives.dao.MeterRelationDAO;
import com.riozenc.cim.webapp.archives.domain.MeterDomain;
import com.riozenc.cim.webapp.archives.domain.MeterRelationDomain;
import com.riozenc.cim.webapp.archives.service.IMeterRelationService;
import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.annotation.TransactionService;

@TransactionService
public class MeterRelationServiceImpl implements IMeterRelationService {

	@TransactionDAO
	private MeterRelationDAO meterRelationDAO;

	@Override
	public int insert(MeterRelationDomain t) {
		return meterRelationDAO.insert(t);
	}

	@Override
	public int delete(MeterRelationDomain t) {
		return meterRelationDAO.delete(t);
	}

	@Override
	public int update(MeterRelationDomain t) {
		return meterRelationDAO.update(t);
	}

	@Override
	public MeterRelationDomain findByKey(MeterRelationDomain t) {
		return meterRelationDAO.findByKey(t);
	}

	@Override
	public List<MeterRelationDomain> findByWhere(MeterRelationDomain t) {
		return meterRelationDAO.findByWhere(t);
	}

	@Override
	public int deleteList(List<MeterRelationDomain> meterRelList) {
		return meterRelationDAO.deleteList(meterRelList);
	}

	@Override
	public List<MeterRelationDomain> getMeterRelationByWriteSectIds(List<Long> writeSectIds) {
		return meterRelationDAO.getMeterRelationByWriteSectId(writeSectIds);
	}

	@Override
	public int insertList(List<MeterRelationDomain> meterRelList) {
		return meterRelationDAO.insertList(meterRelList);		
	}

	@Override
	public List<MeterRelationDomain> getMeterRelationByMeterIds(List<Long> MeterIds) {
		
		List<MeterRelationDomain> rList = new ArrayList<MeterRelationDomain>();

		// 处理超过1000个id
		int len = MeterIds.size();
		for (int m = 0; m < len / 999 + 1; m++) {// 遍历次数

			List<Long> tl = MeterIds.subList(m * 999, (m + 1) * 999 > len ? len : (m + 1) * 999);

			List<MeterRelationDomain> tList = meterRelationDAO.getMeterRelationByMeterIds(tl);

			rList.addAll(tList);

		}

		return rList;

	}

	@Override
	public int updateList(List<MeterRelationDomain> mreluList) {
		return meterRelationDAO.updateList(mreluList);		

	}

	//查重
	@Override
	public List<MeterRelationDomain> cc(MeterRelationDomain t) {
		return meterRelationDAO.cc(t);		

	}

}
