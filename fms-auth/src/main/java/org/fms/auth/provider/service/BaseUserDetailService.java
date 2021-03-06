package org.fms.auth.provider.service;

import java.util.ArrayList;
import java.util.List;

import org.fms.auth.provider.common.utils.JsonUtils;
import org.fms.auth.provider.config.auth.filter.MyLoginAuthenticationFilter;
import org.fms.auth.provider.config.auth.pojo.BaseUserDetail;
import org.fms.auth.provider.controller.DeptInfoController;
import org.fms.auth.provider.controller.MenuInfoController;
import org.fms.auth.provider.controller.RoleInfoController;
import org.fms.auth.provider.controller.UserInfoController;
import org.fms.auth.provider.mapper.model.DeptInfo;
import org.fms.auth.provider.mapper.model.MenuInfo;
import org.fms.auth.provider.mapper.model.RoleInfo;
import org.fms.auth.provider.mapper.model.UserInfo;
import org.fms.auth.provider.pojo.ResponseCode;
import org.fms.auth.provider.pojo.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * 基于资源认证
 * TODO 异构系统不同的资源(用户和角色)如何认证？
 * TODO 用户，角色，权限，是不是应该通用？ 平台内应做成通用公共服务
 * TODO 第三方  要按公共通用平台的接入服务(用户,token,默认角色)进行认证和授权
 * <p>
 * <p>
 * 授权的时候是对角色授权，而认证的时候应该基于资源，而不是角色，因为资源是不变的，而用户的角色是会变的
 */
@Service
public class BaseUserDetailService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MenuInfoController menuInfoController;
    @Autowired
    private RoleInfoController roleInfoController;
    @Autowired
    private UserInfoController userInfoController;
    @Autowired
    private DeptInfoController deptInfoController;
