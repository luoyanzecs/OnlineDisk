package cn.team.onlinedisk.utils.md5;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName Encryption
 * @Description TODO
 * @Author luoyanze
 * @Date 2020/7/31 12:32 上午
 * @Version 1.0
 */


public class Encryption {
    /**
     * 对字符串进行加密并且返回一个加密之后的字符串
     *
     * @param str:
     * @return: java.lang.String
     */
    public static String md5(@NotNull String str){
        String md5Code = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes());
            byte[] digest = md5.digest();
            md5Code = new BigInteger(1, digest).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }finally {
            return md5Code;
        }
    }

    /**
     * 将一个普通字符串和加密后的字符串比较.
     *
     * @param str: 待比较字符串
     * @param strEncrypted:  加密后的字符串
     * @return: boolean
     */
    public static boolean check(String str, String strEncrypted){
        return strEncrypted.equals(md5(str));
    }
}
