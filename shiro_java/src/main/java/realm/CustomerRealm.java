package realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 自定义realm实现，将认证授权的数据来源转为数据库实现
 *
 *
 */
public class CustomerRealm extends AuthorizingRealm {
    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal = (String) authenticationToken.getPrincipal();
        System.out.println(principal);
        // 根据身份信息(principal 即前端返回的用户名) 使用jdbc 或者 mybatis 等查询相关的数据库
       String username = "ljj";
       String password = "123456";

        if(username.equals(principal)){
            /**
             * 参数1 ： 返回数据库中正确的用户名
             * 参数2 ： 返回数据库中正确的密码
             * 参数3 ： 提供当前realm 的名字 this.getName()
             */
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username,password,this.getName());
            return simpleAuthenticationInfo;
        }

        return null;
    }
}
