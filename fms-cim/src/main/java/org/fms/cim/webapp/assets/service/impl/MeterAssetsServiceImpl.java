/**
 *    Auth:riozenc
 *    Date:2019年3月8日 下午3:38:49
 *    Title:com.riozenc.cim.webapp.service.impl.CustomerServiceImpl.java
 **/
package org.fms.cim.webapp.assets.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.riozenc.cim.web.util.CommonUtil;
import com.riozenc.cim.webapp.assets.domain.MeterAssetsEntity;
import org.apache.commons.lang.math.NumberUtils;

import com.riozenc.cim.webapp.archives.domain.MeterDomain;
import com.riozenc.cim.webapp.archives.domain.UserDomain;
import com.riozenc.cim.webapp.assets.dao.MeterAssetsDAO;
import com.riozenc.cim.webapp.assets.dao.MeterAssetsLogDAO;
import com.riozenc.cim.webapp.assets.domain.MeterAssetsDomain;
import com.riozenc.cim.webapp.assets.service.IMeterAssetsService;
import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.annotation.TransactionService;
import com.riozenc.titanTool.spring.web.http.HttpResult;

@TransactionService
public class MeterAssetsServiceImpl implements IMeterAssetsService {

	@TransactionDAO
	private MeterAssetsDAO meterAssetsDAO;
	@TransactionDAO
	private MeterAssetsLogDAO meterAssetsLogDAO;

	@Override
	public int insert(MeterAssetsDomain t) {
		return meterAssetsDAO.insert(t);
	}

	@Override
	public int delete(MeterAssetsDomain t) {
		return meterAssetsDAO.delete(t);
	}

	@Override
	public int update(MeterAssetsDomain t) {
		return meterAssetsDAO.update(t);
	}

	@Override
	public MeterAssetsDomain findByKey(MeterAssetsDomain t) {
		return meterAssetsDAO.findByKey(t);
	}

	@Override
	public List<MeterAssetsDomain> findByWhere(MeterAssetsDomain t) {
		return meterAssetsDAO.findByWhere(t);
	}

	@Override
	public List<MeterAssetsDomain> getMeterAssetsByManager(MeterAssetsDomain meterAssetsDomain) {
		return meterAssetsDAO.getMeterAssetsByDept(meterAssetsDomain);
	}

	@Override
	public List<Map<String, Object>> getAssetsByUser(UserDomain t) {
		return meterAssetsDAO.getAssetsByUser(t);
	}

	/**
	 * 批量新增
	 * 1、获取起始表号和个数
	 * 2、生成新资产并查重
	 * 3、插入
	 * */
	@Override
	public HttpResult addAssetsList(MeterAssetsDomain t) {
				
		String madeNoBegin = t.getMadeNo();
		if(madeNoBegin==null||"".equals(madeNoBegin)||madeNoBegin.length()<5) {
			
			return new HttpResult<>(HttpResult.ERROR, "更新失败，初始表号为空," +
					"或表长度不符合要求");
		}
		String tempSn = madeNoBegin.substring(madeNoBegin.length()-4, madeNoBegin.length());
		String noHead = madeNoBegin.substring(0,madeNoBegin.length()-4);
		if(!NumberUtils.isNumber(tempSn)) {
			
			return new HttpResult<>(HttpResult.ERROR,
					"更新失败，初始表号后四位非数字格式不能转化成流水号");
		}
		Integer beginSn = Integer.valueOf(tempSn);
		Integer assetsNum = t.getAssetsNum();
		if(assetsNum==null||assetsNum==0) {
			
			return new HttpResult<>(HttpResult.ERROR, "更新失败，需新增资产个数为0");
		}

		for(int i =0;i<assetsNum;i++) {
	        
			String tempStr = String.format("%04d", beginSn+i);//字符串格式化. 
			String madeNo = noHead +tempStr;
			String assetsNo=CommonUtil.generateFormatNo(madeNo,18,"0",true);
			t.setMadeNo(madeNo);
			t.setMeterAssetsNo(assetsNo);
			List<MeterAssetsDomain> meterAssetsList = meterAssetsDAO.findByWhereDC(t);
			if(meterAssetsList.size()>0) {
				return new HttpResult<>(HttpResult.ERROR, "更新失败，资产编号重复");

			}
		//	t.setStatus((byte) 0);//购入
			t.setId(null);
			//入库
			int m = meterAssetsDAO.insert(t);

			if(m!=1) {
				return new HttpResult<>(HttpResult.ERROR, "更新失败，存库失败资产号为："+assetsNo);
			}
			
		}
		
		return new HttpResult<>(HttpResult.SUCCESS, "更新成功，新增条数："+assetsNum);
		
		
	}

	@Override
	public List<MeterAssetsDomain> findByWhereDC(MeterAssetsDomain tt) {
		return meterAssetsDAO.findByWhereDC(tt);
	}

	@Override
	public int updateList(List<MeterAssetsDomain> l) {
		return meterAssetsDAO.updateList(l);

	}

	@Override
	public MeterAssetsEntity findMeterEntityByWhere(Long id) {
		return meterAssetsDAO.findMeterEntityByWhere(id);
	}

	@Override
	public List<MeterAssetsDomain> getMeterAssetsByAssetsIds(List<Long> idsList) {
		
		List<MeterAssetsDomain> rl =new ArrayList<MeterAssetsDomain>();
		// 处理超过1000个id
		int len = idsList.size();
		for (int m = 0; m < len / 999 + 1; m++) {// 遍历次数

			List<Long> tl = idsList.subList(m * 999, (m + 1) * 999 > len ? len : (m + 1) * 999);

			List<MeterAssetsDomain> tList = meterAssetsDAO.getMeterAssetsByAssetsIds(tl);

			rl.addAll(tList);

		}

		return rl;
		
	}
	
	
	@Override
	public List<MeterAssetsEntity> getMeterAssetsByFunctionCode(Map ids){
		return meterAssetsDAO.getMeterAssetsByFunctionCode(ids);
	}
	
	@Override
	public int insertList(List<MeterAssetsDomain> rl) {
		return meterAssetsDAO.insertList(rl);
	}

	@Override
	public List<MeterAssetsDomain> getMeterAssetsByNos( List<MeterAssetsDomain> meterAssetsNos) {
		return meterAssetsDAO.getMeterAssetsByNos(meterAssetsNos);
	}
	
	
}
