package com.cyb.blogserver.service.impl;

import com.cyb.blogserver.dao.TeamMemberMapper;
import com.cyb.blogserver.domain.TeamMember;
import com.cyb.blogserver.service.TeamMemberServices;
import com.cyb.blogserver.utils.MyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service(value="teamMemberServices")
public class TeamMemberServicesImpl implements TeamMemberServices {

	private static int MAX_USER_TEAM_MEMBER_COUNT = 5;
	
	@Resource
	private TeamMemberMapper teamMemberMapper;

	@Override
	public int deleteByPrimaryKey(String id) {
		return teamMemberMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(TeamMember record) {

		if(StringUtils.isEmpty(record.getTeamId()) || StringUtils.isEmpty(record.getUserId())){
			return -2;
		}

		//判断当前组队队员数量
		TeamMember teamMemberParam = new TeamMember(null, record.getTeamId(), null, null, null);
		List<TeamMember> list = teamMemberMapper.selectSelective(teamMemberParam);
		if(null != list && list.size() >= MAX_USER_TEAM_MEMBER_COUNT){
			return -1;
		}

		//判断当前组队内是否存在该用户
		teamMemberParam = new TeamMember(null, record.getTeamId(), record.getUserId(), null, null);
		list = teamMemberMapper.selectSelective(teamMemberParam);
		if(null != list && list.size() > 0){
			return 0;
		}

		//添加队员
		record.setId(MyUtils.getPrimaryKey());
		record.setAddTime(new Date());
		record.setIsCaptain(0);
		return teamMemberMapper.insert(record);
	}

	@Override
	public int insertSelective(TeamMember record) {
		return teamMemberMapper.insertSelective(record);
	}

	@Override
	public TeamMember selectByPrimaryKey(String id) {
		return teamMemberMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TeamMember> selectSelective(TeamMember record) {
		return teamMemberMapper.selectSelective(record);
	}

}
