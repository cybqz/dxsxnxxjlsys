package com.cyb.cleg.dao;

import com.cyb.cleg.domain.Team;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeamMapper {
    int insert(Team record);

    int insertSelective(Team record);

    int deleteByPrimaryKey(String id);

    Team selectByPrimaryKey(String id);

    List<Team> selectSelective(Team record);
}