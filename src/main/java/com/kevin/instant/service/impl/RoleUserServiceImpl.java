package com.kevin.instant.service.impl;

import com.kevin.instant.entity.RoleUser;
import com.kevin.instant.mapper.IRoleUserDao;
import com.kevin.instant.service.IRoleUserService;
import com.kevin.instant.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: Instant
 * @create: 2020-04-22 23:43
 * @author: WYF
 * @description:
 * @version: 1.0
 **/

@Service
public class RoleUserServiceImpl implements IRoleUserService {

    @Autowired(required = false)
    private IRoleUserDao IRoleUserDao;

    @Override
    public int insertOne(RoleUser roleUser) {
        roleUser.setCreateTime(DateUtil.getDefaultDateTime());
        int i = IRoleUserDao.insert(roleUser);
        return i;
    }
}
