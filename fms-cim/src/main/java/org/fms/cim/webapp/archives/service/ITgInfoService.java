package org.fms.cim.webapp.archives.service;

import com.riozenc.cim.webapp.archives.domain.TgInfoDomain;
import com.riozenc.titanTool.spring.webapp.service.BaseService;

import java.util.List;

public interface ITgInfoService extends BaseService<TgInfoDomain> {
    public List<TgInfoDomain> findByNo(TgInfoDomain t);
}
