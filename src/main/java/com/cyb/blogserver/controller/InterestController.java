package com.cyb.blogserver.controller;

import com.cyb.blogserver.common.Constant;
import com.cyb.blogserver.common.Tips;
import com.cyb.blogserver.domain.Interest;
import com.cyb.blogserver.domain.User;
import com.cyb.blogserver.service.InterestServices;
import com.cyb.blogserver.utils.UserValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/interest")
public class InterestController {

	@Autowired
	private InterestServices interestServices;
	
	@RequestMapping(value="/getUserInterestAll")
	@ResponseBody
	public Tips test () {
		Tips tips = new Tips("false", false);
		UserValidate validate = new UserValidate();
		User user = validate.isLoginAuthenticated();
		if(null != user) {
			Interest interest = new Interest(null, user.getId(), Constant.PARAMES_INTEREST);
			List<Interest> list = interestServices.selectSelective(interest);
			tips = new Tips("true", true, list);
		}
		return tips;
	}
}
