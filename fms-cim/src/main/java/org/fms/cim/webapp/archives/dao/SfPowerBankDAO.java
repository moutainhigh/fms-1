/**
 *    Auth:riozenc
 *    Date:2019年3月8日 下午3:28:46
 *    Title:com.riozenc.cim.webapp.dao.CustomerDAO.java
 **/
package org.fms.cim.webapp.archives.dao;

import java.util.List;

import org.fms.cim.webapp.archives.domain.SfPowerBankDomain;

import com.riozenc.titanTool.annotation.PaginationSupport;
import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.spring.webapp.dao.AbstractTransactionDAOSupport;
import com.riozenc.titanTool.spring.webapp.dao.BaseDAO;

@TransactionDAO()
public class SfPowerBankDAO extends AbstractTransactionDAOSupport implements BaseDAO<SfPowerBankDomain> {

	@Override
	public int insert(SfPowerBankDomain t) {
		return getPersistanceManager().insert(getNamespace() + ".insert", t);
	}

	@Override
	public int delete(SfPowerBankDomain t) {
		return getPersistanceManager().delete(getNamespace() + ".delete", t);
	}

	@Override
	public int update(SfPowerBankDomain t) {
		return getPersistanceManager().update(getNamespace() + ".update", t);
	}

	@Override
	public SfPowerBankDomain findByKey(SfPowerBankDomain t) {
		return getPersistanceManager().load(getNamespace() + ".findByKey", t);
	}

	@Override
	@PaginationSupport
	public List<SfPowerBankDomain> findByWhere(SfPowerBankDomain t) {
		return getPersistanceManager().find(getNamespace() + ".findByWhere", t);
	}

}
