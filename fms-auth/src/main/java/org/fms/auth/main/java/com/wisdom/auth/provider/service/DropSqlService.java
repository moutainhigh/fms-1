package org.fms.auth.main.java.com.wisdom.auth.provider.service;

import org.fms.auth.main.java.com.wisdom.auth.autoconfigure.service.BaseService;
import org.fms.auth.main.java.com.wisdom.auth.provider.mapper.model.DropSql;
import org.fms.auth.main.java.com.wisdom.auth.provider.mapper.mapper.DropSqlMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

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
