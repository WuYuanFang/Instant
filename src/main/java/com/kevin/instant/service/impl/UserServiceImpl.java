package com.kevin.instant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kevin.instant.entity.Role;
import com.kevin.instant.entity.RoleUser;
import com.kevin.instant.entity.User;
import com.kevin.instant.mapper.IRoleDao;
import com.kevin.instant.mapper.IUserDao;
import com.kevin.instant.mapper.RoleUserDao;
import com.kevin.instant.service.IUserService;
import com.kevin.instant.util.DateUtil;
import com.kevin.instant.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.List;
import java.util.UUID;

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

    @Autowired(required = false)
    private RoleUserDao roleUserDao;


    @Override
    public User getUserById(String id) {
        User user = userDao.selectById(id);
        return user;
    }

    @Override
    public boolean checkUserIsExistByUserName(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.eq("username", username);
        List<User> list = userDao.selectList(wrapper);
        return list.size() > 0;
    }

    @Override
    public User selectByNameAndPwd(String username, String password) {
        String md5Pwd = MD5.md5(password);
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.eq("username", username);
        wrapper.eq("password", md5Pwd);
        User user = userDao.selectOne(wrapper);
        return user;
    }

    @Override
    public User insertUserNameAndPwd(String username, String password) {
        String md5Pwd = MD5.md5(password);
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername(username);
        user.setPassword(md5Pwd);
        user.setCreateTime(DateUtil.getDefaultDateTime());
        int i = userDao.insert(user);
        RoleUser roleUser = new RoleUser();
        roleUser.setId(UUID.randomUUID().toString());
        roleUser.setUserId(user.getId());
        // 注册的都是普通用户
        roleUser.setRoleCode("1");
        roleUser.setCreateTime(DateUtil.getDefaultDateTime());
        roleUserDao.insert(roleUser);
        if (i > 0) {
            return user;
        }
        return null;
    }
}
