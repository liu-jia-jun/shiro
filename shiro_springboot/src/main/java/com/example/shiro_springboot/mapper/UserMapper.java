package com.example.shiro_springboot.mapper;

import com.example.shiro_springboot.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
    User getUserByUserName(String username);

    String test();
}
