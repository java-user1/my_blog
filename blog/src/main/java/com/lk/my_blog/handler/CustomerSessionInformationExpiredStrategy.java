package com.lk.my_blog.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lk.my_blog.model.RespBean;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: 刘康
 * @Date: 2021/6/28 10:10
 * @Description:
 */
@Component
public class CustomerSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        HttpServletResponse response = event.getResponse();
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(401);
        RespBean respBean = RespBean.error("另一台设备已登录");
        PrintWriter out = response.getWriter();
        out.write(new ObjectMapper().writeValueAsString(respBean));
        out.flush();
        out.close();
    }
}
