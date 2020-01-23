package com.cyb.blogserver.dao;

import com.cyb.blogserver.domain.Interest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InterestMapper {
    int deleteByPrimaryKey(String id);

    int insert(Interest record);

    int insertSelective(Interest record);

    Interest selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Interest record);

    int updateByPrimaryKey(Interest record);
}