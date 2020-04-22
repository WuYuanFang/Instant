package com.kevin.instant.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.security.MessageDigest;

/**
 * @program: Instant
 * @create: 2020-04-16 13:50
 * @author: WYF
 * @description: MD5加密类
 * @version: 1.0
 **/

public class MD5 {

    public static final Logger logger = LoggerFactory.getLogger(MD5.class);

    public static String md5(String str, String charset) {
        StringBuilder buf = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charset == null || "".equals(charset)) {
                md.update(str.getBytes());
            } else {
                md.update(str.getBytes(Charset.forName(charset)));
            }
            byte[] bytes = md.digest();
            for (int i : bytes) {
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (Exception e) {
            logger.error("md5出现异常，参数str：{}", str, e);
            throw new RuntimeException(e);
        }
    }

    public static String md5(String str) {
        StringBuilder buf = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] bytes = md.digest();
            for (int i : bytes) {
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (Exception e) {
            logger.error("md5出现异常，参数str：{}", str, e);
            throw new RuntimeException(e);
        }
    }


    /**
     * md5签名
     * @param sourceData
     * @param key
     * @param charset
     * @return
     * @throws Exception
     */
    public static String signByMD5(String sourceData, String key, String charset) throws Exception {
        String data = sourceData + key;
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] sign = md5.digest(data.getBytes(charset));

        return bytes2HexString(sign).toUpperCase();
    }

    /**
     * 将byte数组转成十六进制的字符串
     *
     * @param bytes
     *            byte[]
     * @return String
     */
    public static String bytes2HexString(byte[] bytes) {
        StringBuilder ret = new StringBuilder(bytes.length);
        String hex;
        for (byte b : bytes) {
            hex = Integer.toHexString(b & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret.append(hex.toUpperCase());
        }
        return ret.toString();
    }

}
