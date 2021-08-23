package com.lk.my_blog.service.Impl;


import com.lk.my_blog.dao.UrlMapper;
import com.lk.my_blog.model.Role;
import com.lk.my_blog.model.Url;
import com.lk.my_blog.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 刘康
 * @Date: 2021/6/26 10:10
 * @Description:
 */
@Service
public class UrlServiceImpl implements UrlService {
    @Autowired
    private UrlMapper urlMapper;

    @Override
    public int insert(Url url) {
        int i = urlMapper.insert(url);
        return i;
    }

    @Override
    public String selectById(int id) {
        return urlMapper.selectById(id);
    }

    @Override
    public List<Url> getAllUrlByRid(int rid) {
        return urlMapper.getUrlByRole(rid);
    }

    @Override
    public int updateById(Url url) {
        return urlMapper.updateById(url);
    }


    @Override
    public List<Role> getRoleByUrl(String url) {
        List<Role> roleByUrl = urlMapper.getRoleByUrl(url);
        return roleByUrl;
    }
}
