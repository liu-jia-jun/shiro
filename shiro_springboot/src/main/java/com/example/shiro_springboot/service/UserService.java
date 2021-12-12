package com.example.shiro_springboot.service;


import com.example.shiro_springboot.entity.User;

public interface UserService {


    User getUserByUserName(String username);

    int insertUser(User user);


}
