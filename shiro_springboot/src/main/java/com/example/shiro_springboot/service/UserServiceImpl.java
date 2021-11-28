package com.example.shiro_springboot.service;


import com.example.shiro_springboot.entity.User;
import com.example.shiro_springboot.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByUserName(String username) {


        return userMapper.selectUserByUserName(username);
    }


}
