package org.fms.auth.main.java.com.wisdom.auth.provider.service;

import org.fms.auth.main.java.com.wisdom.auth.autoconfigure.service.BaseService;
import org.fms.auth.main.java.com.wisdom.auth.provider.mapper.model.SystemInfo;
import org.fms.auth.main.java.com.wisdom.auth.provider.pojo.response.ModuleAndSystemResponse;
import org.fms.auth.main.java.com.wisdom.auth.provider.mapper.mapper.SystemInfoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yxs on 2019/1/9.
 */
@Service
public class SystemInfoService extends BaseService<SystemInfo> {
    public List<ModuleAndSystemResponse> selectModuleAndSystem() {
        return ((SystemInfoMapper)mapper).selectModuleAndSystem();
    }
}
