package com.chings.core.service.impl;

import com.chings.core.dao.UserDao;
import com.chings.core.model.User;
import com.chings.core.service.IUserService;
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
}
