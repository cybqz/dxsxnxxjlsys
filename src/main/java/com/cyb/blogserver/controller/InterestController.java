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
import com.cyb.forum.common.R;
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
		super.validLogined();
		if(isLogined) {
			Interest interestParam = new Interest(null, currentLoginedUser.getId(), null, null);
			List<Interest> list = interestServices.selectSelective(interestParam);
			if(null != list && list.size() > 0){
				for(Interest interest : list){
					Parames parames = paramesServices.selectByPrimaryKey(interest.getParameId());
					interest.setValue(parames.getValue());
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
		super.validLogined();
		if(isLogined) {
			if(null == interestList || interestList.isEmpty()){
				tips.setMsg("兴趣标签未选择");
			}else{
				R result = interestServices.editUserInterest(currentLoginedUser.getId(), interestList);
				tips.setMsg(result.getMsg());
				tips.setValidate(result.isSuccess());
			}
		}
		return tips;
	}
}
