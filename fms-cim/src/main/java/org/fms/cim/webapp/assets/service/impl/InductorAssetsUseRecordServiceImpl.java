/**
 *    Auth:riozenc
 *    Date:2019年3月12日 下午7:16:23
 *    Title:com.riozenc.cim.webapp.service.impl.SettlementServiceImpl.java
 **/
package org.fms.cim.webapp.assets.service.impl;

import java.util.List;

import com.riozenc.cim.webapp.assets.dao.InductorAssetsDAO;
import com.riozenc.cim.webapp.assets.dao.InductorAssetsUseRecordDAO;
import com.riozenc.cim.webapp.assets.domain.InductorAssetsDomain;
import com.riozenc.cim.webapp.assets.domain.InductorAssetsUseRecordDomain;
import com.riozenc.cim.webapp.assets.service.IInductorAssetsUseRecordService;
import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.annotation.TransactionService;

@TransactionService
public class InductorAssetsUseRecordServiceImpl implements IInductorAssetsUseRecordService {
	
	@TransactionDAO
	private InductorAssetsUseRecordDAO inductorAssetsUseRecordDAO;
	@TransactionDAO
	private InductorAssetsDAO inductorAssetsDAO;
	
	@Override
	public int insert(InductorAssetsUseRecordDomain e) {
		//添加领用记录
		int iaur = inductorAssetsUseRecordDAO.insert(e);
		//TODO 更新状态为领出待装
		if(iaur == 1) {
			InductorAssetsDomain iad = new InductorAssetsDomain();
			iad.setId(e.getInductorAssetsId());
			iad.setStatus((byte) 6);//领出待装
			iaur = inductorAssetsDAO.update(iad);
		}
		
		return iaur;
	}

	@Override
	public int delete(InductorAssetsUseRecordDomain e) {

		return inductorAssetsUseRecordDAO.delete(e);
	}

	@Override
	public int update(InductorAssetsUseRecordDomain e) {

		return inductorAssetsUseRecordDAO.update(e);
	}

	@Override
	public InductorAssetsUseRecordDomain findByKey(InductorAssetsUseRecordDomain e) {

		return inductorAssetsUseRecordDAO.findByKey(e);
	}

	@Override
	public List<InductorAssetsUseRecordDomain> findByWhere(InductorAssetsUseRecordDomain e) {

		return inductorAssetsUseRecordDAO.findByWhere(e);
	}


}
