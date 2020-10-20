/**
 * Author : czy
 * Date : 2019年11月21日 下午1:49:19
 * Title : com.riozenc.cim.webapp.archives.dao.TransformerLossTableParamDAO.java
 *
**/
package org.fms.cim.webapp.archives.dao;

import java.util.List;

import com.riozenc.cim.webapp.archives.domain.TransformerLossTableParamDomain;
import com.riozenc.titanTool.annotation.PaginationSupport;
import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.spring.webapp.dao.AbstractTransactionDAOSupport;
import com.riozenc.titanTool.spring.webapp.dao.BaseDAO;

@TransactionDAO
public class TransformerLossTableParamDAO extends AbstractTransactionDAOSupport
		implements BaseDAO<TransformerLossTableParamDomain> {

	@Override
	public int insert(TransformerLossTableParamDomain t) {
		return getPersistanceManager().insert(getNamespace() + ".insert", t);
	}

	@Override
	public int delete(TransformerLossTableParamDomain t) {
		return getPersistanceManager().delete(getNamespace() + ".delete", t);
	}

	@Override
	public int update(TransformerLossTableParamDomain t) {
		return getPersistanceManager().update(getNamespace() + ".update", t);
	}

	@Override
	public TransformerLossTableParamDomain findByKey(TransformerLossTableParamDomain t) {
		return getPersistanceManager().load(getNamespace() + ".findByKey", t);
	}

	@Override
	@PaginationSupport
	public List<TransformerLossTableParamDomain> findByWhere(TransformerLossTableParamDomain t) {
		return getPersistanceManager().find(getNamespace() + ".findByWhere", t);
	}

}
