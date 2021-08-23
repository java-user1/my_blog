package com.lk.my_blog.controller;

import com.lk.my_blog.config.LogConfig;
import com.lk.my_blog.model.Contact;
import com.lk.my_blog.model.RespBean;
import com.lk.my_blog.model.User;
import com.lk.my_blog.model.VerifyCode;
import com.lk.my_blog.service.Impl.ContactServiceImpl;
import com.lk.my_blog.service.UserService;
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

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(){
        return "home/index1";
    }

    @ApiOperation("登录页")
    @GetMapping("/login")
    public String login(){
        return "admin/login";
    }

    @ApiOperation("注册页")
    @GetMapping("/register")
    public String register(){
        return "admin/register";
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

    @ApiOperation("注册验证")
    @PostMapping("/submit_register")
    @ResponseBody
    public RespBean submitRegister(@RequestParam String username,
                                   @RequestParam String password,
                                   @RequestParam String email,
                                   @RequestParam String surePassword){
        if(password.length()<6||password.length()>14){
            return RespBean.error("密码长度在6-14位之间");
        }
        if(!password.equals(surePassword)){
            return RespBean.error("确认密码不正确");
        }
        User userByName = userService.getUserByName(username);
        if(userByName!=null){
            return RespBean.error("用户名已存在");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        int i = userService.addUser(user);
        if(i>0){
            return RespBean.ok("注册成功");
        }else {
            return RespBean.error("注册失败");
        }
    }
}
