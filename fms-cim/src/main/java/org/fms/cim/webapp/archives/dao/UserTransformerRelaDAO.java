/**
 *    Auth:riozenc
 *    Date:2019年3月12日 下午7:11:34
 *    Title:com.riozenc.cim.webapp.dao.UserDAO.java
 **/
package org.fms.cim.webapp.archives.dao;

import java.util.List;

import org.fms.cim.webapp.archives.domain.UserDomain;
import org.fms.cim.webapp.archives.domain.UserTransformerRelaDomain;

import com.riozenc.cim.api.annotation.SynchronizeTrigger;
import com.riozenc.titanTool.annotation.PaginationSupport;
import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.spring.webapp.dao.AbstractTransactionDAOSupport;
import com.riozenc.titanTool.spring.webapp.dao.BaseDAO;

@TransactionDAO
public class UserTransformerRelaDAO extends AbstractTransactionDAOSupport implements BaseDAO<UserTransformerRelaDomain> {

	@Override
	@SynchronizeTrigger
	public int insert(UserTransformerRelaDomain t) {

		return getPersistanceManager().insert(getNamespace() + ".insert", t);
	}

	@Override
	public int delete(UserTransformerRelaDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().delete(getNamespace() + ".delete", t);
	}

	@Override
	@SynchronizeTrigger
	public int update(UserTransformerRelaDomain t) {
		return getPersistanceManager().update(getNamespace() + ".update", t);
	}

	@Override
	public UserTransformerRelaDomain findByKey(UserTransformerRelaDomain t) {
		return getPersistanceManager().load(getNamespace() + ".findByKey", t);
	}

	@Override
	@PaginationSupport
	public List<UserTransformerRelaDomain> findByWhere(UserTransformerRelaDomain t) {
		return getPersistanceManager().find(getNamespace() + ".findByWhere", t);
	}
	@PaginationSupport
	public List<UserTransformerRelaDomain> getUserTransformerRelaByUser(UserDomain t) {
		return getPersistanceManager().find(getNamespace() + ".getUserTransformerRelaByUser", t);
	}

	@SynchronizeTrigger
	public int saveUserTransformerRela(UserTransformerRelaDomain tt) {
		return getPersistanceManager().insert(getNamespace() + ".saveUserTransformerRela", tt);

	}

}
