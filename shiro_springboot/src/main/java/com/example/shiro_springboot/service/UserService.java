package com.example.shiro_springboot.service;

import com.example.shiro_springboot.pojo.User;
import org.springframework.stereotype.Service;

public interface UserService {

    User getUserByUserName(String username);

    String test();
}
