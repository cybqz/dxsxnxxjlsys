package com.cyb.cleg.controller;

import com.cyb.chat.service.CybTeamChatWSService;
import com.cyb.cleg.common.BaseController;
import com.cyb.cleg.common.Tips;
import com.cyb.cleg.domain.TeamMember;
import com.cyb.cleg.service.TeamMemberServices;
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

	@Autowired
	private CybTeamChatWSService cybTeamChatWSService;
	
	@RequestMapping(value="/add")
	@ResponseBody
	public Tips add (TeamMember teamMember) {
		super.validLogined();
		if(isLogined) {
			tips.setMsg("添加队员失败");
			teamMember.setUserId(currentLoginedUser.getId());
			int result = teamMemberServices.insert(teamMember);
			if(result == -3){
				tips.setMsg("队伍编号不能为空");
			}else if(result == -2){
				tips.setMsg("用户编号不能为空");
			}else if(result == -1){
				tips.setMsg("队伍已满");
			}else if(result == 0){
				tips.setMsg("已经在队伍里了");
			}else if(result > 0){
				cybTeamChatWSService.addRegister(teamMember.getTeamId(), currentLoginedUser.getId(), currentLoginedUser.getUserName());
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
