package com.chings.core.config.shiro;

import com.chings.core.model.User;
import com.chings.core.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @Author Administrator
 * @Date 2018/6/25
 */
public class LoginRealm extends AuthorizingRealm {

    @Autowired
    IUserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //TODO 要根据不同的Token类型分别进行登陆判断
        User user = null;

        UsernamePasswordToken utoken = (UsernamePasswordToken) token;

        String username = utoken.getUsername();
        char[] passwordArray = utoken.getPassword();

        String password = new String(passwordArray);

        if(token instanceof RandomCodeUsernamePassworToken){
            RandomCodeUsernamePassworToken _token = (RandomCodeUsernamePassworToken) token;
            user = userService.login(username, password,_token.getRamdomCode(),_token.getSessionId());
        }else{
            UsernamePasswordToken _token = (UsernamePasswordToken) token;
            user = userService.login(username, password);
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user,user.password, getName());

        return authenticationInfo;
    }
}
