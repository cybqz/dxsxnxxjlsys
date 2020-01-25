package com.cyb.blogserver.service;

import com.cyb.blogserver.domain.TeamMember;

import java.util.List;

public interface TeamMemberServices {

    int deleteByPrimaryKey(String id);

    int insert(TeamMember record);

    int insertSelective(TeamMember record);

    TeamMember selectByPrimaryKey(String id);

    List<TeamMember> selectSelective(TeamMember record);

}