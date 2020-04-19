package com.cyb.cleg.service.impl;

import com.cyb.cleg.dao.TeamMapper;
import com.cyb.cleg.dao.TeamMemberMapper;
import com.cyb.cleg.domain.Team;
import com.cyb.cleg.domain.TeamMember;
import com.cyb.cleg.service.TeamServices;
import com.cyb.cleg.utils.MyUtils;
import com.cyb.common.tips.Tips;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service(value="teamServices")
public class TeamServicesImpl implements TeamServices {
	
	@Resource
	private TeamMapper teamMapper;

	@Resource
	private TeamMemberMapper teamMemberMapper;

	@Override
	public int deleteByPrimaryKey(String id) {
		return teamMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Tips insert(Team record) {

		Tips tips = new Tips("新增失败", true, false);
		List<Team> list = teamMapper.selectSelective(record);
		if(null != list && list.size() > 0){
			tips.setMsg("相同队伍已经存在");
		}else{
			record.setId(MyUtils.getPrimaryKey());
			record.setCreateDate(new Date());
			int addTeam = teamMapper.insert(record);
			if(addTeam > 0){
				TeamMember teamMember = new TeamMember(MyUtils.getPrimaryKey(), record.getId(), record.getUserId(), 1, new Date());
				teamMemberMapper.insert(teamMember);
				tips = new Tips("新增成功", true, record);
			}
		}
		return tips;
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
