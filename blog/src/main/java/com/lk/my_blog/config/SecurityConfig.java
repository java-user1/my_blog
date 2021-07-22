package com.lk.my_blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: 刘康
 * @Date: 2021/7/22 9:49
 * @Description:
 */

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").permitAll()
                .and()
                .formLogin().loginPage("/toLogin").successForwardUrl("/admin")
                .and().logout().logoutSuccessUrl("/toLogin");
    }
}
