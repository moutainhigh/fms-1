package org.fms.auth.provider.mapper.mapper;


import java.util.List;

import org.fms.auth.provider.mapper.model.RoleDeptRel;

import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface RoleDeptRelMapper extends Mapper<RoleDeptRel> {
    List<RoleDeptRel> selectRoleDept(String roleId);
}