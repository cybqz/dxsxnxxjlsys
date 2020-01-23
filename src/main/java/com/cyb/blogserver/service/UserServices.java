package com.cyb.blogserver.service;

import java.util.List;
import java.util.Set;

import com.cyb.blogserver.common.Tips;
import org.apache.ibatis.annotations.Param;
import com.cyb.blogserver.domain.User;

public interface UserServices {

    int deleteByPrimaryKey(String id);

    int insert(User record, String basePath);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	Set<String> queryRolesByName(String userName);

	Set<String> queryPermissionByName(String userName);

    User selectByUserName(String username);

    /**
     * 签到
     * @return
     */
    public Tips signin();
}