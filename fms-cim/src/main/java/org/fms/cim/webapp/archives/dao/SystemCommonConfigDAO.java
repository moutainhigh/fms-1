/**
 *    Auth:riozenc
 *    Date:2019年3月8日 下午3:28:46
 *    Title:com.riozenc.cim.webapp.dao.CustomerDAO.java
 **/
package org.fms.cim.webapp.archives.dao;

import java.util.List;

import com.riozenc.cim.webapp.archives.domain.SystemCommonConfigDomain;
import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.spring.webapp.dao.AbstractTransactionDAOSupport;
import com.riozenc.titanTool.spring.webapp.dao.BaseDAO;

@TransactionDAO()
public class SystemCommonConfigDAO extends AbstractTransactionDAOSupport implements BaseDAO<SystemCommonConfigDomain> {

	@Override
	public int insert(SystemCommonConfigDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().insert(getNamespace() + ".insert", t);
	}

	@Override
	public int delete(SystemCommonConfigDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().delete(getNamespace() + ".delete", t);
	}

	@Override
	public int update(SystemCommonConfigDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().update(getNamespace() + ".update", t);
	}

	@Override
	public SystemCommonConfigDomain findByKey(SystemCommonConfigDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().load(getNamespace() + ".findByKey", t);
	}
	
	@Override
	public List<SystemCommonConfigDomain> findByWhere(SystemCommonConfigDomain t) {
		// TODO Auto-generated method stub
		return getPersistanceManager().find(getNamespace() + ".findByWhere", t);
	}

	public SystemCommonConfigDomain findByKeyValue(String type,Long key){
		SystemCommonConfigDomain  inductorConfig=new SystemCommonConfigDomain();
		inductorConfig.setType(type);
		inductorConfig.setParamKey(key);
		List<SystemCommonConfigDomain> systemCommonConfigDomains=findByWhere(inductorConfig);
		if(null==systemCommonConfigDomains || systemCommonConfigDomains.size()<1){
			return new SystemCommonConfigDomain();
		}else{
			return systemCommonConfigDomains.get(0);
		}
	}

}
