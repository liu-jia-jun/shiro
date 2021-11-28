package com.example.shiro_springboot.mapper;

import com.example.shiro_springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface UserMapper {

    User selectUserByUserName(String username);

}
