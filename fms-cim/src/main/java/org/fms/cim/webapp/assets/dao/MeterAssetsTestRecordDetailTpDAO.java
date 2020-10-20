/**
 *    Auth:riozenc
 *    Date:2019年3月8日 下午3:28:46
 *    Title:com.riozenc.cim.webapp.dao.CustomerDAO.java
 **/
package org.fms.cim.webapp.assets.dao;

import java.util.List;
import com.riozenc.cim.webapp.assets.domain.MeterAssetsTestRecordDetailTpDomain;
import com.riozenc.titanTool.annotation.PaginationSupport;
import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.spring.webapp.dao.AbstractTransactionDAOSupport;
import com.riozenc.titanTool.spring.webapp.dao.BaseDAO;

@TransactionDAO
public class MeterAssetsTestRecordDetailTpDAO extends AbstractTransactionDAOSupport implements BaseDAO<MeterAssetsTestRecordDetailTpDomain> {

	@Override
	public int insert(MeterAssetsTestRecordDetailTpDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().insert(getNamespace() + ".insert", t);
	}

	@Override
	public int delete(MeterAssetsTestRecordDetailTpDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().delete(getNamespace() + ".delete", t);
	}

	@Override
	public int update(MeterAssetsTestRecordDetailTpDomain t) {
		// TODO Auto-generated method stub
	//	insertLog(t, "update");
		return getPersistanceManager().update(getNamespace() + ".update", t);
	}

	@Override
	public MeterAssetsTestRecordDetailTpDomain findByKey(MeterAssetsTestRecordDetailTpDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().load(getNamespace() + ".findByKey", t);
	}

	@Override
	@PaginationSupport
	public List<MeterAssetsTestRecordDetailTpDomain> findByWhere(MeterAssetsTestRecordDetailTpDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().find(getNamespace() + ".findByWhere", t);
	}


}
