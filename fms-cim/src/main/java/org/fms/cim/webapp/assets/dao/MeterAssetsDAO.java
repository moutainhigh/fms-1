/**
 *    Auth:riozenc
 *    Date:2019年3月8日 下午3:28:46
 *    Title:com.riozenc.cim.webapp.dao.CustomerDAO.java
 **/
package org.fms.cim.webapp.assets.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ExecutorType;
import org.fms.cim.webapp.archives.domain.UserDomain;
import org.fms.cim.webapp.assets.domain.MeterAssetsDomain;
import org.fms.cim.webapp.assets.domain.MeterAssetsEntity;
import org.fms.cim.webapp.assets.domain.MeterAssetsLogDomain;
import org.springframework.beans.BeanUtils;

import com.riozenc.titanTool.annotation.PaginationSupport;
import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.spring.webapp.dao.AbstractTransactionDAOSupport;
import com.riozenc.titanTool.spring.webapp.dao.BaseDAO;

@TransactionDAO
public class MeterAssetsDAO extends AbstractTransactionDAOSupport implements BaseDAO<MeterAssetsDomain> {

	@Override
	public int insert(MeterAssetsDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().insert(getNamespace() + ".insert", t);
	}

	@Override
	public int delete(MeterAssetsDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().delete(getNamespace() + ".delete", t);
	}

	@Override
	public int update(MeterAssetsDomain t) {
		// TODO Auto-generated method stub
	//	insertLog(t, "update");
		return getPersistanceManager().update(getNamespace() + ".update", t);
	}
	

	public int updateList(List<MeterAssetsDomain> lt) {

		return getPersistanceManager().updateList(getNamespace() + ".update", lt);
	}

	@Override
	public MeterAssetsDomain findByKey(MeterAssetsDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().load(getNamespace() + ".findByKey", t);
	}

	@Override
	@PaginationSupport
	public List<MeterAssetsDomain> findByWhere(MeterAssetsDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().find(getNamespace() + ".findByWhere", t);
	}

	public List<MeterAssetsDomain> getMeterAssetsByDept(MeterAssetsDomain meterAssetsDomain) {
		return getPersistanceManager().find(getNamespace() + ".getMeterAssetsByDept", meterAssetsDomain);
	}

	public int insertLog(MeterAssetsDomain meterAssetsDomain, String method) {
		MeterAssetsLogDomain meterAssetsLogDomain = new MeterAssetsLogDomain();
		BeanUtils.copyProperties(meterAssetsDomain, meterAssetsLogDomain, "id");// 对象赋值，忽略id属性
		meterAssetsLogDomain.setOperation(method);
		meterAssetsLogDomain.setOperationDate(new Date());
		meterAssetsLogDomain.setOperator(Long.valueOf(meterAssetsDomain.getManagerId()));
		return getPersistanceManager().insert(getNamespace() + ".insertLog", meterAssetsLogDomain);
	}

	public List<Map<String,Object>> getAssetsByUser(UserDomain t) {

		return getPersistanceManager().find(getNamespace() + ".getAssetsByUser", t);
	}

	public List<MeterAssetsDomain> findByWhereDC(MeterAssetsDomain tt) {
		// TODO Auto-generated method stub
		return getPersistanceManager().find(getNamespace() + ".findByWhereDC", tt);
	}

//	public int addAssetsList(MeterAssetsDomain t) {
//		// TODO Auto-generated method stub
//		return getPersistanceManager().insertList(getNamespace() + ".addAssetsList", t);
//	}

	@PaginationSupport
	public MeterAssetsEntity findMeterEntityByWhere(Long id) {
		// TODO Auto-generated method stub
		return getPersistanceManager().load(getNamespace() + ".findMeterEntityByWhere", id);
	}

	public List<MeterAssetsDomain> getMeterAssetsByAssetsIds(List<Long> idsList) {
		return getPersistanceManager().find(getNamespace() + ".getMeterAssetsByAssetsIds", idsList);

	}
	@PaginationSupport
	public List<MeterAssetsEntity> getMeterAssetsByFunctionCode(Map ids) {
		return getPersistanceManager().find(getNamespace() + ".getMeterAssetsByFunctionCode", ids);

	}
	
	public int insertList(List<MeterAssetsDomain> rl) {
		return getPersistanceManager(ExecutorType.BATCH).insertList(getNamespace() + ".insertList", rl);
	}

	public List<MeterAssetsDomain> getMeterAssetsByNos( List<MeterAssetsDomain> meterAssetsDomains) {
		if(meterAssetsDomains==null||meterAssetsDomains.size()==0) {
			return meterAssetsDomains;
		}
		return getPersistanceManager(ExecutorType.BATCH).find(getNamespace() + ".getMeterAssetsByNos", meterAssetsDomains);
	}


}
