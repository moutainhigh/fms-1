/**
 * Author : czy
 * Date : 2019年11月21日 下午1:49:31
 * Title : com.riozenc.cim.webapp.archives.dao.TransformerLossFormulaParamDAO.java
 *
**/
package org.fms.cim.webapp.archives.dao;

import java.util.List;

import org.fms.cim.webapp.archives.domain.TransformerLossFormulaParamDomain;

import com.riozenc.titanTool.annotation.PaginationSupport;
import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.spring.webapp.dao.AbstractTransactionDAOSupport;
import com.riozenc.titanTool.spring.webapp.dao.BaseDAO;

@TransactionDAO
public class TransformerLossFormulaParamDAO extends AbstractTransactionDAOSupport
		implements BaseDAO<TransformerLossFormulaParamDomain> {

	@Override
	public int insert(TransformerLossFormulaParamDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().insert(getNamespace() + ".insert", t);
	}

	@Override
	public int delete(TransformerLossFormulaParamDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().delete(getNamespace() + ".delete", t);
	}

	@Override
	public int update(TransformerLossFormulaParamDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().update(getNamespace() + ".update", t);
	}

	@Override
	public TransformerLossFormulaParamDomain findByKey(TransformerLossFormulaParamDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().load(getNamespace() + ".findByKey", t);
	}

	@Override
	@PaginationSupport
	public List<TransformerLossFormulaParamDomain> findByWhere(TransformerLossFormulaParamDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().find(getNamespace() + ".findByWhere", t);
	}

}
