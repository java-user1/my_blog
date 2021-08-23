package com.lk.my_blog.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lk.my_blog.model.RespBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: 刘康
 * @Date: 2021/6/27 17:08
 * @Description:
 */
@Component
public class CustomerAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        RespBean ok = RespBean.ok("登录成功");
        String s = new ObjectMapper().writeValueAsString(ok);
        out.write(s);
        out.flush();
        out.close();
    }
}
