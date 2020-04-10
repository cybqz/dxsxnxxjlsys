package com.cyb.cleg.controller;

import com.cyb.cleg.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cyb.cleg.domain.User;
import com.cyb.cleg.common.Tips;
import com.cyb.cleg.service.LoginServices;

/**
 * 用户登录控制层
 */
@Controller
@RequestMapping(value="/login")
public class LoginController extends BaseController {
	
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
		super.validLogined();
		if(isLogined){
			tips.setMsg("不能重复登陆");
			return tips;
		}
		return loginServices.login(user);
	}

	/**
	 * 管理员登陆
	 * @param user
	 * @return Tips
	 */
	@RequestMapping(value="/adminlogin")
	@ResponseBody
	public Tips adminLogin (User user) {
		super.validLogined();
		if(isLogined){
			tips.setMsg("不能重复登陆");
			return tips;
		}
		return loginServices.adminLogin(user);
	}

	/**
	 * 登出
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/logout")
	@ResponseBody
	public Tips logout (User user) {
		super.validLogined();
		if(!isLogined){
			return tips;
		}
		return loginServices.logout(user);
	}
}
