package com.cyb.cleg.service.impl;

import com.cyb.cleg.dao.TeamMapper;
import com.cyb.cleg.dao.TeamMemberMapper;
import com.cyb.cleg.domain.Team;
import com.cyb.cleg.domain.TeamMember;
import com.cyb.cleg.service.TeamServices;
import com.cyb.cleg.utils.MyUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service(value="teamServices")
public class TeamServicesImpl implements TeamServices {

	private static int MAX_USER_TEAM_COUNT = 5;
	
	@Resource
	private TeamMapper teamMapper;

	@Resource
	private TeamMemberMapper teamMemberMapper;

	@Override
	public int deleteByPrimaryKey(String id) {
		return teamMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Team record) {

		Team teamParam = new Team(null, record.getUserId(), null, null);
		List<Team> list = teamMapper.selectSelective(teamParam);
		if(list.size() >= MAX_USER_TEAM_COUNT){
			return -1;
		}else{
			String name = record.getName();
			name += (list.size() + 1) + "对";
			record.setId(MyUtils.getPrimaryKey());
			record.setCreateDate(new Date());
			record.setName(name);
			int addTeam = teamMapper.insert(record);
			if(addTeam > 0){
				TeamMember teamMember = new TeamMember(MyUtils.getPrimaryKey(), record.getId(), record.getUserId(), 1, new Date());
				teamMemberMapper.insert(teamMember);
				return addTeam;
			}
			return 0;
		}
	}

	@Override
	public int insertSelective(Team record) {
		return teamMapper.insertSelective(record);
	}

	@Override
	public Team selectByPrimaryKey(String id) {
		return teamMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Team> selectSelective(Team record) {
		return teamMapper.selectSelective(record);
	}

}
