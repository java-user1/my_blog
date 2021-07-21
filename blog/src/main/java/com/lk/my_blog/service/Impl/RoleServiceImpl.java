package com.lk.my_blog.service.Impl;

import com.lk.my_blog.config.LogConfig;
import com.lk.my_blog.dao.RoleMapper;
import com.lk.my_blog.model.Role;
import com.lk.my_blog.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 刘康
 * @Date: 2021/7/21 12:01
 * @Description:
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private LogConfig logConfig;

    @Override
    public Role getRoleById(Integer id) {
        Role role = roleMapper.getRoleById(id);
        return role;
    }

    @Override
    public Role getRoleByName(String name) {
        Role role = roleMapper.getRoleByName(name);
        return role;
    }

    @Override
    public List<Role> getAllRole() {
        List<Role> allRole = roleMapper.getAllRole();
        return allRole;
    }

    @Override
    public int addRole(Role role) {
        int i = roleMapper.addRole(role);
        logConfig.getLog().info("插入成功");
        return i;
    }

    @Override
    public int updateRoleByRole(Role role) {
        int i = roleMapper.updateRoleByRole(role);
        if(i>=0){
            logConfig.getLog().info("更新成功");
        }else {
            logConfig.getLog().info("更新失败");
        }
        return 0;
    }

    @Override
    public int deleteRoleByName(String role) {
        int i = roleMapper.deleteRoleByName(role);
        if(i>=0){
            logConfig.getLog().info("更新成功");
        }else {
            logConfig.getLog().info("更新失败");
        }
        return 0;
    }
}
