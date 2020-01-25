package com.cyb.blogserver.domain;

import java.util.Date;

public class Interest {

    private String id;

    private String userId;

    private String parameId;

    private Date createDate;

    private Parames parames;

    public Interest(String id, String userId, String parameId, Date createDate) {
        this.id = id;
        this.userId = userId;
        this.parameId = parameId;
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

    public String getParameId() {
        return parameId;
    }

    public void setParameId(String parameId) {
        this.parameId = parameId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Parames getParames() {
        return parames;
    }

    public void setParames(Parames parames) {
        this.parames = parames;
    }
}