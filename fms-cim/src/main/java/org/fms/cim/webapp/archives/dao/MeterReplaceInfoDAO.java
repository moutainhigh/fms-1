/**
 *    Auth:riozenc
 *    Date:2019年3月8日 下午3:28:46
 *    Title:com.riozenc.cim.webapp.dao.CustomerDAO.java
 **/
package org.fms.cim.webapp.archives.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.fms.cim.webapp.archives.domain.MeterReplaceDomain;

import com.riozenc.titanTool.annotation.PaginationSupport;
import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.spring.webapp.dao.AbstractTransactionDAOSupport;
import com.riozenc.titanTool.spring.webapp.dao.BaseDAO;

@TransactionDAO()
public class MeterReplaceInfoDAO extends AbstractTransactionDAOSupport implements BaseDAO<MeterReplaceDomain> {

	@Override
	public int insert(MeterReplaceDomain t) {
		return getPersistanceManager().insert(getNamespace() + ".insert", t);
	}

	@Override
	public int delete(MeterReplaceDomain t) {
		return 0;
	}

	@Override
	public int update(MeterReplaceDomain t) {
		return getPersistanceManager().update(getNamespace() + ".update", t);
	}

	@Override
	public MeterReplaceDomain findByKey(MeterReplaceDomain t) {
		return getPersistanceManager().load(getNamespace() + ".findByKey", t);
	}

	@Override
	@PaginationSupport
	public List<MeterReplaceDomain> findByWhere(MeterReplaceDomain t) {
		return getPersistanceManager().find(getNamespace() + ".findByWhere", t);
	}

	public List<MeterReplaceDomain> findByMeter(MeterReplaceDomain mr) {
		return getPersistanceManager().find(getNamespace() + ".findByMeter", mr);
	}

	public List<MeterReplaceDomain> getMeterReplaceByWriteSectIds(List<Long> writeSectIds) {
		if(writeSectIds==null||writeSectIds.size()==0) {
			return new ArrayList<MeterReplaceDomain>();
		}
		return getPersistanceManager().find(getNamespace() + ".getMeterReplaceByWriteSectIds", writeSectIds);
	}

	public List<MeterReplaceDomain> getMeterReplaceByMeterIds(List<Long> meterIds) {
		if(meterIds==null||meterIds.size()==0) {
			return new ArrayList<MeterReplaceDomain>();
		}
		return getPersistanceManager().find(getNamespace() + ".getMeterReplaceByMeterIds", meterIds);
	}

	public int insertList(List<MeterReplaceDomain> mrList) {
		return getPersistanceManager(ExecutorType.BATCH).insertList(getNamespace() + ".insert", mrList);

	}

	public int updateList(List<MeterReplaceDomain> mruList) {
		return getPersistanceManager(ExecutorType.BATCH).updateList(getNamespace() + ".update", mruList);

	}

	public int getMaxMeterSn(MeterReplaceDomain m) {
		
		if(m.getMeterId()==null) {
			return 0;
		}
		List<MeterReplaceDomain> tl =  getPersistanceManager().find(getNamespace() + ".getMaxMeterSn", m);
		if(tl.size()>0) {
			return tl.get(0).getMeterSn();
		}
		return 0;
	}
}
