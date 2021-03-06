package org.fms.auth.provider.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.fms.auth.autoconfigure.controller.CrudController;
import org.fms.auth.provider.common.pojo.TableData;
import org.fms.auth.provider.mapper.model.DropSql;
import org.fms.auth.provider.pojo.ResponseData;
import org.fms.auth.provider.pojo.request.DropSqlRequest;
import org.fms.auth.provider.service.DropSqlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fp295 on 2018/5/13.
 */
@RestController
public class DropSqlController extends CrudController<DropSql, DropSqlRequest> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public Map<String,List<LinkedHashMap<String, Object>>> dropMap=new HashMap<String,List<LinkedHashMap<String, Object>>>();

    @Autowired
    private DropSqlService dropSqlService;


    @PostMapping("/system/getSysDropFromSql")
    @ResponseBody
    public Object getSysDropFromSql() {
        List<DropSql> dropList=dropSqlService.selectDropSql();
        for (DropSql dom:dropList) {
            List<LinkedHashMap<String, Object>> list = dropSqlService.findBySql(dom.getDropsql());
            dropMap.put(dom.getDropName(),list);
        }
        return dropMap;
    }



    @Override
    protected ResponseData<TableData<DropSql>> queryRecord(DropSqlRequest query) {
        return null;
    }

    @Override
    protected ResponseData<DropSql> addRecord(DropSql record) {
        return null;
    }

    @Override
    protected ResponseData<DropSql> deleteRecord(List<DropSql> record) {
        return null;
    }

    @Override
    protected ResponseData<DropSql> updateRecord(DropSql record) {
        return null;
    }
}
