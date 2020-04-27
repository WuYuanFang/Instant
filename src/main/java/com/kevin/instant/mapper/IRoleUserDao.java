package com.kevin.instant.mapper;

import com.kevin.instant.entity.RoleUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IRoleUserDao {
    int deleteByPrimaryKey(String id);

    int insert(RoleUser record);

    int insertSelective(RoleUser record);

    RoleUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RoleUser record);

    int updateByPrimaryKey(RoleUser record);
}