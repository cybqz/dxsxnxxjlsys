package com.cyb.cleg.service;

import com.cyb.cleg.domain.Interest;
import com.cyb.common.result.R;

import java.util.List;

public interface InterestServices {

    int deleteByPrimaryKey(String id);

    int insert(Interest record);

    int insertSelective(Interest record);

    Interest selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Interest record);

    int updateByPrimaryKey(Interest record);

    List<Interest> selectSelective(Interest record);

    R validateInterest(List<String> list);

    /**
     * 编辑用户兴趣
     * @param id 用户ID
     * @param interestList 兴趣ID
     * @return
     */
    R editUserInterest(String id, List<String> interestList);
}