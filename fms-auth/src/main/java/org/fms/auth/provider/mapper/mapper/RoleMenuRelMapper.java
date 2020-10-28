package org.fms.auth.provider.mapper.mapper;


import java.util.List;

import org.fms.auth.provider.mapper.model.RoleMenuRel;

import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface RoleMenuRelMapper extends Mapper<RoleMenuRel> {
    List<RoleMenuRel> selectLeafRoleModule(String roleId);
    List<Integer> roleMenuButton(RoleMenuRel roleMenuRel);
    int saveButtonAccess(RoleMenuRel roleMenuRel);

}