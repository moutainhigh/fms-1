package org.fms.auth.provider.service;

import java.util.List;

import org.fms.auth.autoconfigure.service.BaseService;
import org.fms.auth.provider.mapper.mapper.SystemInfoMapper;
import org.fms.auth.provider.mapper.model.SystemInfo;
import org.fms.auth.provider.pojo.response.ModuleAndSystemResponse;
import org.springframework.stereotype.Service;

/**
 * Created by yxs on 2019/1/9.
 */
@Service
public class SystemInfoService extends BaseService<SystemInfo> {
    public List<ModuleAndSystemResponse> selectModuleAndSystem() {
        return ((SystemInfoMapper)mapper).selectModuleAndSystem();
    }
}
