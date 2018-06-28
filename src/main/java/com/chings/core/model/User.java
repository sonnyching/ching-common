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

    @Column(name = "password")
    public String passWord;

    @Column(name = "name")
    public String name;

    @Column(name="time")
    public Date time;

}
