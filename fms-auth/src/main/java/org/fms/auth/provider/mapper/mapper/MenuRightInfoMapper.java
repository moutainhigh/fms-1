package org.fms.auth.provider.mapper.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.fms.auth.provider.mapper.model.MenuRightInfo;

import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface MenuRightInfoMapper extends Mapper<MenuRightInfo> {
    List<MenuRightInfo> getRightsByMenuId(@Param("menuId") Integer menuId);
    List<MenuRightInfo> checkRight(MenuRightInfo menuRightInfo);

}