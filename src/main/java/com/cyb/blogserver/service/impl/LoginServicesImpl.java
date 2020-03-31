package com.cyb.blogserver.service.impl;

import javax.annotation.Resource;
import com.alibaba.fastjson.JSONObject;
import com.cyb.authority.service.CybAuthorityLoginService;
import com.cyb.blogserver.common.Constant;
import com.cyb.blogserver.service.AccumulatePointsServices;
import com.cyb.blogserver.service.UserServices;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import com.cyb.blogserver.domain.User;
import com.cyb.blogserver.common.Tips;
import com.cyb.blogserver.service.LoginServices;

@Service(value="loginServices")
public class LoginServicesImpl implements LoginServices {

	@Resource
	private CybAuthorityLoginService authorityLoginService;

	@Resource
	private AccumulatePointsServices accumulatePointsServices;

	@Resource
	private UserServices userServices;

	@Override
	public Tips login(User user) {
		Tips tips = new Tips("登陆失败", false);
		JSONObject loginResult = authorityLoginService.doLogin(user.getUserName(), user.getPassword());
		if(null != loginResult && loginResult.containsKey("authToken")){
			JSONObject result = new JSONObject();
			User resultUser = userServices.selectByUserName(user.getUserName());
			if(null != result){
				accumulatePointsServices.addPoints(resultUser.getId(), Constant.PARAMES_NAME_SIGNIN);
			}

			result.put("userId", resultUser.getId());
			result.put("userName", user.getUserName());
			result.put("authToken", loginResult.getString("authToken"));
			tips = new Tips("登录成功！", true, result);
		}
		return tips;
	}

	@Override
	public Tips logout(User user) {
		Tips tips = new Tips("没有登陆，退出失败", false);
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()) {
			subject.logout();
			tips = new Tips("退出成功！", true);
		}
		return tips;
	}
}
