/**
 * Auth:riozenc
 * Date:2019年3月14日 上午8:50:19
 * Title:com.riozenc.cim.webapp.action.MeterAction.java
 **/
package org.fms.cim.webapp.archives.action;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.riozenc.cim.web.config.JsonGrid;
import com.riozenc.cim.webapp.archives.domain.*;
import com.riozenc.cim.webapp.archives.service.IMeterInductorAssetsService;
import com.riozenc.cim.webapp.archives.service.IMeterMeterAssetsService;
import com.riozenc.cim.webapp.archives.service.IMeterService;
import com.riozenc.cim.webapp.archives.service.ISystemCommonConfigService;
import com.riozenc.cim.webapp.assets.domain.InductorAssetsDomain;
import com.riozenc.cim.webapp.assets.domain.InductorAssetsEntity;
import com.riozenc.cim.webapp.assets.domain.MeterAssetsDomain;
import com.riozenc.cim.webapp.assets.domain.MeterAssetsEntity;
import com.riozenc.cim.webapp.assets.service.IInductorAssetsService;
import com.riozenc.cim.webapp.assets.service.IMeterAssetsService;
import com.riozenc.titanTool.common.json.utils.GsonUtils;
import com.riozenc.titanTool.spring.web.http.HttpResult;
import com.riozenc.titanTool.spring.web.http.HttpResultPagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.*;

@ControllerAdvice
@RequestMapping("meter")
public class MeterAction {

    @Autowired
    @Qualifier("meterServiceImpl")
    private IMeterService meterService;

    @Autowired
    @Qualifier("meterInductorAssetsServiceImpl")
    private IMeterInductorAssetsService meterInductorAssetsService;

    @Autowired
    @Qualifier("meterMeterAssetsServiceImpl")
    private IMeterMeterAssetsService meterMeterAssetsService;

    @Autowired
    @Qualifier("inductorAssetsServiceImpl")
    private IInductorAssetsService inductorAssetsService;

    @Autowired
    @Qualifier("meterAssetsServiceImpl")
    private IMeterAssetsService meterAssetsService;

    @Autowired
    @Qualifier("systemCommonConfigServiceImpl")
    private ISystemCommonConfigService systemCommonConfigService;

    /**
     * 根据用户获取计量点
     */
    @ResponseBody
    @PostMapping(params = "method=getMeterByUser")
    public JsonGrid getMeterByUser(@RequestBody String body)
            throws JsonParseException, JsonMappingException, IOException {
        UserDomain userDomain = GsonUtils.readValue(body, UserDomain.class);
        return new JsonGrid(userDomain, meterService.getMeterByUser(userDomain));
    }

    /**
     * 根据计量点信息获取计量点
     */
    @ResponseBody
    @PostMapping(params = "method=getMeterByWhere")
    public Mono<JsonGrid> getMeterByWhere(@RequestBody String body)
            throws JsonParseException, JsonMappingException, IOException {
        MeterDomain e = GsonUtils.readValue(body, MeterDomain.class);
        return Mono.just(new JsonGrid(e, meterService.findByWhere(e)));
    }

    /**
     * 根据客户id获取计量点
     */
    @ResponseBody
    @PostMapping(params = "method=getMeterByCustomer")
    public Mono<JsonGrid> getMetersByCustomer(@RequestBody String body) {
        MeterDomain e = GsonUtils.readValue(body, MeterDomain.class);
        return Mono.just(new JsonGrid(e, meterService.getMeterByCustomer(e)));
    }

    @ResponseBody
    @PostMapping(params = "method=queryMetersByCustomer")
    public List<MeterDomain> queryMetersByCustomer(@RequestBody String meter) {
        Map<String, Object> map = GsonUtils.readValue(meter, Map.class);
        MeterDomain meterDomain = GsonUtils.readValue(GsonUtils.toJson(map.get("meter")), MeterDomain.class);
        return meterService.getMeterByCustomer(meterDomain);
    }

