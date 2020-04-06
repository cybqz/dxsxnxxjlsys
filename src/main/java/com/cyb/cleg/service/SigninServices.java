package com.cyb.cleg.service;

import com.cyb.cleg.common.Tips;
import com.cyb.cleg.domain.Signin;
import com.cyb.cleg.domain.User;

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