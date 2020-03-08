package com.cyb.blogserver.domain;

import lombok.Data;

import java.util.Date;

@Data
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
}