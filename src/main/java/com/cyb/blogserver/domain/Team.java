package com.cyb.blogserver.domain;

import java.util.Date;

public class Team {
    private String id;

    private String userId;

    private String name;

    private Date createDate;

    public Team(String id, String userId, String name, Date createDate) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.createDate = createDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}