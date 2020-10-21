/**
 *    Auth:riozenc
 *    Date:2019年3月12日 下午7:16:23
 *    Title:com.riozenc.cim.webapp.service.impl.SettlementServiceImpl.java
 **/
package org.fms.cim.webapp.archives.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.fms.cim.webapp.archives.dao.MeterMeterAssetsRelDAO;
import org.fms.cim.webapp.archives.domain.MeterDomain;
import org.fms.cim.webapp.archives.domain.MeterMeterAssetsRelDomain;
import org.fms.cim.webapp.archives.domain.MeterWriteSnEntity;
import org.fms.cim.webapp.archives.domain.WriteSectDomain;
import org.fms.cim.webapp.archives.service.IMeterMeterAssetsService;

import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.annotation.TransactionService;

@TransactionService
public class MeterMeterAssetsServiceImpl implements IMeterMeterAssetsService {

	@TransactionDAO
	private MeterMeterAssetsRelDAO meterMeterAssetsDAO;

	@Override
	public int insert(MeterMeterAssetsRelDomain t) {
		return meterMeterAssetsDAO.insert(t);
	}

	@Override
	public int delete(MeterMeterAssetsRelDomain t) {
		return meterMeterAssetsDAO.delete(t);
	}

	@Override
	public int update(MeterMeterAssetsRelDomain t) {
		return meterMeterAssetsDAO.update(t);
	}

	@Override
	public MeterMeterAssetsRelDomain findByKey(MeterMeterAssetsRelDomain t) {
		return meterMeterAssetsDAO.findByKey(t);
	}

	@Override
	public List<MeterMeterAssetsRelDomain> findByWhere(MeterMeterAssetsRelDomain t) {
		return meterMeterAssetsDAO.findByWhere(t);
	}

	@Override
	public List<MeterMeterAssetsRelDomain> getMeterAssetsByMeterIds(List<Long> ids) {

		List<MeterMeterAssetsRelDomain> rList = new ArrayList<MeterMeterAssetsRelDomain>();
		//处理超过1000个id
		int len = ids.size();
		for(int m=0;m<len/999+1;m++) {//遍历次数

			List<Long> tl = ids.subList(m*999, (m+1)*999 > len ? len : (m+1)*999);

			List<MeterMeterAssetsRelDomain> tList = meterMeterAssetsDAO.getMeterAssetsByMeterIds(tl);
			rList.addAll(tList);
		}

		return rList;
	}
	@Override
	public List<MeterMeterAssetsRelDomain> getMeterEntityByMeterIds(String s){
		return meterMeterAssetsDAO.getMeterEntityByMeterIds(s);
	}

	@Override
	public Byte getNextMeterOrder(Long s){
		return meterMeterAssetsDAO.getNextMeterOrder(s);
	}

	public List<MeterWriteSnEntity> getMeterOrderByWriteSect(WriteSectDomain tl){
		return meterMeterAssetsDAO.getMeterOrderByWriteSect(tl);
	}

	public List<MeterWriteSnEntity> generateWriteOrderAuto(WriteSectDomain tl){
		return meterMeterAssetsDAO.generateWriteOrderAuto(tl);
	}

	public int updateList(List<MeterMeterAssetsRelDomain> t){
		return meterMeterAssetsDAO.updateList(t);
	}

	@Override
	public int insertList(List<MeterMeterAssetsRelDomain> mmarList) {
		return meterMeterAssetsDAO.insertList(mmarList);
	}

	@Override
	public List<MeterMeterAssetsRelDomain> getMeterAssetsByWriteSectIds(List<Long> writeSectIds) {
		return meterMeterAssetsDAO.getMeterAssetsByWriteSectIds(writeSectIds);

	}

	@Override
	public int updateByMeterAssetsId(MeterMeterAssetsRelDomain mmar) {
		return meterMeterAssetsDAO.updateByMeterAssetsId(mmar);

	}

	@Override
	public int deleteByMeterIds(MeterDomain meter) throws Exception {
		int i = meterMeterAssetsDAO.deleteByMeterIds(meter);
		if(i>1000) {
			throw new Exception("删除数量超过1000！！！！");
		}
		return i;

	}

}
