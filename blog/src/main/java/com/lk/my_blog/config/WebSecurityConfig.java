package com.lk.my_blog.config;

import com.lk.my_blog.filter.VerifyCodeFilter;
import com.lk.my_blog.handler.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: 刘康
 * @Date: 2021/7/22 9:49
 * @Description:
 */

//@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("userDetailService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private CustomUrlDecisionManager customUrlDecisionManager;

    @Autowired
    private CustomerAuthenticationSuccessHandler customerAuthenticationSuccessHandler;

    @Autowired
    private CustomerAuthenticationFailureHandler customerAuthenticationFailureHandler;

    @Autowired
    private CustomerLogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private CustomerAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private CustomerSessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    @Autowired
    private VerifyCodeFilter verifyCodeFilter;

    // url权限拦截
    @Autowired
    private CustomFilterInvocationSecurityMetadataSource customFilterInvocationSecurityMetadataSource;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/home/verifyCode","/admin/images/register.jpg","/home/**");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/admin/").hasAuthority("admin")
                .antMatchers("/admin/profile").hasAuthority("admin")
                .and()
                .formLogin().loginPage("/home/login").loginProcessingUrl("/toLogin").permitAll()
                .successHandler(customerAuthenticationSuccessHandler)
                .failureHandler(customerAuthenticationFailureHandler)
                //注销
                .and().logout().permitAll().logoutSuccessUrl("/home/login")
                .and().csrf().disable();
        http.headers().frameOptions().disable();
        http.addFilterBefore(verifyCodeFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
