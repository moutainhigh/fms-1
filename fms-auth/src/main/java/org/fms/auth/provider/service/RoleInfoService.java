package org.fms.auth.provider.service;

import java.util.List;

import org.fms.auth.autoconfigure.service.BaseService;
import org.fms.auth.provider.mapper.mapper.RoleInfoMapper;
import org.fms.auth.provider.mapper.model.RoleInfo;
import org.springframework.stereotype.Service;

/**
 * Created by yxs on 2019/1/9.
 */
@Service
public class RoleInfoService extends BaseService<RoleInfo> {

    /**
     * 根据用户查询角色
     * @param userId
     * @return
     */
    public List<RoleInfo> getRoleByUserId(Integer userId) {
        return ((RoleInfoMapper)mapper).getRoleByUserId(userId);
    }

    public List<RoleInfo> userRoleTree(Integer userId) {
        return ((RoleInfoMapper)mapper).userRoleTree(userId);
    }


}
