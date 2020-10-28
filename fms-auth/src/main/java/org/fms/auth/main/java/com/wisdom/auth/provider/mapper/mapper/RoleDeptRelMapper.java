package org.fms.auth.main.java.com.wisdom.auth.provider.mapper.mapper;


import org.fms.auth.main.java.com.wisdom.auth.provider.mapper.model.RoleDeptRel;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface RoleDeptRelMapper extends Mapper<RoleDeptRel> {
    List<RoleDeptRel> selectRoleDept(String roleId);
}