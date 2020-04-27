package com.kevin.instant.service;

import com.kevin.instant.entity.User;

/**
 * @program: Instant
 * @create: 2020-04-21 11:45
 * @author: WYF
 * @description:
 * @version: 1.0
 **/

public interface IUserService {

    /**
     * 通过用户ID获取用户信息
     * @param id 用户ID
     * @return 用户信息
     */
    public User getUserById(String id);

    /**
     * 判断用户名是否存在
     * @param username 用户名
     * @return 结果
     */
    public boolean checkUserIsExistByUserName(String username);

    /**
     * 通过用户名和密码查询用户信息
     * @param username 用户名
     * @param password 密码
     * @return 用户信息
     */
    public User selectByNameAndPwd(String username, String password);

    /**
     * 添加用户名和密码
     * @param username 用户名
     * @param password 密码
     * @return 用户信息
     */
    public User insertUserNameAndPwd(String username, String password);

    /**
     * 更新用户信息
     * @param user 用户信息
     * @return 结果
     */
    public boolean updateUser(User user);

    /**
     * 添加用户
     * @param user 用户信息
     * @return 结果
     */
    public boolean insertUser(User user);
}
