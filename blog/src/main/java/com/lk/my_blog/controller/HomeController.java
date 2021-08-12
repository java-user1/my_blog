package com.lk.my_blog.controller;

import com.lk.my_blog.config.LogConfig;
import com.lk.my_blog.model.Contact;
import com.lk.my_blog.model.RespBean;
import com.lk.my_blog.model.VerifyCode;
import com.lk.my_blog.service.Impl.ContactServiceImpl;
import com.lk.my_blog.service.VerifyCodeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: 刘康
 * @Date: 2021/7/19 10:42
 * @Description:
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private VerifyCodeService verifyCodeService;

    @Autowired
    private LogConfig logConfig;

    @Autowired
    private ContactServiceImpl contactService;

    @RequestMapping("/")
    public String index(){
        return "home/index1";
    }

    @ApiOperation("登录页")
    @GetMapping("/login")
    public String login(){
        return "admin/login";
    }

    @ApiOperation("关于我")
    @GetMapping("/about")
    public String about(){
        return "home/about";
    }

    @ApiOperation("博客主页")
    @GetMapping("/blog")
    public String blog(){
        return "home/blog";
    }

    @ApiOperation("联系我")
    @GetMapping("/contact")
    public String contact(){
        return "home/Contact";
    }

    @ApiOperation("联系我添加一条数据")
    @PostMapping(value = "/addContact")
    @ResponseBody
    public RespBean addContact(@RequestBody Contact contact){
        logConfig.getLog().info(""+contact);
        int i = contactService.addContact(contact);
        if(i>-1){
            return RespBean.ok("添加成功");
        }else {
            return RespBean.error("添加失败");
        }
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
