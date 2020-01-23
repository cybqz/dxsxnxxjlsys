package com.cyb.blogserver.dao;

import com.cyb.blogserver.domain.Interest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InterestMapper {
    int deleteByPrimaryKey(String id);

    int insert(Interest record);

    int insertSelective(Interest record);

    Interest selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Interest record);

    int updateByPrimaryKey(Interest record);

    List<Interest> selectSelective(Interest record);
}