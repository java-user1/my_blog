package com.lk.my_blog.handler;

import com.lk.my_blog.model.Role;
import com.lk.my_blog.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * @Author: 刘康
 * @Date: 2021/6/22 10:34
 * @Description: 根据传过来的地址，分析出请求所需要的权限信息
 * */
@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    private UrlService urlService;
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        String url = ((FilterInvocation) o).getRequestUrl();
        List<Role> roleByUrl = urlService.getRoleByUrl(url);
        if(roleByUrl==null||roleByUrl.size()==0){
            //路径没有权限，直接放行
            return null;
        }
        String[] attribute = new String[roleByUrl.size()];
        for(int i=0;i<roleByUrl.size();i++){
            attribute[i]=roleByUrl.get(i).getRole();
        }
        return SecurityConfig.createList(attribute);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
