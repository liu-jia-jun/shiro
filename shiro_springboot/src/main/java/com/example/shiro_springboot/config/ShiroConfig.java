package com.example.shiro_springboot.config;



import com.example.shiro_springboot.shiro.CustormerRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author asus
 *
 * shiro 整合springboot 的配置类
 */
@Configuration
public class ShiroConfig {
    /**
     * 创建SHiroFilter 负责拦截所有请求
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 给filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        // shiroFilterFactoryBean 是用于拦截所有的请求，我们通过shiriFilterFactoryBean来配置系统受限资源，配置系统公共资源
        Map<String,String> map = new HashMap<>();

        //
        map.put("/user/login","anon");
        // authc 配置 请求这个资源需要认证和授权
        map.put("/user/index","authc");
        map.put("/","authc");


        // 配置默认认证界面路径，即登录页面路径
        shiroFilterFactoryBean.setLoginUrl("/user/login");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);



        return shiroFilterFactoryBean;
    }

    // 创建安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(Realm realm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();

        // 给安全管理器设置自定义的realm
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }

    /**
     * 创建自定义的Realm
     * @return
     */
    @Bean
    public Realm getReaml(){
        CustormerRealm custormerRealm = new CustormerRealm();
        return  custormerRealm;
    }
}
