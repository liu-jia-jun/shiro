package com.example.shiro_springboot;


import com.example.shiro_springboot.utils.SaltUtil;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShiroSpringbootApplicationTests {



    @Test
    void contextLoads() {

        System.out.println(SaltUtil.getSalt(8));
    }

}