//    @Autowired
//    private RedisTemplate<String, RoleInfo> redisTemplate;
//    @Autowired
//    private RedisTemplate<String, MenuInfo> resourcesTemplate;
//    @Autowired
//    private RedisTemplate<String, DeptInfo> deptInfoRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public UserDetails loadUserByUsername(String var1) throws UsernameNotFoundException {
        //user&:@test
        UserInfo userInfo;
        String[] parameter;
        int index = var1.indexOf("&:@");
        System.out.println("-------------" + var1);
        if (index != -1) {
            parameter = var1.split("&:@");
        } else {
            // 如果是 refresh_token 不分割
            parameter = new String[]{MyLoginAuthenticationFilter.SPRING_SECURITY_RESTFUL_TYPE_DEFAULT, var1};
        }

        // 手机验证码调用FeignClient根据电话号码查询用户 TODO 多个clinet?
        if (MyLoginAuthenticationFilter.SPRING_SECURITY_RESTFUL_TYPE_PHONE.equals(parameter[0])) {
            ResponseData<UserInfo> baseUserResponseData = null;
            //if(null!=baseUserService.getUserByPhone(parameter[1])){
//            baseUserResponseData = baseUserService.getUserByPhone(parameter[1]);
            //   System.out.println("====baseUserService.getUserByPhone=====");
//            }else{
//                baseUserResponseData=baseUserService2.getUserByPhone(parameter[1]);
//                System.out.println("====baseUserService2.getUserByPhone=====");
//            }

            if (baseUserResponseData.getResultData() == null || !ResponseCode.SUCCESS.getCode().equals(baseUserResponseData.getStatusCode())) {
                logger.error("找不到该用户，手机号码：" + parameter[1]);
                throw new UsernameNotFoundException("找不到该用户，手机号码：" + parameter[1]);
            }
            userInfo = baseUserResponseData.getResultData();
        } else if (MyLoginAuthenticationFilter.SPRING_SECURITY_RESTFUL_TYPE_QR.equals(parameter[0])) {
            // 扫码登陆根据token从redis查询用户
            userInfo = null;
        } else {
            // 账号密码登陆调用FeignClient根据用户名查询用户 TODO 多个clinet?
            ResponseData<UserInfo> baseUserResponseData = null;
//            baseUserResponseData = baseUserService.getUserByUserName(parameter[1]);
            baseUserResponseData = userInfoController.getUserByUserName(parameter[1]);


            System.out.println("====baseUserResponseData:" + JsonUtils.deserializer(baseUserResponseData));
            if (baseUserResponseData.getResultData() == null || !ResponseCode.SUCCESS.getCode().equals(baseUserResponseData.getStatusCode())) {
                logger.error("找不到该用户，用户名：" + parameter[1]);
                throw new UsernameNotFoundException("找不到该用户，用户名：" + parameter[1]);
            }
            userInfo = baseUserResponseData.getResultData();
        }

        List<GrantedAuthority> authorities=new ArrayList<>();
        if("wx".equals(parameter[0])){

        }else{

            // 调用FeignClient查询角色
            ResponseData<List<RoleInfo>> baseRoleListResponseData = null;
            //if(null!=baseRoleService.getRoleByUserId(userInfo.getId())){
            baseRoleListResponseData = roleInfoController.getRoleByUserId(userInfo.getId());
            //   System.out.println("====baseRoleService.getMenusByUserId(userInfo.getId()=====");
//        }else{
//            baseRoleListResponseData=baseRoleService2.getRoleByUserId(userInfo.getId());
//            System.out.println("====baseRoleService2.getMenusByUserId(userInfo.getId()=====");
//        }
            System.out.println("====baseRoleListResponseData:" + JsonUtils.deserializer(baseRoleListResponseData));
            List<RoleInfo> roles;
            if (baseRoleListResponseData.getResultData() == null || !ResponseCode.SUCCESS.getCode().equals(baseRoleListResponseData.getStatusCode())) {
                logger.error("查询角色失败！");
                roles = new ArrayList<>();
            } else {
                roles = baseRoleListResponseData.getResultData();
            }

            //调用FeignClient查询菜单
            ResponseData<List<MenuInfo>> baseModuleResourceListResponseData = null;
            // if(null!=baseModuleResourceService.getMenusByUserId(userInfo.getId())){
            baseModuleResourceListResponseData = menuInfoController.getMenusByUserId(userInfo.getId());
            // System.out.println("==== baseModuleResourceService.getMenusByUserId(userInfo.getId()=====");
//        }else{
//            baseModuleResou/role/menu/saverceListResponseData = baseModuleResourceService2.getMenusByUserId(userInfo.getId());
//            System.out.println("==== baseModuleResourceService2.getMenusByUserId(userInfo.getId()=====");
//        }

            System.out.println("====baseModuleResourceListResponseData:" + JsonUtils.deserializer(baseModuleResourceListResponseData));
            //调用FeignClient查询组织机构
            ResponseData<List<DeptInfo>> deptInfoListResponseData = deptInfoController.getDeptsByUserId(userInfo.getId());
            // 获取用户权限列表
             authorities = convertToAuthorities(userInfo, roles);  //TODO

            // 存储菜单到redis
            if (ResponseCode.SUCCESS.getCode().equals(baseModuleResourceListResponseData.getStatusCode()) && baseModuleResourceListResponseData.getResultData() != null) {
                redisTemplate.delete(userInfo.getId() + "-menu");
                baseModuleResourceListResponseData.getResultData().forEach(e -> {
                    redisTemplate.opsForList().rightPush(userInfo.getId() + "-menu", e);
                });
            }
            if (ResponseCode.SUCCESS.getCode().equals(deptInfoListResponseData.getStatusCode()) && deptInfoListResponseData.getResultData() != null) {
                redisTemplate.delete(userInfo.getId() + "-dept");
                deptInfoListResponseData.getResultData().forEach(e -> {
                    redisTemplate.opsForList().leftPush(userInfo.getId() + "-dept", e);
                });
            }
        }


        // 返回带有用户权限信息的User
        org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(userInfo.getUserName(),
                userInfo.getPassword(), isActive(userInfo.getStatus()), isAccountNonExpired(userInfo.getStatus()), true, isAccountNonLocked(userInfo.getStatus()), authorities);
        return new BaseUserDetail(userInfo, user);
    }

    /**
     * @param active 1 正常、2 禁用、3 删除、4 锁定
     * @return
     */
    private boolean isActive(int active) {
        return active == 1 || active == 4 ? true : false;
    }

    private boolean isAccountNonExpired(int active) {
        return active == 2 ? false : true;
    }

    private boolean isAccountNonLocked(int active) {
        return active == 4 ? false : true;
    }

    /**
     * 当用户拥有一个角色或多个角色，返回资源权限
     *
     * @param userInfo
     * @param roles
     * @return
     */
    private List<GrantedAuthority> convertToAuthorities(UserInfo userInfo, List<RoleInfo> roles) {
        List<GrantedAuthority> authorities = new ArrayList();
        // 清除 Redis 中用户的角色
        logger.debug("清除序列化");
        redisTemplate.delete(userInfo.getId().toString());
        roles.forEach(e -> {
            // 存储用户、角色信息到GrantedAuthority，并放到GrantedAuthority列表
            GrantedAuthority authority = new SimpleGrantedAuthority(e.getRoleNo());
            authorities.add(authority);
            //存储角色到redis
            logger.debug("存储序列化");
            redisTemplate.opsForList().rightPush(userInfo.getId().toString(), e);
        });
        return authorities;
    }
}
