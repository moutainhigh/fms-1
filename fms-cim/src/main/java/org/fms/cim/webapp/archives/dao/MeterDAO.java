/**
 *    Auth:riozenc
 *    Date:2019年3月8日 下午3:28:46
 *    Title:com.riozenc.cim.webapp.dao.CustomerDAO.java
 **/
package org.fms.cim.webapp.archives.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.fms.cim.webapp.archives.domain.CustomerDomain;
import org.fms.cim.webapp.archives.domain.MeterDomain;
import org.fms.cim.webapp.archives.domain.MeterInformationEntity;
import org.fms.cim.webapp.archives.domain.MeterRelationDomain;
import org.fms.cim.webapp.archives.domain.SettlementDomain;
import org.fms.cim.webapp.archives.domain.TransformerMeterRelationDomain;
import org.fms.cim.webapp.archives.domain.UserDomain;
import org.fms.cim.webapp.assets.domain.InductorAssetsDomain;
import org.fms.cim.webapp.assets.domain.MeterAssetsDomain;

import com.riozenc.titanTool.annotation.PaginationSupport;
import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.spring.webapp.dao.AbstractTransactionDAOSupport;
import com.riozenc.titanTool.spring.webapp.dao.BaseDAO;

@TransactionDAO()
public class MeterDAO extends AbstractTransactionDAOSupport implements BaseDAO<MeterDomain> {

	@Override
	public int insert(MeterDomain t) {
		return getPersistanceManager().insert(getNamespace() + ".insert", t);
	}

	@Override
	public int delete(MeterDomain t) {
		return getPersistanceManager().delete(getNamespace() + ".delete", t);
	}

	@Override
	public int update(MeterDomain t) {
		return getPersistanceManager().update(getNamespace() + ".update", t);
	}

	@Override
	public MeterDomain findByKey(MeterDomain t) {
		return getPersistanceManager().load(getNamespace() + ".findByKey", t);
	}

	@Override
	@PaginationSupport
	public List<MeterDomain> findByWhere(MeterDomain t) {
		return getPersistanceManager().find(getNamespace() + ".findByWhere", t);
	}

	@PaginationSupport
	public List<MeterDomain> getMeterByUser(UserDomain userDomain) {
		return getPersistanceManager().find(getNamespace() + ".getMeterByUser", userDomain);
	}

	public int deleteMeterRelList(List<MeterRelationDomain> meterRelationDomains) {
		return getPersistanceManager().updateList(getNamespace() + ".deleteMeterRel", meterRelationDomains);
	}

	@PaginationSupport
	public List<MeterDomain> getMeterByMeterAssestsNo(MeterAssetsDomain t) {
		return getPersistanceManager().find(getNamespace() + ".getMeterByMeterAssestsNo", t);
	}

	@PaginationSupport
	public List<MeterDomain> getMeterByInductorAssestsNo(InductorAssetsDomain t) {
		return getPersistanceManager().find(getNamespace() + ".getMeterByInductorAssestsNo", t);
	}

	@PaginationSupport
	public List<MeterDomain> getMeterByIds(List<Long> tl) {
		if (tl == null || tl.size() == 0) {
			return new ArrayList<MeterDomain>();
		}
		return getPersistanceManager().find(getNamespace() + ".getMeterByIds", tl);
	}

	public List<MeterRelationDomain> getMeterRelByMeterIds(List<Long> sl) {
		if (sl == null || sl.size() == 0) {
			return new ArrayList<MeterRelationDomain>();
		}
		return getPersistanceManager().find(getNamespace() + ".getMeterRelByMeterIds", sl);

	}

	// 根据抄表段号获取计量点号
	// 根据抄表段获取计量点ID,1.获取该抄表段下的计量点，2.获取该抄表段下用户的计量点 目前是1
	@Deprecated
	public List<Long> getMeterIdsByWriteSectIds(List<Long> ids) {

		return getPersistanceManager().find(getNamespace() + ".getMeterIdsByWriteSectIds", ids);

	}

	public List<MeterDomain> getMeterByUserIds(List<Long> userIds) {
		if (userIds == null || userIds.size() == 0) {
			return new ArrayList<MeterDomain>();
		}
		return getPersistanceManager().find(getNamespace() + ".getMeterByUserIds", userIds);
	}

	/**
	 * 根据抄表区段ID获取计量点
	 *
	 * @param writeSectIds
	 * @return
	 */
	public List<MeterDomain> getMeterByWriteSectIds(List<Long> writeSectIds) {
		// TODO Auto-generated method stub
		return getPersistanceManager().find(getNamespace() + ".getMeterByWriteSectIds", writeSectIds);
	}

	public List<Long> getMeterIdsByUserIds(List<Long> userIds) {

		return getPersistanceManager().find(getNamespace() + ".getMeterIdsByUserIds", userIds);

	}

