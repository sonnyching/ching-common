package com.chings.core.config.shiro;

import com.chings.core.model.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Date;

/**
 * @Author Administrator
 * @Date 2018/6/25
 */
public class LoginRealm extends AuthorizingRealm {

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //TODO

        UsernamePasswordToken utoken = (UsernamePasswordToken) token;

        String username = utoken.getUsername();
        char[] password = utoken.getPassword();

        String s = new String(password);

        //TODO 执行一系列查询过程
        User user = new User();
        user.id = 1;
        user.name = "haha";
        user.password = "123456";
        user.time = new Date();

        if (user == null) {
            throw new UnknownAccountException("账号或密码不正确");
        }

        // 密码错误
        if (!user.password.equals(s)) {
            throw new IncorrectCredentialsException("账号或密码不正确");
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user,user.password, getName());

        return authenticationInfo;
    }
}
