package com.cyb.cleg.service;

import com.cyb.cleg.domain.User;
import com.cyb.cleg.common.Tips;

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