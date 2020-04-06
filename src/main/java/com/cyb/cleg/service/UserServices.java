package com.cyb.cleg.service;

import com.cyb.cleg.common.Tips;
import com.cyb.cleg.domain.User;

public interface UserServices {

    int deleteByPrimaryKey(String id);

    int insert(User record, String basePath);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUserName(String username);

    /**
     * 签到
     * @return
     */
    public Tips signin(User user);
}