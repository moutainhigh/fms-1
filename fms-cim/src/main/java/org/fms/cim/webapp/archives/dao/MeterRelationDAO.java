/**
 *    Auth:riozenc
 *    Date:2019年3月8日 下午3:28:46
 *    Title:com.riozenc.cim.webapp.dao.CustomerDAO.java
 **/
package org.fms.cim.webapp.archives.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.ExecutorType;

import com.riozenc.cim.webapp.archives.domain.MeterMeterAssetsRelDomain;
import com.riozenc.cim.webapp.archives.domain.MeterRelationDomain;
import com.riozenc.titanTool.annotation.PaginationSupport;
import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.spring.webapp.dao.AbstractTransactionDAOSupport;
import com.riozenc.titanTool.spring.webapp.dao.BaseDAO;

@TransactionDAO()
public class MeterRelationDAO extends AbstractTransactionDAOSupport implements BaseDAO<MeterRelationDomain> {

	@Override
	public int insert(MeterRelationDomain t) {
		return getPersistanceManager().insert(getNamespace() + ".insert", t);
	}

	@Override
	public int delete(MeterRelationDomain t) {
		return getPersistanceManager().delete(getNamespace() + ".delete", t);
	}

	@Override
	public int update(MeterRelationDomain t) {
		return getPersistanceManager().update(getNamespace() + ".update", t);
	}

	@Override
	public MeterRelationDomain findByKey(MeterRelationDomain t) {
		return getPersistanceManager().load(getNamespace() + ".findByKey", t);
	}

	@Override
	@PaginationSupport
	public List<MeterRelationDomain> findByWhere(MeterRelationDomain t) {
		return getPersistanceManager().find(getNamespace() + ".findByWhere", t);
	}

	public int deleteList(List<MeterRelationDomain> meterRelList) {
		return getPersistanceManager().deleteList(getNamespace() + ".delete", meterRelList);
	}

	public List<MeterRelationDomain> getMeterRelationByWriteSectId(List<Long> writeSectIds) {
		if(writeSectIds==null||writeSectIds.size()==0) {
			return new ArrayList<MeterRelationDomain>();
		}
		return getPersistanceManager().find(getNamespace() + ".getMeterRelationByWriteSectId", writeSectIds);
	}

	public int insertList(List<MeterRelationDomain> meterRelList) {
		return getPersistanceManager(ExecutorType.BATCH).insertList(getNamespace() + ".insert", meterRelList);
	}

	public List<MeterRelationDomain> getMeterRelationByMeterIds(List<Long> meterIds) {
		if(meterIds==null||meterIds.size()==0) {
			return new ArrayList<MeterRelationDomain>();
		}
		return getPersistanceManager().find(getNamespace() + ".getMeterRelationByMeterIds", meterIds);

	}

	public int updateList(List<MeterRelationDomain> mreluList) {
		return getPersistanceManager(ExecutorType.BATCH).updateList(getNamespace() + ".update", mreluList);

	}

	public List<MeterRelationDomain> cc(MeterRelationDomain t) {
		return getPersistanceManager().find(getNamespace() + ".cc", t);

	}
}
