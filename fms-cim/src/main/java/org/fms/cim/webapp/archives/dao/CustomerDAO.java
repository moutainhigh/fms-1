/**
 *    Auth:riozenc
 *    Date:2019年3月8日 下午3:28:46
 *    Title:com.riozenc.cim.webapp.dao.CustomerDAO.java
 **/
package org.fms.cim.webapp.archives.dao;

import java.util.List;

import org.apache.ibatis.session.ExecutorType;

import com.riozenc.cim.api.annotation.SynchronizeTrigger;
import com.riozenc.cim.webapp.archives.domain.CustomerDomain;
import com.riozenc.titanTool.annotation.PaginationSupport;
import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.spring.webapp.dao.AbstractTransactionDAOSupport;
import com.riozenc.titanTool.spring.webapp.dao.BaseDAO;

@TransactionDAO()
public class CustomerDAO extends AbstractTransactionDAOSupport implements BaseDAO<CustomerDomain> {

	@Override
	@SynchronizeTrigger
	public int insert(CustomerDomain t) {
		return getPersistanceManager().insert(getNamespace() + ".insert", t);
	}
	
	public int insertList(List<CustomerDomain> tl) {
		return getPersistanceManager(ExecutorType.BATCH).insertList(getNamespace() + ".insert", tl);
	}

	@Override
	public int delete(CustomerDomain t) {
		return getPersistanceManager().delete(getNamespace() + ".delete", t);
	}

	@Override
	@SynchronizeTrigger
	public int update(CustomerDomain t) {
		return getPersistanceManager().update(getNamespace() + ".update", t);
	}

	@Override
	public CustomerDomain findByKey(CustomerDomain t) {
		return getPersistanceManager().load(getNamespace() + ".findByKey", t);
	}

	@Override
	@PaginationSupport
	public List<CustomerDomain> findByWhere(CustomerDomain t) {
		return getPersistanceManager().find(getNamespace() + ".findByWhere", t);
	}

	public List<CustomerDomain> getCustomerByIds(List<String> tl) {
		return getPersistanceManager().find(getNamespace() + ".getCustomerByIds", tl);
	}

	public List<CustomerDomain> customerNoDC(CustomerDomain customerDomain) {
		return getPersistanceManager().find(getNamespace() + ".customerNoDC", customerDomain);
	}

	@PaginationSupport
	public List<CustomerDomain> getCustomerByNo(CustomerDomain customerDomain) {
		return getPersistanceManager().find(getNamespace() + ".getCustomerByNo", customerDomain);
	}

}
