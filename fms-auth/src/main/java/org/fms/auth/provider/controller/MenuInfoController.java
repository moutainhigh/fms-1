package org.fms.auth.provider.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.fms.auth.autoconfigure.controller.CrudController;
import org.fms.auth.provider.common.pojo.TableData;
import org.fms.auth.provider.config.redis.AccessTokenUtils;
import org.fms.auth.provider.mapper.model.MenuInfo;
import org.fms.auth.provider.mapper.model.MenuRightInfo;
import org.fms.auth.provider.mapper.model.RoleMenuRel;
import org.fms.auth.provider.mapper.model.UserInfo;
import org.fms.auth.provider.pojo.ResponseCode;
import org.fms.auth.provider.pojo.ResponseData;
import org.fms.auth.provider.pojo.request.MenuInfoRequest;
import org.fms.auth.provider.service.MenuInfoService;
import org.fms.auth.provider.service.MenuRightInfoService;
import org.fms.auth.provider.service.RoleMenuRelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
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
public class MenuInfoController extends CrudController<MenuInfo, MenuInfoRequest> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MenuInfoService menuInfoService;

    @Autowired
    private AccessTokenUtils accessTokenUtils;

    @Autowired
    private RoleMenuRelService roleMenuRelService;

    @Autowired
    private MenuRightInfoService menuRightInfoService;

    @RequestMapping(value = "/menu/user/{userId}", method = RequestMethod.GET)
    public ResponseData<List<MenuInfo>> getMenusByUserId(@PathVariable("userId") Integer userId) {
        logger.debug("根据用户查询菜单");
        List<MenuInfo> list;
        try {
            list = menuInfoService.getMenusByUserId(userId);
        }catch (Exception e){
            logger.error("根据用户查询菜单错误");
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), list);
    }

    @GetMapping("/menu/auth/tree")
    public ResponseData<List<MenuInfo>> getCurrentMenu() {
        List<MenuInfo> list = accessTokenUtils.getMenuInfo();
         logger.debug("查询当前用户菜单");
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), list);//
    }

    @RequestMapping("/menu/error")
    public void loginError(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        AuthenticationException exception =
                (AuthenticationException)request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
        try {
            response.getWriter().write(exception.toString());
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从redis中查询当前用户拥有的菜单，返回列表结构
     * @return
     */

    @GetMapping("/menu/auth/table")
    public ResponseData<List<MenuInfo>> getCurrentMenuList() {
        logger.debug("查询当前用户组织机构列表");
        List<MenuInfo> list =listHierarchy(accessTokenUtils.getMenuInfo());
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), list);
    }

    private List<MenuInfo> listHierarchy(List<MenuInfo> parent){
        List<MenuInfo> result = new ArrayList<MenuInfo>();
        for (MenuInfo deptInfo:parent){
            MenuInfo temp = (MenuInfo)deptInfo.clone();
            temp.setChildren(null);
            result.add(temp);
            if(deptInfo.getChildren()!=null){
                result.addAll(listHierarchy(deptInfo.getChildren()));
            }
        }
        return result;
    }
    @PostMapping(value = "/menu/tree")
    private ResponseData<List<MenuInfo>> getModuleTree(@RequestBody MenuInfo moduleResources) {
        logger.debug("查询模块树");
        List<MenuInfo> list;
        try {
            list = menuInfoService.getModuleTree(moduleResources.getId(), moduleResources.getSystemId(), moduleResources.getStatus());
        } catch (Exception e) {
            logger.error("查询模块树异常" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), list);
    }

    @PostMapping(value = "/menu/roleMenuTree")
    private ResponseData<List<MenuInfo>> roleMenuTree(@RequestBody MenuInfo moduleResources) {
        logger.debug("查询角色拥有的菜单树");
        List<MenuInfo> list;
        try {
            list = menuInfoService.roleMenuTree(moduleResources.getRoleId());
        } catch (Exception e) {
            logger.error("查询模块树异常" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), list);
    }

    @PostMapping(value = "/menu/menuButton")
    private ResponseData<List<MenuRightInfo>> menuButton(@RequestBody MenuInfo menu) {
        List<MenuRightInfo> list;
        try {
            list = menuInfoService.menuButton(menu.getId());
        } catch (Exception e) {
            logger.error("查询模块树异常" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), list);
    }

    @PostMapping(value = "/menu/roleMenuButton")
    private ResponseData<List<Integer>> roleMenuButton(@RequestBody RoleMenuRel menu) {
        List<Integer> list;
        try {
            list = roleMenuRelService.roleMenuButton(menu);
        } catch (Exception e) {
            logger.error("查询模块树异常" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), list);
    }

    @PostMapping(value = "/menu/saveButtonAccess")
    private ResponseData<RoleMenuRel> saveButtonAccess(@RequestBody RoleMenuRel roleMenuRel) {
        Integer buttonAccess=0;
        try {

            for (int i = 0; i < roleMenuRel.getAccessList().size(); i++) {
                for (Map<String, String> buttonDatum : roleMenuRel.getButtonData()) {
                    if(buttonDatum.get("key")!=null&&buttonDatum.get("key").equals(roleMenuRel.getAccessList().get(i))){
//                           String aa=buttonDatum.get("rightDescribe");
                            buttonAccess+=Integer.parseInt(buttonDatum.get("rightDescribe"));
                    }
                }

            }
            roleMenuRel.setButton(buttonAccess);
            roleMenuRelService.saveButtonAccess(roleMenuRel);
        } catch (Exception e) {
            logger.error("查询模块树异常" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }


    @PostMapping("/menu/table")
    @Override
    protected ResponseData<TableData<MenuInfo>> queryRecord(@RequestBody MenuInfoRequest query) {
        logger.debug("查询模块表格");
        Example example = new Example(MenuInfo.class);
        Example.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(query.getParentId())) {
            criteria.andEqualTo("parentId", query.getParentId());
        } else if (query.getSystemId()!=null) {
            criteria.andEqualTo("systemId",  query.getSystemId());
            Example.Criteria criteria1 = example.createCriteria();
            criteria1.andIsNull("parentId");
            criteria1.orEqualTo("parentId",0);
            example.and(criteria1);
        } else {
            UserInfo userInfo = accessTokenUtils.getUserInfo();
            if("sysadmin".equals(userInfo.getUserId())){
                criteria.andEqualTo("parentId", 0);
            }
            else {
                List<MenuInfo> list = accessTokenUtils.getMenuInfo();
                List<MenuInfo> result = new ArrayList<MenuInfo>();
                for (int i = (query.getPageNum() - 1) * query.getPageSize(); i < Math.min(list.size(), query.getPageSize()); i++) {
                    result.add(list.get(i));
                }
                PageInfo<MenuInfo> pageInfo = new PageInfo<MenuInfo>();
                pageInfo.setPageNum(query.getPageNum());
                pageInfo.setPageSize(query.getPageSize());
                pageInfo.setList(result);
                return getTableData(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), pageInfo);
            }
        }
        example.orderBy("sort");
        PageInfo<MenuInfo> pageInfo = menuInfoService.selectByExampleList(example, query.getPageNum(), query.getPageSize());

        return getTableData(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), pageInfo);
    }

    @PostMapping("/menu/add")
    @Override
    protected ResponseData<MenuInfo> addRecord(@RequestBody MenuInfo record) {
        logger.debug("添加模块");
        try {
//            record.setId(UUID.uuid32());
            record.setCreateDate(new Date());
            record.setSystemId(1);
            menuInfoService.insertSelective(record);
        } catch (Exception e) {
            logger.error("添加模块失败：" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

    @PostMapping("/menu/delete")
    @Override
    protected ResponseData<MenuInfo> deleteRecord(@RequestBody List<MenuInfo> record) {
        logger.debug("删除模块");
        try {
            menuInfoService.deleteBatchByPrimaryKey(record);
        } catch (Exception e) {
            logger.error("删除模块失败：" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

    @PostMapping("/menu/update")
    @Override
    protected ResponseData<MenuInfo> updateRecord(@RequestBody MenuInfo record) {
        logger.debug("更新模块");
        try {
            record.setUpdateDate(new Date());
            menuInfoService.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            logger.error("更新模块失败：" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

    @GetMapping("/menu/validate/{menuCode}")
    public ResponseData<MenuInfo> validateModuleCode(@PathVariable("menuCode") String menuCode) {
        logger.debug("校验模块编码是否存在");
        MenuInfo menuInfo = new MenuInfo();
        menuInfo.setMenuCode(menuCode);
        menuInfo = menuInfoService.selectOne(menuInfo);
        if(menuInfo == null) {
            return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
        }
        return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
    }

    /**
     * 验证是否有菜单及按钮操作权限
     * @param url
     * @param button
     * @return
     */
    @PostMapping("/menu/validate")
    public boolean validateMenu(String url,Integer button){
        if(button==null){
            return false;
        }
        List<MenuInfo> menuInfos = accessTokenUtils.getMenuInfo();

        return validateMenu(menuInfos,url,button);

    }

    private boolean validateMenu(List<MenuInfo> menuInfos, String url, Integer button){
        if(menuInfos==null||menuInfos.size()==0){
            return false;
        }
        boolean result = false;
        for (int i = 0; i <menuInfos.size() ; i++) {
            MenuInfo menuInfo = menuInfos.get(i);
            if(menuInfo.getIsLeaf()==1) {
                if (menuInfo.getMenuUrl() != null && !menuInfo.getMenuUrl().isEmpty() && url.startsWith(menuInfo.getMenuUrl())) {
                    if ((button & 0xFF) == 0xFF) {
                        return true;
                    }
                    if ((button & menuInfo.getButton())>0) {
                        result = true;
                    }
                    break;
                }
            }
            else{
                result = validateMenu(menuInfo.getChildren(),url,button);
            }
        }
        return result;
    }

    @PostMapping("/right/queryRightTable")
    private ResponseData<List<MenuRightInfo>> queryRightTable(@RequestBody MenuRightInfo menuRightInfo) {
        logger.debug("根据菜单查询按钮");
        List<MenuRightInfo> list;
        try {
            list = menuRightInfoService.getRightsByMenuId(menuRightInfo.getMenuId());
        }catch (Exception e){
            logger.error("根据用户查询角色失败");
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), list);
    }


    @PostMapping("/right/checkRight")
    private ResponseData<List<MenuRightInfo>> checkRight(@RequestBody MenuRightInfo menuRightInfo) {
        logger.debug("根据菜单查询按钮");
        List<MenuRightInfo> list;
        try {
            list = menuRightInfoService.checkRight(menuRightInfo);
        }catch (Exception e){
            logger.error("根据用户查询角色失败");
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), list);
    }

    @PostMapping("/right/rightInsert")
    private ResponseData<MenuRightInfo> rightInsert(@RequestBody MenuRightInfo menuRightInfo) {
        logger.debug("根据菜单保存按钮");
        menuRightInfo.setRightDescribe((int)Math.pow(2,menuRightInfo.getSort()));
        try {
             menuRightInfoService.insertSelective(menuRightInfo);
        }catch (Exception e){
            logger.error("根据用户查询角色失败");
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

    @PostMapping("/right/rightUpdate")
    private ResponseData<MenuRightInfo> rightUpdate(@RequestBody MenuRightInfo menuRightInfo) {
        logger.debug("根据菜单修改按钮");
        try {
            menuRightInfoService.updateByPrimaryKeySelective(menuRightInfo);
        }catch (Exception e){
            logger.error("根据用户查询角色失败");
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }
}
