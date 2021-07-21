package com.lk.my_blog.service;

import com.lk.my_blog.model.UserRole;

import java.util.List;

/**
 * @Author: 刘康
 * @Date: 2021/7/21 11:11
 * @Description:
 */
public interface UserRoleService {
    /**
     * 根据用户id查询用户信息
     * @param id
     * @return role
     */
    UserRole getUserRoleById(Integer id);

    /**
     * 查询所有用户信息
     * @return
     */
    List<UserRole> getAllUserRole();

    /**
     * 新增用户角色关联信息
     * @param userRole
     * @return
     */
    int addUserRole(UserRole userRole);

    /**
     * 更新用户角色关联信息
     * @param userRole
     * @return
     */
    int updateRoleById(UserRole userRole);

    /**
     * 根据id删除用户角色关联信息
     * @param id
     * @return
     */
    int deleteRoleById(Integer id);
}