    /**
     * 新增计量点
     */
    @ResponseBody
    @PostMapping(params = "method=insertMeter")
    public Mono<HttpResult> insertMeter(@RequestBody String body)
            throws JsonParseException, JsonMappingException, IOException {

        MeterDomain t = GsonUtils.readValue(body, MeterDomain.class);
        //计费表必须有电价
        if (Objects.equals(t.getMeterType(), (byte) 0) && t.getPriceType() == null) {
            return Mono.just(new HttpResult<>(HttpResult.ERROR, "新增计量点信息失败，计费计量点必须选择电价"));
        }
        //基本电费判断
        if (!Objects.equals(t.getBaseMoneyFlag(), (byte) 0) && t.getBasicPrice() == null) {
            return Mono.just(new HttpResult<>(HttpResult.ERROR, "新增计量点信息失败，需要计算基本电费时必须选择基本电费电价"));

        } else if (Objects.equals(t.getBaseMoneyFlag(), (byte) 2) && t.getChargingCapacity() == null) {
            return Mono.just(new HttpResult<>(HttpResult.ERROR, "新增计量点信息失败，基本电费按容量计算时必须填写计费容量"));

        } else if (Objects.equals(t.getBaseMoneyFlag(), (byte) 1) && t.getNeedIndex() == null) {
            return Mono.just(new HttpResult<>(HttpResult.ERROR, "新增计量点信息失败，基本电费按需要计算时必须填写最大需量"));

        }


        // meter_no查重
        MeterDomain tt = new MeterDomain();
        tt.setMeterNo(t.getMeterNo());
        int count = meterService.findByNo(tt).size();
        if (count > 0) {
            return Mono.just(new HttpResult<>(HttpResult.ERROR, "新增计量点信息失败，计量点号重复"));
        }
        // 算费次数，前台不传默认为1次
        if (t.getCountTimes() == null) {
            t.setCountTimes((byte) 1);
        }
        t.setCreateDate(new Date());
        System.out.println(t);
        if (meterService.insert(t) > 0) {
            return Mono.just(new HttpResult<>(HttpResult.SUCCESS, "新增计量点信息成功"));
        }
        return Mono.just(new HttpResult<>(HttpResult.ERROR, "新增计量点信息失败"));
    }

    /**
     * 修改计量点
     */
    @ResponseBody
    @PostMapping(params = "method=updateMeter")
    public Mono<HttpResult> updateMeter(@RequestBody String body)
            throws JsonParseException, JsonMappingException, IOException {

        MeterDomain t = GsonUtils.readValue(body, MeterDomain.class);
        t.setCreateDate(new Date());

        int count = meterService.update(t);
        if (count > 0) {
            return Mono.just(new HttpResult<>(HttpResult.SUCCESS, "修改计量点信息成功"));
        }
        return Mono.just(new HttpResult<>(HttpResult.ERROR, "修改计量点信息失败"));
    }

    /**
     * 根据电能表资产号获取测量点档案 不分页 精确查询
     */
    @ResponseBody
    @PostMapping(params = "method=getMeterByMeterAssestsNo")
    public Mono<List<MeterDomain>> getMeterByMeterAssestsNo(@RequestBody String body)
            throws JsonParseException, JsonMappingException, IOException {
        MeterAssetsDomain t = GsonUtils.readValue(body, MeterAssetsDomain.class);
        List<MeterDomain> list = meterService.getMeterByMeterAssestsNo(t);
        return Mono.just(list);
    }

    /**
     * 根据互感器资产号获取测量点档案 不分页 精确查询
     */
    @ResponseBody
    @PostMapping(params = "method=getMeterByInductorAssestsNo")
    public Mono<List<MeterDomain>> getMeterByInductorAssestsNo(@RequestBody String body)
            throws JsonParseException, JsonMappingException, IOException {
        InductorAssetsDomain t = GsonUtils.readValue(body, InductorAssetsDomain.class);
        List<MeterDomain> list = meterService.getMeterByInductorAssestsNo(t);
        return Mono.just(list);
    }

    /*------------------------------抄表初始化---以下方法已弃用，试运行后删除----------------------------*/
    // 计量点档案
//	@ResponseBody
//	@PostMapping(params = "method=getMeterByIds")
//	public Mono<List<MeterDomain>> getMeterByIds(@RequestBody String body)
//			throws JsonParseException, JsonMappingException, IOException {
//		JsonNode jsonNode = JSONUtil.readValue(body, JsonNode.class);
//		
//		List<Long> ids = 
//				JSONUtil.readValue(jsonNode.get("ids").toString(), new TypeReference<List<Long>>() {});
//
//		List<MeterDomain> list = meterService.getMeterByIds(ids);
//		return Mono.just(list);
//	}
//
//	// 计量点关系
//	@ResponseBody
//	@PostMapping(params = "method=getMeterRelByMeterIds")
//	public Mono<List<MeterDomain>> getMeterRelByMeterIds(@RequestBody String body)
//			throws JsonParseException, JsonMappingException, IOException {
//		JsonNode jsonNode = JSONUtil.readValue(body, JsonNode.class);
//		List<Long> ids = 
//				JSONUtil.readValue(jsonNode.get("ids").toString(), new TypeReference<List<Long>>() {});
//
//		List<MeterDomain> list = meterService.getMeterRelByMeterIds(ids);
//		return Mono.just(list);
//	}
//
//	// 计量点档案-抄表段
//	@ResponseBody
//	@PostMapping(params = "method=getMeterByWriteSectIds")
//	public Mono<List<MeterDomain>> getMeterByWriteSectIds(@RequestBody String body)
//			throws JsonParseException, JsonMappingException, IOException {
//		JsonNode jsonNode = JSONUtil.readValue(body, JsonNode.class);
//		
//		List<Long> ids = 
//				JSONUtil.readValue(jsonNode.get("ids").toString(), new TypeReference<List<Long>>() {});
//
//		List<Long> meterIds = meterService.getMeterIdsByWriteSectIds(ids);
//
//		List<MeterDomain> list = meterService.getMeterByIds(meterIds);
//		return Mono.just(list);
//	}
//
//	// 计量点关系-抄表段
//	@ResponseBody
//	@PostMapping(params = "method=getMeterRelByWriteSectIds")
//	public Mono<List<MeterDomain>> getMeterRelByWriteSectIds(@RequestBody String body)
//			throws JsonParseException, JsonMappingException, IOException {
//		JsonNode jsonNode = JSONUtil.readValue(body, JsonNode.class);
//		String s = jsonNode.get("ids").toString();
//		String meterIds = meterService.getMeterIdsByWriteSectIds(s);
//		List<MeterDomain> list = meterService.getMeterRelByMeterIds(meterIds);
//		return Mono.just(list);
//	}

