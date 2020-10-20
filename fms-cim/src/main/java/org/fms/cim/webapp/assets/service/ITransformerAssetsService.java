/**
 *    Auth:riozenc
 *    Date:2019年3月12日 下午7:15:22
 *    Title:com.riozenc.cim.webapp.service.ISettlementService.java
 **/
package org.fms.cim.webapp.assets.service;

import java.util.List;

import com.riozenc.cim.webapp.assets.domain.TransformerAssetsDomain;
import com.riozenc.titanTool.spring.webapp.service.BaseService;

public interface ITransformerAssetsService extends BaseService<TransformerAssetsDomain> {
	public List<TransformerAssetsDomain> getTransformerAssetsByManager(TransformerAssetsDomain transformerAssetsDomain);
	public List<TransformerAssetsDomain> getTransformerAssetsByWhere(TransformerAssetsDomain transformerAssetsDomain);
	public List<TransformerAssetsDomain> findByNoId(TransformerAssetsDomain e);

	
}
