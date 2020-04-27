package com.kevin.instant.controller;

import com.kevin.instant.core.response.Response;
import com.kevin.instant.core.token.TokenService;
import com.kevin.instant.entity.User;
import com.kevin.instant.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: Instant
 * @create: 2020-04-26 16:12
 * @author: WYF
 * @description:
 * @version: 1.0
 **/

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/getDetailInfo")
    public Response getDetailInfo() {
        // 获得request对象,response对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String token = request.getHeader("token");
        String userId = TokenService.getIdByToken(token);
        User user = userService.getUserById(userId);
        if (user != null) {
            return Response.getSuccessResult(user);
        } else {
            return Response.getErrorResult("获取用户信息失败");
        }
    }

    @GetMapping("/getInfoById")
    public Response getUserInfo(@RequestParam String id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return Response.getSuccessResult(user);
        } else {
            return Response.getErrorResult("找不到该用户");
        }
    }

    @PostMapping("/updateInfo")
    public Response updateUserInfo(User user) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String token = request.getHeader("token");
        String userId = TokenService.getIdByToken(token);
        user.setId(userId);
        boolean result = userService.updateUser(user);
        if (result) {
            return Response.getSuccessResult("修改成功");
        } else {
            return Response.getErrorResult("修改失败");
        }
    }

}
