/**
 *    Auth:riozenc
 *    Date:2019年3月8日 下午3:28:46
 *    Title:com.riozenc.cim.webapp.dao.CustomerDAO.java
 **/
package org.fms.cim.webapp.assets.dao;

import java.util.List;

import com.riozenc.cim.webapp.archives.domain.UserDomain;
import com.riozenc.cim.webapp.assets.domain.InductorAssetsDomain;
import com.riozenc.cim.webapp.assets.domain.InductorAssetsEntity;
import com.riozenc.titanTool.annotation.PaginationSupport;
import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.spring.webapp.dao.AbstractTransactionDAOSupport;
import com.riozenc.titanTool.spring.webapp.dao.BaseDAO;

@TransactionDAO()
public class InductorAssetsDAO extends AbstractTransactionDAOSupport implements BaseDAO<InductorAssetsDomain> {

	@Override
	public int insert(InductorAssetsDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().insert(getNamespace() + ".insert", t);
	}

	@Override
	public int delete(InductorAssetsDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().delete(getNamespace() + ".delete", t);
	}

	@Override
	public int update(InductorAssetsDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().update(getNamespace() + ".update", t);
	}

	@Override
	public InductorAssetsDomain findByKey(InductorAssetsDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().load(getNamespace() + ".findByKey", t);
	}

	@Override
	@PaginationSupport
	public List<InductorAssetsDomain> findByWhere(InductorAssetsDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().find(getNamespace() + ".findByWhere", t);
	}

	public List<InductorAssetsDomain> getInductorAssetsByDept(InductorAssetsDomain t) {
		return getPersistanceManager().find(getNamespace() + ".getInductorAssetsByDept", t);
	}

	public List<InductorAssetsDomain> getInductorAssetsByUser(UserDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().find(getNamespace() + ".getInductorAssetsByUser", t);
	}

	public List<InductorAssetsDomain> assetsNoDC(InductorAssetsDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().find(getNamespace() + ".assetsNoDC", t);
	}

	public int updateList(List<InductorAssetsDomain> l) {
		return getPersistanceManager().updateList(getNamespace() + ".update", l);

	}

	@PaginationSupport
	public InductorAssetsEntity findInductEntityByWhere(String t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().load(getNamespace() +
				".findInductEntityByWhere", t);
	}

}
