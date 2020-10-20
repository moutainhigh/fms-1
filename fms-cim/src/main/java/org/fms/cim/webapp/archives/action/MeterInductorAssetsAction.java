package org.fms.cim.webapp.archives.action;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.riozenc.cim.webapp.archives.service.IMeterInductorAssetsService;
import com.riozenc.titanTool.common.json.utils.GsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@ControllerAdvice
@RequestMapping("meterInductorAssets")
public class MeterInductorAssetsAction {

    @Autowired
    @Qualifier("meterInductorAssetsServiceImpl")
    private IMeterInductorAssetsService meterInductorAssetsService;

    //获取计量点上下一个表序号
    @ResponseBody
    @PostMapping(params = "method=getNextInductorOrder")
    public Byte getNextInductorOrder(@RequestBody String body)
            throws JsonParseException, JsonMappingException, IOException {
        Map<String,String> t = GsonUtils.readValue(body, Map.class);
        Long meterId=new Long(t.get("meterId").toString());
        return meterInductorAssetsService.getNextInductorOrder(meterId);
    }
}
