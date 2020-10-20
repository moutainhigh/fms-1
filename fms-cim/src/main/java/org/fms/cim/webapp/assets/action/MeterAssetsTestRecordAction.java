/**
 *    Auth:riozenc
 *    Date:2019年3月14日 下午8:26:01
 *    Title:com.riozenc.cim.webapp.action.MeterAssetsAction.java
 **/
package org.fms.cim.webapp.assets.action;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.riozenc.cim.web.config.JsonGrid;
import com.riozenc.cim.webapp.archives.service.ISystemCommonConfigService;
import com.riozenc.cim.webapp.assets.domain.InductorAssetsTestRecordDetailDomain;
import com.riozenc.cim.webapp.assets.domain.MeterAssetsDomain;
import com.riozenc.cim.webapp.assets.domain.MeterAssetsTestRecordDetailTpDomain;
import com.riozenc.cim.webapp.assets.domain.MeterAssetsTestRecordDetailUpDomain;
import com.riozenc.cim.webapp.assets.domain.MeterAssetsTestRecordDomain;
import com.riozenc.cim.webapp.assets.service.IMeterAssetsTestRecordService;
import com.riozenc.titanTool.common.json.utils.GsonUtils;
import com.riozenc.titanTool.common.json.utils.JSONUtil;
import com.riozenc.titanTool.spring.web.http.HttpResult;

import reactor.core.publisher.Mono;

/**
 * 互感器资产检测
 *
 * @author yhx
 *
 */
@ControllerAdvice(assignableTypes = MeterAssetsTestRecordAction.class)
@RequestMapping("meterAssetsTestRecord")
public class MeterAssetsTestRecordAction  {

	@Autowired
	@Qualifier("meterAssetsTestRecordServiceImpl")
	private IMeterAssetsTestRecordService meterAssetsTestRecordService;
	@Autowired
	@Qualifier("systemCommonConfigServiceImpl")
	private ISystemCommonConfigService systemCommonConfigService;

	
	/**
	 * 新增电能表资产检定记录
	 *
	 * @param 
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@ResponseBody
	@PostMapping(params = "method=addMeterAssetsTestRecord")
	public HttpResult addMeterAssetsTestRecord(@RequestBody String body) throws JsonParseException, JsonMappingException, IOException {
		
		JsonNode jsonNode = JSONUtil.readValue(body, JsonNode.class);
		
		MeterAssetsTestRecordDomain matr = 
				JSONUtil.readValue(jsonNode.get("meterAssetsTestRecord").toString(), MeterAssetsTestRecordDomain.class);
		
		MeterAssetsTestRecordDetailUpDomain matrdu = 
				JSONUtil.readValue(jsonNode.get("meterAssetsTestRecordDetailList").toString(), MeterAssetsTestRecordDetailUpDomain.class);
		
		MeterAssetsTestRecordDetailTpDomain matrdt = 
				JSONUtil.readValue(jsonNode.get("meterAssetsTestRecordDetailList").toString(), MeterAssetsTestRecordDetailTpDomain.class);

		if(matr==null || matrdu==null || matrdt==null) {
			return new HttpResult<>(HttpResult.ERROR, "检测记录或检测记录明细为空.");
		}

		return meterAssetsTestRecordService.addMeterAssetsUseRecord(matr,matrdu,matrdt);

	}

	@ResponseBody
	@PostMapping(params = "method=testTemp")
	public HttpResult testTemp(@RequestBody String body) throws JsonParseException, JsonMappingException, IOException {
		
		JsonNode jsonNode = JSONUtil.readValue(body, JsonNode.class);
		
		String testResult = 
				JSONUtil.readValue(jsonNode.get("testResult").toString(), String.class);
		
		List<MeterAssetsDomain> MeterAssetsList = 
				JSONUtil.readValue(jsonNode.get("MeterAssetsList").toString(), new TypeReference<List<MeterAssetsDomain>>() {});

		if("0".equals(testResult)) {
			for(MeterAssetsDomain ma : MeterAssetsList) {
				ma.setStatus((byte) 5);//检定合格后更新为库存待装
			}
		}
		//TODO 需要添加检定不合格的处理
		int count = meterAssetsTestRecordService.testTemp(MeterAssetsList);
		if (count > 0)
			return new HttpResult<>(HttpResult.SUCCESS, "更新成功.");
		else
			return new HttpResult<>(HttpResult.ERROR, "更新失败.");

	}
	

	/**
	 * 查询互感器资产信息领用记录
	 *
	 * @param MeterAssetsTestRecordDomain
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@ResponseBody
	@PostMapping(params = "method=getMeterAssetsUseRecordByKey")
	public Mono<MeterAssetsTestRecordDomain> getMeterAssetsUseRecordByKey(@RequestBody MeterAssetsTestRecordDomain t) {
		// TODO Auto-generated method stub
		MeterAssetsTestRecordDomain rt = meterAssetsTestRecordService.findByKey(t);
		return Mono.just(rt);
	}

	/**
	 * 查询互感器资产
	 * */
	@ResponseBody
	@PostMapping(params = "method=getMeterAssetsUseRecordByWhere")
	public Mono<JsonGrid> getMeterAssetsUseRecordByWhere(@RequestBody String body) {
		MeterAssetsTestRecordDomain t = GsonUtils.readValue(body, MeterAssetsTestRecordDomain.class);

		List<MeterAssetsTestRecordDomain> list = meterAssetsTestRecordService.findByWhere(t);

		JsonGrid grid = new JsonGrid(t, list);

		return Mono.just(grid);
	}

	/**
	 * 更新
	 *
	 * @param MeterAssetsTestRecordDomain
	 * @return
	 */
	@ResponseBody
	@PostMapping(params = "method=update")
	 Object update(@RequestBody String body) {

		MeterAssetsTestRecordDomain t = GsonUtils.readValue(body, MeterAssetsTestRecordDomain.class);

		int i = meterAssetsTestRecordService.update(t);
		if (i > 0)
			return new HttpResult<>(HttpResult.SUCCESS, "更新成功.");
		else
			return new HttpResult<>(HttpResult.ERROR, "更新失败.");
	}


}
