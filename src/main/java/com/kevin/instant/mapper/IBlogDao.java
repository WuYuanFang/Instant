package com.kevin.instant.mapper;

import com.kevin.instant.entity.Blog;

public interface IBlogDao {
    int deleteByPrimaryKey(String id);

    int insert(Blog record);

    int insertSelective(Blog record);

    Blog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKey(Blog record);
}