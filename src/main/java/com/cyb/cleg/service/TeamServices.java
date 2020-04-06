package com.cyb.cleg.service;

import com.cyb.cleg.domain.Team;

import java.util.List;

public interface TeamServices {

    int deleteByPrimaryKey(String id);

    int insert(Team record);

    int insertSelective(Team record);

    Team selectByPrimaryKey(String id);

    List<Team> selectSelective(Team record);

}