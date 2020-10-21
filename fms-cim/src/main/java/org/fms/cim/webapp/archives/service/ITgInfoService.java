package org.fms.cim.webapp.archives.service;

import java.util.List;

import org.fms.cim.webapp.archives.domain.TgInfoDomain;

import com.riozenc.titanTool.spring.webapp.service.BaseService;

public interface ITgInfoService extends BaseService<TgInfoDomain> {
    public List<TgInfoDomain> findByNo(TgInfoDomain t);
}
