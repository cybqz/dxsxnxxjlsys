package com.cyb.blogserver.common;

import com.cyb.blogserver.domain.User;
import com.cyb.blogserver.utils.UserValidate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 基础控制类
 */
public class BaseController {

    @Autowired
    private UserValidate userValidate;

    public User user;

    /**
     * 验证用户是否登陆
     */
    public void validLogined(){

        user = userValidate.isLoginAuthenticated();
    }
}
