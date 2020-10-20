package org.fms.cim.webapp.archives.service.impl;

import java.util.List;

import com.riozenc.cim.webapp.archives.dao.TransformerLossFormulaParamDAO;
import com.riozenc.cim.webapp.archives.domain.TransformerLossFormulaParamDomain;
import com.riozenc.cim.webapp.archives.service.ITransformerLossFormulaParamService;
import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.annotation.TransactionService;

@TransactionService
public class TransformerLossFormulaParamServiceImpl implements ITransformerLossFormulaParamService {
	@TransactionDAO
	private TransformerLossFormulaParamDAO transformerLossParamDAO;

	@Override
	public int insert(TransformerLossFormulaParamDomain t) {
		return transformerLossParamDAO.insert(t);
	}

	@Override
	public int delete(TransformerLossFormulaParamDomain t) {
		return transformerLossParamDAO.delete(t);
	}

	@Override
	public int update(TransformerLossFormulaParamDomain t) {
		return transformerLossParamDAO.update(t);
	}

	@Override
	public TransformerLossFormulaParamDomain findByKey(TransformerLossFormulaParamDomain t) {
		return transformerLossParamDAO.findByKey(t);
	}

	@Override
	public List<TransformerLossFormulaParamDomain> findByWhere(TransformerLossFormulaParamDomain t) {
		return transformerLossParamDAO.findByWhere(t);
	}
}
