/**
 *    Auth:riozenc
 *    Date:2019年3月12日 下午7:16:23
 *    Title:com.riozenc.cim.webapp.service.impl.SettlementServiceImpl.java
 **/
package org.fms.cim.webapp.archives.service.impl;

import java.util.List;

import org.fms.cim.webapp.archives.dao.LineDAO;
import org.fms.cim.webapp.archives.dao.SubsLineRelaDAO;
import org.fms.cim.webapp.archives.domain.LineDomain;
import org.fms.cim.webapp.archives.domain.SubsLineRelaDomain;
import org.fms.cim.webapp.archives.service.ILineService;

import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.annotation.TransactionService;

@TransactionService
public class LineServiceImpl implements ILineService {

	@TransactionDAO
	private LineDAO lineDAO;
	@TransactionDAO
	private SubsLineRelaDAO subsLineRelaDAO;


	@Override
	public int insert(LineDomain t) {
		// TODO Auto-generated method stub
		return lineDAO.insert(t);
	}

	@Override
	public int delete(LineDomain t) {
		// TODO Auto-generated method stub
		return lineDAO.delete(t);
	}

	@Override
	public int update(LineDomain t) {
		// TODO Auto-generated method stub
		return lineDAO.update(t);
	}

	@Override
	public LineDomain findByKey(LineDomain t) {
		// TODO Auto-generated method stub
		return lineDAO.findByKey(t);
	}

	@Override
	public List<LineDomain> findByWhere(LineDomain t) {
		// TODO Auto-generated method stub
		return lineDAO.findByWhere(t);
	}

	@Override
	public List<LineDomain> findByLineCode(LineDomain t) {
		// TODO Auto-generated method stub
		return lineDAO.findByLineCode(t);
	}

	public boolean addLineAndSubsLineRela(LineDomain line,SubsLineRelaDomain t) {
		// TODO Auto-generated method stub
		int lcount =  lineDAO.insert(line);

		if(lcount<=0) {
			return false;
		}

		t.setLineId(line.getId());
		int r = subsLineRelaDAO.insert(t);
		if(r>0) {
			return true;

		}

		return true;
	}

	public boolean updateLineAndSubsLineRela(LineDomain line,SubsLineRelaDomain t) {


		int rcount = subsLineRelaDAO.update(t);
		int lcount = lineDAO.update(line);

		if(lcount>0) {
			return true;
		}

		return false;
	}

	@Override
	public List<LineDomain> findByLineIds(LineDomain e){
		return lineDAO.findByLineIds(e);
	}
}
