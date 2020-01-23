package com.cyb.blogserver.dao;

import com.cyb.blogserver.domain.Signin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SigninMapper {
    int deleteByPrimaryKey(String id);

    int insert(Signin record);

    int insertSelective(Signin record);

    Signin selectByPrimaryKey(String id);

    Signin selectOneByUserDateTime(Signin record);

    int updateByPrimaryKeySelective(Signin record);

    int updateByPrimaryKey(Signin record);
}