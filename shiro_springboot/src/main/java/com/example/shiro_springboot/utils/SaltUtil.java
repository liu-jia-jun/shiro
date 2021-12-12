package com.example.shiro_springboot.utils;

import java.util.Random;

/**
 * @author 刘佳俊
 */
public class SaltUtil {

    public static String getSalt(int n){
        String str = "ABCDEFGHIGKLMNOPQRSTUVWXYZabcdefghigklmnopqrstuvwxyz()*&^%$#@!1234567890";
        StringBuilder salt = new StringBuilder();
        for(int i = 0;i<n; i++){
          salt.append(str.charAt(new Random().nextInt(str.length())));

        }
        return salt.toString();
    }

    public static void main(String[] args) {
        System.out.println(getSalt(8));
    }

}
