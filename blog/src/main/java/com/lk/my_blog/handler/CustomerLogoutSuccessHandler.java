package com.lk.my_blog.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lk.my_blog.model.RespBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: 刘康
 * @Date: 2021/6/28 9:51
 * @Description:
 */
@Component
public class CustomerLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        RespBean ok = RespBean.ok("注销成功");
        out.write(new ObjectMapper().writeValueAsString(ok));
        out.flush();
        out.close();
    }
}
