package com.kevin.instant.entity;

/**
 * @program: Instant
 * @create: 2020-04-22 20:28
 * @author: WYF
 * @description:
 * @version: 1.0
 **/

public class UserDetail extends User {

    /**
     * 用户令牌，用于辨识用户，然后进行资源的访问
     */
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserDetail{" +
                "token='" + token + '\'' +
                '}';
    }
}
