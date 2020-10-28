package org.fms.auth.main.java.com.wisdom.auth.provider.service;

import org.fms.auth.main.java.com.wisdom.auth.autoconfigure.service.BaseService;
import org.fms.auth.main.java.com.wisdom.auth.provider.mapper.model.MenuRightInfo;
import org.fms.auth.main.java.com.wisdom.auth.provider.mapper.mapper.MenuRightInfoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yxs on 2019/1/9.
 */
@Service
public class MenuRightInfoService extends BaseService<MenuRightInfo> {


    /**
     * 根据用户查询菜单
     * @param menuId
     * @return
     */
    public List<MenuRightInfo> getRightsByMenuId(Integer menuId) {
        return ((MenuRightInfoMapper)mapper).getRightsByMenuId(menuId);
    }
//    检查是否有重复序号
    public List<MenuRightInfo> checkRight(MenuRightInfo menuRightInfo) {
        return mapper.select(menuRightInfo);
    }

}
