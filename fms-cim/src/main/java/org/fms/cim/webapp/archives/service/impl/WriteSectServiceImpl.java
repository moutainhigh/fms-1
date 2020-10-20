/**
 *    Auth:riozenc
 *    Date:2019年3月12日 下午7:16:23
 *    Title:com.riozenc.cim.webapp.service.impl.SettlementServiceImpl.java
 **/
package org.fms.cim.webapp.archives.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.type.TypeReference;
import com.riozenc.cim.webapp.archives.dao.MeterDAO;
import com.riozenc.cim.webapp.archives.dao.WriteSectDAO;
import com.riozenc.cim.webapp.archives.domain.LineDomain;
import com.riozenc.cim.webapp.archives.domain.TransformerDomain;
import com.riozenc.cim.webapp.archives.domain.WriteSectDomain;
import com.riozenc.cim.webapp.archives.service.IWriteSectService;
import com.riozenc.titanTool.annotation.TransactionDAO;
import com.riozenc.titanTool.annotation.TransactionService;
import com.riozenc.titanTool.common.json.utils.GsonUtils;
import com.riozenc.titanTool.common.json.utils.JSONUtil;
import com.riozenc.titanTool.spring.web.client.TitanTemplate;
import com.riozenc.titanTool.spring.web.http.HttpResult;

@TransactionService
public class WriteSectServiceImpl implements IWriteSectService {

	@TransactionDAO
	private WriteSectDAO writeSectDAO;
	@TransactionDAO
	private MeterDAO meterDAO;

	@Autowired
	private TitanTemplate titanTemplate;

	@Override
	public int insert(WriteSectDomain t) {
		return writeSectDAO.insert(t);
	}

	@Override
	public int delete(WriteSectDomain t) {
		return writeSectDAO.delete(t);
	}

	@Override
	public int update(WriteSectDomain t) {
		return writeSectDAO.update(t);
	}

	@Override
	public WriteSectDomain findByKey(WriteSectDomain t) {
		return writeSectDAO.findByKey(t);
	}

	@Override
	public List<WriteSectDomain> findByWhere(WriteSectDomain t) {
		return writeSectDAO.findByWhere(t);
	}

	@Override
	public List<Long> getDeptIdsByWriteSectIds(List<Long> ids) {

		return writeSectDAO.getDeptIdsByWriteSectIds(ids);
	
	}

	@Override
	public HttpResult getMeterInitSituation(List<WriteSectDomain> writeSectDomains,Integer mon){
		
		HashMap<Long,Integer> mapNum = getWriteSectMeterNum();
		HashMap<Long,Integer> mapInitNum = getMonogoWriteSectMeterNum(mon);

		HttpResult rh = new HttpResult();
		rh.setStatusCode(HttpResult.ERROR);

		for(WriteSectDomain writeSectDomain:writeSectDomains) {
			Long writeSectDomainId = writeSectDomain.getId();
			if(mapNum.get(writeSectDomainId)==null) {
				writeSectDomain.settInitNum(0);
			}else {
				writeSectDomain.settInitNum(mapNum.get(writeSectDomainId));
			}

			if(mapInitNum.get(writeSectDomainId)==null) {
				writeSectDomain.setInitedNum(0);
			}else {
				writeSectDomain.setInitedNum(mapInitNum.get(writeSectDomainId));
			}
		}
		
		rh.setStatusCode(HttpResult.SUCCESS);
		rh.setResultData(writeSectDomains);
		return rh;

	}

	@Override
	public int insertList(List<WriteSectDomain> wsList) {
		return writeSectDAO.insertList(wsList);
	}
	
	//获取monogo中抄表区段中的计量点个数---------已初始化数
	public HashMap<Long,Integer> getMonogoWriteSectMeterNum(Integer mon){
		//获取数据
		HttpResult c2 = new HttpResult();
		
		HashMap<Long,Integer> rmap = new HashMap<Long,Integer>();
		try {
			
			Map<String, Object> params = new HashMap<>();
			params.put("mon",mon);
			c2 = titanTemplate.postJson("BILLING-SERVER", "billingServer/writeSect?method=getMeterInitSituation",
					null, params, HttpResult.class);
			
			if(c2.getStatusCode()==200&&c2.getResultData()!=null) {
				
				rmap = JSONUtil.readValue(c2.getResultData().toString(), new TypeReference<HashMap<Long,Integer>>() {});
			}


		} catch (Exception e) {
			System.out.print("billing服务执行出错");
			e.printStackTrace();
		}
		
		return rmap;

		
	}
	
	
	//获取抄表区段中的计量点个数
	public HashMap<Long,Integer> getWriteSectMeterNum(){
		//获取数据
		List<HashMap<String,Long>> c1 = meterDAO.getMeterCountByWriteSect();
		//格式转换
		HashMap<Long,Integer> rmap = new HashMap<Long,Integer>();
		for(HashMap<String,Long> tmap : c1) {
			rmap.put(tmap.get("ID"), tmap.get("NUM")==null?0:tmap.get("NUM").intValue());
		}
		
		return rmap;
	}

}
