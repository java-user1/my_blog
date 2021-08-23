package com.lk.my_blog.service;


import com.lk.my_blog.model.Role;
import com.lk.my_blog.model.Url;

import java.util.List;

/**
 * @Author: 刘康
 * @Date: 2021/6/26 10:02
 * @Description:
 */
public interface UrlService {
    /**
     * 插入一条
     * @param url
     * @return
     */
    int insert(Url url);

    /**
     * 根据id查询url
     * @param id
     * @return
     */
    String selectById(int id);

    /**
     * 根据用户角色查询url信息
     * @param rid
     * @return 包含url信息的列表
     */
    List<Url> getAllUrlByRid(int rid);

    /**
     * 根据id更新url信息
     * @param url
     * @return
     */
    int updateById(Url url);

    /**
     * 查询url匹配的权限
     * @param url
     * @return
     */
    List<Role> getRoleByUrl(String url);

}
