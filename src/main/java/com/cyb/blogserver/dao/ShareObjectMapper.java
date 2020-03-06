package com.cyb.blogserver.dao;

import com.cyb.blogserver.common.Pagenation;
import com.cyb.blogserver.domain.ShareObject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShareObjectMapper {
    int countByExample(ShareObject shareObject);

    int insert(ShareObject record);

    int insertSelective(ShareObject record);

    ShareObject selectByPrimaryKey(String id);

    List<ShareObject> selectSelective(ShareObject shareObject, Pagenation pagenation);
}