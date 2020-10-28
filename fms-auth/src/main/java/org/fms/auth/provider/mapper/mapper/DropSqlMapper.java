package org.fms.auth.provider.mapper.mapper;


import java.util.LinkedHashMap;
import java.util.List;

import org.fms.auth.provider.mapper.model.DropSql;

import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface DropSqlMapper extends Mapper<DropSql> {
    List<DropSql> selectDropSql();
    List<LinkedHashMap<String, Object>> findBySql(String sql);
}