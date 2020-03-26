package com.cyb.blogserver.utils;

import com.cyb.authority.domain.CybAuthorityUser;
import com.cyb.blogserver.service.UserServices;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import com.cyb.blogserver.domain.User;
import com.cyb.blogserver.common.Tips;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserValidate {

	@Autowired
	private UserServices userSerivces;
	
	/**
	 * 获取当前已登录且验证通过的用户信息
	 * @return
	 */
	public User isLoginAuthenticated() {

		Subject subject = SecurityUtils.getSubject();
		User user = null;

		if(subject.isAuthenticated()) {
			CybAuthorityUser cybAuthorityUser = (CybAuthorityUser) subject.getPrincipal();
			return cybAuthorityUserToUser(cybAuthorityUser);
		}
		return null;
	}
	
	/**
	 * 获取当前已登录的用户信息
	 * @return
	 */
	public User isLoginNoAuthenticated() {
		Subject subject = SecurityUtils.getSubject();
		CybAuthorityUser cybAuthorityUser = (CybAuthorityUser) subject.getPrincipal();
		return cybAuthorityUserToUser(cybAuthorityUser);
	}
	
	/**
	 * 验证登录状态、角色、权限
	 * @param tips
	 * @param role
	 * @param permission
	 * @return
	 */
	public User validateAll(Tips tips, String role, String permission) {
		User user = isLoginAuthenticated();
		//登录验证
		if(user != null) {
			Subject subject = SecurityUtils.getSubject();
			if(StringUtils.isNotBlank(role) && !subject.hasRole(role)) {
				tips.setMsg("您没有相关角色支持操作！");
			}else if(StringUtils.isNotBlank(permission) && !subject.isPermitted(permission)) {
				tips.setMsg("您没有相关权限支持操作！");
			}else {
				tips.setValidate(true);
			}
		}else {
			tips.setMsg("请登录后继续操作！");
		}
		return user;
	}

	private User cybAuthorityUserToUser (CybAuthorityUser cybAuthorityUser){
		if(null != cybAuthorityUser){
			return userSerivces.selectByUserName(cybAuthorityUser.getName());
		}
		return null;
	}
}
