package com.lk.my_blog.filter;

import com.lk.my_blog.config.LogConfig;
import com.lk.my_blog.exception.ValidateCodeException;
import com.lk.my_blog.handler.CustomerAuthenticationFailureHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;
import org.thymeleaf.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: 刘康
 * @Date: 2021/6/29 9:51
 * @Description:
 */
@Component
public class VerifyCodeFilter extends OncePerRequestFilter {
    private static final String FILTER_APPLIED = "__spring_security_verifyCodeFilter_filterApplied";
    @Autowired
    private CustomerAuthenticationFailureHandler customerAuthenticationFailureHandler;

    @Autowired
    private LogConfig logConfig;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(StringUtils.equalsIgnoreCase("/toLogin",request.getRequestURI())
                && StringUtils.equalsIgnoreCase(request.getMethod(),"POST")){
            String captcha = request.getParameter("captcha");
            String verifyCode = (String) request.getSession().getAttribute("VerifyCode");
            // 验证码判断空或者不匹配问题
            if(StringUtils.equals(captcha,"")){
                customerAuthenticationFailureHandler.onAuthenticationFailure(request,response
                        ,new ValidateCodeException("验证码不能为空"));
            }
            else if(!StringUtils.equalsIgnoreCase(verifyCode,captcha)){
                customerAuthenticationFailureHandler.onAuthenticationFailure(request,response
                        ,new ValidateCodeException("验证码输入错误"));
            }
            else {
                    filterChain.doFilter(request,response);
            }
        }
        else{
            filterChain.doFilter(request, response);
        }
    }
}
