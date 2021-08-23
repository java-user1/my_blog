package com.lk.my_blog.dao;


import com.lk.my_blog.model.UrlRole;

import java.util.List;

/**
 * @Author: 刘康
 * @Date: 2021/6/27 9:49
 * @Description: 角色和url对应关系
 */
public interface UrlRoleMapper {
    /**
     * 查询表url_role
     * @return
     */
    List<UrlRole> getAll();

    /**
     * 插入一条信息
     * @param urlRole
     * @return
     */
    int insert(UrlRole urlRole);

    /**
     * 根据id更新一条信息
     * @param urlRole
     * @return
     */
    int updateById(UrlRole urlRole);

    /**
     * 根据id删除一条信息
     * @param id
     * @return
     */
    int deleteById(int id);
}
