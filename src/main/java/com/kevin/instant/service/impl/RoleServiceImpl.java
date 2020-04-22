package com.kevin.instant.service.impl;

import com.kevin.instant.mapper.IRoleDao;
import com.kevin.instant.entity.Role;
import com.kevin.instant.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: Instant
 * @create: 2020-04-20 17:39
 * @author: WYF
 * @description:
 * @version: 1.0
 **/

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired(required = false)
    private IRoleDao roleDao;

    @Override
    public Role selectById(String id) {
        return roleDao.selectById(id);
    }
}
