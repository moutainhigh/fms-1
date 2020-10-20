/**
 *    Auth:riozenc
 *    Date:2019年3月8日 下午3:28:46
 *    Title:com.riozenc.cim.webapp.dao.CustomerDAO.java
 **/
package org.fms.cim.webapp.archives.dao;

import com.riozenc.cim.api.annotation.SynchronizeTrigger;
import com.riozenc.cim.webapp.archives.domain.MeterDomain;
import com.riozenc.cim.webapp.archives.domain.MeterMeterAssetsRelDomain;
import com.riozenc.cim.webapp.archives.domain.MeterReplaceDomain;
import com.riozenc.cim.webapp.archives.domain.MeterWriteSnEntity;
import com.riozenc.cim.webapp.archives.domain.WriteSectDomain;
import com.riozenc.titanTool.annotation.PaginationSupport;
import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.spring.webapp.dao.AbstractTransactionDAOSupport;
import com.riozenc.titanTool.spring.webapp.dao.BaseDAO;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.ExecutorType;

@TransactionDAO()
public class MeterMeterAssetsRelDAO extends AbstractTransactionDAOSupport implements BaseDAO<MeterMeterAssetsRelDomain> {

	@Override
//	@SynchronizeTrigger
	public int insert(MeterMeterAssetsRelDomain t) {
		return getPersistanceManager().insert(getNamespace() + ".insert", t);
	}

	@Override
	public int delete(MeterMeterAssetsRelDomain t) {
		return getPersistanceManager().delete(getNamespace() + ".delete", t);
	}

	@Override
//	@SynchronizeTrigger
	public int update(MeterMeterAssetsRelDomain t) {
		return getPersistanceManager().update(getNamespace() + ".update", t);
	}

	@Override
	public MeterMeterAssetsRelDomain findByKey(MeterMeterAssetsRelDomain t) {
		return getPersistanceManager().load(getNamespace() + ".findByKey", t);
	}

	@Override
	@PaginationSupport
	public List<MeterMeterAssetsRelDomain> findByWhere(MeterMeterAssetsRelDomain t) {
		return getPersistanceManager().find(getNamespace() + ".findByWhere", t);
	}

	public List<MeterMeterAssetsRelDomain> getMeterAssetsByMeterIds(List<Long> tl) {
		if(tl==null||tl.size()==0) {
			return new ArrayList<MeterMeterAssetsRelDomain>();
		}
		return getPersistanceManager().find(getNamespace() + ".getMeterAssetsByMeterIds", tl);
	}

	public List<MeterMeterAssetsRelDomain> getMeterEntityByMeterIds(String tl) {
		return getPersistanceManager().find(getNamespace() + ".getMeterEntityByMeterIds", tl);
	}

	public Byte getNextMeterOrder(Long tl){
		return getPersistanceManager().load(getNamespace() + ".getNextMeterOrder", tl);
	}
	@PaginationSupport
	public List<MeterWriteSnEntity> getMeterOrderByWriteSect(WriteSectDomain tl){
		return getPersistanceManager().find(getNamespace() + ".getMeterOrderByWriteSect", tl);
	}

	public List<MeterWriteSnEntity> generateWriteOrderAuto(WriteSectDomain tl){
		return getPersistanceManager().find(getNamespace() + ".generateWriteOrderAuto", tl);
	}

	public int updateList(List<MeterMeterAssetsRelDomain> t) {
		return getPersistanceManager(ExecutorType.BATCH).updateList(getNamespace() + ".update", t);
	}

	public int insertList(List<MeterMeterAssetsRelDomain> mmarList) {
		return getPersistanceManager(ExecutorType.BATCH).insertList(getNamespace() + ".insert", mmarList);
	}

	public List<MeterMeterAssetsRelDomain> getMeterAssetsByWriteSectIds(List<Long> writeSectIds) {
		if(writeSectIds==null||writeSectIds.size()==0) {
			return new ArrayList<MeterMeterAssetsRelDomain>();
		}
		return getPersistanceManager().find(getNamespace() + ".getMeterAssetsByWriteSectIds", writeSectIds);
	}

	public int updateByMeterAssetsId(MeterMeterAssetsRelDomain mmar) {
		return getPersistanceManager().update(getNamespace() + ".updateByMeterAssetsId", mmar);

	}

	public int deleteByMeterIds(MeterDomain meter) {
		if(meter.getIdList()==null || "".equals(meter.getIdList())) {
			return 0;
		}
		return getPersistanceManager().update(getNamespace() + ".deleteByMeterIds", meter);
	}
	
}
