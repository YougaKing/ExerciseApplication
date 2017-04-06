package youga.https.aes;

import android.util.Base64;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.zip.CRC32;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by YougaKing on 2017/3/10.
 */

public class AesTools {


    // /** 算法/模式/填充 **/
    private static final String CipherMode = "AES/CFB8/NoPadding";
    private static final String PRE = "~1Ba";


    private static long crc(String s) {
        return crc(s.getBytes());
    }


    private static long crc(byte[] s) {
        CRC32 crc32 = new CRC32();
        crc32.update(s);
        return crc32.getValue();
    }


    public static class Server {

        private String password, iv;

        public Server(String password, String iv) {
            this.password = password;
            this.iv = iv;
        }


        public byte[] byteMerger(byte[] byte_1, byte[] byte_2) {
            byte[] byte_3 = new byte[byte_1.length + byte_2.length];
            System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
            System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
            return byte_3;
        }

        public byte[] longToByte(long number) {
            byte[] result = new byte[8];
            for (int i = 7; i >= 0; i--) {
                result[i] = (byte) (number & 0xFF);
                number >>= 8;
            }
            return result;
        }

        /**
         * 加密
         *
         * @return
         */
        private byte[] encrypt(byte[] bytes) {
            try {
                Cipher cipher = Cipher.getInstance(CipherMode);
                cipher.init(Cipher.ENCRYPT_MODE, createKey(password), createIV(iv));
                return cipher.doFinal(bytes);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * 加密 + 效验
         *
         * @return
         */
        public byte[] encryptData(String content) {
            String preContent = PRE + content;
            long crc = crc(preContent);
            Log.i("Server", "crc:" + crc);
            byte[] crcByte = longToByte(crc);//crc 64位 byte[]
            Log.i("Server", "crcByte:" + Arrays.toString(crcByte));
//            byte[] temp = Arrays.copyOfRange(crcByte,0,4);
            byte[] originalByte = byteMerger(preContent.getBytes(), crcByte);//最终需要加密的 byte[]
            Log.i("Server", "originalByte:" + Arrays.toString(originalByte));
            return encrypt(originalByte);
        }
    }


    public static class Client {
        String password, iv;

        public Client(String password, String iv) {
            this.password = password;
            this.iv = iv;
        }


        public static long bytesToLong(byte[] b) {
            long result = 0;
            for (int i = 0; i < 8; i++) {
                result <<= 8;
                result |= (b[i] & 0xFF);
            }
            return result;
        }

        /**
         * 解密
         *
         * @return
         */
        private byte[] decrypt(byte[] bytes) {
            try {
                Cipher cipher = Cipher.getInstance(CipherMode);
                cipher.init(Cipher.DECRYPT_MODE, createKey(password), createIV(iv));
                return cipher.doFinal(bytes);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * 解密 + CRC的校验
         */
        public String decryptData(byte[] data) {
            byte[] originalByte = decrypt(data);
            Log.i("Client", "originalByte:" + Arrays.toString(originalByte));
            byte[] crcByte = Arrays.copyOfRange(originalByte, originalByte.length - 8, originalByte.length);
            Log.i("Client", "crcByte:" + Arrays.toString(crcByte));
            byte[] temp = Arrays.copyOfRange(originalByte, 0, originalByte.length - 8);
            String result = new String(temp);
            Log.i("Client", "crc(temp):" + crc(temp));
            Log.i("Client", "bytesToLong(crcByte):" + bytesToLong(crcByte));
            if (bytesToLong(crcByte) == crc(temp)) {
                result = result.replaceAll(PRE, "");
            } else {
                //key 失效
                result = null;
            }
            return result;
        }
    }


    private static SecretKeySpec createKey(String key) {
        byte[] data = null;
        if (key == null) {
            key = "";
        }
        StringBuffer sb = new StringBuffer(16);
        sb.append(key);
        while (sb.length() < 16) {
            sb.append("0");
        }
        if (sb.length() > 16) {
            sb.setLength(16);
        }
        try {
            data = sb.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new SecretKeySpec(data, "AES");
    }

    private static IvParameterSpec createIV(String password) {
        byte[] data = null;
        if (password == null) {
            password = "";
        }
        StringBuffer sb = new StringBuffer(16);
        sb.append(password);
        while (sb.length() < 16) {
            sb.append("0");
        }
        if (sb.length() > 16) {
            sb.setLength(16);
        }
        try {
            data = sb.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new IvParameterSpec(data);
    }
}
