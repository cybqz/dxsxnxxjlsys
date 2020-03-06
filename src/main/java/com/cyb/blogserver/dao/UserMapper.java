package com.cyb.blogserver.dao;

import com.cyb.blogserver.domain.User;

import java.util.List;
import java.util.Set;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    int deleteByPrimaryKey(String id);

    int insert(User record);

    int updateByPrimaryKey(User record);

	Set<String> queryRolesByName(String userName);

	Set<String> queryPermissionByName(String userName);

    User selectByUserName(String userName);

    User selectByPrimaryKey(String id);
}