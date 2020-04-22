package com.kevin.instant.controller;

import com.kevin.instant.core.response.Response;
import com.kevin.instant.entity.User;
import com.kevin.instant.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/login")
    public Response login() {
        User user = userService.getUserById("5d167193-8380-11ea-9f33-00163e048250");
        return Response.getSuccessResult(user);
    }


}
