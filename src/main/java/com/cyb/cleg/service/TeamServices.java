package com.cyb.cleg.service;

import com.cyb.cleg.domain.Team;
import com.cyb.common.tips.Tips;

import java.util.List;

public interface TeamServices {

    int deleteByPrimaryKey(String id);

    Tips insert(Team record);

    int insertSelective(Team record);

    Team selectByPrimaryKey(String id);

    List<Team> selectSelective(Team record);

}