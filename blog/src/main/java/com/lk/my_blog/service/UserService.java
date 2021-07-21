package com.lk.my_blog.service;

import com.lk.my_blog.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 刘康
 * @Date: 2021/7/21 11:08
 * @Description:
 */
@Service
public interface UserService {
    /**
     * 根据用户id查询用户信息
     * @param id
     * @return
     */
    User getUserById(Integer id);

    /**
     * 根据用户名查询用户信息
     * @param name
     * @return User
     */
    User getUserByName(String name);

    /**
     * 查询所有用户信息
     * @return
     */
    List<User> getAllUser();

    /**
     * 新增用户信息
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 根据用户名更新用户信息
     * @param user
     * @return
     */
    int updateUserByName(User user);

    /**
     * 根据用户名删除用户
     * @param name
     * @return
     */
    int deleteUserByName(String name);
}
