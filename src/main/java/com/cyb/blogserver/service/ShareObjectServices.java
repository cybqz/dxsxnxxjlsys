package com.cyb.blogserver.service;

import com.cyb.blogserver.common.Pagenation;
import com.cyb.blogserver.domain.ShareObject;

import java.util.List;

public interface ShareObjectServices {

    int insert(ShareObject record);

    int insertSelective(ShareObject record);

    int deleteByPrimaryKey(String id);

    ShareObject selectByPrimaryKey(String id);

    List<ShareObject> hotsearch(ShareObject record, Pagenation pagenation);

    List<ShareObject> selectSelective(ShareObject record, Pagenation pagenation);
}
