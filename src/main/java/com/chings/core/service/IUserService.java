package com.chings.core.service;

import com.chings.core.model.User;

import java.util.List;

/**
 * @Author Administrator
 * @Date 2018/6/22
 */
public interface IUserService {

    User findUserById(int id);

    User findUserByName(String name);

    List<User> ListUser();

}
