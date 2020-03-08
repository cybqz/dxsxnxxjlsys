package com.cyb.blogserver.dao;

import com.cyb.blogserver.common.Pagenation;
import com.cyb.blogserver.domain.ShareObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShareObjectMapper {

    int insert(ShareObject record);

    int insertSelective(ShareObject record);

    int updateHotByPrimaryKey(ShareObject record);

    int countByShareObject(ShareObject shareObject);

    ShareObject selectByPrimaryKey(String id);

    List<ShareObject> hotsearch(@Param("shareObject") ShareObject shareObject,@Param("pagenation") Pagenation pagenation);

    List<ShareObject> selectSelective(@Param("shareObject") ShareObject shareObject,@Param("pagenation") Pagenation pagenation);
}