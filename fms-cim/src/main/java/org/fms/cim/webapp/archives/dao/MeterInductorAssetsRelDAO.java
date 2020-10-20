/**
 *    Auth:riozenc
 *    Date:2019年3月8日 下午3:28:46
 *    Title:com.riozenc.cim.webapp.dao.CustomerDAO.java
 **/
package org.fms.cim.webapp.archives.dao;

import com.riozenc.cim.api.annotation.SynchronizeTrigger;
import com.riozenc.cim.webapp.archives.domain.MeterInductorAssetsRelDomain;
import com.riozenc.titanTool.annotation.PaginationSupport;
import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.spring.webapp.dao.AbstractTransactionDAOSupport;
import com.riozenc.titanTool.spring.webapp.dao.BaseDAO;

import java.util.ArrayList;
import java.util.List;

@TransactionDAO()
public class MeterInductorAssetsRelDAO extends AbstractTransactionDAOSupport implements BaseDAO<MeterInductorAssetsRelDomain> {

	@Override
//	@SynchronizeTrigger
	public int insert(MeterInductorAssetsRelDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().insert(getNamespace() + ".insert", t);
	}

	@Override
	public int delete(MeterInductorAssetsRelDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().delete(getNamespace() + ".delete", t);
	}

	@Override
//	@SynchronizeTrigger
	public int update(MeterInductorAssetsRelDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().update(getNamespace() + ".update", t);
	}

	@Override
	public MeterInductorAssetsRelDomain findByKey(MeterInductorAssetsRelDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().load(getNamespace() + ".findByKey", t);
	}

	@Override
	@PaginationSupport
	public List<MeterInductorAssetsRelDomain> findByWhere(MeterInductorAssetsRelDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().find(getNamespace() + ".findByWhere", t);
	}

	public List<MeterInductorAssetsRelDomain> getMeterInductorByMeterIds(List<Long> tl) {
		if(tl==null||tl.size()==0) {
			return new ArrayList<MeterInductorAssetsRelDomain>();
		}
		return getPersistanceManager().find(getNamespace() + ".getMeterInductorByMeterIds", tl);
	}

	public List<MeterInductorAssetsRelDomain> getInductorEntityByMeterIds(String tl) {
		// TODO Auto-generated method stub
		return getPersistanceManager().find(getNamespace() + ".getInductorEntityByMeterIds", tl);
	}

	public Byte getNextInductorOrder(Long tl){
		return getPersistanceManager().load(getNamespace() + ".getNextInductorOrder", tl);
	}

	public List<MeterInductorAssetsRelDomain> getMeterInductorByWriteSectIds(List<Long> writeSectIds) {
		
		if(writeSectIds==null||writeSectIds.size()==0) {
			return new ArrayList<MeterInductorAssetsRelDomain>();
		}
		return getPersistanceManager().load(getNamespace() + ".getMeterInductorByWriteSectIds", writeSectIds);

	}
}
