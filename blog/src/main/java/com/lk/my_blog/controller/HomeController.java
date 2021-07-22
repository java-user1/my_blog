package com.lk.my_blog.controller;

import com.lk.my_blog.config.LogConfig;
import com.lk.my_blog.model.VerifyCode;
import com.lk.my_blog.service.VerifyCodeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: 刘康
 * @Date: 2021/7/19 10:42
 * @Description:
 */
@Controller
public class HomeController {

    @Autowired
    private VerifyCodeService verifyCodeService;

    @Autowired
    private LogConfig logConfig;

    @RequestMapping(value = {"/","/toLogin"})
    public String login(){
        return "admin/login";
    }

    @ApiOperation("验证码")
    @GetMapping("/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response){
        VerifyCode verifyCode = verifyCodeService.generate(80, 28);
        try {
            String code = verifyCode.getCode();
//            logConfig.getLog().info(code);
            //将VerifyCode绑定在session
            request.getSession().setAttribute("VerifyCode",code);
            response.setHeader("Prama","no-cache");
            response.setHeader("Cache-Control","no-cache");
            response.setDateHeader("Expires",0);
            response.setContentType("image/jpeg");
            response.getOutputStream().write(verifyCode.getImgBytes());
            response.getOutputStream().flush();
        } catch (IOException e) {
            logConfig.getLog().info(" ",e);
        }
    }
}
