package com.cyb.blogserver.controller;

import com.cyb.blogserver.common.BaseController;
import com.cyb.blogserver.common.Constant;
import com.cyb.blogserver.common.Tips;
import com.cyb.blogserver.domain.Interest;
import com.cyb.blogserver.domain.Parames;
import com.cyb.blogserver.domain.User;
import com.cyb.blogserver.service.InterestServices;
import com.cyb.blogserver.service.ParamesServices;
import com.cyb.blogserver.utils.UserValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/interest")
public class InterestController extends BaseController {

	@Autowired
	private InterestServices interestServices;

	@Autowired
	private ParamesServices paramesServices;

	/**
	 * 获取用户兴趣
	 * @return
	 */
	@RequestMapping(value="/getUserInterestAll")
	@ResponseBody
	public Tips getUserInterestAll () {
		Tips tips = new Tips("false", false);
		super.validLogined();
		if(null != user) {
			Interest interestParam = new Interest(null, user.getId(), null, null);
			List<Interest> list = interestServices.selectSelective(interestParam);
			if(null != list && list.size() > 0){
				for(Interest interest : list){
					Parames parames = paramesServices.selectByPrimaryKey(interest.getParameId());
					interest.setParames(parames);
				}
			}
			tips = new Tips("true", true, list);
		}
		return tips;
	}

	/**
	 * 编辑用户兴趣
	 * @param interestList
	 * @return
	 */
	@RequestMapping(value="/editUserInterest")
	@ResponseBody
	public Tips editUserInterest (@RequestParam(value = "interestList", required = true) List<String> interestList) {
		Tips tips = new Tips("保存兴趣失败！", false);
		super.validLogined();
		if(null != user) {
			boolean result = interestServices.editUserInterest(user.getId(), interestList);
			if(result){
				tips = new Tips("保存兴趣成功！", true);
			}
		}
		return tips;
	}
}
