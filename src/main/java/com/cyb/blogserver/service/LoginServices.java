package com.cyb.blogserver.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.cyb.blogserver.domain.User;
import com.cyb.blogserver.common.Tips;

/**
 * 登陆接口
 */
public interface LoginServices {


	/**
	 * 登入
	 * @param user
	 * @return
	 */
	public Tips login(User user);

	/**
	 * 登出
	 * @param user
	 * @return
	 */
	public Tips logout(User user);
}