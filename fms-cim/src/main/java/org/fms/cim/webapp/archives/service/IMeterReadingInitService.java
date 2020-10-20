package org.fms.cim.webapp.archives.service;

import java.util.List;

import com.riozenc.cim.webapp.archives.domain.WriteSectDomain;
import com.riozenc.titanTool.spring.web.http.HttpResult;

public interface IMeterReadingInitService {

	public HttpResult meterReadingInitByCustomer(List<Long> ids,String sn);

	public HttpResult meterReadingInitByWriteSec(List<Long> ids,String sn);

	public HttpResult getInitMetersByWriteSect(WriteSectDomain e);
	
	
}
 