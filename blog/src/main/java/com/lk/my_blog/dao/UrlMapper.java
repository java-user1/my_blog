package com.lk.my_blog.dao;

import com.lk.my_blog.model.Role;
import com.lk.my_blog.model.Url;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: 刘康
 * @Date: 2021/6/26 9:29
 * @Description: 地址路径sql操作接口
 */
@Mapper
@Repository
public interface UrlMapper {
    /**
     * 根据id查询url
     * @param id url id
     * @return url
     */
    String selectById(int id);

    /**
     * 根据角色信息查询url信息
     * @param rid 角色id
     * @return url信息
     */
    List<Url> getUrlByRole(int rid);

    /**
     * 查询url匹配的权限
     * @param url
     * @return
     */
    List<Role> getRoleByUrl(String url);

    /**
     * 查询全部url
     * @return 全部url列表
     */
    List<Url> getAllUrl();

    /**
     * 插入一条url
     * @param url
     * @return
     */
    int insert(Url url);

    /**
     * 根据id删除一条url信息
     * @param id url id
     * @return
     */
    int deleteById(int id);

    /**
     * 根据url id更新一条url信息
     * @param url
     * @return
     */
    int updateById(Url url);

}
