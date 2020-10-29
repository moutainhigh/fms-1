/**
 *    Auth:riozenc
 *    Date:2019年3月12日 下午7:11:34
 *    Title:com.riozenc.cim.webapp.dao.UserDAO.java
 **/
package org.fms.cim.webapp.archives.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ExecutorType;
import org.fms.cim.webapp.archives.domain.CustomerDomain;
import org.fms.cim.webapp.archives.domain.TransformerDomain;
import org.fms.cim.webapp.archives.domain.TransformerLineRelDomain;
import org.fms.cim.webapp.archives.domain.UserDomain;
import org.fms.cim.webapp.archives.domain.UserTransformerRelaDomain;
import org.fms.cim.webapp.assets.domain.TransformerAssetsDomain;

import com.riozenc.titanTool.annotation.PaginationSupport;
import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.spring.webapp.dao.AbstractTransactionDAOSupport;
import com.riozenc.titanTool.spring.webapp.dao.BaseDAO;

@TransactionDAO("read")
public class TransformerDAO extends AbstractTransactionDAOSupport implements BaseDAO<TransformerDomain> {

	@Override
	public int insert(TransformerDomain t) {
		return getPersistanceManager().insert(getNamespace() + ".insert", t);
	}	
	
	public int insertList(List<TransformerDomain> t) {
		return getPersistanceManager(ExecutorType.BATCH).insertList(getNamespace() + ".insertList", t);
	}

	@Override
	public int delete(TransformerDomain t) {
		return getPersistanceManager().delete(getNamespace() + ".delete", t);
	}

	@Override
	public int update(TransformerDomain t) {
		return getPersistanceManager().update(getNamespace() + ".update", t);
	}

	@Override
	public TransformerDomain findByKey(TransformerDomain t) {
		return getPersistanceManager().load(getNamespace() + ".findByKey", t);
	}

	@Override
	@PaginationSupport
	public List<TransformerDomain> findByWhere(TransformerDomain t) {
		return getPersistanceManager().find(getNamespace() + ".findByWhere", t);
	}
	@PaginationSupport
	public List<TransformerDomain> getTransformerByUser(UserDomain userDomain){
		return getPersistanceManager().find(getNamespace() + ".getTransformerByUser", userDomain);
	}

	public int insertLineTransRela(TransformerDomain t) {
		return getPersistanceManager().insert(getNamespace() + ".insertLineTransRela", t);
	}
	@PaginationSupport
	public List<TransformerDomain> findRelByTranId(TransformerDomain t) {
		return getPersistanceManager().find(getNamespace() + ".findRelByTranId", t);
	}

	public int updateLineTransRela(TransformerDomain t) {
		return getPersistanceManager().update(getNamespace() + ".updateLineTransRela", t);
	}
	@PaginationSupport
	public List<TransformerDomain> getTransformerByAsset(TransformerAssetsDomain t) {
		return getPersistanceManager().find(getNamespace() + ".getTransformerByAsset", t);
	}
	@PaginationSupport
	public List<UserTransformerRelaDomain> getUserTransformerRelaByUser(UserDomain t) {
		return getPersistanceManager().find(getNamespace() + ".getUserTransformerRelaByUser", t);
	}

	public int saveUserTransformerRela(UserTransformerRelaDomain tt) {
		return getPersistanceManager().insert(getNamespace() + ".saveUserTransformerRela", tt);

	}

	public List<TransformerDomain> getTransformerByMeterIds(List<Long> tl) {
		if(tl==null||tl.size()==0) {
			return new ArrayList<TransformerDomain>();
		}
		return getPersistanceManager().find(getNamespace() + ".getTransformerByMeterIds", tl);
	}

    public List<TransformerDomain> findByNo(TransformerDomain t) {
		return getPersistanceManager().find(getNamespace() + ".findByNo", t);
    }

	public List<TransformerDomain> findByNoId(TransformerDomain transformerDomain) {
		return getPersistanceManager().find(getNamespace() + ".findByNoId", transformerDomain);

	}

	public List<Map> getTransformerByRel(List<Long> ids) {
		if(ids==null||ids.size()==0) {
			return new ArrayList<Map>();
		}
		return getPersistanceManager().find(getNamespace() + ".getTransformerByRel", ids);

	}
	@PaginationSupport
	public List<TransformerDomain> getAvaTransformerByWhere(TransformerDomain t) {
		return getPersistanceManager().find(getNamespace() +
				".getAvaTransformerByWhere", t);
	}

	public List<TransformerDomain> getTransformerByWriteSectIds(List<Long> ids) {
		if(ids==null||ids.size()==0) {
			return new ArrayList<TransformerDomain>();
		}
		return getPersistanceManager().find(getNamespace() + ".getTransformerByWriteSectIds", ids);

	}

	@PaginationSupport
	public List<TransformerDomain> getTransformerByCustomer(CustomerDomain customerDomain) {
		return getPersistanceManager().find(getNamespace() + ".getTransformerByCustomer", customerDomain);

	}

	public int insertLineTransRelaList(List<TransformerDomain> transformerList) {
		return getPersistanceManager(ExecutorType.BATCH).insertList(getNamespace() + ".insertLineTransRela", transformerList);		
	}

	public List<TransformerLineRelDomain> findRelByTranformIds(TransformerLineRelDomain t) {
		return getPersistanceManager().find(getNamespace() + ".findRelByTranformIds", t);
	}

	public List<TransformerLineRelDomain> findRelByLineIds(TransformerLineRelDomain t) {
		return getPersistanceManager().find(getNamespace() + ".findRelByLineIds", t);
	}

	public int updateByTransformerAssetsId(TransformerDomain t) {
		return getPersistanceManager().update(getNamespace() + ".updateByTransformerAssetsId", t);
	}
}
