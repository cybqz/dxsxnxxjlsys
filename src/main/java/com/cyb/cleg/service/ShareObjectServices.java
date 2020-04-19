package com.cyb.cleg.service;

import com.cyb.cleg.domain.ShareObject;
import com.cyb.common.pagination.Pagination;

import java.util.List;

public interface ShareObjectServices {

    int insert(ShareObject record);

    int insertSelective(ShareObject record);

    int deleteByPrimaryKey(String id);

    ShareObject selectByPrimaryKey(String id);

    List<ShareObject> hotsearch(ShareObject record, Pagination pagination);

    List<ShareObject> selectSelective(ShareObject record, Pagination pagination);
}
