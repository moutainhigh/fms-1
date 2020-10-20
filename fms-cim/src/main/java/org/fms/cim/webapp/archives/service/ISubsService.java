/**
 *    Auth:riozenc
 *    Date:2019年3月8日 下午3:38:03
 *    Title:com.riozenc.cim.webapp.service.ICustomerService.java
 **/
package org.fms.cim.webapp.archives.service;

import com.riozenc.cim.webapp.archives.domain.SubsDomain;
import com.riozenc.titanTool.spring.webapp.service.BaseService;

import java.util.List;

public interface ISubsService extends BaseService<SubsDomain> {

    public List<SubsDomain> findByNo(SubsDomain subsDomain);
}
