package com.example.shiro_springboot.controller;


import com.example.shiro_springboot.entity.User;
import com.example.shiro_springboot.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author asus
 */
@Controller
public class TestController {

    @Autowired
    UserService userService;

    @GetMapping("/index")
    public String toIndex() {
        return "index";
    }

    @GetMapping("/login")
    public String toLogin() {
        return "login";
    }

    @PostMapping("/toAccessIndex")
    public String toAccessIndex(String username, String password) {

        // 获取主体对象
        Subject subject = SecurityUtils.getSubject();
        System.out.println(username);
        System.out.println(password);
        try {
            subject.login(new UsernamePasswordToken(username, password));

            return "index";
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户名错误");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("密码错误");
        }

        return "login";
    }

    @GetMapping("/logout")
    public String toLogout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }

    @PostMapping("/register")
    @ResponseBody
    public int Register(@RequestBody User user) {


        int i = userService.insertUser(user);


        return i;

    }

    @GetMapping("/toRegister")
    public String toRegister() {
        return "register";
    }


    /**
     * @return
     */
    @RequiresRoles(value = {"admin", "user"})// 用于判断角色，同时具有 admin user 才能访问该方法
    @RequiresPermissions("user:update:01")// 用于判断权限字符串，基于权限的授权操作
    @GetMapping("/test")
    public String test() {
        System.out.println("进入该方法");

        // 获取主体对象
        // 通过代码方式进行一个基于角色的授权操作
        Subject subject = SecurityUtils.getSubject();

        if (subject.hasRole("admin")) {
            System.out.println("角色匹配，操作成功");
        } else {
            System.out.println("角色不匹配,操作失败");
        }


        return null;

    }


}
