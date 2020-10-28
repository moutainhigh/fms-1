package org.fms.auth.provider.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.fms.auth.autoconfigure.service.BaseService;
import org.fms.auth.provider.mapper.mapper.UserInfoMapper;
import org.fms.auth.provider.mapper.model.UserInfo;
import org.fms.auth.provider.mapper.model.UserRoleRel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.entity.Example;

/**
 * Created by yxs on 2019/1/9.
 */
@Service
public class UserInfoService extends BaseService<UserInfo> {

    @Autowired
    private UserRoleRelService userRoleRelService;


    /**
     * 批量重置密码
     * @param record
     * @param newPassword
     */
    @Transactional
    public void resetPassword(List<UserInfo> record, String newPassword) {
        record.forEach(e -> {
            UserInfo userInfo = new UserInfo();
            userInfo.setId(e.getId());
            userInfo.setPassword(new BCryptPasswordEncoder(6).encode(newPassword));
            userInfo.setUpdateDate(new Date());
            updateByPrimaryKeySelective(userInfo);
        });
    }

    /**
     * 删除用户
     * @param record
     */
    @Transactional
    public void deleteBatch(List<UserInfo> record) {
        record.forEach(e -> {
            Example example = new Example(UserRoleRel.class);
            example.createCriteria().andEqualTo("userId", e.getId());
            userRoleRelService.deleteByExample(example);
            deleteByPrimaryKey(e);
        });
    }

    public List<UserInfo> getUserTableByRoleId(UserInfo info) {
        return ((UserInfoMapper)mapper).getUserTableByRoleId(info);
    }
    public List<UserInfo> getUnUserTableByRoleId(UserInfo info) {
        return ((UserInfoMapper)mapper).getUnUserTableByRoleId(info);
    }
    public List<Map<Long,Object>> findMapByDomain(UserInfo t) {
        // TODO Auto-generated method stub
        return ((UserInfoMapper)mapper).findMapByDomain(t);
    }

}
