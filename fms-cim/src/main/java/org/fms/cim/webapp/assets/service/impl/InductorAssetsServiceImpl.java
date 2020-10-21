/**
 *    Auth:riozenc
 *    Date:2019年3月12日 下午7:16:23
 *    Title:com.riozenc.cim.webapp.service.impl.SettlementServiceImpl.java
 **/
package org.fms.cim.webapp.assets.service.impl;

import java.util.List;

import org.fms.cim.webapp.archives.domain.UserDomain;
import org.fms.cim.webapp.assets.dao.InductorAssetsDAO;
import org.fms.cim.webapp.assets.domain.InductorAssetsDomain;
import org.fms.cim.webapp.assets.domain.InductorAssetsEntity;
import org.fms.cim.webapp.assets.service.IInductorAssetsService;

import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.annotation.TransactionService;

@TransactionService
public class InductorAssetsServiceImpl implements IInductorAssetsService {
	
	@TransactionDAO
	private InductorAssetsDAO inductorAssetsDAO;

	@Override
	public int insert(InductorAssetsDomain t) {
		// TODO Auto-generated method stub
		return inductorAssetsDAO.insert(t);
	}

	@Override
	public int delete(InductorAssetsDomain t) {
		// TODO Auto-generated method stub
		return inductorAssetsDAO.delete(t);
	}

	@Override
	public int update(InductorAssetsDomain t) {
		// TODO Auto-generated method stub
		return inductorAssetsDAO.update(t);
	}

	@Override
	public InductorAssetsDomain findByKey(InductorAssetsDomain t) {
		// TODO Auto-generated method stub
		return inductorAssetsDAO.findByKey(t);
	}

	@Override
	public List<InductorAssetsDomain> findByWhere(InductorAssetsDomain t) {
		// TODO Auto-generated method stub
		return inductorAssetsDAO.findByWhere(t);
	}

	@Override
	public List<InductorAssetsDomain> getInductorAssetsByManager(InductorAssetsDomain inductorAssetsDomain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InductorAssetsDomain> getInductorAssetsByUser(UserDomain t) {
		// TODO Auto-generated method stub
		return inductorAssetsDAO.getInductorAssetsByUser(t);
	}

	@Override
	public List<InductorAssetsDomain> assetsNoDC(InductorAssetsDomain t) {
		// TODO Auto-generated method stub
		return inductorAssetsDAO.assetsNoDC(t);
	}

	@Override
	public int updateList(List<InductorAssetsDomain> l) {
		// TODO Auto-generated method stub
		return inductorAssetsDAO.updateList(l);
	}

	@Override
	public InductorAssetsEntity findInductEntityByWhere(String t) {
		// TODO Auto-generated method stub
		return inductorAssetsDAO.findInductEntityByWhere(t);
	}

}
