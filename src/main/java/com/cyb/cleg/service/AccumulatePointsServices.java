package com.cyb.cleg.service;

import com.cyb.cleg.domain.AccumulatePoints;
import com.cyb.cleg.vo.AccumulatePointsVO;

import java.util.List;


public interface AccumulatePointsServices {

    int deleteByPrimaryKey(String id);

    int insert(AccumulatePoints record);

    int insertSelective(AccumulatePoints record);

    int updateByPrimaryKeySelective(AccumulatePoints record);

    int updateByPrimaryKey(AccumulatePoints record);

    AccumulatePoints selectByPrimaryKey(String id);

    List<AccumulatePoints> selectSelective(AccumulatePoints record);

    /**
     * 增加积分
     * @param userId
     * @param type
     * @return
     */
    int addPoints(String userId, String type);

    /**
     * 查询全网前10
     * @return
     */
    List<AccumulatePointsVO> selectSystemTopTen();

    /**
     * 查询好友前10
     * @param userId
     * @return
     */
    List<AccumulatePointsVO> selectFriendsTopTen(String userId);
}