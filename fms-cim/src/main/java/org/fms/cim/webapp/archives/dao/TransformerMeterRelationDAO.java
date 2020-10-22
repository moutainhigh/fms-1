/**
 *    Auth:riozenc
 *    Date:2019年3月12日 下午7:11:34
 *    Title:com.riozenc.cim.webapp.archives.dao.TransformerMeterRelationDAO.java
 **/
package org.fms.cim.webapp.archives.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.fms.cim.webapp.archives.domain.MeterDomain;
import org.fms.cim.webapp.archives.domain.TransformerDomain;
import org.fms.cim.webapp.archives.domain.TransformerLineRelDomain;
import org.fms.cim.webapp.archives.domain.TransformerMeterRelationDomain;

import com.riozenc.titanTool.annotation.PaginationSupport;
import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.spring.webapp.dao.AbstractTransactionDAOSupport;
import com.riozenc.titanTool.spring.webapp.dao.BaseDAO;

@TransactionDAO
public class TransformerMeterRelationDAO extends AbstractTransactionDAOSupport
		implements BaseDAO<TransformerMeterRelationDomain> {

	@Override
	public int insert(TransformerMeterRelationDomain t) {
		return getPersistanceManager().insert(getNamespace() + ".insert", t);
	}

	@Override
	public int delete(TransformerMeterRelationDomain t) {
		return getPersistanceManager().delete(getNamespace() + ".delete", t);
	}

	@Override
	public int update(TransformerMeterRelationDomain t) {
		return getPersistanceManager().update(getNamespace() + ".update", t);
	}

	@Override
	public TransformerMeterRelationDomain findByKey(TransformerMeterRelationDomain t) {
		return getPersistanceManager().load(getNamespace() + ".findByKey", t);
	}

	@Override
	@PaginationSupport
	public List<TransformerMeterRelationDomain> findByWhere(TransformerMeterRelationDomain t) {
		return getPersistanceManager().find(getNamespace() + ".findByWhere", t);
	}

	public List<TransformerMeterRelationDomain> getTransformerMeterRelationByWriteSectId(List<Long> writeSectIds) {
		if(writeSectIds==null||writeSectIds.size()==0) {
			return new ArrayList<TransformerMeterRelationDomain>();
		}
		return getPersistanceManager().find(getNamespace() + ".getTransformerMeterRelationByWriteSectId", writeSectIds);
	}

	public List<TransformerMeterRelationDomain> getTransformerMeterRelationByMeterIds(List<Long> meterIds) {
		if(meterIds==null||meterIds.size()==0) {
			return new ArrayList<TransformerMeterRelationDomain>();
		}
		return getPersistanceManager().find(getNamespace() + ".getTransformerMeterRelationByMeterIds", meterIds);
	}

	public int insertList(List<TransformerMeterRelationDomain> tmrList) {
		return getPersistanceManager(ExecutorType.BATCH).insertList(getNamespace() + ".insert", tmrList);

	}

	public List<TransformerMeterRelationDomain> getDistinctTransGroupNo(TransformerMeterRelationDomain t) {
		return getPersistanceManager().find(getNamespace() +".getDistinctTransGroupNo", t);

	}
	
	public List<MeterDomain> getMeterByTransformer(TransformerDomain t) {
		return getPersistanceManager().find(getNamespace() +".getDistinctTransGroupNo", t);

	}

	public int updateList(List<TransformerMeterRelationDomain> tmruList) {
		return getPersistanceManager(ExecutorType.BATCH).updateList(getNamespace() + ".update", tmruList);

	}

	public List<TransformerMeterRelationDomain> findDeleteList() {
		return getPersistanceManager().find(getNamespace() +".findDeleteList", null);

	}

	public int deleteList(List<TransformerMeterRelationDomain> deleteList) {
		return getPersistanceManager().deleteList(getNamespace() +".delete", deleteList);

	}

	public List<TransformerLineRelDomain> findTransformerLineByMeterIds(List<Long> meterIds) {
		return getPersistanceManager().find(getNamespace() +".findTransformerLineByMeterIds", meterIds);

	}

}
