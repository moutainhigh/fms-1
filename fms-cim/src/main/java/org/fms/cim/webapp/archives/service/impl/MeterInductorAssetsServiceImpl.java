/**
 *    Auth:riozenc
 *    Date:2019年3月12日 下午7:16:23
 *    Title:com.riozenc.cim.webapp.service.impl.SettlementServiceImpl.java
 **/
package org.fms.cim.webapp.archives.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.fms.cim.webapp.archives.dao.MeterInductorAssetsRelDAO;
import org.fms.cim.webapp.archives.domain.MeterInductorAssetsRelDomain;
import org.fms.cim.webapp.archives.service.IMeterInductorAssetsService;

import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.annotation.TransactionService;

@TransactionService
public class MeterInductorAssetsServiceImpl implements IMeterInductorAssetsService {

	@TransactionDAO
	private MeterInductorAssetsRelDAO meterInductorAssetsDAO;

	@Override
	public int insert(MeterInductorAssetsRelDomain t) {
		// TODO Auto-generated method stub
		return meterInductorAssetsDAO.insert(t);
	}

	@Override
	public int delete(MeterInductorAssetsRelDomain t) {
		// TODO Auto-generated method stub
		return meterInductorAssetsDAO.delete(t);
	}

	@Override
	public int update(MeterInductorAssetsRelDomain t) {
		// TODO Auto-generated method stub
		return meterInductorAssetsDAO.update(t);
	}

	@Override
	public MeterInductorAssetsRelDomain findByKey(MeterInductorAssetsRelDomain t) {
		// TODO Auto-generated method stub
		return meterInductorAssetsDAO.findByKey(t);
	}

	@Override
	public List<MeterInductorAssetsRelDomain> findByWhere(MeterInductorAssetsRelDomain t) {
		// TODO Auto-generated method stub
		return meterInductorAssetsDAO.findByWhere(t);
	}
	@Override
	public List<MeterInductorAssetsRelDomain> getMeterInductorByMeterIds(List<Long> idsList) {

		List<MeterInductorAssetsRelDomain> rList = new ArrayList<MeterInductorAssetsRelDomain>();
		//处理超过1000个id
		int len = idsList.size();
		for(int m=0;m<len/999+1;m++) {//遍历次数

			List<Long> tl = idsList.subList(m*999, (m+1)*999 > len ? len : (m+1)*999);

			List<MeterInductorAssetsRelDomain> tList = meterInductorAssetsDAO.getMeterInductorByMeterIds(tl);
			rList.addAll(tList);
		}
		//去重
		HashSet<MeterInductorAssetsRelDomain> rSet = new HashSet<MeterInductorAssetsRelDomain>(rList);
		
		return new ArrayList<MeterInductorAssetsRelDomain>(rSet);
	}

	@Override
	public List<MeterInductorAssetsRelDomain> getInductorEntityByMeterIds(String s){
		return meterInductorAssetsDAO.getInductorEntityByMeterIds(s);
	}
	@Override
	public Byte getNextInductorOrder(Long s){
		return meterInductorAssetsDAO.getNextInductorOrder(s);
	}

	@Override
	public List<MeterInductorAssetsRelDomain> getMeterInductorByWriteSectIds(List<Long> writeSectIds) {
		return meterInductorAssetsDAO.getMeterInductorByWriteSectIds(writeSectIds);

	}

}