	public List<MeterDomain> meterNoDC(MeterDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().find(getNamespace() + ".meterNoDC", t);
	}

	public int reUpdate(MeterDomain meter) {
		// TODO Auto-generated method stub
		return getPersistanceManager().update(getNamespace() + ".reUpdate", meter);
	}

//	public int insertTransMeterRela(MeterDomain t) {
//		// TODO Auto-generated method stub
//		return getPersistanceManager().insert(getNamespace() + ".insertTransMeterRela", t);
//	}

	public List<MeterDomain> findRelByTranIdAndMeterId(MeterDomain t) {
		return getPersistanceManager().find(getNamespace() + ".findRelByTranIdAndMeterId", t);
	}

//	public int updateTransMeterRela(MeterDomain t) {
//		return getPersistanceManager().update(getNamespace() + ".updateTransMeterRela", t);
//	}

	public List<MeterDomain> findByNo(MeterDomain t) {
		return getPersistanceManager().find(getNamespace() + ".findByNo", t);
	}

	// 根据客户名 户号查计量点
	@PaginationSupport
	public List<MeterDomain> getMetersByCustomer(CustomerDomain t) {
		return getPersistanceManager().find(getNamespace() + ".getMetersByCustomer", t);
	}

	// 根据用电户名 户号查计量点
	@PaginationSupport
	public List<MeterDomain> getMetersByUser(UserDomain t) {
		return getPersistanceManager().find(getNamespace() + ".getMetersByUser", t);
	}

	// 根据结算户名 户号查计量点
	@PaginationSupport
	public List<MeterDomain> getMetersBySettlement(SettlementDomain t) {
		return getPersistanceManager().find(getNamespace() + ".getMetersBySettlement", t);
	}

	@PaginationSupport
	public List<MeterDomain> getMeterByCustomer(MeterDomain e) {
		return getPersistanceManager().find(getNamespace() + ".getMeterByCustomer", e);

	}

	public List<TransformerMeterRelationDomain> findTransformerGroupNo(TransformerMeterRelationDomain rel) {
		return getPersistanceManager().find(getNamespace() + ".findTransformerGroupNo", rel);
	}

	@PaginationSupport
	public List<MeterInformationEntity> getMeterInformation(MeterInformationEntity e) {
		return getPersistanceManager().find(getNamespace() + ".getMeterInformation", e);

	}

	public int updateList(List<MeterDomain> e) {
		return getPersistanceManager(ExecutorType.BATCH).updateList(getNamespace() + ".update", e);

	}

	public List<Long> getMeterIdsByMeterRel(List<Long> tl) {
		if (tl == null || tl.size() == 0) {
			return new ArrayList<Long>();
		}
		return getPersistanceManager().find(getNamespace() + ".getMeterIdsByMeterRel", tl);
	}

	public List<HashMap<String, Long>> getMeterCountByWriteSect() {
		return getPersistanceManager().find(getNamespace() + ".getMeterCountByWriteSect", null);
	}

	public int insertList(List<MeterDomain> meterList) {
		return getPersistanceManager(ExecutorType.BATCH).insertList(getNamespace() + ".insert", meterList);

	}

	// 只返回单表
	public List<MeterDomain> getMeterByMeterIds(List<Long> tList) {
		if (tList == null || tList.size() == 0) {
			return new ArrayList<MeterDomain>();
		}
		return getPersistanceManager().find(getNamespace() + ".getMeterByMeterIds", tList);

	}

	public List<MeterDomain> getRelationMeterByWriteSectIds(List<Long> writeSectIds) {
		if (writeSectIds == null || writeSectIds.size() == 0) {
			return new ArrayList<MeterDomain>();
		}
		return getPersistanceManager().find(getNamespace() + ".getRelationMeterByWriteSectIds", writeSectIds);
	}

	public List<MeterDomain> selectMetersByMeterIds(List<Long> meterIds) {
		return getPersistanceManager().find(getNamespace() + ".selectMetersByMeterIds", meterIds);
	}

	public int updateWriteSectIdByUserId(MeterDomain meterDomain) {
		return getPersistanceManager().update(getNamespace() + ".updateWriteSectIdByUserId", meterDomain);

	}

	public List<MeterDomain> getMeterByUserNos(List<UserDomain> userList) {
		if (userList == null || userList.size() == 0) {
			return new ArrayList<MeterDomain>();
		}
		return getPersistanceManager().find(getNamespace() + ".getMeterByUserNos", userList);

	}

	public List<MeterDomain> getMeterAndUserByIds(List<Long> meterIds) {
		return getPersistanceManager().find(getNamespace() + ".getMeterAndUserByIds", meterIds);

	}

}
