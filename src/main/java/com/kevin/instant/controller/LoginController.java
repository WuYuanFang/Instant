package com.kevin.instant.controller;

import com.kevin.instant.core.annotation.PassToken;
import com.kevin.instant.core.response.Response;
import com.kevin.instant.core.token.TokenService;
import com.kevin.instant.entity.User;
import com.kevin.instant.entity.UserDetail;
import com.kevin.instant.service.IUserService;
import com.kevin.instant.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @program: Instant
 * @create: 2020-04-21 09:25
 * @author: WYF
 * @description:
 * @version: 1.0
 **/

@RestController
public class LoginController {

    @Autowired
    private IUserService userService;

    @PassToken
    @PostMapping("/loginIn")
    public Response login(@RequestParam Map<String, String> map) {
        String username = map.get("username");
        String password = map.get("password");
        boolean isExist = userService.checkUserIsExistByUserName(username);
        if (!isExist) {
            return Response.getErrorResult("用户名不存在");
        }
        User user = userService.selectByNameAndPwd(username, password);
        if (user != null) {
            UserDetail userDetail = new UserDetail();
            userDetail.setUsername(user.getUsername());
            userDetail.setNickname(user.getNickname());
            userDetail.setId(user.getId());
            userDetail.setToken(TokenService.getToken(user.getId()));
            return Response.getSuccessResult("登录成功", userDetail);
        }else{
            return Response.getErrorResult("密码错误");
        }
    }


}
