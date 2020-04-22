package com.kevin.instant.util;

import java.util.HashMap;
import java.util.Map;

import static com.kevin.instant.util.Constant.CHARSET_UTF8;

/**
 * @program: Instant
 * @create: 2020-04-21 10:27
 * @author: WYF
 * @description: 解析请求头的签名是否正确
 * @version: 1.0
 **/

public class SignUtil {

    private static final String signKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNDANKEVIN";

    /**
     * 解析请求头的信息，然后解析成用于判断用户请求是否有误
     * @param result 请求头数据
     * @return 解析后的用户数据
     */
    public static boolean parseFilter(Map<String, String> result) throws Exception {
        Map<String, String> map = new HashMap<>();
        if (result == null || result.size() == 0) {
            return false;
        }
        StringBuilder builder = new StringBuilder();
        if (result.get("token") != null) {
            builder.append("token=" + result.get("token"));
        }
        // 签名准备
        String sign = MD5.signByMD5(builder.toString(), signKey, Constant.CHARSET_UTF8);
        if (result.get("sign") != null && (result.get("sign").equals(sign))) {
            return true;
        }
        return false;
    }

}
