/**
 *    Auth:riozenc
 *    Date:2018年5月10日 上午10:29:32
 *    Title:cis.web.support.transaction.TransactionDAOSupport.java
 **/
package org.fms.cim.support.transaction;

import org.springframework.context.annotation.Configuration;

import com.riozenc.titanTool.properties.Global;
import com.riozenc.titanTool.spring.transaction.registry.TransactionDAORegistryPostProcessor;

@Configuration
public class TransactionDAOSupport extends TransactionDAORegistryPostProcessor {

	@Override
	public String getNamespace() {
		return Global.getConfig("namespace");
	}

}
