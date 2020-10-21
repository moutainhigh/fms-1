/**
 *    Auth:riozenc
 *    Date:2019年3月12日 下午7:15:22
 *    Title:com.riozenc.cim.webapp.service.ISettlementService.java
 **/
package org.fms.cim.webapp.archives.service;

import java.util.List;

import org.fms.cim.webapp.archives.domain.WriteSectDomain;

import com.riozenc.titanTool.spring.web.http.HttpResult;
import com.riozenc.titanTool.spring.webapp.service.BaseService;

public interface IWriteSectService extends BaseService<WriteSectDomain> {

	public List<Long> getDeptIdsByWriteSectIds(List<Long> ids);

	public HttpResult getMeterInitSituation(List<WriteSectDomain> l,Integer mon) ;

	public int insertList(List<WriteSectDomain> wsList);

}
