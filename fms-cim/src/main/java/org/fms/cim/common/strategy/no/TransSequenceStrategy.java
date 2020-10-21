package org.fms.cim.common.strategy.no;

import com.riozenc.cim.web.util.CommonUtil;
import com.riozenc.cim.webapp.archives.domain.SysSequenceNoDomain;
import com.riozenc.cim.webapp.archives.service.ISysSequenceNoService;
import com.riozenc.titanTool.annotation.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * 变压器号生成规则 6位流水号 如000001
 */
@Service
public class TransSequenceStrategy implements SequenceStrategy{

    @Autowired
    @Qualifier("sysSequenceNoServiceImpl")
    private ISysSequenceNoService sysSequenceNoService;
    @Override
    public String generateSequenceNo(String condition) {
        SysSequenceNoDomain sysSequenceNo = new SysSequenceNoDomain();
        sysSequenceNo.setCode("TRANS");
        sysSequenceNo.setFormat("000000");
        sysSequenceNo.setName("变压器号规则(HG)");
        //sysSequenceNo.setMon(CommonUtil.getYMD().get(0)+CommonUtil.getYMD()
        // .get(1));
        String maxNo = sysSequenceNoService.genSequenceNo(sysSequenceNo);
        return maxNo;
    }

    @Override
    public String generateSequenceNo(String condition,int size) {
        SysSequenceNoDomain sysSequenceNo = new SysSequenceNoDomain();
        sysSequenceNo.setCode("TRANS");
        sysSequenceNo.setFormat("000000");
        sysSequenceNo.setName("变压器号规则(HG)");
        //sysSequenceNo.setMon(CommonUtil.getYMD().get(0)+CommonUtil.getYMD()
        // .get(1));
        String maxNo =  sysSequenceNoService.genSequenceNo(sysSequenceNo, size);
        return maxNo;
    }

}
