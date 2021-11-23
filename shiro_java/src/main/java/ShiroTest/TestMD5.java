package ShiroTest;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * MD5算法的使用
 */
public class TestMD5 {
    public static void main(String[] args) {
        // 创建一个MD5算法
//        Md5Hash md5Hash = new Md5Hash();
//        md5Hash.setBytes("123456".getBytes());
//        String s = md5Hash.toHex();
//        System.out.println(s);


        System.out.println("++++++++++++MD5的使用+++++++++++++++");


        Md5Hash md5Hash = new Md5Hash("123456");
        System.out.println(md5Hash.toHex());

        System.out.println("++++++++++++MD5 and 加盐处理（salt）+++++++++++++++");
        Md5Hash md5Hash1 = new Md5Hash("123456","xo*7ps");
        System.out.println(md5Hash1.toHex());


        System.out.println("++++++++++++MD5 and 加盐处理（salt） and hash 散列+++++++++++++++");

        // 1024是hash 散列的散列次数
        Md5Hash md5Hash2 = new Md5Hash("123456","XO*7ps",1024);
        System.out.println(md5Hash2.toHex());


    }
}
