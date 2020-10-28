package org.fms.auth.provider.mapper.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.fms.auth.provider.mapper.model.DeptInfo;

import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface DeptInfoMapper extends Mapper<DeptInfo> {
    List<DeptInfo> getDeptsByUserId(@Param("userId") Integer userId);
    List<DeptInfo> selectDeptTree(@Param("id") Integer id);
    List<DeptInfo> roleDeptTree(@Param("ROLE_ID") Integer id);
    List<DeptInfo> selectDeptDrop(DeptInfo deptInfo);
    List<DeptInfo> findByWhere(DeptInfo deptInfo);
    List<Map<Long,Object>> findIdMapByDomain(DeptInfo t) ;
    List<Map<Long,Object>> findDeptIdMapByDomain(DeptInfo t) ;
}