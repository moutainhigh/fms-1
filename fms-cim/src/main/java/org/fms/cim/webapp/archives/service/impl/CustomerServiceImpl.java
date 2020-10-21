/**
 *    Auth:riozenc
 *    Date:2019年3月8日 下午3:38:49
 *    Title:com.riozenc.cim.webapp.service.impl.CustomerServiceImpl.java
 **/
package org.fms.cim.webapp.archives.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.fms.cim.webapp.archives.dao.CustomerDAO;
import org.fms.cim.webapp.archives.domain.CustomerDomain;
import org.fms.cim.webapp.archives.service.ICustomerService;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.annotation.TransactionService;

@TransactionService
public class CustomerServiceImpl implements ICustomerService {

	@TransactionDAO("master")
	private CustomerDAO customerDAO;

	@Override
	public int insert(CustomerDomain t) {
		return customerDAO.insert(t);
	}
	
	@Override
	public int insertList(List<CustomerDomain> tl) {
		return customerDAO.insertList(tl);
	}

	@Override
	public int delete(CustomerDomain t) {
		return customerDAO.delete(t);
	}

	@Override
	public int update(CustomerDomain t) {
		return customerDAO.update(t);
	}

	@Override
	public CustomerDomain findByKey(CustomerDomain t) {
		return customerDAO.findByKey(t);
	}

	@Override
	public List<CustomerDomain> findByWhere(CustomerDomain t) {
		return customerDAO.findByWhere(t);
	}

	@Override
	public List<CustomerDomain> getCustomerByIds(String s) throws JsonParseException, JsonMappingException, IOException {
		// TODO 处理字符串
		String[] tarr = s.split(",");
		List<String> idsList = Arrays.asList(tarr);
		List<CustomerDomain> rList = new ArrayList<CustomerDomain>();
		//处理超过1000个id
		int len = tarr.length;
		for(int m=0;m<len/999+1;m++) {//遍历次数
			
			List<String> tl = idsList.subList(m, m+999>len?len:m+999);
			
			List<CustomerDomain> tList = customerDAO.getCustomerByIds(tl);
			rList.addAll(tList);
		}

		return rList;
		
	}

	@Override
	public List<CustomerDomain> customerNoDC(CustomerDomain customerDomain) {
		return customerDAO.customerNoDC(customerDomain);
	}

	@Override
	public List<CustomerDomain> getCustomerByNo(CustomerDomain customerDomain) {
		return customerDAO.getCustomerByNo(customerDomain);
	}

}