    @ResponseBody
    @PostMapping(params = "method=getMeterMeterInformation")
    public HttpResult getMeterInformation(@RequestBody String body) {

        MeterInformationEntity meterInformationEntity = GsonUtils.readValue(body, MeterInformationEntity.class);
        List<MeterInformationEntity> meterInformationEntities = meterService
                .getMeterInformation(meterInformationEntity);
        try {
            // 计量点号集合拼接字符串
            // meterInformationEntities.stream().map(t -> String.valueOf(t
            // .getId())).collect(Collectors.toList());
            // in方法查计量点关系表
            meterInformationEntities.stream().forEach(t -> {
                t.setParent("1");
                // 计量点关系
                List<MeterMeterAssetsRelDomain> meterMeterAssetsRelDomains = meterMeterAssetsService
                        .getMeterEntityByMeterIds(t.getId().toString());

                List<MeterAssetsEntity> meterAssetsEntities = new ArrayList<>();
                meterMeterAssetsRelDomains.stream().forEach(m -> {
                    MeterAssetsEntity meterAssetsEntity = new MeterAssetsEntity();
                    meterAssetsEntity = meterAssetsService.findMeterEntityByWhere(m.getMeterAssetsId());
                    meterAssetsEntity.setParent("0");
                    // 电能表信息
                    SystemCommonConfigDomain systemCommonConfigDomain = systemCommonConfigService
                            .findByKeyValue("FUNC_KIND_CODE", new Long(meterAssetsEntity.getFuncKindCode()));
                    // 构造前台消息
                    meterAssetsEntity.setMeterId(m.getMeterId());
                    meterAssetsEntity.setShowName4(systemCommonConfigDomain.getParamValue());
                    meterAssetsEntity.setMadeDate(m.getCreateDate());
                    meterAssetsEntity.setTsFlag(m.getTsFlag());
                    systemCommonConfigDomain = systemCommonConfigService.findByKeyValue("FUNCTION_CODE",
                            m.getFunctionCode());
                    meterAssetsEntity.setShowName5(systemCommonConfigDomain.getParamValue());
                    systemCommonConfigDomain = systemCommonConfigService.findByKeyValue("POWER_DIRECTION",
                            new Long(m.getPowerDirection()));
                    meterAssetsEntity.setShowName6(systemCommonConfigDomain.getParamValue());
                    meterAssetsEntity.setPowerDirection(m.getPowerDirection());
                    meterAssetsEntity.setPhaseSeq(m.getPhaseSeq());
                    meterAssetsEntity.setFunctionCode(m.getFunctionCode());
                    meterAssetsEntity.setFactorNum(m.getFactorNum());
                    meterAssetsEntities.add(meterAssetsEntity);
                });
                t.setMeterAssetsEntities(meterAssetsEntities);
            });
        } catch (Exception e) {
            e.printStackTrace();
            return new HttpResult<>(HttpResult.ERROR, "查询失败");
        }
        return new HttpResult<>(HttpResult.SUCCESS,
                new HttpResultPagination(meterInformationEntity, meterInformationEntities));
    }

