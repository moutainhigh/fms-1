package org.fms.cim.common.strategy.no;

import org.fms.cim.webapp.archives.domain.SysSequenceNoDomain;
import org.fms.cim.webapp.archives.service.ISysSequenceNoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * 收费流水号 15位流水号 如000001
 */
@Service
public class ArrearageNoStrategy implements SequenceStrategy {

	@Autowired
	@Qualifier("sysSequenceNoServiceImpl")
	private ISysSequenceNoService sysSequenceNoService;

	@Override
	public String generateSequenceNo(String condition) {
		SysSequenceNoDomain sysSequenceNo = new SysSequenceNoDomain();
		sysSequenceNo.setCode("ARREARAGE_NO");
		// sysSequenceNo.setFormat("000000000000000");
		sysSequenceNo.setName("欠费流水号(GDDL)");
		// sysSequenceNo.setMon(CommonUtil.getYMD().get(0)+CommonUtil.getYMD()
		// .get(1));
		String maxNo = sysSequenceNoService.genSequenceNo(sysSequenceNo);
		return maxNo;
	}

	@Override
	public String generateSequenceNo(String condition, int size) {
		SysSequenceNoDomain sysSequenceNo = new SysSequenceNoDomain();
		sysSequenceNo.setCode("ARREARAGE_NO");
		// sysSequenceNo.setFormat("000000000000000");
		sysSequenceNo.setName("欠费流水号(GDDL)");
		// sysSequenceNo.setMon(CommonUtil.getYMD().get(0)+CommonUtil.getYMD()
		// .get(1));
		String maxNo = sysSequenceNoService.genSequenceNo(sysSequenceNo, size);
		return maxNo;
	}

}
