/**
 * Auth:riozenc
 * Date:2019年3月13日 下午4:43:01
 * Title:com.riozenc.cim.webapp.action.TransformerAction.java
 **/
package org.fms.cim.webapp.archives.action;

import java.io.IOException;
import java.util.List;

import com.riozenc.cim.webapp.archives.domain.TransformerLineRelDomain;
import com.riozenc.cim.webapp.archives.service.ITransformerMeterRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.riozenc.cim.web.config.JsonGrid;
import com.riozenc.cim.webapp.archives.domain.TransformerDomain;
import com.riozenc.titanTool.common.json.utils.GsonUtils;
import reactor.core.publisher.Mono;

@ControllerAdvice
@RequestMapping("transformerMeterRel")
public class TransformerMeterRelAction {

    @Autowired
    @Qualifier("transformerMeterRelationServiceImpl")
    private ITransformerMeterRelationService transformerMeterRelationService;

  

    @ResponseBody
    @PostMapping(params = "method=getMeterByTransformer")
    public Mono<JsonGrid> getMeterByTransformer(@RequestBody String body)
            throws JsonParseException, JsonMappingException, IOException {
        TransformerDomain t = GsonUtils.readValue(body, TransformerDomain.class);
        return Mono.just(new JsonGrid(t, transformerMeterRelationService.getMeterByTransformer(t)));
    }

    @ResponseBody
    @PostMapping(params = "method=findTransformerLineByMeterIds")
    public List<TransformerLineRelDomain> findTransformerLineByMeterIds(String meterIdsJson) {
        List<Long> meterIds= GsonUtils.readValueToList(meterIdsJson,  Long.class);
        return transformerMeterRelationService.findTransformerLineByMeterIds(meterIds);
    }

}
