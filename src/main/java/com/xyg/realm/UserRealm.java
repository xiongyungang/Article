package com.xyg.realm;

import com.xyg.domain.User;
import com.xyg.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    /**
     * 执行授权逻辑
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 执行认证逻辑
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行认证逻辑 ");

        //shiro判断逻辑
        UsernamePasswordToken userToken = (UsernamePasswordToken)token;

        //查看有无该手机号
        List<User> users = userService.findUserByMobileNo(userToken.getUsername());

            if (users.isEmpty()){
            //用户名不存在
            return null;
        }
        User user = users.get(0);

        //判断密码
        //将user作为身份信息传入
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
