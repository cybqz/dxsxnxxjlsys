package com.cyb.blogserver.service.impl;

import com.cyb.blogserver.dao.InterestMapper;
import com.cyb.blogserver.dao.TeamMemberMapper;
import com.cyb.blogserver.domain.TeamMember;
import com.cyb.blogserver.service.TeamMemberServices;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
