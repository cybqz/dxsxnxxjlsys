package com.cyb.cleg.dao;

import com.cyb.cleg.domain.UserRole;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRoleMapper {

    int deleteByPrimaryKey(String id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
}