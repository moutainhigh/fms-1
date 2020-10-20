/**
 *    Auth:riozenc
 *    Date:2019年3月8日 下午3:38:03
 *    Title:com.riozenc.cim.webapp.service.ICustomerService.java
 **/
package org.fms.cim.webapp.archives.service;

import com.riozenc.cim.webapp.archives.domain.SystemCommonConfigDomain;
import com.riozenc.titanTool.spring.webapp.service.BaseService;

public interface ISystemCommonConfigService extends BaseService<SystemCommonConfigDomain> {

    public SystemCommonConfigDomain findByKeyValue(String type, Long key);
}
