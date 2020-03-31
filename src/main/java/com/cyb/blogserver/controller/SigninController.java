package com.cyb.blogserver.controller;

import com.cyb.blogserver.common.BaseController;
import com.cyb.blogserver.common.Tips;
import com.cyb.blogserver.domain.*;
import com.cyb.blogserver.service.*;
import com.cyb.blogserver.utils.MyUtils;
import com.cyb.blogserver.vo.RolePermissionVO;
import com.cyb.blogserver.vo.UserRolePermissionVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
	 * 签到
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
