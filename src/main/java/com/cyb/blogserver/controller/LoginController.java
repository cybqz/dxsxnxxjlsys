package com.cyb.blogserver.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cyb.blogserver.domain.User;
import com.cyb.blogserver.common.Tips;
import com.cyb.blogserver.service.LoginServices;

/**
 * 用户登录控制层
 */
@Controller
@RequestMapping(value="/login")
public class LoginController {
	
	@Autowired
	private LoginServices loginServices;

	/**
	 * 登陆
	 * @param user
	 * @return Tips
	 */
	@RequestMapping(value="/login")
	@ResponseBody
	public Tips login (User user) {

		return loginServices.login(user);
	}

	/**
	 * 登出
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/logout")
	@ResponseBody
	public Tips logout (User user) {
		return loginServices.logout(user);
	}
}
