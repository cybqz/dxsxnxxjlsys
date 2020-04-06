package com.cyb.cleg.dao;

import com.cyb.cleg.domain.AccumulatePoints;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface AccumulatePointsMapper {

    int deleteByPrimaryKey(String id);

    int insert(AccumulatePoints record);

    int insertSelective(AccumulatePoints record);

    int updateByPrimaryKeySelective(AccumulatePoints record);

    int updateByPrimaryKey(AccumulatePoints record);

    AccumulatePoints selectByPrimaryKey(String id);

    AccumulatePoints selectOneSelective(@Param("accumulatePoints") AccumulatePoints accumulatePoints,@Param("startDate") Date startDate,@Param("endDate") Date endDate);

    List<Map<String,Object>> selectTopList(@Param("size")Integer size, @Param("userList") List<String> userList);
}