package org.fms.auth.provider.service;

import java.util.List;
import java.util.Map;

import org.fms.auth.autoconfigure.service.BaseService;
import org.fms.auth.provider.mapper.mapper.DeptInfoMapper;
import org.fms.auth.provider.mapper.model.DeptInfo;
import org.springframework.stereotype.Service;

/**
 * Created by yxs on 2019/1/9.
 */
@Service
public class DeptInfoService extends BaseService<DeptInfo> {


    /**
     * 根据用户查询组织机构
     * @param userId
     * @return
     */
    public List<DeptInfo> getDeptsByUserId(Integer userId) {
        return ((DeptInfoMapper)mapper).getDeptsByUserId(userId);
    }

    /**
     * 根据父节点查询组织机构
     * @param id
     * @return
     */
    public List<DeptInfo> selectDeptTree(Integer id) {
        return ((DeptInfoMapper)mapper).selectDeptTree(id);
    }

    public List<DeptInfo> findByWhere(DeptInfo deptInfo) {
        return ((DeptInfoMapper)mapper).findByWhere(deptInfo);
    }

    public List<DeptInfo> roleDeptTree(Integer id) {
        return ((DeptInfoMapper)mapper).roleDeptTree(id);
    }

    public List<DeptInfo> selectDeptDrop(DeptInfo deptInfo){
        return ((DeptInfoMapper)mapper).selectDeptDrop(deptInfo);
    }
    public List<Map<Long,Object>> findIdMapByDomain(DeptInfo t) {
        // TODO Auto-generated method stub
        return ((DeptInfoMapper)mapper).findIdMapByDomain(t);
    }
    public List<Map<Long,Object>> findDeptIdMapByDomain(DeptInfo t) {
        // TODO Auto-generated method stub
        return ((DeptInfoMapper)mapper).findDeptIdMapByDomain(t);
    }
}
