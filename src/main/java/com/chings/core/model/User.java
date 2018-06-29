package com.chings.core.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author Administrator
 * @Date 2018/6/22
 */
@Entity
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id ;

    public String password;

    public String name;

    public Date time;

}
