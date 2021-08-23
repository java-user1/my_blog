package com.lk.my_blog.service.Impl;

import com.lk.my_blog.dao.UrlRoleMapper;
import com.lk.my_blog.model.UrlRole;
import com.lk.my_blog.service.UrlRoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: 刘康
 * @Date: 2021/8/8 15:22
 * @Description:
 */
public class UrlRoleServiceImpl implements UrlRoleService {
    @Autowired
    private UrlRoleMapper urlRoleMapper;
    @Override
    public List<UrlRole> getAll() {
        return urlRoleMapper.getAll();
    }

    @Override
    public int insert(UrlRole urlRole) {
        return urlRoleMapper.insert(urlRole);
    }

    @Override
    public int updateById(UrlRole urlRole) {
        return urlRoleMapper.updateById(urlRole);
    }

    @Override
    public int deleteById(int id) {
        return urlRoleMapper.deleteById(id);
    }
}
