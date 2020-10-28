package org.fms.auth.provider.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.fms.auth.autoconfigure.controller.CrudController;
import org.fms.auth.provider.common.pojo.TableData;
import org.fms.auth.provider.config.redis.AccessTokenUtils;
import org.fms.auth.provider.mapper.model.RoleDeptRel;
import org.fms.auth.provider.mapper.model.RoleInfo;
import org.fms.auth.provider.mapper.model.RoleMenuRel;
import org.fms.auth.provider.mapper.model.UserRoleRel;
import org.fms.auth.provider.pojo.ResponseCode;
import org.fms.auth.provider.pojo.ResponseData;
import org.fms.auth.provider.pojo.request.RoleInfoRequest;
import org.fms.auth.provider.service.RoleDeptRelService;
import org.fms.auth.provider.service.RoleInfoService;
import org.fms.auth.provider.service.RoleMenuRelService;
import org.fms.auth.provider.service.UserRoleRelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Example;

/**
 * Created by yxs on 2019/1/17.
 */
@RestController
public class RoleInfoController extends CrudController<RoleInfo, RoleInfoRequest> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RoleInfoService roleInfoService;

    @Autowired
    private RoleMenuRelService roleMenuRelService;

    @Autowired
    private UserRoleRelService userRoleRelService;

    @Autowired
    private RoleDeptRelService roleDeptRelService;
    @Autowired
    private AccessTokenUtils accessTokenUtils;

    @RequestMapping(value = "/role/user/{userId}", method = RequestMethod.GET)
    public ResponseData<List<RoleInfo>> getRoleByUserId(@PathVariable("userId") Integer userId) {
        logger.debug("根据用户查询角色");
        List<RoleInfo> list;
        try {
            list = roleInfoService.getRoleByUserId(userId);
        }catch (Exception e){
            logger.error("根据用户查询角色失败");
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), list);
    }

    /**
     * 从redis中查询当前用户拥有的角色，返回列表结构
     * @return
     */
    @GetMapping("/role/auth/table")
    public ResponseData<List<RoleInfo>> getCurrentRoleList() {
        logger.debug("查询当前用户角色列表");
        List<RoleInfo> list =accessTokenUtils.getRoleInfo();
        List<RoleInfo> result = new ArrayList<RoleInfo>();
        for (RoleInfo roleInfo:list){
            RoleInfo temp = (RoleInfo)roleInfo.clone();
            temp.setModules(null);
            result.add(temp);
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), result);
    }

    @GetMapping("/role/all")
    public ResponseData<List<RoleInfo>> getAllRole() {
        logger.debug("获取所有角色");
        List<RoleInfo> list;
        try {
            list = roleInfoService.selectAll();
        }catch (Exception e){
            logger.error("获取所有角色失败");
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), list);
    }

    @PostMapping("/role/table")
    @Override
    protected ResponseData<TableData<RoleInfo>> queryRecord(@RequestBody RoleInfoRequest query) {
        System.out.println("------------------------provider1-------------------------------"+query);
        logger.debug("查询角色表格");
        Example example = new Example(RoleInfo.class);
        Example.Criteria criteria = example.createCriteria();

        if(!StringUtils.isEmpty(query.getRoleNo())) {
            criteria.andLike("roleNo", "%" + query.getRoleNo() + "%");
        }
        if(!StringUtils.isEmpty(query.getRoleName())) {
            criteria.andLike("roleName", "%" + query.getRoleName() + "%");
        }
        if(!StringUtils.isEmpty(query.getRemark())) {
            criteria.andLike("remark", "%" + query.getRemark() + "%");
        }
        if(query.getStatus()!= null) {
            criteria.andEqualTo("status",  query.getStatus() );
        }

        PageInfo<RoleInfo> pageInfo = roleInfoService.selectByExampleList(example, query.getPageNum(), query.getPageSize());

        return getTableData(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), pageInfo);
    }

    @PostMapping("/role/add")
    @Override
    protected ResponseData<RoleInfo> addRecord(@RequestBody RoleInfo record) {
        logger.debug("添加角色");
        try {
//            record.setId(UUID.uuid32());
            record.setCreateDate(new Date());
            roleInfoService.insertSelective(record);
        } catch (Exception e) {
            logger.error("添加角色失败：" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

    @PostMapping("/role/delete")
    @Override
    protected ResponseData<RoleInfo> deleteRecord(@RequestBody List<RoleInfo> record) {
        logger.debug("删除角色");
        try {
            roleInfoService.deleteBatchByPrimaryKey(record);
        } catch (Exception e) {
            logger.error("删除角色失败：" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

    @PostMapping("/role/update")
    @Override
    protected ResponseData<RoleInfo> updateRecord(@RequestBody RoleInfo record) {
        logger.debug("更新角色");
        try {
            record.setUpdateDate(new Date());
            roleInfoService.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            logger.error("更新角色失败：" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

    @GetMapping("/role/validate/{roleNo}")
    public ResponseData<RoleInfo> validateRoleCode(@PathVariable("roleNo") String roleNo) {
        logger.debug("校验角色编码是否存在");
        RoleInfo roleInfo = new RoleInfo();
        roleInfo.setRoleNo(roleNo);
        roleInfo = roleInfoService.selectOne(roleInfo);
        if(roleInfo == null) {
            return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
        }
        return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
    }

    @GetMapping("/role/menu/{roleId}")
    public ResponseData<List<RoleMenuRel>> getAuthModule(@PathVariable("roleId") String roleId) {
        logger.debug("查询角色已授权模块");
        List<RoleMenuRel> list;
        try {
            list = roleMenuRelService.selectLeafRoleModule(roleId);
        } catch (Exception e) {
            logger.error("查询角色已授权模块失败：" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), list);
    }


    @PostMapping("/role/saveRoleMenuRel")
    public ResponseData saveRoleMenuRel(@RequestBody RoleMenuRel roleMenuRel) {
        logger.debug("保存角色权限");
        try {
            List<RoleMenuRel> addOrUpdateObj=new ArrayList<RoleMenuRel>();
//            Map<Integer,String> map=new HashMap<Integer,String>();
//            for (Integer integer : roleMenuRel.getOldRoleList()) {
//                map.put(integer,"old");
//            }
//            for (Integer integer : roleMenuRel.getNewRoleList()) {
//                if(map.get(integer)!=null){
//                    map.put(integer,"false");
//                }else{
//                    map.put(integer,"new");
//                }
//            }
//            List<Integer> needDelList=new ArrayList<Integer>();
//            List<RoleMenuRel> addOrUpdateObj=new ArrayList<RoleMenuRel>();
//
//            for (Map.Entry<Integer, String> entry : map.entrySet()) {
//                if(entry.getKey()==null){
//                    continue;
//                }
//                if ("old".equals(entry.getValue())) {
//                    needDelList.add(entry.getKey());
//                }
//                if ("new".equals(entry.getValue())) {
//                    RoleMenuRel newRoleMenuRel=new RoleMenuRel();
//                    newRoleMenuRel.setMenuId(entry.getKey());
//                    newRoleMenuRel.setStatus(1);
//                    newRoleMenuRel.setRoleId(roleMenuRel.getRoleId());
//                    addOrUpdateObj.add(newRoleMenuRel);
//                }
//            }
//            roleMenuRelService.saveRoleMenuRels(addOrUpdateObj);
//            if(needDelList.size()>0){
//                roleMenuRel.setNeedDelList(needDelList);
//                roleMenuRelService.deleteRoleMenuRel(roleMenuRel);
//            }
            roleMenuRelService.deleteRoleMenuRel(roleMenuRel);
            for (Integer o : roleMenuRel.getNewRoleList()) {
                    RoleMenuRel newRoleMenuRel=new RoleMenuRel();
                    newRoleMenuRel.setMenuId(o);
                    newRoleMenuRel.setStatus(1);
                    newRoleMenuRel.setRoleId(roleMenuRel.getRoleId());
                    addOrUpdateObj.add(newRoleMenuRel);
            }
            roleMenuRelService.saveRoleMenuRels(addOrUpdateObj);

        } catch (RuntimeException e) {
            logger.error("保存角色权限失败" + e.getMessage());
            e.printStackTrace();
            return new ResponseData(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage(), ResponseCode.ERROR.getMessage());
        }

        return new ResponseData(ResponseCode.SUCCESS.getCode(),"", ResponseCode.SUCCESS.getMessage());
    }


    @PostMapping("/role/saveRoleDeptRel")
    public ResponseData saveRoleDeptRel(@RequestBody RoleDeptRel roleDeptRel) {
        //没有子节点时会出问题
        logger.debug("保存角色权限");
        try {
            roleDeptRelService.deleteRoleDeptRel(roleDeptRel);
            List<RoleDeptRel> addOrUpdateObj=new ArrayList<RoleDeptRel>();
            for (Integer i : roleDeptRel.getNewRoleList()) {
                    RoleDeptRel newRoleDeptRel=new RoleDeptRel();
                    newRoleDeptRel.setDeptId(i);
                    newRoleDeptRel.setRoleId(roleDeptRel.getRoleId());
                    addOrUpdateObj.add(newRoleDeptRel);
            }
            roleDeptRelService.saveRoleDeptRels(addOrUpdateObj);

//            Map<Integer,String> map=new HashMap<Integer,String>();
//            for (Integer integer : roleDeptRel.getOldRoleList()) {
//                map.put(integer,"old");
//            }
//            for (Integer integer : roleDeptRel.getNewRoleList()) {
//                if(map.get(integer)!=null){
//                    map.put(integer,"false");
//                }else{
//                    map.put(integer,"new");
//                }
//            }
//            List<Integer> needDelList=new ArrayList<Integer>();
//            List<RoleDeptRel> addOrUpdateObj=new ArrayList<RoleDeptRel>();
//
//            for (Map.Entry<Integer, String> entry : map.entrySet()) {
//                if ("old".equals(entry.getValue())) {
//                    needDelList.add(entry.getKey());
//                }
//                if ("new".equals(entry.getValue())) {
//                    RoleDeptRel newRoleDeptRel=new RoleDeptRel();
//                    newRoleDeptRel.setDeptId(entry.getKey());
//                    newRoleDeptRel.setRoleId(roleDeptRel.getRoleId());
//                    addOrUpdateObj.add(newRoleDeptRel);
//                }
//            }
//            roleDeptRelService.saveRoleDeptRels(addOrUpdateObj);
//            if(needDelList.size()>0){
//                roleDeptRel.setNeedDelList(needDelList);
//                roleDeptRelService.deleteRoleDeptRel(roleDeptRel);
//            }

        } catch (RuntimeException e) {
            logger.error("保存角色权限失败" + e.getMessage());
            e.printStackTrace();
            return new ResponseData(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage(), ResponseCode.ERROR.getMessage());
        }

        return new ResponseData(ResponseCode.SUCCESS.getCode(),"", ResponseCode.SUCCESS.getMessage());
    }


    @PostMapping("/role/saveUserRoleRel")
    public ResponseData saveUserRoleRel(@RequestBody List<UserRoleRel> userRoleRelList) {
        logger.debug("保存角色权限");
        try {
            userRoleRelService.saveUserRole(userRoleRelList);
        } catch (RuntimeException e) {
            logger.error("保存角色权限失败" + e.getMessage());
            e.printStackTrace();
            return new ResponseData(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage(), ResponseCode.ERROR.getMessage());
        }

        return new ResponseData(ResponseCode.SUCCESS.getCode(),"", ResponseCode.SUCCESS.getMessage());
    }



    @PostMapping("/role/menu/save")
    public ResponseData saveRoleResourcesAuth(@RequestBody List<RoleMenuRel> roleModule) {
        logger.debug("保存角色权限");
        try {
            roleMenuRelService.saveRoleModule(roleModule);
        } catch (RuntimeException e) {
            logger.error("保存角色权限失败" + e.getMessage());
            e.printStackTrace();
            return new ResponseData(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage(), ResponseCode.ERROR.getMessage());
        }

        return new ResponseData(ResponseCode.SUCCESS.getCode(),"", ResponseCode.SUCCESS.getMessage());
    }


    @GetMapping("/role/dept/{roleId}")
    public ResponseData<List<RoleDeptRel>> getAuthDept(@PathVariable("roleId") String roleId) {
        logger.debug("查询角色已授权组织机构");
        List<RoleDeptRel> list;
        try {
            list = roleDeptRelService.selectRoleDept(roleId);
        } catch (Exception e) {
            logger.error("查询角色已授权组织机构失败：" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), list);
    }

    @PostMapping("/role/dept/save")
    public ResponseData saveRoleDeptAuth(@RequestBody List<RoleDeptRel> roleDept) {
        logger.debug("保存角色权限");
        try {
            roleDeptRelService.saveRoleDept(roleDept);
        } catch (RuntimeException e) {
            logger.error("保存角色权限失败" + e.getMessage());
            e.printStackTrace();
            return new ResponseData(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage(), ResponseCode.ERROR.getMessage());
        }

        return new ResponseData(ResponseCode.SUCCESS.getCode(),"", ResponseCode.SUCCESS.getMessage());
    }

    @PostMapping(value = "/role/userRoleTree")
    private ResponseData<List<RoleInfo>> userRoleTree(@RequestBody RoleInfo moduleResources) {
        logger.debug("查询角色拥有的菜单树");
        List<RoleInfo> list;
        try {
            list = roleInfoService.userRoleTree(moduleResources.getUserId());
        } catch (Exception e) {
            logger.error("查询模块树异常" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), list);
    }

}
