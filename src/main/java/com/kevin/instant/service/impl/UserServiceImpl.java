package com.kevin.instant.service.impl;

import com.kevin.instant.entity.User;
import com.kevin.instant.mapper.IUserDao;
import com.kevin.instant.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: Instant
 * @create: 2020-04-21 11:45
 * @author: WYF
 * @description:
 * @version: 1.0
 **/
@Service
public class UserServiceImpl implements IUserService {

    @Autowired(required = false)
    private IUserDao userDao;

    @Override
    public User getUserById(String id) {
        User user = userDao.selectById(id);
        return user;
    }
}
