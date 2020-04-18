package com.cyb.cleg.controller;

import com.cyb.authority.base.BaseController;
import com.cyb.cleg.service.*;
import com.cyb.common.tips.Tips;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 签到控制层
 */
@Controller
@RequestMapping(value="/signin")
public class SigninController extends BaseController {

	@Autowired
	private SigninServices signinServices;

	/**
	 * 签到
	 * @return
	 */
	@RequestMapping(value="/signin")
	@ResponseBody
	public Tips signin () {
		super.validLogined();
		if(isLogined){
			return signinServices.signin(currentLoginedUser);
		}
		return tips;
	}

	/**
	 * 签到校验
	 * @return
	 */
	@RequestMapping(value="/validatesignin")
	@ResponseBody
	public Tips validateSignin () {
		super.validLogined();
		if(isLogined){
			boolean isSigninToday = signinServices.isSiginToday(currentLoginedUser);
			tips.setData(isSigninToday);
		}
		return tips;
	}
}
