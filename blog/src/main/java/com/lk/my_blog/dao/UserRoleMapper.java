package com.lk.my_blog.dao;

import com.lk.my_blog.model.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: 刘康
 * @Date: 2021/7/19 12:34
 * @Description:用户角色关联数据表
 */
@Repository
@Mapper
public interface UserRoleMapper {
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
