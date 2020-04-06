package com.cyb.cleg.dao;

import com.cyb.cleg.domain.User;

import java.util.Set;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    int deleteByPrimaryKey(String id);

    void insert(User record);

    int updateByPrimaryKey(User record);

	Set<String> queryRolesByName(String userName);

	Set<String> queryPermissionByName(String userName);

    User selectByUserName(String userName);

    User selectByPrimaryKey(String id);
}