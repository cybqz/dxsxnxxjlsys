package com.cyb.cleg.service.impl;

import javax.annotation.Resource;
import com.alibaba.fastjson.JSONObject;
import com.cyb.authority.service.CybAuthorityLoginService;
import com.cyb.cleg.common.Constant;
import com.cyb.cleg.dao.RoleMapper;
import com.cyb.cleg.dao.UserRoleMapper;
import com.cyb.cleg.domain.Role;
import com.cyb.cleg.domain.UserRole;
import com.cyb.cleg.service.AccumulatePointsServices;
import com.cyb.cleg.service.UserRoleServices;
import com.cyb.cleg.service.UserServices;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import com.cyb.cleg.domain.User;
import com.cyb.cleg.common.Tips;
import com.cyb.cleg.service.LoginServices;

import java.util.List;

@Service(value="loginServices")
public class LoginServicesImpl implements LoginServices {

	@Resource
	private CybAuthorityLoginService authorityLoginService;

	@Resource
	private AccumulatePointsServices accumulatePointsServices;

	@Resource
	private UserServices userServices;

	@Resource
	private RoleMapper roleMapper;

	@Resource
	private UserRoleMapper userRoleMapper;

	@Override
	public Tips login(User user) {
		Tips tips = new Tips("登陆失败", false);
		JSONObject result = commonLogin(user);
		if(null != result){
			tips = new Tips("登录成功！", true, result);
		}
		return tips;
	}

	@Override
	public Tips adminLogin(User user){
		Tips tips = new Tips("登陆失败", false);

		if(StringUtils.isEmpty(user.getUserName())){
			tips.setMsg("用户名不能为空");
		}else{
			User userTemp = userServices.selectByUserName(user.getUserName());
			if(null != userTemp){
				List<UserRole> userRoleList = userRoleMapper.selectByUserId(userTemp.getId());
				if(null != userRoleList && !userRoleList.isEmpty()){
					for(UserRole userRole : userRoleList){

						Role role = roleMapper.selectByPrimaryKey(userRole.getRoleId());
						if(null != role && role.getName().equals(Constant.ROLE_ADMIN)){

							JSONObject result = commonLogin(user);
							if(null != result){
								tips = new Tips("登录成功！", true, result);
								return tips;
							}
						}
					}
				}
			}
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

	private JSONObject commonLogin(User user){
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
			return result;
		}
		return null;
	}
}
