package org.fms.cim.webapp.archives.action;

import java.io.IOException;
import java.util.List;

import org.fms.cim.common.strategy.no.SequenceEnvironment;
import org.fms.cim.common.strategy.no.SequenceStrategy;
import org.fms.cim.webapp.archives.domain.MeterDomain;
import org.fms.cim.webapp.archives.domain.SettlementDomain;
import org.fms.cim.webapp.archives.domain.SettlementMeterRelDomain;
import org.fms.cim.webapp.archives.domain.SystemCommonConfigDomain;
import org.fms.cim.webapp.archives.service.IBillService;
import org.fms.cim.webapp.archives.service.IMeterService;
import org.fms.cim.webapp.archives.service.ISettlementMeterRelService;
import org.fms.cim.webapp.archives.service.ISettlementService;
import org.fms.cim.webapp.archives.service.ISystemCommonConfigService;
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
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.riozenc.titanTool.common.json.utils.JSONUtil;
import com.riozenc.titanTool.spring.web.http.HttpResult;

@ControllerAdvice
@RequestMapping("cim_bill")
public class BillAction {

    @Autowired
    @Qualifier("billServiceImpl")
    private IBillService billService;

    @Autowired
    @Qualifier("chargeFlowNoStrategy")
    private SequenceStrategy chargeFlowNoStrategy;

    @Autowired
    @Qualifier("arrearageNoStrategy")
    private SequenceStrategy arrearageNoStrategy;

    @Autowired
    @Qualifier("settlementMeterRelServiceImpl")
    private ISettlementMeterRelService settlementMeterRelService;

    @Autowired
    @Qualifier("settlementServiceImpl")
    private ISettlementService settlementService;

    @Autowired
    @Qualifier("systemCommonConfigServiceImpl")
    private ISystemCommonConfigService iSystemCommonConfigService;

    @Autowired
    @Qualifier("meterServiceImpl")
    private IMeterService meterService;

    /**
     * 保存由业扩传过来的客户、用电户、计量点、电表资产、互感器资产信息
     */
    @ResponseBody
    @RequestMapping(params = "method=getMeters")
    public List<MeterDomain> bemAddReceive(@RequestBody String body)
            throws JsonParseException, JsonMappingException, IOException {

        JsonNode jsonNode = JSONUtil.readValue(body, JsonNode.class);

        return billService.getMeters(jsonNode);

    }

