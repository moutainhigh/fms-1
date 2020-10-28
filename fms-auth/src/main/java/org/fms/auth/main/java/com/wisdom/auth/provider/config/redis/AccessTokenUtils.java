package org.fms.auth.main.java.com.wisdom.auth.provider.config.redis;

import org.fms.auth.main.java.com.wisdom.auth.provider.config.AccessTokenConfiguration;

import org.fms.auth.main.java.com.wisdom.auth.provider.mapper.model.DeptInfo;
import org.fms.auth.main.java.com.wisdom.auth.provider.mapper.model.MenuInfo;
import org.fms.auth.main.java.com.wisdom.auth.provider.mapper.model.RoleInfo;
import org.fms.auth.main.java.com.wisdom.auth.provider.mapper.model.UserInfo;
import org.fms.auth.main.java.com.wisdom.auth.provider.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;


/**
 * Created by yxs on 2019/1/29.
 */
@Service
public class AccessTokenUtils {

//    @Autowired
//    private TokenStore tokenStore;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    AccessTokenConfiguration accessTokenConfiguration;

//    @Autowired
//    private RedisTemplate<String, RoleInfo> baseRoleTemplate;
//
//    @Autowired
//    private RedisTemplate<String, MenuInfo> baseModelTemplate;
//    @Autowired
//    private RedisTemplate<String, DeptInfo> deptInfoRedisTemplate;
@Autowired
private RedisTemplate redisTemplate;
    /**
     * 从token获取用户信息
     * @return
     */
    public UserInfo getUserInfo(){
        String token = this.extractHeaderToken(request);
        if (token==null){
            token = request.getParameter("access_token");
            if (token == null) {
                return null;
            } else {
                request.setAttribute("ACCESS_TOKEN_TYPE", "Bearer");
            }
        }
        String content = accessTokenConfiguration.extract(token);
        Map map = JsonUtils.serializable(content, Map.class);
        String json = JsonUtils.deserializer(map.get("user_info"));
        UserInfo userInfo = JsonUtils.serializable(json, UserInfo.class);
        return userInfo;
    }

    public List<RoleInfo> getRoleInfo(){
        String userId = getUserInfo().getId().toString();
        long size = redisTemplate.opsForList().size(userId);
        return redisTemplate.opsForList().range(userId, 0, size);
    }

    public List<MenuInfo> getMenuInfo(){
        String key = getUserInfo().getId().toString() + "-menu";
        long size = redisTemplate.opsForList().size(key);
        return redisTemplate.opsForList().range(key, 0, size);
    }

    public List<DeptInfo> getDeptInfo(){
        String key = getUserInfo().getId().toString() + "-dept";
        long size = redisTemplate.opsForList().size(key);
        return redisTemplate.opsForList().range(key, 0, size);
    }

    protected String extractHeaderToken(HttpServletRequest request) {
        Enumeration headers = request.getHeaders("Authorization");

        String value;
        do {
            if (!headers.hasMoreElements()) {
                return null;
            }

            value = (String)headers.nextElement();
        } while(!value.toLowerCase().startsWith("Bearer".toLowerCase()));

        return value;
    }
}
