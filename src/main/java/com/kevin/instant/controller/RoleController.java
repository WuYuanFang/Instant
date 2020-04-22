package com.kevin.instant.controller;

import com.kevin.instant.core.response.Response;
import com.kevin.instant.entity.Role;
import com.kevin.instant.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: Instant
 * @create: 2020-04-20 17:41
 * @author: WYF
 * @description:
 * @version: 1.0
 **/

@RestController()
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @GetMapping("/index")
    public Response roleInfo() {
        Role role = roleService.selectById("f58a3ee5-82e6-11ea-9f33-00163e048250");
        return ResponseUtil.Success(role);
    }
}
