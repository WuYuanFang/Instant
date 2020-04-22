package com.kevin.instant.controller;

import com.kevin.instant.core.annotation.PassToken;
import com.kevin.instant.core.response.Response;
import com.kevin.instant.entity.User;
import com.kevin.instant.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @program: Instant
 * @create: 2020-04-22 20:39
 * @author: WYF
 * @description:
 * @version: 1.0
 **/

@RestController
public class RegisterController {

    /**
     * 注册的用户名和密码的最小长度
     */
    private static final int userNameLength = 5;

    @Autowired
    private IUserService userService;

    @PassToken
    @PostMapping("/register")
    public Response registerByNameAndPwd(@RequestParam Map<String, String> param){
        String username = param.get("username");
        String password = param.get("password");
        if (username == null || username.length() < userNameLength) {
            return Response.getErrorResult("注册的用户名不能小于"+userNameLength+"位");
        }
        if (password == null) {
            return Response.getErrorResult("注册的密码不能为空");
        }
        boolean isExist = userService.checkUserIsExistByUserName(username);
        if (isExist) {
            return Response.getErrorResult("用户名已被注册");
        }
        User user = userService.insertUserNameAndPwd(username, password);
        if (user != null) {
            return Response.getSuccessResult("注册成功", user);
        }
        return Response.getSuccessResult("注册失败，请稍后重试");
    }


}
