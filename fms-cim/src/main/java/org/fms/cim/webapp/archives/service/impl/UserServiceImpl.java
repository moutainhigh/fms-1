/**
 *    Auth:riozenc
 *    Date:2019年3月12日 下午7:16:33
 *    Title:com.riozenc.cim.webapp.service.impl.UserServiceImpl.java
 **/
package org.fms.cim.webapp.archives.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.fms.cim.webapp.archives.dao.CustomerDAO;
import org.fms.cim.webapp.archives.dao.UserDAO;
import org.fms.cim.webapp.archives.domain.CustomerDomain;
import org.fms.cim.webapp.archives.domain.UserDomain;
import org.fms.cim.webapp.archives.service.IUserService;

import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.annotation.TransactionService;

@TransactionService
public class UserServiceImpl implements IUserService {

	@TransactionDAO
	private UserDAO userDAO;
	@TransactionDAO
	private CustomerDAO customerDAO;

	@Override
	public int insert(UserDomain t) {
		return userDAO.insert(t);
	}

	@Override
	public int delete(UserDomain t) {
		return userDAO.delete(t);
	}

	@Override
	public int update(UserDomain t) {
		return userDAO.update(t);
	}

	@Override
	public UserDomain findByKey(UserDomain t) {
		return userDAO.findByKey(t);
	}

	@Override
	public List<UserDomain> findByWhere(UserDomain t) {
		return userDAO.findByWhere(t);
	}

	@Override
	public List<UserDomain> findByNo(UserDomain t) {
		return userDAO.findByNo(t);
	}

	@Override
	public List<UserDomain> getUserAllInfo(UserDomain userDomain) {
		return userDAO.getUserAllInfo(userDomain);
	}

	@Override
	public boolean updateUserCustomer(UserDomain userDomain, CustomerDomain customerDomain) {
		int userCount = userDAO.update(userDomain);
		int customerCount = customerDAO.update(customerDomain);
		if (userCount == 1 || customerCount == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean addUserCustomer(UserDomain userDomain, CustomerDomain customerDomain) {
		int userCount = userDAO.insert(userDomain);
		int customerCount = customerDAO.insert(customerDomain);
		if (userCount == 1 || customerCount == 1) {
			return true;
		}
		return false;

	}

	@Override
	public List<UserDomain> getUserByIds(List<Long> ids) {

		List<UserDomain> rl = new ArrayList<UserDomain>();

		// 处理超过1000个id
		int len = ids.size();
		for (int m = 0; m < len / 999 + 1; m++) {// 遍历次数
			// 根据用户ID获取用户档案 subList方法包含fromIndex, 不包含 toIndex
			List<Long> tl = ids.subList(m * 999, (m + 1) * 999 > len ? len : (m + 1) * 999);

			List<UserDomain> tempList = userDAO.getUserByIds(tl);

			if (tempList.size() == 0 || tempList == null || tempList.get(0) == null) {
				continue;
			}

			rl.addAll(tempList);

		}
		return rl;

	}

	@Override
	public List<Long> getUserIdsByCustomerIds(List<Long> ids) {
		return userDAO.getUserIdsByCustomerIds(ids);
	}
	
	public List<UserDomain> getUserByCustomerIds(List<Long> customerIds){
		return userDAO.getUserByCustomerIds(customerIds);
	}

	@Override
	public List<Long> getUserIdsByWriteSectIds(List<Long> ids) {
		return userDAO.getUserIdsByWriteSectIds(ids);

	}

	public List<UserDomain> getUserByWriteSectIds(List<Long> writeSectIds) {
		return userDAO.getUserByWriteSectIds(writeSectIds);
	}

	@Override
	public List<Long> getDeptIdsByUserIds(List<Long> idsList) {
		return userDAO.getDeptIdsByUserIds(idsList);
	}

	@Override
	public List<UserDomain> getUsersByCustomerNo(UserDomain userDomain) {
		return userDAO.getUsersByCustomerNo(userDomain);
	}

	@Override
	public List<UserDomain> userNoDC(UserDomain userDomain) {
		return userDAO.userNoDC(userDomain);
	}

	@Override
	public int insertList(List<UserDomain> userList) {
		return userDAO.insertList(userList);
	}

}
