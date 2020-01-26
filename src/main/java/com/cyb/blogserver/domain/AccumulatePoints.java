package com.cyb.blogserver.domain;

import java.util.Date;

public class AccumulatePoints {
    private String id;

    private String userId;

    private String parameId;

    private Integer points;

    private Date createDate;

    public AccumulatePoints(String id, String userId, String parameId, Integer points, Date createDate) {
        this.id = id;
        this.userId = userId;
        this.parameId = parameId;
        this.points = points;
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

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}