package com.lk.my_blog.service.Impl;

import com.lk.my_blog.config.LogConfig;
import com.lk.my_blog.dao.UserMapper;
import com.lk.my_blog.model.User;
import com.lk.my_blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 刘康
 * @Date: 2021/7/21 11:12
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LogConfig logConfig;

    @Override
    public User getUserById(Integer id) {
        User user = userMapper.getUserById(id);
        return user;
    }

    @Override
    public User getUserByName(String name) {
        User user = userMapper.getUserByName(name);
        return user;
    }

    @Override
    public List<User> getAllUser() {
        List<User> allUser = userMapper.getAllUser();
        return allUser;
    }

    @Override
    public int addUser(User user) {
        int i = userMapper.addUser(user);
        logConfig.getLog().info("插入成功");
        return i;
    }

    @Override
    public int updateUserByName(User user) {
        return userMapper.updateUserByName(user);
    }

    @Override
    public int deleteUserByName(String name) {

        return userMapper.deleteUserByName(name);
    }
}
