/**
 *    Auth:riozenc
 *    Date:2019年3月8日 下午3:38:03
 *    Title:com.riozenc.cim.webapp.service.ICustomerService.java
 **/
package org.fms.cim.webapp.archives.service;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.riozenc.cim.webapp.archives.domain.CustomerDomain;
import com.riozenc.titanTool.spring.webapp.service.BaseService;

public interface ICustomerService extends BaseService<CustomerDomain> {

	List<CustomerDomain> getCustomerByIds(String s) throws JsonParseException, JsonMappingException, IOException;

	List<CustomerDomain> customerNoDC(CustomerDomain customerDomain);

	int insertList(List<CustomerDomain> customerList);

	List<CustomerDomain> getCustomerByNo(CustomerDomain customerDomain);

}
