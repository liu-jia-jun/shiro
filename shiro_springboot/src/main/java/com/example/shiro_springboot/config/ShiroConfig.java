package com.example.shiro_springboot.config;



import com.example.shiro_springboot.shiro.CustormerRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
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
 *
 *
 * shiro中常见的过滤器
 *
 * 1. anon  配置请求的url可以匿名访问 ，即无需认证和授权
 *
 * 2. authc 指定的url 需要form表单登录默认会从请求中获取username，password，remember 等参数并尝试登录，
 *          如果登录失败就会挑战到loginUrl配置的路径
 *          我们也可以用这个过滤器做默认的登录逻辑，但是一般我们都自己写在控制器中，可以定制出错的返回信息
 *
 * 3. authcBasic    指定的url需要basic登录
 *
 * 4. logout        退出过滤器，配置指定的url即可实现退出功能
 *
 * 5. noSessionCreation     禁止创建会话
 *
 * 6. perms     需要指定的权限才能访问
 *
 * 7. port      需要指定的端口才能访问
 *
 * 8. roles     需要指定的角色才能访问
 *
 * 9. ssl       需要https请求才能访问
 *
 * 10 user      需要已登录或者"记住我"的用户才能访问
 *
 *
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

        // anon 配置 请求这个资源可以不用认证和授权
        map.put("/login","anon");
        // authc 配置 请求这个资源需要认证和授权
        map.put("/index","authc");



        // 配置默认认证界面路径，即登录页面路径
        shiroFilterFactoryBean.setLoginUrl("/login");
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

        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        // 使用算法
        credentialsMatcher.setHashAlgorithmName("md5");
        // 使用散列并指定散列此时，默认是1次
        credentialsMatcher.setHashIterations(1024);
        custormerRealm.setCredentialsMatcher(credentialsMatcher);
        return  custormerRealm;
    }
}
