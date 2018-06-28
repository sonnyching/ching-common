package com.chings.core.dao;

import com.chings.core.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Administrator
 * @Date 2018/6/22
 */
public interface UserDao extends JpaRepository<User,Integer>{

    public User findUserById(int id);

    public User findUserByName(String name);

}
