package com.cyb.blogserver.dao;

import com.cyb.blogserver.domain.UserFriend;

public interface UserFriendMapper {
    int countByExample(UserFriend record);

    int insert(UserFriend record);

    int insertSelective(UserFriend record);
}