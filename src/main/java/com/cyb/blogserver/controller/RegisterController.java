package com.cyb.blogserver.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import com.cyb.blogserver.domain.Interest;
import com.cyb.blogserver.domain.Parames;
import com.cyb.blogserver.service.InterestServices;
import com.cyb.blogserver.service.ParamesServices;
import com.cyb.blogserver.utils.MyUtils;
import com.cyb.blogserver.vo.UserCreateVO;
import com.cyb.forum.common.R;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cyb.blogserver.domain.User;
import com.cyb.blogserver.common.Tips;
import com.cyb.blogserver.service.UserServices;

@Controller
@RequestMapping(value="/register")
public class RegisterController {
	
	@Autowired
	private UserServices userSerivces;

	@Autowired
	private InterestServices interestServices;
	
	@RequestMapping(value="/register")
	@ResponseBody
	public Tips register (UserCreateVO userCreate, HttpSession session) {
		Tips tips = new Tips("用户信息不能为空", false);
		if(userCreate != null) {
			String username = userCreate.getUserName();
			int sex = userCreate.getSex();
			if(StringUtils.isNotBlank(username) && StringUtils.isNotBlank(userCreate.getPassword()) &&
					(sex == 0 || sex == 1)) {
				R r = interestServices.validateInterest(userCreate.getInterestList());
				if(r.isSuccess()){
					//检查用户名是否存在
					User userTemp = userSerivces.selectByUserName(username);
					if(null != userTemp) {
						tips.setMsg("用户已存在！");
					}else {
						String url = session.getServletContext().getRealPath("/");
						User user = new User();
						BeanUtils.copyProperties(userCreate, user);
						String userId = UUID.randomUUID().toString();
						user.setId(userId);
						int count = userSerivces.insert(user, url);
						if(count > 0) {
							interestServices.editUserInterest(userId, userCreate.getInterestList());
							tips = new Tips("注册成功", true);
						}
					}
				}else{
					tips.setMsg(r.getMsg());
					tips.setValidate(r.isSuccess());
				}
			}
		}
		return tips;
	}
}
