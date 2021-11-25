package com.example.shiro_springboot.service;


import com.example.shiro_springboot.mapper.UserMapper;
import com.example.shiro_springboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByUserName(String username) {


        return userMapper.getUserByUserName(username);
    }

    @Override
    public String test() {
        return userMapper.test();
    }
}
