/**
 *    Auth:riozenc
 *    Date:2019年3月18日 下午2:20:46
 *    Title:com.riozenc.cim.webapp.assets.action.AbstractAssetsAction.java
 **/
package org.fms.cim.webapp.assets.action;

import java.util.List;

import reactor.core.publisher.Mono;

public abstract class AbstractAssetsAction<T> {

	final static byte SCRAP = 0;// 报废
	final static byte INACTIVE = 1;// 入库待用
	final static byte RUN = 2;// 运行
	final static byte INSPECTION = 3;//待检

	/**
	 * 新增资产
	 * 
	 * @param t
	 * @return
	 */
	abstract public Object addAssets(T t);

	/**
	 * 领用资产
	 * 
	 * @param mybatisEntity
	 * @return
	 */
	abstract public Object useAssets(T t);

	/**
	 * 退还资产
	 * 
	 * @param mybatisEntity
	 * @return
	 */
	abstract public Object returnAssets(T t);

	/**
	 * 报废资产
	 * 
	 * @param t
	 * @return
	 */
	abstract public Object scrapAssets(T t);

	/**
	 * 查询资产（区域限制）
	 * 
	 * @param mybatisEntity
	 * @return
	 */
	abstract public Mono<List<T>> getAssetsByManager(T t);

}
