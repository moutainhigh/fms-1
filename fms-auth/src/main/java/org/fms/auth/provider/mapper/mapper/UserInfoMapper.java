package org.fms.auth.provider.mapper.mapper;


import java.util.List;
import java.util.Map;

import org.fms.auth.provider.mapper.model.UserInfo;

import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface UserInfoMapper extends Mapper<UserInfo> {
    List<UserInfo> getUserTableByRoleId(UserInfo info);
    List<UserInfo> getUnUserTableByRoleId(UserInfo info);
    UserInfo selectHegangUser(UserInfo info);
    List<Map<Long,Object>> findMapByDomain(UserInfo t) ;
}