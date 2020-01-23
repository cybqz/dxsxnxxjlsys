package com.cyb.blogserver.service;

import com.cyb.blogserver.domain.Parames;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ParamesServices {

    int deleteByPrimaryKey(String id);

    int insert(Parames record);

    int insertSelective(Parames record);

    Parames selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Parames record);

    int updateByPrimaryKey(Parames record);

    List<Parames> selectByParames(Parames record);
}