package com.cyb.cleg.dao;

import com.cyb.cleg.domain.TeamMember;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeamMemberMapper {
    int insert(TeamMember record);

    int insertSelective(TeamMember record);

    int deleteByPrimaryKey(String id);

    TeamMember selectByPrimaryKey(String id);

    List<TeamMember> selectSelective(TeamMember record);
}