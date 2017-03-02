package net.gcl.ticket.dama2;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by guochenglai on 1/9/17.
 */
public class EncryptUtil {
    private static Logger logger = LoggerFactory.getLogger(EncryptUtil.class);
    public static String encryptDamaLoginParam(String preAuthKey,String softwareName,String usename,String password) {
        //压缩软件KEY为8字节，用作DES加密的KEY
        //压缩软件KEY为8字节，用作DES加密的KEY
        byte [] key16 = hexString2ByteArray(softwareName);
        byte [] key8 = new byte[8];
        for (int i = 0; i < 8; i++) {
            key8[i] = (byte)((key16[i] ^ key16[i + 8]) & 0xff);
        }

        try {
            byte [] pwd_data = password.getBytes("utf-8");
            java.security.MessageDigest md5 = java.security.MessageDigest.getInstance("MD5");
            md5.update(pwd_data, 0, pwd_data.length);
            String pwd_md5_str = byteArray2HexString(md5.digest()); //转为16进制字符串

            String enc_data_str = preAuthKey + "\n" + usename + "\n" + pwd_md5_str;

            SecureRandom sr = new SecureRandom();
            DESKeySpec dks = new DESKeySpec(key8);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secureKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE , secureKey, sr);
            byte [] resultData = cipher.doFinal(enc_data_str.getBytes("utf-8"));
            return byteArray2HexString(resultData);
        } catch (Exception e) {
            logger.error("encrypt dama2 request cause exxception ",e);
        }
        return null;
    }

    //16进制字符串转为BYTE数组
    private static byte[] hexString2ByteArray(String hexStr) throws NumberFormatException {
        int len = hexStr.length();
        if (len % 2 != 0)
            throw new NumberFormatException();

        byte [] result = new byte[len / 2];

        for (int i = 0; i < len; i += 2) {
            String s = hexStr.substring(i, i + 2);
            int n = Integer.parseInt(s, 16);
            result[i / 2] = (byte)(n & 0xff);
        }

        return result;
    }

    //转化BYTE数组为16进制字符串
    private static String byteArray2HexString(byte [] data) {
        StringBuilder sb = new StringBuilder();
        for (byte b : data) {
            String s = Integer.toHexString(b & 0xff);
            if (s.length() == 1) {
                sb.append("0" + s);
            } else {
                sb.append(s);
            }
        }
        return sb.toString();
    }
}
