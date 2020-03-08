package com.cyb.blogserver.service;

import com.cyb.blogserver.domain.AccumulatePoints;


public interface AccumulatePointsServices {

    int deleteByPrimaryKey(String id);

    int insert(AccumulatePoints record);

    int insertSelective(AccumulatePoints record);

    int updateByPrimaryKeySelective(AccumulatePoints record);

    int updateByPrimaryKey(AccumulatePoints record);

    AccumulatePoints selectByPrimaryKey(String id);

    AccumulatePoints selectOneSelective(AccumulatePoints record);

    /**
     * 增加积分
     * @param userId
     * @param type
     * @return
     */
    int addPoints(String userId, String type);
}