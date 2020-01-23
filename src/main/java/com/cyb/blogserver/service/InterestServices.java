package com.cyb.blogserver.service;

import com.cyb.blogserver.common.Tips;
import com.cyb.blogserver.domain.Interest;
import com.cyb.blogserver.domain.User;

import java.util.List;
import java.util.Set;

public interface InterestServices {

    int deleteByPrimaryKey(String id);

    int insert(Interest record);

    int insertSelective(Interest record);

    Interest selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Interest record);

    int updateByPrimaryKey(Interest record);

    List<Interest> selectSelective(Interest record);
}