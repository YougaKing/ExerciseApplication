package youga.https;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import youga.https.aes.AesTools;
import youga.https.rsa.RSACodeHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        String url = "https://kyfw.12306.cn/otn/";
//
//        HttpManager manager = new HttpManager(this);
//        manager.get(url);

//
//        //初始化工作
//        RSACodeHelper helper = new RSACodeHelper();
//        //公钥加密密码后的字符串
//        String encryptString = helper.encrypt("123456");
//        Log.i("MainActivity", "encryptString:" + encryptString);
//        //私钥解密，还原密码
//        String password = helper.decrypt(encryptString);
//        Log.i("MainActivity", "password:" + password);


        String password = "123456";
        String iv = "666888";
        AesTools.Server server = new AesTools.Server(password, iv);
        AesTools.Client client = new AesTools.Client(password, iv);
        String content = "HelloWorld";
        byte[] bytes = server.encryptData(content);
        Log.i("MainActivity", "bytes:" + new String(bytes));
        String result = client.decryptData(bytes);
        Log.i("MainActivity", "result:" + result);
    }
}
