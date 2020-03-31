package com.cyb.blogserver.service;

import com.cyb.blogserver.common.Tips;
import com.cyb.blogserver.domain.Signin;
import com.cyb.blogserver.domain.User;

public interface SigninServices {

    int deleteByPrimaryKey(String id);

    int insert(Signin record);

    int insertSelective(Signin record);

    Signin selectByPrimaryKey(String id);

    Signin selectOneByUserDateTime(Signin record);

    int updateByPrimaryKeySelective(Signin record);

    int updateByPrimaryKey(Signin record);


    /**
     * 签到
     * @return
     */
    public Tips signin(User user);


    boolean isSiginToday(User currentLoginedUser);
}