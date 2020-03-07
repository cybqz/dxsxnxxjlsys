package com.cyb.blogserver.controller;

import com.cyb.blogserver.common.BaseController;
import com.cyb.blogserver.common.Tips;
import com.cyb.blogserver.domain.Team;
import com.cyb.blogserver.domain.User;
import com.cyb.blogserver.service.TeamServices;
import com.cyb.blogserver.utils.UserValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 *组队控制层
 */
@Controller
@RequestMapping(value="/team")
public class TeamController extends BaseController {

	@Autowired
	private TeamServices teamServices;
	
	@RequestMapping(value="/add")
	@ResponseBody
	public Tips add () {
		Tips tips = new Tips("添加组队失败！", true, false);
		super.validLogined();
		if(null != user) {
			Team team = new Team(null, user.getId(), user.getUserName(), null);
			int result = teamServices.insert(team);
			if(result > 0){
				tips = new Tips("添加组队成功！", true, true);
			}
		}
		return tips;
	}

	@RequestMapping(value="/delete")
	@ResponseBody
	public Tips delete (String id) {
		Tips tips = new Tips("删除组队失败！", true, false);
		super.validLogined();
		if(null != user) {
			int result = teamServices.deleteByPrimaryKey(id);
			if(result > 0){
				tips = new Tips("删除组队成功！", true, true);
			}
		}
		return tips;
	}
}
