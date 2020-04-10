package com.cyb.cleg.utils;

import com.cyb.authority.domain.CybAuthorityUser;
import com.cyb.cleg.service.UserServices;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import com.cyb.cleg.domain.User;
import com.cyb.cleg.common.Tips;
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
		if(subject.isAuthenticated()) {
			CybAuthorityUser cybAuthorityUser = (CybAuthorityUser) subject.getPrincipal();
			return cybAuthorityUserToUser(cybAuthorityUser);
		}
		return null;
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
				tips.setValidate(false);
				tips.setMsg("您没有相关角色支持操作！");
			}else if(StringUtils.isNotBlank(permission) && !subject.isPermitted(permission)) {
				tips.setValidate(false);
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
