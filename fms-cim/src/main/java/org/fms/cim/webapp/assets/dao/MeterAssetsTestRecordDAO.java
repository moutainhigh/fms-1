/**
 *    Auth:riozenc
 *    Date:2019年3月8日 下午3:28:46
 *    Title:com.riozenc.cim.webapp.dao.CustomerDAO.java
 **/
package org.fms.cim.webapp.assets.dao;

import java.util.List;

import org.fms.cim.webapp.assets.domain.MeterAssetsDomain;
import org.fms.cim.webapp.assets.domain.MeterAssetsTestRecordDomain;

import com.riozenc.titanTool.annotation.PaginationSupport;
import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.spring.webapp.dao.AbstractTransactionDAOSupport;
import com.riozenc.titanTool.spring.webapp.dao.BaseDAO;

@TransactionDAO
public class MeterAssetsTestRecordDAO extends AbstractTransactionDAOSupport implements BaseDAO<MeterAssetsTestRecordDomain> {

	@Override
	public int insert(MeterAssetsTestRecordDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().insert(getNamespace() + ".insert", t);
	}

	@Override
	public int delete(MeterAssetsTestRecordDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().delete(getNamespace() + ".delete", t);
	}

	@Override
	public int update(MeterAssetsTestRecordDomain t) {
		// TODO Auto-generated method stub
	//	insertLog(t, "update");
		return getPersistanceManager().update(getNamespace() + ".update", t);
	}

	@Override
	public MeterAssetsTestRecordDomain findByKey(MeterAssetsTestRecordDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().load(getNamespace() + ".findByKey", t);
	}

	@Override
	@PaginationSupport
	public List<MeterAssetsTestRecordDomain> findByWhere(MeterAssetsTestRecordDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().find(getNamespace() + ".findByWhere", t);
	}

	public int testTemp(List<MeterAssetsDomain> meterAssetsList) {
		// TODO Auto-generated method stub
		return getPersistanceManager().insertList(getNamespace() + ".insert", meterAssetsList);
		
	}


}
