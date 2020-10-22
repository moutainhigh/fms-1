/**
 *    Auth:riozenc
 *    Date:2019年3月12日 下午7:11:34
 *    Title:com.riozenc.cim.webapp.dao.UserDAO.java
 **/
package org.fms.cim.webapp.archives.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.fms.cim.webapp.archives.domain.UserDomain;

import com.riozenc.titanTool.annotation.PaginationSupport;
import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.spring.webapp.dao.AbstractTransactionDAOSupport;
import com.riozenc.titanTool.spring.webapp.dao.BaseDAO;

@TransactionDAO
public class UserDAO extends AbstractTransactionDAOSupport implements BaseDAO<UserDomain> {

	@Override
	public int insert(UserDomain t) {
		return getPersistanceManager().insert(getNamespace() + ".insert", t);
	}

	@Override
	public int delete(UserDomain t) {
		return getPersistanceManager().delete(getNamespace() + ".delete", t);
	}

	@Override
	public int update(UserDomain t) {
		return getPersistanceManager().update(getNamespace() + ".update", t);
	}

	@Override
	public UserDomain findByKey(UserDomain t) {
		return getPersistanceManager().load(getNamespace() + ".findByKey", t);
	}

	@Override
	@PaginationSupport
	public List<UserDomain> findByWhere(UserDomain t) {
		return getPersistanceManager().find(getNamespace() + ".findByWhere", t);
	}

	@PaginationSupport
	public List<UserDomain> getUserAllInfo(UserDomain userDomain) {
		return getPersistanceManager().find(getNamespace() + ".getUserAllInfo", userDomain);
	}

	public List<UserDomain> getUserByIds(List<Long> idsList) {
		if(idsList==null||idsList.size()==0) {
			return new ArrayList<UserDomain>();
		}
		return getPersistanceManager().find(getNamespace() + ".getUserByIds", idsList);
	}

	/**
	 * {@link #getUserByCustomerIds(List)}
	 * @param idsList
	 * @return
	 */
	@Deprecated
	public List<Long> getUserIdsByCustomerIds(List<Long> idsList) {
		if(idsList==null||idsList.size()==0) {
			return new ArrayList<Long>();
		}
		return getPersistanceManager().find(getNamespace() + ".getUserIdsByCustomerIds", idsList);
	}
	
	public List<UserDomain> getUserByCustomerIds(List<Long> customerIds){
		if(customerIds==null||customerIds.size()==0) {
			return new ArrayList<UserDomain>();
		}
		return getPersistanceManager().find(getNamespace() + ".getUserByCustomerIds", customerIds);
	}

	/**
	 * 建议使用 {@link #getUserByWriteSectIds(List)}
	 * 
	 * @param ids
	 * @return
	 */
	@Deprecated
	public List<Long> getUserIdsByWriteSectIds(List<Long> ids) {
		if(ids==null||ids.size()==0) {
			return new ArrayList<Long>();
		}
		return getPersistanceManager().find(getNamespace() + ".getUserIdsByWriteSectIds", ids);
	}

	public List<UserDomain> getUserByWriteSectIds(List<Long> writeSectIds) {
		if(writeSectIds==null||writeSectIds.size()==0) {
			return new ArrayList<UserDomain>();
		}
		return getPersistanceManager().find(getNamespace() + ".getUserByWriteSectIds", writeSectIds);
	}

	public List<Long> getDeptIdsByUserIds(List<Long> idsList) {
		if(idsList==null||idsList.size()==0) {
			return new ArrayList<Long>();
		}
		return getPersistanceManager().find(getNamespace() + ".getDeptIdsByUserIds", idsList);

	}

	public List<UserDomain> userNoDC(UserDomain userDomain) {
		return getPersistanceManager().find(getNamespace() + ".userNoDC", userDomain);

	}

	public List<UserDomain> findByNo(UserDomain t) {
		return getPersistanceManager().find(getNamespace() + ".findByNo", t);
	}

	public int insertList(List<UserDomain> userList) {
		return getPersistanceManager(ExecutorType.BATCH).insertList(getNamespace() + ".insert", userList);

	}

	@PaginationSupport
	public List<UserDomain> getUsersByCustomerNo(UserDomain userDomain) {
		return getPersistanceManager().find(getNamespace()+".getUsersByCustomerNo", userDomain);
	}

}
