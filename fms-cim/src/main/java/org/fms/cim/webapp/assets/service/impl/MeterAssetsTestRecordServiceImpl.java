/**
 *    Auth:riozenc
 *    Date:2019年3月12日 下午7:16:23
 *    Title:com.riozenc.cim.webapp.service.impl.SettlementServiceImpl.java
 **/
package org.fms.cim.webapp.assets.service.impl;

import java.util.List;

import com.riozenc.cim.webapp.archives.dao.SystemCommonConfigDAO;
import com.riozenc.cim.webapp.assets.dao.MeterAssetsDAO;
import com.riozenc.cim.webapp.assets.dao.MeterAssetsTestRecordDAO;
import com.riozenc.cim.webapp.assets.dao.MeterAssetsTestRecordDetailTpDAO;
import com.riozenc.cim.webapp.assets.dao.MeterAssetsTestRecordDetailUpDAO;
import com.riozenc.cim.webapp.assets.domain.MeterAssetsTestRecordDomain;
import com.riozenc.cim.webapp.assets.domain.MeterAssetsDomain;
import com.riozenc.cim.webapp.assets.domain.MeterAssetsTestRecordDetailTpDomain;
import com.riozenc.cim.webapp.assets.domain.MeterAssetsTestRecordDetailUpDomain;
import com.riozenc.cim.webapp.assets.service.IMeterAssetsTestRecordService;
import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.annotation.TransactionService;
import com.riozenc.titanTool.spring.web.http.HttpResult;

@TransactionService
public class MeterAssetsTestRecordServiceImpl implements IMeterAssetsTestRecordService {
	
	@TransactionDAO
	private MeterAssetsTestRecordDAO meterAssetsTestRecordDAO;	
	@TransactionDAO
	private MeterAssetsDAO meterAssetsDAO;
	@TransactionDAO
	private MeterAssetsTestRecordDetailTpDAO meterAssetsTestRecordDetailTpDAO;
	@TransactionDAO
	private MeterAssetsTestRecordDetailUpDAO meterAssetsTestRecordDetailUpDAO;
	@TransactionDAO
	private SystemCommonConfigDAO systemCommonConfigDAO;
	
	@Override
	public int insert(MeterAssetsTestRecordDomain e) {
	
		return meterAssetsTestRecordDAO.insert(e);
	}

	@Override
	public int delete(MeterAssetsTestRecordDomain e) {

		return meterAssetsTestRecordDAO.delete(e);
	}

	@Override
	public int update(MeterAssetsTestRecordDomain e) {

		return meterAssetsTestRecordDAO.update(e);
	}

	@Override
	public MeterAssetsTestRecordDomain findByKey(MeterAssetsTestRecordDomain e) {

		return meterAssetsTestRecordDAO.findByKey(e);
	}

	@Override
	public List<MeterAssetsTestRecordDomain> findByWhere(MeterAssetsTestRecordDomain e) {

		return meterAssetsTestRecordDAO.findByWhere(e);
	}


	@Override
	public HttpResult addMeterAssetsUseRecord(MeterAssetsTestRecordDomain matr,
			MeterAssetsTestRecordDetailUpDomain matrdu, MeterAssetsTestRecordDetailTpDomain matrdt) {
		/**
		 * 1、更改资产状态
		 * 2、插入检定记录
		 * 3、插入明细
		 * 
		 * */
		HttpResult httpResult = new HttpResult<>(HttpResult.ERROR, "看见此信息说明方法有问题.");
		
		return httpResult;
	}

	@Override
	public int testTemp(List<MeterAssetsDomain> meterAssetsList) {

		return meterAssetsDAO.updateList(meterAssetsList);

	}


}
