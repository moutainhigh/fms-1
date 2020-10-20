/**
 *    Auth:riozenc
 *    Date:2019年3月8日 下午3:28:46
 *    Title:com.riozenc.cim.webapp.dao.CustomerDAO.java
 **/
package org.fms.cim.webapp.archives.dao;

import java.util.HashMap;
import java.util.List;

import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.spring.webapp.dao.AbstractTransactionDAOSupport;

@TransactionDAO()
public class ArchivesRelCheckDAO extends AbstractTransactionDAOSupport {

	public List<HashMap<String, Object>> meterCheck(List<Long> meterIds) {
		return getPersistanceManager().find(getNamespace() + ".meterCheck", meterIds);

	}



}
