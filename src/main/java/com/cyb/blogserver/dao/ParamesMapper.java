package com.cyb.blogserver.dao;

import com.cyb.blogserver.domain.Parames;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ParamesMapper {

    int deleteByPrimaryKey(String id);

    int insert(Parames record);

    int insertSelective(Parames record);

    Parames selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Parames record);

    int updateByPrimaryKey(Parames record);

    List<Parames> selectByParames(Parames record);
}