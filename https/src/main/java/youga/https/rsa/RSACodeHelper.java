package youga.https.rsa;

import android.util.Base64;
import android.util.Log;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Created by YougaKing on 2017/3/10.
 */

public class RSACodeHelper {

    Server mServer;

    public RSACodeHelper() {
        mServer = new Server();
        Log.i("RSACodeHelper", "PublicKey:" + Base64.encodeToString(mServer.getPublicKeyByte(), Base64.DEFAULT));
        Log.i("RSACodeHelper", "PrivateKey:" + Base64.encodeToString(mServer.getPrivateKeyByte(), Base64.DEFAULT));
    }

    /**
     * 加密
     *
     * @param content 内容
     * @return
     */
    public String encrypt(String content) {
        String strEncrypt = null;
        try {

            // 实例化加解密类
            Cipher cipher = Cipher.getInstance("RSA");
            // 明文
            byte[] plainText = content.getBytes();
            // 加密
            cipher.init(Cipher.ENCRYPT_MODE, new Client(mServer.getPublicKeyByte()).getPublicKey());
            //将明文转化为根据公钥加密的密文，为byte数组格式
            byte[] enBytes = cipher.doFinal(plainText);
            //为了方便传输我们可以将byte数组转化为base64的编码
            strEncrypt = Base64.encodeToString(enBytes, Base64.DEFAULT);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvalidKeyException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (BadPaddingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            return strEncrypt;
        }
    }

    /**
     * 解密
     *
     * @param encString 内容
     * @return
     */
    public String decrypt(String encString) {
        Cipher cipher = null;
        String strDecrypt = null;
        try {
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, mServer.getPrivateKey());
            //先将转为base64编码的加密后的数据转化为byte数组
            byte[] enBytes = Base64.decode(encString, Base64.DEFAULT);
            //解密称为byte数组，应该为字符串数组最后转化为字符串
            byte[] deBytes = cipher.doFinal(enBytes);

            strDecrypt = new String(deBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (BadPaddingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvalidKeyException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            return strDecrypt;
        }

    }


    public static class Server {
        private RSAPublicKey mPublicKey;
        private RSAPrivateKey mPrivateKey;

        public Server() {
            KeyPairGenerator keyPairGen = null;
            try {
                //设置使用何种加密算法
                keyPairGen = KeyPairGenerator.getInstance("RSA");
                // 密钥位数
                keyPairGen.initialize(512);
                // 密钥对
                KeyPair keyPair = keyPairGen.generateKeyPair();
                // 公钥
                mPublicKey = (RSAPublicKey) keyPair.getPublic();

                // 私钥
                mPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

        public byte[] getPublicKeyByte() {
            return mPublicKey.getEncoded();
        }

        public byte[] getPrivateKeyByte() {
            return mPrivateKey.getEncoded();
        }

        public RSAPrivateKey getPrivateKey() {
            return mPrivateKey;
        }
    }

    public static class Client {

        PublicKey mPublicKey;

        public Client(byte[] keyByte) {
            // 得到公钥
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyByte);
            try {
                KeyFactory factory = KeyFactory.getInstance("RSA");
                mPublicKey = factory.generatePublic(keySpec);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            }

        }

        public PublicKey getPublicKey() {
            return mPublicKey;
        }
    }

}
