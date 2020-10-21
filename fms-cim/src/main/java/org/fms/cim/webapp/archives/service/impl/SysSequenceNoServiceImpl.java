/**
 *    Auth:riozenc
 *    Date:2019年3月8日 下午3:38:49
 *    Title:com.riozenc.cim.webapp.service.impl.CustomerServiceImpl.java
 **/
package org.fms.cim.webapp.archives.service.impl;

import java.text.DecimalFormat;
import java.util.List;

import org.fms.cim.webapp.archives.dao.SysSequenceNoDAO;
import org.fms.cim.webapp.archives.domain.SysSequenceNoDomain;
import org.fms.cim.webapp.archives.service.ISysSequenceNoService;

import com.riozenc.cim.web.util.CommonUtil;
import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.annotation.TransactionService;

@TransactionService
public class SysSequenceNoServiceImpl implements ISysSequenceNoService {

	@TransactionDAO("master")
	private SysSequenceNoDAO sysSequenceNoDAO;

	@Override
	public int insert(SysSequenceNoDomain t) {
		// TODO Auto-generated method stub
		return sysSequenceNoDAO.insert(t);
	}

	@Override
	public int delete(SysSequenceNoDomain t) {
		// TODO Auto-generated method stub
		return sysSequenceNoDAO.delete(t);
	}

	@Override
	public int update(SysSequenceNoDomain t) {
		// TODO Auto-generated method stub
		return sysSequenceNoDAO.update(t);
	}

	@Override
	public SysSequenceNoDomain findByKey(SysSequenceNoDomain t) {
		// TODO Auto-generated method stub
		return sysSequenceNoDAO.findByKey(t);
	}

	@Override
	public List<SysSequenceNoDomain> findByWhere(SysSequenceNoDomain t) {
		// TODO Auto-generated method stub
		return sysSequenceNoDAO.findByWhere(t);
	}

	//获得序列号
    @Override
	public String genSequenceNo(SysSequenceNoDomain inputSys) {
		List<SysSequenceNoDomain> sysSequenceNo =sysSequenceNoDAO.findByWhere(inputSys);
		Integer maxNo = new Integer(0);
		String outputValue = "";
		String format = "";
		//没有最大值则生成
		if (null == sysSequenceNo || sysSequenceNo.size()<1) {
			inputSys.setMaxNo(new Integer(0));
			maxNo = 0;
			format = inputSys.getFormat();
			if(null==inputSys.getMon()||"".equals(inputSys.getMon())){
                inputSys.setMon(CommonUtil.getYMD().get(0)+CommonUtil.getYMD().get(1));
            }
			sysSequenceNoDAO.insert(inputSys);
		} else {
			SysSequenceNoDomain updateSys = new SysSequenceNoDomain();
			updateSys.setId(sysSequenceNo.get(0).getId());
			updateSys.setCode(sysSequenceNo.get(0).getCode());
			updateSys.setMaxNo(sysSequenceNo.get(0).getMaxNo() + 1);
			maxNo = sysSequenceNo.get(0).getMaxNo() + 1;
			format = sysSequenceNo.get(0).getFormat();
			sysSequenceNoDAO.update(updateSys);
		}
		if (null != format) {
			DecimalFormat df = new DecimalFormat(format);
			outputValue = df.format(maxNo);
		} else {
			outputValue = maxNo.toString();
		}
		return outputValue;
	}


    //获得批量序列号
    @Override
    public String genSequenceNo(SysSequenceNoDomain inputSys,int size) {
        List<SysSequenceNoDomain> sysSequenceNo =sysSequenceNoDAO.findByWhere(inputSys);
        Integer maxNo = new Integer(0);
        String outputValue = "";
        String format = "";
        //没有最大值则生成
        if (null == sysSequenceNo || sysSequenceNo.size()<1) {
            inputSys.setMaxNo(size);
            maxNo = 0;
            format = inputSys.getFormat();
            if(null==inputSys.getMon()||"".equals(inputSys.getMon())){
                inputSys.setMon(CommonUtil.getYMD().get(0)+CommonUtil.getYMD().get(1));
            }
            sysSequenceNoDAO.insert(inputSys);
        } else {
            SysSequenceNoDomain updateSys = new SysSequenceNoDomain();
            updateSys.setId(sysSequenceNo.get(0).getId());
            updateSys.setCode(sysSequenceNo.get(0).getCode());
            updateSys.setMaxNo(sysSequenceNo.get(0).getMaxNo() + size);
            maxNo = sysSequenceNo.get(0).getMaxNo() + 1;
            format = sysSequenceNo.get(0).getFormat();
            sysSequenceNoDAO.update(updateSys);
        }
        if (null != format) {
            DecimalFormat df = new DecimalFormat(format);
            outputValue = df.format(maxNo);
        } else {
            outputValue = maxNo.toString();
        }
        return outputValue;
    }
}