    @ResponseBody
    @PostMapping(params = "method=getMeterInductorInformation")
    public HttpResult getMeterInductorInformation(@RequestBody String body) {

        MeterInformationEntity meterInformationEntity = GsonUtils.readValue(body, MeterInformationEntity.class);
        List<MeterInformationEntity> meterInformationEntities = meterService
                .getMeterInformation(meterInformationEntity);
        try {
            // 遍历计量点
            meterInformationEntities.stream().forEach(t -> {
                t.setParent("1");
                // 计量点关系
                List<MeterInductorAssetsRelDomain> meterInductorAssetsRelEntities = meterInductorAssetsService
                        .getInductorEntityByMeterIds(t.getId().toString());

                List<InductorAssetsEntity> inductorAssetsEntities = new ArrayList<>();
                // 遍历计量点互感器关系
                meterInductorAssetsRelEntities.stream().forEach(m -> {
                    InductorAssetsEntity inductorAssetsEntity = new InductorAssetsEntity();
                    inductorAssetsEntity = inductorAssetsService
                            .findInductEntityByWhere(m.getInductorAssetsId().toString());
                    inductorAssetsEntity.setParent("0");
                    inductorAssetsEntity.setPhaseSeq(m.getPhaseSeq());
                    // 更改返回前台字段，配合已有的换表记录功能
                    switch (inductorAssetsEntity.getInductorType()) {
                        case 1:
                            inductorAssetsEntity.setCtAssetsId(inductorAssetsEntity.getId());
                            inductorAssetsEntity.setCtAssetsNo(inductorAssetsEntity.getInductorAssetsNo());
                            break;
                        case 2:
                            inductorAssetsEntity.setPtAssetsId(inductorAssetsEntity.getId());
                            inductorAssetsEntity.setPtAssetsNo(inductorAssetsEntity.getInductorAssetsNo());

                        case 3:
                            inductorAssetsEntity.setCtAssetsId(inductorAssetsEntity.getId());
                            inductorAssetsEntity.setCtAssetsNo(inductorAssetsEntity.getInductorAssetsNo());
                            inductorAssetsEntity.setPtAssetsId(inductorAssetsEntity.getId());
                            inductorAssetsEntity.setPtAssetsNo(inductorAssetsEntity.getInductorAssetsNo());

                    }
                    // 计量点号
                    inductorAssetsEntity.setId(m.getMeterId());
                    // 更新互感器型号
                    SystemCommonConfigDomain systemCommonConfigDomain = systemCommonConfigService
                            .findByKeyValue("INDUCTOR_TYPE", new Long(inductorAssetsEntity.getInductorType()));
                    inductorAssetsEntity.setShowName4(systemCommonConfigDomain.getParamValue());
                    inductorAssetsEntity.setMadeDate(m.getCreateDate());
                    inductorAssetsEntity.setMeterId(m.getMeterId());
                    inductorAssetsEntities.add(inductorAssetsEntity);
                });

                t.setInductorAssetsEntities(inductorAssetsEntities);
            });
        } catch (Exception e) {
            return new HttpResult<>(HttpResult.SUCCESS, "查询失败");
        }
        return new HttpResult<>(HttpResult.SUCCESS,
                new HttpResultPagination(meterInformationEntity, meterInformationEntities));
    }

    @ResponseBody
    @PostMapping(params = "method=findMeterAndMeterRelByMeterIds")
    public List<MeterDomain> findMeterAndMeterRelByMeterIds(@RequestBody String meterIdsJson) {
        HashMap<String,Object> map=  GsonUtils.readValue(meterIdsJson, HashMap.class);
        List<Long> meterIds=GsonUtils.readValueToList(GsonUtils.toJson(map.get("meterIds")),Long.class);
        return meterService.findMetersByMeterIds(meterIds);
    }

    @ResponseBody
    @PostMapping(params = "method=findMeterByMeterIds")
    public List<MeterDomain> findMeterByMeterIds(@RequestBody String meterIdsJson) {
        HashMap<String,Object> map=  GsonUtils.readValue(meterIdsJson, HashMap.class);
        List<Long> meterIds=GsonUtils.readValueToList(GsonUtils.toJson(map.get("meterIds")),Long.class);
        return meterService.getMeterByMeterIds(meterIds);
    }

    @ResponseBody
    @PostMapping(params = "method=findMeterByUserIds")
    public List<MeterDomain> findMeterByUserIds(@RequestBody String userIdsJson) {
        HashMap<String,Object> map = GsonUtils.readValue(userIdsJson, HashMap.class);
        List<Long> userIds=GsonUtils.readValueToList(GsonUtils.toJson(map.get("userIds")),Long.class);
        return meterService.getMeterByUserIds(userIds);
    }

    @ResponseBody
    @PostMapping(params = "method=getMeterAndUserByIds")
    public List<MeterDomain> getMeterAndUserByIds(@RequestBody String meterIdsJson) {
        HashMap<String,List<Long>> map=  GsonUtils.readValue(meterIdsJson, HashMap.class);
        return meterService.getMeterAndUserByIds(map.get("meterIds"));
    }

}
