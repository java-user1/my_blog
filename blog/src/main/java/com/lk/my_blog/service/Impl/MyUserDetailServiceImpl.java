package com.lk.my_blog.service.Impl;

import com.lk.my_blog.config.LogConfig;
import com.lk.my_blog.model.User;
import com.lk.my_blog.service.RoleService;
import com.lk.my_blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 刘康
 * @Date: 2021/7/22 10:00
 * @Description: 配置用户权限
 */
@Service("userDetailService")
public class MyUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private LogConfig logConfig;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.getUserByName(s);
        logConfig.getLog().info("用户信息"+user);
        if(user==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        String role = roleService.getRoleByUid(user.getId());
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(role);
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                new BCryptPasswordEncoder().encode(user.getPassword()),authorities);
    }
}
