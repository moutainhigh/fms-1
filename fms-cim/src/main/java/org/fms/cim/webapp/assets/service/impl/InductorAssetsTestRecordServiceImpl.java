/**
 *    Auth:riozenc
 *    Date:2019年3月12日 下午7:16:23
 *    Title:com.riozenc.cim.webapp.service.impl.SettlementServiceImpl.java
 **/
package org.fms.cim.webapp.assets.service.impl;

import java.util.Date;
import java.util.List;

import com.riozenc.cim.webapp.archives.dao.SystemCommonConfigDAO;
import com.riozenc.cim.webapp.assets.dao.InductorAssetsDAO;
import com.riozenc.cim.webapp.assets.dao.InductorAssetsTestRecordDAO;
import com.riozenc.cim.webapp.assets.dao.InductorAssetsTestRecordDetailDAO;
import com.riozenc.cim.webapp.assets.domain.InductorAssetsDomain;
import com.riozenc.cim.webapp.assets.domain.InductorAssetsTestRecordDetailDomain;
import com.riozenc.cim.webapp.assets.domain.InductorAssetsTestRecordDomain;
import com.riozenc.cim.webapp.assets.service.IInductorAssetsTestRecordService;
import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.annotation.TransactionService;
import com.riozenc.titanTool.spring.web.http.HttpResult;

@TransactionService
public class InductorAssetsTestRecordServiceImpl implements IInductorAssetsTestRecordService {
	
	@TransactionDAO
	private InductorAssetsTestRecordDAO inductorAssetsTestRecordDAO;
	@TransactionDAO
	private InductorAssetsTestRecordDetailDAO inductorAssetsTestRecordDetailDAO;
	@TransactionDAO
	private InductorAssetsDAO inductorAssetsDAO;
	@TransactionDAO
	private SystemCommonConfigDAO systemCommonConfigDAO;
	
	@Override
	public int insert(InductorAssetsTestRecordDomain e) {
		//添加检定记录
		int iaur = inductorAssetsTestRecordDAO.insert(e);
		//TODO 更新状态为库存待装
		if(iaur == 1) {
			InductorAssetsDomain iad = new InductorAssetsDomain();
		//	iad.setId(e.getInductorAssetsId());
			iad.setStatus((byte) 5);//库存待装
			iaur = inductorAssetsDAO.update(iad);
		}
		
		return iaur;
	}

	@Override
	public int delete(InductorAssetsTestRecordDomain e) {

		return inductorAssetsTestRecordDAO.delete(e);
	}

	@Override
	public int update(InductorAssetsTestRecordDomain e) {

		return inductorAssetsTestRecordDAO.update(e);
	}

	@Override
	public InductorAssetsTestRecordDomain findByKey(InductorAssetsTestRecordDomain e) {

		return inductorAssetsTestRecordDAO.findByKey(e);
	}

	@Override
	public List<InductorAssetsTestRecordDomain> findByWhere(InductorAssetsTestRecordDomain e) {

		return inductorAssetsTestRecordDAO.findByWhere(e);
	}

	@Override
	public HttpResult addInductorAssetsUseRecord(InductorAssetsTestRecordDomain iatr,
			List<InductorAssetsTestRecordDetailDomain> iatrdList) {
		/**
		 * 1、更改资产状态
		 * 2、插入检定记录
		 * 3、插入明细
		 * 
		 * */
		HttpResult httpResult = new HttpResult<>(HttpResult.ERROR, "看见此信息说明方法有问题.");
	
		//1、更改资产状态
		InductorAssetsDomain tempIa = new InductorAssetsDomain();
		if(iatr.getInductorAssetsID1()!=null) {
			tempIa.setId(iatr.getInductorAssetsID1());
			tempIa.setStatus((byte) 2);
			if(inductorAssetsDAO.update(tempIa)<1) {
				httpResult.setMessage("资产状态更新失败");
				return httpResult;
			}	
		}
		if(iatr.getInductorAssetsID2()!=null) {
			tempIa.setId(iatr.getInductorAssetsID1());
			tempIa.setStatus((byte) 2);
			if(inductorAssetsDAO.update(tempIa)<1) {
				httpResult.setMessage("资产状态更新失败");
				return httpResult;
			}	
		}
		if(iatr.getInductorAssetsID3()!=null) {
			tempIa.setId(iatr.getInductorAssetsID1());
			tempIa.setStatus((byte) 2);
			if(inductorAssetsDAO.update(tempIa)<1) {
				httpResult.setMessage("资产状态更新失败");
				return httpResult;
			}	
		}
		//2、插入检定记录
		iatr.setCreateDate(new Date());
		iatr.setStatus((byte) 1);
		if(inductorAssetsTestRecordDAO.insert(iatr)<1) {
			httpResult.setMessage("插入检定记录失败");
			return httpResult;
		}
		//3、插入明细
		for(InductorAssetsTestRecordDetailDomain iatrd: iatrdList) {
			if(inductorAssetsTestRecordDetailDAO.insert(iatrd)<1) {
				httpResult.setMessage("插入检定记录明细失败");
				return httpResult;
			}
		}
		httpResult.setStatusCode(HttpResult.SUCCESS);
		httpResult.setMessage("资产状态更新成功，插入检定记录成功，插入检定记录明细成功");

		return httpResult;
	}


}
