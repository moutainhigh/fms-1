package org.fms.auth.main.java.com.wisdom.auth.provider.mapper.mapper;


import org.fms.auth.main.java.com.wisdom.auth.provider.mapper.model.RoleInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface RoleInfoMapper extends Mapper<RoleInfo> {
    List<RoleInfo> getRoleByUserId(@Param("userId") Integer userId);
    List<RoleInfo> userRoleTree(@Param("USER_ID") Integer userId);

}