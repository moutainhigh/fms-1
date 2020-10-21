/**
 * Author : czy
 * Date : 2019年8月23日 下午3:54:16
 * Title : com.riozenc.cim.webapp.archives.service.impl.TransformerMeterRelationServiceImpl.java
 *
**/
package org.fms.cim.webapp.archives.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.fms.cim.webapp.archives.dao.TransformerMeterRelationDAO;
import org.fms.cim.webapp.archives.domain.MeterDomain;
import org.fms.cim.webapp.archives.domain.TransformerDomain;
import org.fms.cim.webapp.archives.domain.TransformerLineRelDomain;
import org.fms.cim.webapp.archives.domain.TransformerMeterRelationDomain;
import org.fms.cim.webapp.archives.service.ITransformerMeterRelationService;

import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.annotation.TransactionService;

@TransactionService
public class TransformerMeterRelationServiceImpl implements ITransformerMeterRelationService {
	@TransactionDAO
	private TransformerMeterRelationDAO transformerMeterRelationDAO;

	@Override
	public int insert(TransformerMeterRelationDomain t) {
		return transformerMeterRelationDAO.insert(t);
	}

	@Override
	public int delete(TransformerMeterRelationDomain t) {
		return transformerMeterRelationDAO.delete(t);
	}

	@Override
	public int update(TransformerMeterRelationDomain t) {
		return transformerMeterRelationDAO.update(t);
	}

	@Override
	public TransformerMeterRelationDomain findByKey(TransformerMeterRelationDomain t) {
		return transformerMeterRelationDAO.findByKey(t);
	}

	@Override
	public List<TransformerMeterRelationDomain> findByWhere(TransformerMeterRelationDomain t) {
		return transformerMeterRelationDAO.findByWhere(t);
	}

	@Override
	public List<TransformerMeterRelationDomain> getTransformerMeterRelationByWriteSectId(List<Long> writeSectIds) {
		return transformerMeterRelationDAO.getTransformerMeterRelationByWriteSectId(writeSectIds);
	}

	@Override
	public List<TransformerMeterRelationDomain> getTransformerMeterRelationByMeterIds(List<Long> meterIds) {

		List<TransformerMeterRelationDomain> rList = new ArrayList<TransformerMeterRelationDomain>();
		// 处理超过1000个id
		int len = meterIds.size();
		for (int m = 0; m < len / 999 + 1; m++) {// 遍历次数

			List<Long> tl = meterIds.subList(m*999, (m+1)*999 > len ? len : (m+1)*999);

			List<TransformerMeterRelationDomain> tList = transformerMeterRelationDAO.getTransformerMeterRelationByMeterIds(tl);
			rList.addAll(tList);
		}
		//去重
		
		return rList;
		
	}

	@Override
	public int insertList(List<TransformerMeterRelationDomain> tmrList) {
		return transformerMeterRelationDAO.insertList(tmrList);

	}
	@Override
	public List<TransformerMeterRelationDomain> getDistinctTransGroupNo(TransformerMeterRelationDomain t) {
		return transformerMeterRelationDAO.getDistinctTransGroupNo(t);

	}
	@Override
	public List<MeterDomain> getMeterByTransformer(TransformerDomain t) {
		return transformerMeterRelationDAO.getMeterByTransformer(t);

	}

	@Override
	public int updateList(List<TransformerMeterRelationDomain> tmruList) {
		return transformerMeterRelationDAO.updateList(tmruList);

	}
	@Override
	public List<TransformerLineRelDomain> findTransformerLineByMeterIds(List<Long> meterIds) {
		return transformerMeterRelationDAO.findTransformerLineByMeterIds(meterIds);

	}
	

}
