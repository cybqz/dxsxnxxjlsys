package com.cyb.blogserver.domain;

import java.util.Date;

public class UserFriend {
    private String id;

    private String userId;

    private String userFriendId;

    private Date createDatetime;

    public UserFriend(String id, String userId, String userFriendId, Date createDatetime) {
        this.id = id;
        this.userId = userId;
        this.userFriendId = userFriendId;
        this.createDatetime = createDatetime;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserFriendId() {
        return userFriendId;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }
}