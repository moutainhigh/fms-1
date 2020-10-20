/**
 *    Auth:riozenc
 *    Date:2019年3月12日 下午7:16:23
 *    Title:com.riozenc.cim.webapp.service.impl.SettlementServiceImpl.java
 **/
package org.fms.cim.webapp.archives.service.impl;

import com.riozenc.cim.webapp.archives.dao.MeterDAO;
import com.riozenc.cim.webapp.archives.dao.TransformerDAO;
import com.riozenc.cim.webapp.archives.domain.MeterDomain;
import com.riozenc.cim.webapp.archives.domain.MeterInformationEntity;
import com.riozenc.cim.webapp.archives.domain.MeterRelationDomain;
import com.riozenc.cim.webapp.archives.domain.UserDomain;
import com.riozenc.cim.webapp.archives.service.IMeterService;
import com.riozenc.cim.webapp.assets.domain.InductorAssetsDomain;
import com.riozenc.cim.webapp.assets.domain.MeterAssetsDomain;
import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.annotation.TransactionService;

import java.util.ArrayList;
import java.util.List;

@TransactionService
public class MeterServiceImpl implements IMeterService {

	@TransactionDAO
	private MeterDAO meterDAO;
	@TransactionDAO
	private TransformerDAO transformerDAO;

	@Override
	public int insert(MeterDomain meterDomain) {
		return meterDAO.insert(meterDomain);
	}

	@Override
	public int delete(MeterDomain t) {
		return meterDAO.delete(t);
	}

	@Override
	public int update(MeterDomain meterDomain) {
		return meterDAO.update(meterDomain);
	}

	@Override
	public MeterDomain findByKey(MeterDomain t) {
		return meterDAO.findByKey(t);
	}

	@Override
	public List<MeterDomain> findByNo(MeterDomain t) {
		return meterDAO.findByNo(t);
	}

	@Override
	public List<MeterDomain> findByWhere(MeterDomain t) {
		return meterDAO.findByWhere(t);
	}

	@Override
	public List<MeterDomain> getMeterByUser(UserDomain userDomain) {
		return meterDAO.getMeterByUser(userDomain);
	}

	@Override
	public int deleteMeterRelList(List<MeterRelationDomain> list) {
		return meterDAO.deleteMeterRelList(list);
	}

	@Override
	public List<MeterDomain> getMeterByMeterAssestsNo(MeterAssetsDomain t) {
		return meterDAO.getMeterByMeterAssestsNo(t);
	}

	@Override
	public List<MeterDomain> getMeterByInductorAssestsNo(InductorAssetsDomain t) {
		return meterDAO.getMeterByInductorAssestsNo(t);
	}

	@Override
	public List<MeterDomain> getMeterByIds(List<Long> ids) {

		List<MeterDomain> rList = new ArrayList<MeterDomain>();

		// 处理超过1000个id
		int len = ids.size();
		for (int m = 0; m < len / 999 + 1; m++) {// 遍历次数

			List<Long> tl = ids.subList(m * 999, (m + 1) * 999 > len ? len : (m + 1) * 999);

			List<MeterDomain> tList = meterDAO.getMeterByIds(tl);

			rList.addAll(tList);

		}
		
		return rList;
	}

	@Override
	public List<MeterRelationDomain> getMeterRelByMeterIds(List<Long> ids) {

		List<MeterRelationDomain> rList = new ArrayList<MeterRelationDomain>();
		// 处理超过1000个id
		int len = ids.size();
		for (int m = 0; m < len / 999 + 1; m++) {// 遍历次数

			List<Long> tl = ids.subList(m * 999, (m + 1) * 999 > len ? len : (m + 1) * 999);

			List<MeterRelationDomain> tList = meterDAO.getMeterRelByMeterIds(tl);
			rList.addAll(tList);
		}

		return rList;
	}

	@Override
	public List<Long> getMeterIdsByWriteSectIds(List<Long> ids) {
		return meterDAO.getMeterIdsByWriteSectIds(ids);
	}

