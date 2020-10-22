/**
 *    Auth:riozenc
 *    Date:2018年5月10日 上午10:29:41
 *    Title:cis.web.support.transaction.TransactionServiceSupport.java
 **/
package org.fms.cim.support.transaction;

import org.springframework.context.annotation.Configuration;

import com.riozenc.titanTool.properties.Global;
import com.riozenc.titanTool.spring.transaction.registry.TransactionServiceRegistryPostProcessor;

@Configuration
public class TransactionServiceSupport extends TransactionServiceRegistryPostProcessor {

	@Override
	public String getNamespace() {
		return Global.getConfig("namespace");
	}

}
