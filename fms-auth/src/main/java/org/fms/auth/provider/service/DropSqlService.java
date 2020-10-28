package org.fms.auth.provider.service;

import java.util.LinkedHashMap;
import java.util.List;

import org.fms.auth.autoconfigure.service.BaseService;
import org.fms.auth.provider.mapper.mapper.DropSqlMapper;
import org.fms.auth.provider.mapper.model.DropSql;
import org.springframework.stereotype.Service;

/**
 * Created by yxs on 2019/1/9.
 */
@Service
public class DropSqlService extends BaseService<DropSql> {
    public List<DropSql> selectDropSql() {
        return ((DropSqlMapper)mapper).selectDropSql();
    }
    public List<LinkedHashMap<String, Object>> findBySql(String sql) {
        return ((DropSqlMapper)mapper).findBySql(sql);

    }
}
