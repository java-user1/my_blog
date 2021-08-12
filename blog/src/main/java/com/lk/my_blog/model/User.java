package com.lk.my_blog.model;

import lombok.Data;

import java.util.Date;

/**
 * @Author: 刘康
 * @Date: 2021/7/19 12:19
 * @Description: 用户信息
 */
@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private Date creatTime;
    private int active;
    private String avatarUrl;
}
