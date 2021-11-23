package ShiroTest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import realm.CustomerRealm;

public class TestCustromerRealmAuthenticator {

    public static void main(String[] args) {
        // 创建securityManager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(new CustomerRealm());
        // 将安装工具类中设置默认的安全管理器
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        // 获取主体对象
        Subject subject = SecurityUtils.getSubject();

        // 创建token令牌
        UsernamePasswordToken token = new UsernamePasswordToken("ljj", "123456");
        try{
            subject.login(token);
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
