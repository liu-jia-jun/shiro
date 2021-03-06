package com.example.shiro_springboot.shiro;

import com.example.shiro_springboot.entity.Perms;
import com.example.shiro_springboot.entity.Role;
import com.example.shiro_springboot.entity.User;
import com.example.shiro_springboot.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author asus
 */
public class CustormerRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        // 获取身份信息
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        System.out.println("调用授权验证" +
                primaryPrincipal);


        // 根据用户名查询出该用户的角色，一个用户可能有多个角色，所以返回的user对象中的role是一个list列表
        User user = userService.getUserByUserName(primaryPrincipal);
        // 授权角色信息

        if (!user.getRoles().isEmpty()){
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            // 将用户的角色信息放到用户中

            for (Role role:user.getRoles()
                 ) {
                simpleAuthorizationInfo.addRole(role.getName());


                // 根据角色查询用户的操作权限

                // 这里需要通过用户的角色来查询用户的操作权限，这里直接使用了一个空的list集合代替
                List<Perms> perms = new ArrayList<>();

                if (!perms.isEmpty()){
                    for (Perms perm : perms) {
                        // 将用户的权限写入到project对象中
                        simpleAuthorizationInfo.addStringPermission(perm.getName());
                    }
                }



            }


           return simpleAuthorizationInfo;
        }




        return null;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        System.out.println("+++++++++++进入认证流程+++++++++" + authenticationToken);
        String principal = (String) authenticationToken.getPrincipal();

        User user = userService.getUserByUserName(principal);

        System.out.println("++++++++++" + authenticationToken.getPrincipal());
        System.out.println("==========" + authenticationToken.getCredentials());

        if (user != null) {

            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), ByteSource.Util.bytes(user.getSalt()), this.getName());
            return simpleAuthenticationInfo;

        }
        return null;
    }
}
