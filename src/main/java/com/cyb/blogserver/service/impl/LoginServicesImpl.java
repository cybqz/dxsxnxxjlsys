package com.cyb.blogserver.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.cyb.authority.service.CybAuthorityLoginService;
import com.cyb.blogserver.dao.SigninMapper;
import com.cyb.blogserver.domain.Signin;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import com.cyb.blogserver.dao.UserMapper;
import com.cyb.blogserver.domain.User;
import com.cyb.blogserver.common.Tips;
import com.cyb.blogserver.service.LoginServices;
import com.cyb.blogserver.service.ParamesServices;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;
import com.qq.connect.utils.QQConnectConfig;

@Service(value="loginServices")
public class LoginServicesImpl implements LoginServices {

	@Resource
	private CybAuthorityLoginService authorityLoginService;

	@Override
	public Tips login(User user) {
		Tips tips = new Tips("false", false);
		boolean loginSuccess = authorityLoginService.doLogin(user.getUserName(), user.getPassword());
		if(loginSuccess){
			tips = new Tips("登录成功！", true, user);
		}
		return tips;
	}

	@Override
	public Tips logout(User user) {
		Tips tips = new Tips("false", false);
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()) {
			subject.logout();
			tips = new Tips("退出成功！", true);
		}
		return tips;
	}
}