    /**
     * 生成收费流水号
     *
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(params = "method=getChargeFlowNo")
    public String getChargeFlowNo() throws JsonParseException, JsonMappingException, IOException {
        SequenceEnvironment flowNoEnvironment = new SequenceEnvironment(chargeFlowNoStrategy);
        return flowNoEnvironment.generateSequenceNo(null);

    }

    /**
     * 生成欠费流水号
     *
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(params = "method=getArrearageNo")

    public String getArrearageNo() throws JsonParseException, JsonMappingException, IOException {
        SequenceEnvironment flowNoEnvironment = new SequenceEnvironment(arrearageNoStrategy);
        return flowNoEnvironment.generateSequenceNo(null);
    }

    // 根据计量点id获取结算户
    @ResponseBody
    @PostMapping(params = "method=getSettlementByBillingMeterIds")
    public HttpResult getSettlementByMeterIds(@RequestBody String body)
            throws JsonParseException, JsonMappingException, IOException {
        JsonNode jn = JSONUtil.readValue(body, JsonNode.class);
        String meterId = jn.get("meterIds") == null ? "" : jn.get("meterIds").toString();
        List<Long> meterIds = JSONUtil.readValue(meterId, new TypeReference<List<Long>>() {
        });

        List<SettlementMeterRelDomain> settlementMeterRelDomains = settlementMeterRelService
                .findSettlementByMeterIds(meterIds);

        return new HttpResult<>(HttpResult.SUCCESS, null,
                settlementMeterRelDomains);
    }

    // 根据结算户id 获取计量点
    @ResponseBody
    @PostMapping(params = "method=getMeterIdsBySettleId")
    public HttpResult getMeterIdsBySettleId(@RequestBody String body)
            throws JsonParseException, JsonMappingException, IOException {
        JsonNode jn = JSONUtil.readValue(body, JsonNode.class);
        if (jn.get("settleMentId") == null) {
            return new HttpResult<>(HttpResult.ERROR, "结算户Id为null");
        }
        long settleMentId = jn.get("settleMentId").asLong();
        SettlementMeterRelDomain settlementMeterRelDomain =
                new SettlementMeterRelDomain();
        settlementMeterRelDomain.setSettlementId(settleMentId);

        List<SettlementMeterRelDomain> settlementMeterRelDomains =
                settlementMeterRelService.findByWhere(settlementMeterRelDomain);

        return new HttpResult<>(HttpResult.SUCCESS, null,
                settlementMeterRelDomains);
    }

    /**
     * 生成收费流水号
     *
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(params = "method=getChargeFlowNoBySize")
    public  String getChargeFlowNoBySize(@RequestBody String body)
            throws JsonParseException, JsonMappingException, IOException {
        JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
        SequenceEnvironment flowNoEnvironment = new SequenceEnvironment(chargeFlowNoStrategy);
        return flowNoEnvironment.generateSequenceNo(null, jsonObject.get("size").getAsInt());

    }

    /**
     * 生成欠费流水号
     *
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(params = "method=getArrearageNoBySize")
    public String getArrearageNoBySize(@RequestBody String body) throws JsonParseException,
            JsonMappingException, IOException {
        JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
        SequenceEnvironment flowNoEnvironment = new SequenceEnvironment(arrearageNoStrategy);
        return flowNoEnvironment.generateSequenceNo(null, jsonObject.get("size").getAsInt());
    }

    @ResponseBody
    @RequestMapping(params = "method=findByKeyValue")
    public SystemCommonConfigDomain findByKeyValue(String body) throws IOException {
        SystemCommonConfigDomain systemCommonConfigDomain = JSONUtil.readValue(body, SystemCommonConfigDomain.class);
        return iSystemCommonConfigService.findByKeyValue(systemCommonConfigDomain.getType(),
                systemCommonConfigDomain.getParamKey());
    }

    @ResponseBody
    @RequestMapping(params = "method=findSystemCommonConfigByType")
    public List<SystemCommonConfigDomain> findSystemCommonConfigByType(@RequestBody String body) throws IOException {
        SystemCommonConfigDomain systemCommonConfigDomain = JSONUtil.readValue(body, SystemCommonConfigDomain.class);
        return iSystemCommonConfigService.findByWhere(systemCommonConfigDomain);
    }

    //获取银行代扣信息
    @ResponseBody
    @RequestMapping(params = "method=findBankSettlement")
    public List<SettlementDomain> findBankSettlement(@RequestBody(required =false) String body) throws IOException {
        SettlementDomain settlementDomain=JSONUtil.readValue(body, SettlementDomain.class);
        return settlementService.findBankSettlement(settlementDomain);
    }

    //根据结算户id获取所有的计量点
    @ResponseBody
    @RequestMapping(params = "method=getMeterIdsBySettlementId")
    public List<Long> getMeterIdsBySettlementId(@RequestBody String body) {
        JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
        return settlementMeterRelService.getMeterIdsBySettlementId(jsonObject.get("settlementId").getAsLong());
    }

    @ResponseBody
    @PostMapping(params = "method=findSettlementByIds")
    public HttpResult findSettlementByIds(@RequestBody String body)
            throws JsonParseException, JsonMappingException, IOException {
        JsonNode jn = JSONUtil.readValue(body, JsonNode.class);
        
        String idjson = jn.get("settleIds") == null ? "" : jn.get("settleIds").toString();
        
        List<Long> ids = JSONUtil.readValue(idjson,new TypeReference<List<Long>>() {});
        
        List<SettlementDomain> settlementDomains = settlementService.findSettlementByIds(ids);
        
        return new HttpResult<>(HttpResult.SUCCESS, null,settlementDomains);
    }

    @ResponseBody
    @PostMapping(params = "method=getMeterByMeterIds")
    public HttpResult getMeterByMeterIds(@RequestBody String body) throws IOException {
        List<Long> ids = JSONUtil.readValue(body, new TypeReference<List<Long>>() {});
        return new HttpResult<>(HttpResult.SUCCESS, null,
                meterService.getMeterByMeterIds(ids));
    }

    @ResponseBody
    @RequestMapping(params = "method=getSfMetersBySettlement")
    public List<MeterDomain> getSfMetersBySettlement(@RequestBody String body)
            throws JsonParseException, JsonMappingException, IOException {

        JsonNode jsonNode = JSONUtil.readValue(body, JsonNode.class);

        return billService.getMeters(jsonNode);

    }



}
