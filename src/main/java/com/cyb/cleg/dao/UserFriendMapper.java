package com.cyb.cleg.dao;

import com.cyb.cleg.domain.UserFriend;

public interface UserFriendMapper {
    int countByExample(UserFriend record);

    int insert(UserFriend record);

    int insertSelective(UserFriend record);
}