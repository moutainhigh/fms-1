package org.fms.cim.webapp.archives.service.impl;

import java.util.Date;
import java.util.List;

import org.fms.cim.webapp.archives.dao.TgInfoDAO;
import org.fms.cim.webapp.archives.domain.TgInfoDomain;
import org.fms.cim.webapp.archives.service.ITgInfoService;

import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.annotation.TransactionService;

@TransactionService
public class TgInfoServiceImpl implements ITgInfoService {

    @TransactionDAO
    private TgInfoDAO tgInfoDAO;

    Date now = new Date();

    @Override
    public int insert(TgInfoDomain t) {
        return tgInfoDAO.insert(t);
    }

    @Override
    public int delete(TgInfoDomain t) {
        return tgInfoDAO.delete(t);
    }

    @Override
    public int update(TgInfoDomain t) {
        return tgInfoDAO.update(t);
    }

    @Override
    public TgInfoDomain findByKey(TgInfoDomain t) {
        return tgInfoDAO.findByKey(t);
    }

    @Override
    public List<TgInfoDomain> findByWhere(TgInfoDomain t) {
        return tgInfoDAO.findByWhere(t);
    }

    @Override
    public List<TgInfoDomain> findByNo(TgInfoDomain t) {
        return tgInfoDAO.findByNo(t);
    }
}
