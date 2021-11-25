package com.example.shiro_springboot.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author asus
 */
@Controller
@RequestMapping("/user")
public class TestController {
    @GetMapping("/index")
    public String toIndex(){
        return "index";
    }

    @GetMapping("/login")
    public String toLogin(){
        return "login";
    }

    @PostMapping("/toAccessIndex")
    public String toAccessIndex(String username,String password){

        // 获取主体对象
        Subject subject = SecurityUtils.getSubject();
        System.out.println(username);
        System.out.println(password);
        try {
            subject.login(new UsernamePasswordToken(username,password));
            return "index";
        }catch (UnknownAccountException e){
            e.printStackTrace();
            System.out.println("用户名错误");
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            System.out.println("密码错误");
        }

        return "login";
    }

    @GetMapping("/logout")
    public String toLogout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }


}
