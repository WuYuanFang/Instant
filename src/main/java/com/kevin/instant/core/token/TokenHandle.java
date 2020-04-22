package com.kevin.instant.core.token;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 令牌处理器
 *
 * 场景：当需要获取用户身份信息，而用户只传递令牌，需要从令牌解析用户信息
 */
public class TokenHandle {

    private static Logger logger = LoggerFactory.getLogger(TokenHandle.class);

    static private String TokenKey = "token";

    public static String getToken(boolean isHeader){
        String token = null;
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        if(isHeader){
            token = request.getHeader(TokenHandle.TokenKey);
        }else{
            token = request.getParameter(TokenHandle.TokenKey);
        }
        return token;
    }


    public static String getTokenFromHeader(){

        return TokenHandle.getToken(true);
    }

    public static String getTokenFromParam(){

        return TokenHandle.getToken(false);
    }

    public static void SetTokenKey(String key){
        TokenHandle.TokenKey = key;
    }

    /**
     * 推荐做法
     */
    public static String autoToken(){
        // 从请求头获取token
        String token = TokenHandle.getTokenFromHeader();
        if(null!=token){
            return token;
        }else{
            // 从请求参数中获取token
            return TokenHandle.getTokenFromParam();
        }
    }

    /**
     * 判断token是否有效
     */
    public static Boolean isEffective(){
        String token = TokenHandle.autoToken();
        logger.info("token------>{}", token);
        return false;
    }

}
