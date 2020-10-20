/**
 *    Auth:riozenc
 *    Date:2019年3月12日 下午7:11:34
 *    Title:com.riozenc.cim.webapp.dao.UserDAO.java
 **/
package org.fms.cim.webapp.assets.dao;

import java.util.List;

import org.apache.ibatis.session.ExecutorType;

import com.riozenc.cim.webapp.assets.domain.TransformerAssetsDomain;
import com.riozenc.titanTool.annotation.PaginationSupport;
import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.spring.webapp.dao.AbstractTransactionDAOSupport;
import com.riozenc.titanTool.spring.webapp.dao.BaseDAO;

@TransactionDAO
public class TransformerAssetsDAO extends AbstractTransactionDAOSupport implements BaseDAO<TransformerAssetsDomain> {

	@Override
	public int insert(TransformerAssetsDomain t) {
		return getPersistanceManager().insert(getNamespace() + ".insert", t);
	}

	@Override
	public int delete(TransformerAssetsDomain t) {
		return getPersistanceManager().delete(getNamespace() + ".delete", t);
	}

	@Override
	public int update(TransformerAssetsDomain t) {
		return getPersistanceManager().update(getNamespace() + ".update", t);
	}

	@Override
	public TransformerAssetsDomain findByKey(TransformerAssetsDomain t) {
		return getPersistanceManager().load(getNamespace() + ".findByKey", t);
	}

	@Override
	public List<TransformerAssetsDomain> findByWhere(TransformerAssetsDomain t) {
		return getPersistanceManager().find(getNamespace() + ".findByWhere", t);
	}

	public List<TransformerAssetsDomain> getTransformerAssetsByDept(TransformerAssetsDomain transformerAssetsDomain) {
		return getPersistanceManager().find(getNamespace() + ".getTransformerAssetsByDept", transformerAssetsDomain);
	}

	@PaginationSupport
	public List<TransformerAssetsDomain> getTransformerAssetsByWhere(TransformerAssetsDomain t) {
		return getPersistanceManager().find(getNamespace() + ".getTransformerAssetsByWhere", t);
	}

	public List<TransformerAssetsDomain> findByNoId(TransformerAssetsDomain e) {
		return getPersistanceManager().find(getNamespace() + ".findByNoId", e);

	}
	
	public int insertList(List<TransformerAssetsDomain> t) {
		return getPersistanceManager(ExecutorType.BATCH).insertList(getNamespace() + ".insertList", t);
	}
	

}
