/**
 * @program registration_system
 * @author: Kevin
 * @create: 2019/11/05 19:56
 * @description:
 */

package com.kevin.instant.interceptor;

import com.kevin.instant.core.annotation.PassToken;
import com.kevin.instant.core.exception.DKException;
import com.kevin.instant.core.token.TokenService;
import com.kevin.instant.entity.User;
import com.kevin.instant.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;


public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private IUserService userService;

    /**
     * 白名单，用于去除不需要登录的URI
     */
//    static List<String> whiteList = Arrays.asList(
//        "/login"
//    );

    /**
     * 请求之前被调用
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求的url
//        String url = request.getServletPath();
//        if(whiteList.contains(url)){
//            return true;
//        }
        // 如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();
        // 判断当前方法有没有用到PassToken的注解，有则跳过
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        // 判断是否有token并判断token是否有效
        String token = request.getHeader("token");
        if (token == null || token.length() == 0) {
            throw new DKException("token无效，请重新登录");
        }
        String userId = TokenService.getIdByToken(token);
        User user = userService.getUserById(userId);
        if (user == null) {
            throw new DKException("用户不存在，请重新登录");
        }
        return true;
    }

    /**
     * 请求之后被调用，页面在被渲染之前被调用。
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
