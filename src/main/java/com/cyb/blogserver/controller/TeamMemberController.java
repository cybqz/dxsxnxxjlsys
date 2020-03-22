package com.cyb.blogserver.controller;

import com.cyb.blogserver.common.BaseController;
import com.cyb.blogserver.common.Tips;
import com.cyb.blogserver.domain.Team;
import com.cyb.blogserver.domain.TeamMember;
import com.cyb.blogserver.domain.User;
import com.cyb.blogserver.service.TeamMemberServices;
import com.cyb.blogserver.service.TeamServices;
import com.cyb.blogserver.utils.UserValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *队员控制层
 */
@Controller
@RequestMapping(value="/teammember")
public class TeamMemberController extends BaseController {

	@Autowired
	private TeamMemberServices teamMemberServices;
	
	@RequestMapping(value="/add")
	@ResponseBody
	public Tips add (TeamMember teamMember) {
		super.validLogined();
		if(isLogined) {
			tips.setMsg("添加队员失败");
			int result = teamMemberServices.insert(teamMember);
			if(result > 0){
				tips = new Tips("添加队员成功！", true, true);
			}
		}
		return tips;
	}

	@RequestMapping(value="/delete")
	@ResponseBody
	public Tips delete (TeamMember teamMember) {
		super.validLogined();
		if(isLogined) {
			tips.setMsg("删除队员失败");
			int result = teamMemberServices.deleteByPrimaryKey(null);
			if(result > 0){
				tips = new Tips("删除队员成功！", true, true);
			}
		}
		return tips;
	}
}
