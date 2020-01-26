package com.cyb.blogserver.dao;

import com.cyb.blogserver.domain.AccumulatePoints;

public interface AccumulatePointsMapper {

    int deleteByPrimaryKey(String id);

    int insert(AccumulatePoints record);

    int insertSelective(AccumulatePoints record);

    int updateByPrimaryKeySelective(AccumulatePoints record);

    int updateByPrimaryKey(AccumulatePoints record);

    AccumulatePoints selectByPrimaryKey(String id);
}