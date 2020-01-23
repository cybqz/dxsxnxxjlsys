package com.cyb.blogserver.domain;

import java.util.Date;

public class Signin {
    private String id;

    private String userId;

    private Date datetime;

    public Signin(String id, String userId, Date datetime) {
        this.id = id;
        this.userId = userId;
        this.datetime = datetime;
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

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}