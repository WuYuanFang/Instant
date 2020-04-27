package com.kevin.instant.entity;

import java.io.Serializable;

/**
 * blog
 * @author 
 */
public class Blog implements Serializable {
    /**
     * 业务无关主键
     */
    private String id;

    /**
     * 博客标题
     */
    private String title;

    /**
     * 博客内容
     */
    private String content;

    /**
     * 发布者ID
     */
    private String userId;

    /**
     * 分类id
     */
    private String typeId;

    /**
     * 是否需要开启即时功能，即倒计时到了就删除该博客
     */
    private Integer isInstant;

    /**
     * 即刻功能的最终结束时间
     */
    private String instantTime;

    /**
     * 是否已经删除，1：未删除、0：已删除
     */
    private Integer isDelete;

    /**
     * 数据的创建者
     */
    private String createUser;

    /**
     * 数据的创建时间
     */
    private String createTime;

    /**
     * 最后修改数据的用户
     */
    private String lastUpdateUser;

    /**
     * 数据最后被修改的时间
     */
    private String lastUpdateTime;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public Integer getIsInstant() {
        return isInstant;
    }

    public void setIsInstant(Integer isInstant) {
        this.isInstant = isInstant;
    }

    public String getInstantTime() {
        return instantTime;
    }

    public void setInstantTime(String instantTime) {
        this.instantTime = instantTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}