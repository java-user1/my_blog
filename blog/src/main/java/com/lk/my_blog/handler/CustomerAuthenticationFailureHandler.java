package com.lk.my_blog.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lk.my_blog.model.RespBean;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: 刘康
 * @Date: 2021/6/28 9:38
 * @Description:
 */
@Component
public class CustomerAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        RespBean respBean = RespBean.error(exception.getMessage());
        if(exception instanceof LockedException){
            respBean.setMsg("账户被锁定");
        }
        else if(exception instanceof CredentialsExpiredException){
            respBean.setMsg("密码过期，请联系管理员");
        }
        else if(exception instanceof AccountExpiredException){
            respBean.setMsg("账号过期，请联系管理员");
        }
        else if(exception instanceof DisabledException){
            respBean.setMsg("账户被禁用，请联系管理员");
        }
        else if(exception instanceof BadCredentialsException){
            respBean.setMsg("用户名或密码错误，请重新输入");
        }
        out.write(new ObjectMapper().writeValueAsString(respBean));
        out.flush();
        out.close();
    }
}
