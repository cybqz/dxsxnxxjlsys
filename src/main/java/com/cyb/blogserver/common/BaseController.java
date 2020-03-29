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

    public User currentLoginedUser;

    protected boolean isLogined = false;

    protected Tips tips = null;

    /**
     * 验证用户是否登陆
     */
    public void validLogined(){

        tips = new Tips("success", true, true);
        currentLoginedUser = userValidate.isLoginAuthenticated();
        isLogined = null != currentLoginedUser;
        if(!isLogined){
            tips = new Tips("请先登陆", true, false);
        }
    }
}
