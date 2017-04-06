package youga.https;

import org.junit.Test;


import youga.https.rsa.RSACodeHelper;

/**
 * Created by YougaKing on 2017/3/10.
 */

public class RsaTest {

    @Test
    public void http() throws Exception {
        //初始化工作
        RSACodeHelper helper = new RSACodeHelper();
        //公钥加密密码后的字符串
        String encryptString = helper.encrypt("123456");
        System.out.println("encryptString:"+encryptString);
        //私钥解密，还原密码
        String password = helper.decrypt(encryptString);
        System.out.println("password:"+password);
    }

}
