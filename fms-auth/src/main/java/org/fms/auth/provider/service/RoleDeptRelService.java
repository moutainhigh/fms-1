package org.fms.auth.provider.service;

import java.util.List;

import org.fms.auth.autoconfigure.service.BaseService;
import org.fms.auth.provider.mapper.mapper.RoleDeptRelMapper;
import org.fms.auth.provider.mapper.model.RoleDeptRel;
import org.fms.auth.provider.mapper.model.RoleMenuRel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.entity.Condition;

/**
 * Created by yxs on 2019/1/9.
 */
@Service
public class RoleDeptRelService extends BaseService<RoleDeptRel> {

    @Transactional
    public void saveRoleDept(List<RoleDeptRel> roleDept) {
        if (roleDept.size() > 0 && roleDept.get(0).getRoleId()!=null) {
            RoleDeptRel deptRel = new RoleDeptRel();
            deptRel.setRoleId(roleDept.get(0).getRoleId());
            mapper.delete(deptRel);
            roleDept.forEach(it -> {
//                it.setId(UUID.uuid32());
                mapper.insertSelective(it);
            });
        }
    }

    // 查询关联角色的叶子模块
    public List<RoleDeptRel> selectRoleDept(String roleId) {
        return ((RoleDeptRelMapper)mapper).selectRoleDept(roleId);
    }

    // 保存角色关联的组织机构
    public void  saveRoleDeptRels(List<RoleDeptRel> roleDeptRel) {
        roleDeptRel.forEach(it -> {
//                it.setId(UUID.uuid32());
            mapper.insertSelective(it);
        });
    }


    public void  deleteRoleDeptRel(RoleDeptRel roleDeptRel) {
//        String menuIds="";
//        for (int i = 0; i <roleDeptRel.getNeedDelList().size() ; i++) {
//            if(i!=0){
//                menuIds+=",";
//            }
//            menuIds+=roleDeptRel.getNeedDelList().get(i).toString();
//        }
        Condition condition=new Condition(RoleMenuRel.class);
        condition.createCriteria().andCondition("role_Id = "+roleDeptRel.getRoleId()+"");
//                .andCondition("dept_Id in ("+menuIds+")");
        mapper.deleteByExample(condition);
    }
}
