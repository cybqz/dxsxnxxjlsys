package com.cyb.cleg.service;

import com.cyb.cleg.domain.ShareObject;
import com.cyb.common.pagenation.Pagenation;

import java.util.List;

public interface ShareObjectServices {

    int insert(ShareObject record);

    int insertSelective(ShareObject record);

    int deleteByPrimaryKey(String id);

    ShareObject selectByPrimaryKey(String id);

    List<ShareObject> hotsearch(ShareObject record, Pagenation pagenation);

    List<ShareObject> selectSelective(ShareObject record, Pagenation pagenation);
}
