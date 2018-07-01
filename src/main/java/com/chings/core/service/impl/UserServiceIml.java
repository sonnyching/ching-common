package com.chings.core.service.impl;

import com.chings.core.common.Constant;
import com.chings.core.common.bean.OnceToken;
import com.chings.core.common.exception.AccountVertifyException;
import com.chings.core.common.exception.FastException;
import com.chings.core.common.exception.LoginVertifyException;
import com.chings.core.common.exception.VertifyCodeException;
import com.chings.core.dao.UserDao;
import com.chings.core.model.User;
import com.chings.core.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Administrator
 * @Date 2018/6/22
 */
@Service
public class UserServiceIml implements IUserService {
    @Autowired
    UserDao userDao;

    @Override
    public User findUserById(int id) {
        return null;
    }

    @Override
    public User findUserByName(String name) {
        return userDao.findUserByName(name);
    }

    @Override
    public List<User> ListUser() {
        return null;
    }

    @Override
    public User login(String username, String password) throws LoginVertifyException{
        User user = findUserByName(username);

        if(user==null || !user.password.equals(password)){
            throw new AccountVertifyException();
        }

        return user;
    }

    @Override
    public User login(String username, String password, String randomCode,String sessionId) throws LoginVertifyException {

        if(StringUtils.isEmpty(randomCode)){
            throw new VertifyCodeException();
        }

        if(! new OnceToken(sessionId).vertifyToken(randomCode)){
            throw new VertifyCodeException();
        }

        return login(username, password);

    }

    @Override
    public boolean logout(int userid) {
        return false;
    }

    @Override
    public String getAndSaveVertifyCode(String sesssionId) {
        OnceToken onceToken = new OnceToken(sesssionId);
        String token = onceToken.createToken();
        onceToken.storeToken(token);
        return token;
    }
}
