package com.example.shiro_springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author asus
 */
@Controller
public class TestController {
    @GetMapping("/")
    public String toIndex(){
        return "index";
    }

    @GetMapping("/login")
    public String toLogin(){
        return "login";
    }
}
