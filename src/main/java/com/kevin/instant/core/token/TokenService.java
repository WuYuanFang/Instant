package com.kevin.instant.core.token;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @program: Instant
 * @create: 2020-04-21 17:07
 * @author: WYF
 * @description: JJWT生成token
 * @version: 1.0
 **/

public class TokenService {

    private static Logger logger = LoggerFactory.getLogger(TokenService.class);

    private static final String keyStr = "KEVIN520DANTOWUXI";

    /**
     * 根据用户ID生成token
     * @param id 用户ID
     * @return 返回token字符串
     */
    public static String getToken(String id) {
        String token = Jwts.builder()
                .setAudience(id) // 设置用户ID
                .setIssuedAt(new Date())//设置签发日期
                .signWith(SignatureAlgorithm.HS256, keyStr)
                .compact();
        return token;
    }

    public static String getIdByToken(String token) {
        return Jwts.parser().setSigningKey(keyStr).parseClaimsJws(token).getBody().getAudience();
    }


}
