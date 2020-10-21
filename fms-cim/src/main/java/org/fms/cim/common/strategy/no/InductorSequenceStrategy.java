package org.fms.cim.common.strategy.no;

import com.riozenc.cim.web.util.CommonUtil;
import com.riozenc.cim.webapp.archives.domain.SysSequenceNoDomain;
import com.riozenc.cim.webapp.archives.service.ISysSequenceNoService;
import com.riozenc.titanTool.annotation.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * 互感器号生成规则 年份+6位流水号 如2019000001
 */
@TransactionService
public class InductorSequenceStrategy implements SequenceStrategy{

    @Autowired
    @Qualifier("sysSequenceNoServiceImpl")
    private ISysSequenceNoService sysSequenceNoService;
    @Override
    public String generateSequenceNo(String condition) {
        String mon=CommonUtil.getYMD().get(0)+CommonUtil.getYMD().get(1);
        SysSequenceNoDomain sysSequenceNo = new SysSequenceNoDomain();
        sysSequenceNo.setCode("INDUCTOR");
        sysSequenceNo.setFormat("000000");
        sysSequenceNo.setMon(mon);
        sysSequenceNo.setName("互感器号规则(HG)");
        String maxNo = sysSequenceNoService.genSequenceNo(sysSequenceNo);
        return mon+maxNo;
    }

    @Override
    public String generateSequenceNo(String condition, int size) {
        String mon=CommonUtil.getYMD().get(0)+CommonUtil.getYMD().get(1);
        SysSequenceNoDomain sysSequenceNo = new SysSequenceNoDomain();
        sysSequenceNo.setCode("INDUCTOR");
        sysSequenceNo.setFormat("000000");
        sysSequenceNo.setMon(mon);
        sysSequenceNo.setName("互感器号规则(HG)");
        String maxNo =  sysSequenceNoService.genSequenceNo(sysSequenceNo, size);
        return maxNo;
    }
}
