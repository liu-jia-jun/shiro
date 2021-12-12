package com.example.shiro_springboot.service;


import com.example.shiro_springboot.entity.User;
import com.example.shiro_springboot.mapper.UserMapper;

import com.example.shiro_springboot.utils.SaltUtil;
import org.apache.shiro.crypto.hash.Md5Hash;
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

    @Override
    public int insertUser(User user) {

        // 通过工具类生成随机盐
        String salt = SaltUtil.getSalt(8);
        // 将随机盐添加到user对象中
        user.setSalt(salt);

        // 对user对象中的密码用随机盐进行加密处理
        Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt, 1024);
        user.setPassword(md5Hash.toHex());

        int i = userMapper.insertUser(user);
        return i;
    }


}
