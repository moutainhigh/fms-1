package org.fms.auth.provider.mapper.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.fms.auth.provider.mapper.model.RoleInfo;

import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface RoleInfoMapper extends Mapper<RoleInfo> {
    List<RoleInfo> getRoleByUserId(@Param("userId") Integer userId);
    List<RoleInfo> userRoleTree(@Param("USER_ID") Integer userId);

}