	@Override
	public List<Long> getMeterIdsByUserIds(List<Long> userIds) {

		List<Long> rl = new ArrayList<Long>();

		// 处理超过1000个id
		int len = userIds.size();
		for (int m = 0; m < len / 999 + 1; m++) {// 遍历次数
			// 根据用户ID获取计量点ID subList方法包含fromIndex, 不包含 toIndex
			List<Long> tl = userIds.subList(m * 999, (m + 1) * 999 > len ? len : (m + 1) * 999);

			List<Long> tempList = meterDAO.getMeterIdsByUserIds(tl);

			if (tempList.size() == 0 || tempList == null || tempList.get(0) == null) {
				continue;
			}

			rl.addAll(tempList);

			// 根据计量点ID和计量点关系关系获取meterIds
			List<Long> relList = meterDAO.getMeterIdsByMeterRel(tl);
			if (relList.size() == 0 || relList == null || relList.get(0) == null) {
				continue;
			}
			rl.addAll(relList);

		}

		return rl;
	}

	@Override
	public List<MeterDomain> getMeterByUserIds(List<Long> userIds) {
		
		List<MeterDomain> rList = new ArrayList<MeterDomain>();
		// 处理超过1000个id
		int len = userIds.size();
		for (int m = 0; m < len / 999 + 1; m++) {// 遍历次数

			List<Long> tl = userIds.subList(m * 999, (m + 1) * 999 > len ? len : (m + 1) * 999);

			List<MeterDomain> tList = meterDAO.getMeterByUserIds(tl);
			rList.addAll(tList);
			
//			//根据套口关系获取套扣计量点
//			List<Long> meterIdList = new ArrayList<Long>();
//			for(MeterDomain tm : tList) {
//				meterIdList.add(tm.getId());
//			}
//			
//			List<Long> relList = meterDAO.getMeterIdsByMeterRel(meterIdList);
//			List<MeterDomain> ttList = meterDAO.getMeterByIds(relList);
//			
//			if (ttList.size() == 0 || ttList == null || ttList.get(0) == null) {
//				continue;
//			}
//			rList.addAll(ttList);

		}
		
		return rList;
		
	}

	@Override
	public List<MeterDomain> meterNoDC(MeterDomain t) {
		return meterDAO.meterNoDC(t);
	}

	@Override
	public List<MeterDomain> getMeterByWriteSectIds(List<Long> writeSectIds) {
		
		return meterDAO.getMeterByWriteSectIds(writeSectIds);
		
	}

	@Override
	public List<MeterDomain> getMeterByCustomer(MeterDomain e) {
		return meterDAO.getMeterByCustomer(e);

	}

	public List<MeterInformationEntity> getMeterInformation(MeterInformationEntity e) {
		return meterDAO.getMeterInformation(e);

	}

	@Override
	public List<MeterDomain> findMetersByMeterIds(List<Long> meterIds) {
		return meterDAO.selectMetersByMeterIds(meterIds);
	}

	@Override
	public int updateList(List<MeterDomain> e) {
		return meterDAO.updateList(e);
	}

	@Override
	public int insertList(List<MeterDomain> meterList) {
		return meterDAO.insertList(meterList);

	}
	@Override
	public List<MeterDomain> getMeterByMeterIds(List<Long> tList){
		return meterDAO.getMeterByMeterIds(tList);
	}

	@Override
	public int updateWriteSectIdByUserId(MeterDomain meterDomain) {
		return meterDAO.updateWriteSectIdByUserId(meterDomain);

	}
	@Override
	public List<MeterDomain> getMeterAndUserByIds(List<Long> meterIds) {
		return meterDAO.getMeterAndUserByIds(meterIds);

	}

	@Override
	public List<MeterDomain> getMeterByUserNos(List<UserDomain> userList) {
		return meterDAO.getMeterByUserNos(userList);

	}

}
