package org.fms.auth.provider.mapper.mapper;


import java.util.List;

import org.fms.auth.provider.mapper.model.SystemInfo;
import org.fms.auth.provider.pojo.response.ModuleAndSystemResponse;

import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface SystemInfoMapper extends Mapper<SystemInfo> {
    List<ModuleAndSystemResponse> selectModuleAndSystem();
}