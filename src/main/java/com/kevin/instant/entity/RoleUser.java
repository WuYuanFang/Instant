package com.kevin.instant.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * role_user
 * @author 
 */
public class RoleUser implements Serializable {
    /**
     * 业务无关主键
     */
    @TableId("id")
    private String id;

    /**
     * 角色代码的role的code
     */
    @TableField("role_code")
    private String roleCode;

    /**
     * 用户代码的code
     */
    @TableField("user_id")
    private String userId;

    /**
     * 角色名称
     */
    @TableField("role_name")
    private String roleName;

    /**
     * 审核状态：0.未审核、1.审核中、2.已审核
     */
    @TableField("status")
    private Integer status;

    /**
     * 数据的创建者
     */
    @TableField("create_user")
    private String createUser;

    /**
     * 数据的创建时间
     */
    @TableField("create_time")
    private String createTime;

    /**
     * 最后修改数据的用户
     */
    @TableField("last_update_user")
    private String lastUpdateUser;

    /**
     * 数据最后被修改的时间
     */
    @TableField("last_update_time")
    private String lastUpdateTime;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "RoleUser{" +
                "id='" + id + '\'' +
                ", roleCode='" + roleCode + '\'' +
                ", userId='" + userId + '\'' +
                ", roleName='" + roleName + '\'' +
                ", status=" + status +
                ", createUser='" + createUser + '\'' +
                ", createTime='" + createTime + '\'' +
                ", lastUpdateUser='" + lastUpdateUser + '\'' +
                ", lastUpdateTime='" + lastUpdateTime + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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