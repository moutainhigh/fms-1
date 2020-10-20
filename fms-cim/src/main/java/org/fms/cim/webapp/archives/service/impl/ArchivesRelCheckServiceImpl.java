/**
 *    Auth:riozenc
 *    Date:2019年3月12日 下午7:16:23
 *    Title:com.riozenc.cim.webapp.service.impl.SettlementServiceImpl.java
 **/
package org.fms.cim.webapp.archives.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.riozenc.cim.webapp.archives.dao.ArchivesRelCheckDAO;
import com.riozenc.cim.webapp.archives.dao.MeterMeterAssetsRelDAO;
import com.riozenc.cim.webapp.archives.domain.MeterDomain;
import com.riozenc.cim.webapp.archives.domain.MeterMeterAssetsRelDomain;
import com.riozenc.cim.webapp.archives.service.IArchivesRelCheckService;
import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.annotation.TransactionService;
import com.riozenc.titanTool.spring.web.http.HttpResult;

@TransactionService
public class ArchivesRelCheckServiceImpl implements IArchivesRelCheckService {

	
	@TransactionDAO
	private ArchivesRelCheckDAO archivesRelCheckDAO;
	@TransactionDAO
	private MeterMeterAssetsRelDAO meterMeterAssetsDAO;

	/**
	 * 根据计量点id查询计量点的相关关系
	 * 
	 *完整性： 
	 * 计量点有无所属用户。
	 * 计量点抄表区段与用户是否一致。
	 * 计量点与结算户关系
	 * 计量点与变压器关系
	 * 
	 * 电能表关系（有一个D相有功表）。
	 * 电能表关系（有一个D相有功表和其他有功表）。
	 * 电能表关系（有3个abc相表）。
	 *  电能表关系（缺相）。
	 *  
	 *  电能表多人使用（用户名）
	 * 
	 * 
	 * 查重：
	 * 计量点编号是否重复。
	 *计量点序号是否重复 
	 * 
	 * */
	
	@Override
	public HttpResult meterCheck(List<MeterDomain> meterList) {

		List<Long> meterIds = new ArrayList<Long>();
		for(MeterDomain t : meterList) {
			meterIds.add(t.getId());
		}

		List<HashMap<String,Object>> checkList = archivesRelCheckDAO.meterCheck(meterIds);
		
		// 电能表关系一次查询。按meterId拆分成不同的小组
		List<MeterMeterAssetsRelDomain> mmarList = meterMeterAssetsDAO.getMeterAssetsByMeterIds(meterIds);
		
		HashMap<Long,List<MeterMeterAssetsRelDomain>> mmarMap = new HashMap<Long,List<MeterMeterAssetsRelDomain>>();
		for(Long id : meterIds) {
			List<MeterMeterAssetsRelDomain> tlist = new ArrayList<MeterMeterAssetsRelDomain>();

			for(MeterMeterAssetsRelDomain t : mmarList) {
				if(t.getMeterId()!=null && t.getMeterId()-id==0 && t.getFunctionCode()-1==0) {
					tlist.add(t);
				}
			}
			mmarMap.put(id, tlist);

		}
		
		List<String> resultList = new ArrayList<String>();
		
		for(HashMap<String,Object> t : checkList) {
			String meter = "计量点号："+ t.get("meterNo")+"计量点名称："+ t.get("meterName")+"\n";
			if(t.get("writeSectionId")==null||t.get("writeSectId")==null) {
				meter += "无抄表区段，或所属用户无抄表区段";
			}
			else if(t.get("writeSectionId").toString().equals(t.get("writeSectId").toString())) {
				meter += "与所属用户的抄表区段不符";
			}
			if(t.get("settlementNo")==null) {
				meter += "未关联结算户";
			}
			if(t.get("transformerNo")==null) {
				meter += "未关联变压器";
			}
			else if(t.get("transformerAssetsId")==null) {
				meter += "关联变压器无资产";
			}
			
			List<MeterMeterAssetsRelDomain> tlist = mmarMap.get(t.get("id"));
//			 * 电能表关系（有一个D相有功表）。
//			 * 电能表关系（有一个D相有功表和其他有功表）。
//			 * 电能表关系（有3个abc相表）。
//			 *  电能表关系（缺相）。
			int a=0;
			int b=0;
			int c=0;
			int d=0;
			for(MeterMeterAssetsRelDomain temp : tlist) {
				switch (temp.getPhaseSeq()) {
				case 1:
					a++;
					break;
				case 2:
					b++;
					break;	
				case 3:
					c++;
					break;	
				case 4:
					d++;
					break;
				default:
					meter += "关联电能表资产未填写相序";
					break;
				}
				
				if(d==1 && 1+2+3>0) {
					meter += "关联电能表资产存在除D相外的有功电能表";
				}else if(d>1) {
					meter += "关联电能表资产存在多块D相有功电能表";
				}else if(!(d==0 && a==1 && b==1 && c==1)) {
					meter += "关联电能表资产缺相或同一相序有多块表";
				}
			}
			
			
			
			resultList.add(meter);

		}

		return new HttpResult<>(HttpResult.SUCCESS,resultList);
	}


}
