package com.cyb.blogserver.domain;

import java.util.Date;

public class TeamMember {
    private String id;

    private String teamId;

    private String userId;

    private Integer isCaptain;

    private Date addTime;

    public TeamMember(String id, String teamId, String userId, Integer isCaptain, Date addTime) {
        this.id = id;
        this.teamId = teamId;
        this.userId = userId;
        this.isCaptain = isCaptain;
        this.addTime = addTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getIsCaptain() {
        return isCaptain;
    }

    public void setIsCaptain(Integer isCaptain) {
        this.isCaptain = isCaptain;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}