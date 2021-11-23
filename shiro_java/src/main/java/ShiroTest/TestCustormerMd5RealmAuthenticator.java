package ShiroTest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import realm.CustomerMd5Realm;
import realm.CustomerRealm;

import java.security.Security;

/**
 * @author asus
 */
public class TestCustormerMd5RealmAuthenticator {
    public static void main(String[] args) {
        // 创建securityManager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

        // 注入realm
        CustomerMd5Realm realm = new CustomerMd5Realm();
        // 设置realm使用hash凭证匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        // 使用算法
        credentialsMatcher.setHashAlgorithmName("md5");
        // 使用散列并指定散列此时，默认是1次
        credentialsMatcher.setHashIterations(1024);
        realm.setCredentialsMatcher(credentialsMatcher);

        defaultSecurityManager.setRealm(realm);

        // 将安装工具类中设置默认的安全管理器
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        // 获取主体对象
        Subject subject = SecurityUtils.getSubject();

        // 创建token令牌
        UsernamePasswordToken token = new UsernamePasswordToken("ljj", "123456");
        try{
            System.out.println("是否登录"+subject.isAuthenticated());
            subject.login(token);
            System.out.println("登录成功");
            System.out.println("是否登录"+subject.isAuthenticated());
        }catch (UnknownAccountException e){
            e.getMessage();
            e.printStackTrace();
//            System.out.println("用户名错误");
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            System.out.println("密码错误");
        }

    }


}
