package com.cyb.blogserver.service;

import com.cyb.blogserver.common.Pagenation;
import com.cyb.blogserver.domain.ShareObject;

import java.util.List;

public interface ShareObjectServices {

    int insert(ShareObject record);

    int insertSelective(ShareObject record);

    ShareObject selectByPrimaryKey(String id);

    List<ShareObject> selectSelective(ShareObject record, Pagenation pagenation);
}
