package com.lk.my_blog.service;

import com.lk.my_blog.model.Role;

import java.util.List;

/**
 * @Author: 刘康
 * @Date: 2021/7/21 11:11
 * @Description:
 */
public interface RoleService {
    /**
     * 根据用户id查询角色信息
     * @param id
     * @return role
     */
    Role getRoleById(Integer id);

    /**
     * 根据用户名查询角色信息
     * @param name
     * @return User
     */
    Role getRoleByName(String name);

    /**
     * 查询所有角色信息
     * @return
     */
    List<Role> getAllRole();

    /**
     * 新增角色信息
     * @param role
     * @return
     */
    int addRole(Role role);

    /**
     * 更新角色信息
     * @param role
     * @return
     */
    int updateRoleByRole(Role role);

    /**
     * 根据角色名删除角色
     * @param role
     * @return
     */
    int deleteRoleByName(String role);

    /**
     * 根据用户id查询用户的角色信息
     * @param uid
     * @return
     */
    String getRoleByUid(int uid);
}
