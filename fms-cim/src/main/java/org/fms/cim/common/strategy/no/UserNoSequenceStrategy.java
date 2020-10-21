package org.fms.cim.common.strategy.no;

import com.riozenc.cim.web.util.CommonUtil;
import com.riozenc.cim.webapp.archives.domain.SysSequenceNoDomain;
import com.riozenc.cim.webapp.archives.service.ISysSequenceNoService;
import com.riozenc.titanTool.annotation.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;

/**
 * 互感器号生成规则 年份+6位流水号 如2019000001
 */
@TransactionService
public class UserNoSequenceStrategy implements SequenceStrategy{

    @Autowired
    @Qualifier("sysSequenceNoServiceImpl")
    private ISysSequenceNoService sysSequenceNoService;

    //户号生成规则
    //营业区域后三位+年+月+日+自增长四位
    @Override
    public String generateSequenceNo(String busi) {
        //去营业区域后三位
        int busiLenth = busi.length();
        String noPre = null;
        if (busiLenth >= 3) {
            noPre = busi.substring(busiLenth - 3, busiLenth);
        } else {
            noPre = busi;
        }
        String userNoCode = "APP" + noPre;
        SysSequenceNoDomain sysSequenceNo = new SysSequenceNoDomain();
        sysSequenceNo.setCode(userNoCode);
        sysSequenceNo.setFormat("0000");
        sysSequenceNo.setPrefix(noPre);
        sysSequenceNo.setName("工作单号(yk_gddl)");
        String maxNo = sysSequenceNoService.genSequenceNo(sysSequenceNo);
        return noPre + CommonUtil.getYMD().get(0).substring(2, 4) + CommonUtil.getYMD().get(1) + CommonUtil.getYMD().get(2) + maxNo;

    }

    //户号生成规则
    //营业区域后三位+年+月+日+自增长四位
    @Override
    public String generateSequenceNo(String busi, int size) {
        //去营业区域后三位
        int busiLenth = busi.length();
        String noPre = null;
        if (busiLenth >= 3) {
            noPre = busi.substring(busiLenth - 3, busiLenth);
        } else {
            noPre = busi;
        }
        String userNoCode = "APP" + noPre;
        SysSequenceNoDomain sysSequenceNo = new SysSequenceNoDomain();
        sysSequenceNo.setCode(userNoCode);
        sysSequenceNo.setFormat("0000");
        sysSequenceNo.setPrefix(noPre);
        sysSequenceNo.setName("工作单号(yk_gddl)");
        String maxNo = sysSequenceNoService.genSequenceNo(sysSequenceNo,  size);
        return noPre + CommonUtil.getYMD().get(0).substring(2, 4) + CommonUtil.getYMD().get(1) + CommonUtil.getYMD().get(2) + maxNo;

    }
}
