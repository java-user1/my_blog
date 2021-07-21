package com.lk.my_blog.service.Impl;

import com.lk.my_blog.config.LogConfig;
import com.lk.my_blog.dao.UserRoleMapper;
import com.lk.my_blog.model.UserRole;
import com.lk.my_blog.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 刘康
 * @Date: 2021/7/21 12:39
 * @Description:
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private LogConfig logConfig;

    @Override
    public UserRole getUserRoleById(Integer id) {
        UserRole userRole = userRoleMapper.getUserRoleById(id);
        return userRole;
    }

    @Override
    public List<UserRole> getAllUserRole() {
        List<UserRole> allUserRole = userRoleMapper.getAllUserRole();
        return allUserRole;
    }

    @Override
    public int addUserRole(UserRole userRole) {
        int i = userRoleMapper.addUserRole(userRole);
        if(i>=0){
            logConfig.getLog().info("更新成功");
        }else {
            logConfig.getLog().info("更新失败");
        }
        return 0;
    }

    @Override
    public int updateRoleById(UserRole userRole) {
        int i = userRoleMapper.addUserRole(userRole);
        if(i>=0){
            logConfig.getLog().info("更新成功");
        }else {
            logConfig.getLog().info("更新失败");
        }
        return 0;
    }

    @Override
    public int deleteRoleById(Integer id) {
        int i = userRoleMapper.deleteRoleById(id);
        if(i>=0){
            logConfig.getLog().info("更新成功");
        }else {
            logConfig.getLog().info("更新失败");
        }
        return 0;
    }
}
