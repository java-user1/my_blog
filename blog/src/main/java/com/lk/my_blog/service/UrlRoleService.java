package com.lk.my_blog.service;

import com.lk.my_blog.model.UrlRole;

import java.util.List;

/**
 * @Author: 刘康
 * @Date: 2021/8/8 15:21
 * @Description:
 */
public interface UrlRoleService {
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
