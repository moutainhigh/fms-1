/**
 *    Auth:riozenc
 *    Date:2019年3月21日 下午5:05:34
 *    Title:com.riozenc.cim.webapp.assets.dao.MeterAssetsLogDAO.java
 **/
package org.fms.cim.webapp.assets.dao;

import java.util.List;

import org.fms.cim.webapp.assets.domain.MeterAssetsLogDomain;

import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.spring.webapp.dao.AbstractTransactionDAOSupport;
import com.riozenc.titanTool.spring.webapp.dao.BaseDAO;

@TransactionDAO
public class MeterAssetsLogDAO extends AbstractTransactionDAOSupport implements BaseDAO<MeterAssetsLogDomain> {

	@Override
	public int insert(MeterAssetsLogDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().insert(getNamespace() + ".insert", t);
	}

	@Override
	public int delete(MeterAssetsLogDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().delete(getNamespace() + ".delete", t);
	}

	@Override
	public int update(MeterAssetsLogDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().update(getNamespace() + ".update", t);
	}

	@Override
	public MeterAssetsLogDomain findByKey(MeterAssetsLogDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().load(getNamespace() + ".findByKey", t);
	}

	@Override
	public List<MeterAssetsLogDomain> findByWhere(MeterAssetsLogDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().find(getNamespace() + ".findByWhere", t);
	}

}
