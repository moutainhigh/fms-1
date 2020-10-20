package org.fms.cim.webapp.archives.action;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.riozenc.cim.web.config.JsonGrid;
import com.riozenc.cim.webapp.archives.domain.MeterDomain;
import com.riozenc.cim.webapp.archives.domain.TransformerMeterRelationDomain;
import com.riozenc.cim.webapp.archives.service.IMeterTransRelService;
import com.riozenc.titanTool.common.json.utils.GsonUtils;
import com.riozenc.titanTool.common.json.utils.JSONUtil;
import com.riozenc.titanTool.spring.web.http.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

@ControllerAdvice
@RequestMapping("meterTransRel")
public class MeterTransRelAction {
    @Autowired
    @Qualifier("meterTransRelServiceImpl")
    private IMeterTransRelService meterTransRelService;
    @ResponseBody
    @PostMapping(params = "method=getTransformerByNoMeterRel")
    public Mono<JsonGrid> getTransformerByNoMeterRel(@RequestBody String body)
            throws JsonParseException, JsonMappingException, IOException {
        MeterDomain t = GsonUtils.readValue(body, MeterDomain.class);
        List<MeterDomain> list = meterTransRelService.getTransformerByNoMeterRel(t);
        return Mono.just(new JsonGrid(t, list));
    }

    @ResponseBody
    @PostMapping(params = "method=getTransformerByMeterRel")
    public Mono<JsonGrid> getTransformerByMeterRel(@RequestBody String body)
            throws JsonParseException, JsonMappingException, IOException {
        MeterDomain t = GsonUtils.readValue(body, MeterDomain.class);
        List<MeterDomain> list = meterTransRelService.getTransformerByMeterRel(t);
        return Mono.just(new JsonGrid(t, list));
    }

    /**
     * 根据抄表区段id信息获取计量点(不分页)
     * */
    @ResponseBody
    @PostMapping(params = "method=getMeterByWriteSectionId")
    public Mono<JsonGrid> getMeterByWriteSectionId(@RequestBody String body)
            throws JsonParseException, JsonMappingException, IOException {
        MeterDomain e = GsonUtils.readValue(body, MeterDomain.class);
        return Mono.just(new JsonGrid(e, meterTransRelService.getMeterByWriteSectionId(e)));
    }

    //TODO 方法很垃圾需要修改	绑定变压器组关系
    @ResponseBody
    @PostMapping(params = "method=addTransformerByMeterRel")
    public Mono<HttpResult> addTransformerByMeterRel(@RequestBody String body)
            throws JsonParseException, JsonMappingException, IOException {

        JsonNode jsonNode = JSONUtil.readValue(body, JsonNode.class);

        List<TransformerMeterRelationDomain> list =
                JSONUtil.readValue(jsonNode.get("rel").toString(), new TypeReference<List<TransformerMeterRelationDomain>>() {});

        MeterDomain deleteList =
                JSONUtil.readValue(jsonNode.get("deleteList").toString(), new TypeReference<MeterDomain>() {});

        String checkFlag = jsonNode.get("checkFlag").toString();
//        if ("true".equals(checkFlag)) {
//            if (list.size() > 0) {
//                String transformerGroupNo = list.get(0).getTransformerGroupNo();
//                if (transformerGroupNo != null && transformerGroupNo != "null") {
//                    TransformerMeterRelationDomain rel = new TransformerMeterRelationDomain();
//                    rel.setTransformerGroupNo(transformerGroupNo);
//                    List<TransformerMeterRelationDomain> l = meterTransRelService.findTransformerGroupNo(rel);
//                    if (l.size() > 0) {
//                        return Mono.just(new HttpResult(HttpResult.ERROR, "请选择新的变压器组号，该组号无法使用。"));
//                    }
//                }
//            }
//        }
        int count = meterTransRelService.addTransformerByMeterRel(list, deleteList);
        if (list.size() > 0) {
            if(count>0) {
                return Mono.just(new HttpResult(HttpResult.SUCCESS, "添加关系成功"));
            } else {
                return Mono.just(new HttpResult(HttpResult.ERROR, "添加关系失败"));
            }
        } else {
            if(count == 0) {
                return Mono.just(new HttpResult(HttpResult.SUCCESS, "删除关系成功"));
            } else {
                return Mono.just(new HttpResult(HttpResult.ERROR, "删除关系失败"));
            }
        }
    }
    
    /**
     * 	 修改变损分摊方式
     * */
    @ResponseBody
    @PostMapping(params = "method=updateTransLoss")
    public Mono<HttpResult> updateTransLoss(@RequestBody String body)
            throws JsonParseException, JsonMappingException, IOException {
    	TransformerMeterRelationDomain e = GsonUtils.readValue(body, TransformerMeterRelationDomain.class);
        int count = meterTransRelService.updateTransLoss(e);
    	
        if(count == 1) {
            return Mono.just(new HttpResult(HttpResult.SUCCESS, "修改变损分摊方式成功"));
        } else {
            return Mono.just(new HttpResult(HttpResult.ERROR, "修改变损分摊方式失败"));
        }   
    }
    
    /**
     * 	 根据变压器关系获取计量点
     * */
    @ResponseBody
    @PostMapping(params = "method=getMeterByTransRel")
    public Mono<JsonGrid> getMeterByTransRel(@RequestBody String body)
            throws JsonParseException, JsonMappingException, IOException {
    	TransformerMeterRelationDomain e = GsonUtils.readValue(body, TransformerMeterRelationDomain.class);
    	
    	return Mono.just(new JsonGrid(e, meterTransRelService.getMeterByTransRel(e)));
    }

}
