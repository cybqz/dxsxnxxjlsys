package com.cyb.cleg.controller;

import com.alibaba.fastjson.JSONObject;
import com.cyb.authority.base.BaseController;
import com.cyb.authority.domain.User;
import com.cyb.authority.service.LoginService;
import com.cyb.cleg.common.Constant;
import com.cyb.cleg.service.AccumulatePointsServices;
import com.cyb.common.tips.Tips;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;

/**
 * 用户登录控制层
 */
@Controller
@RequestMapping(value="/login")
public class LoginController extends BaseController {
	
	@Autowired
	private LoginService loginService;

	@Autowired
	private AccumulatePointsServices accumulatePointsServices;

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

		Tips tips = loginService.login(user);
		if(tips.isValidate()){
			JSONObject result = (JSONObject) tips.getData();
			accumulatePointsServices.addPoints(result.getString("userId"), Constant.PARAMES_NAME_SIGNIN);
		}
		return tips;
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
		return loginService.adminLogin(user, Constant.ROLE_ADMIN);
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
		return loginService.logout(user);
	}
}
