package realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
/**
 * 自定义realm实现，将认证授权的数据来源转为数据库实现
 * 该类主要是通过继承AuthorizingRealm并重写两个方法
 *
 * 一个方法用于授权一个用于认证
 *
 */
public class CustomerMd5Realm extends AuthorizingRealm {

    // 权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        System.out.println("身份信息"+primaryPrincipal);
        // 根据身份信息，用户名，获取当前用户的角色信息，以及权限信息
        SimpleAuthorizationInfo  simpleAuthorizationInfo= new SimpleAuthorizationInfo(); 

      // 将数据库中查询权限信息赋值给权限对象
        simpleAuthorizationInfo.addStringPermission("user:*:01");
        return simpleAuthorizationInfo;
    }
    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 获取身份信息
        String principal = (String) authenticationToken.getPrincipal();

        // 根据用户名查询数据库,这里的username和password 都是根据token中的用户信息到数据库中查询出来的

        String username = "ljj";
        String password = "81c97817c4e4db0baa3ebd97ad2afebb";
        if ("ljj".equals(principal)){
            /**
             * 参数1 ： 返回数据库中正确的用户名
             * 参数2 ： 返回数据库中正确的密码
             * 参数3 ： 注册时的随机盐
             * 参数4 ： 提供当前realm 的名字 this.getName()
             */
            return new SimpleAuthenticationInfo(username,password, ByteSource.Util.bytes("XO*7ps"),this.getName());
        }

        return null;
    }
}
