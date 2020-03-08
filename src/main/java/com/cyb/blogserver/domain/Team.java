package com.cyb.blogserver.domain;

import lombok.Data;

import java.util.Date;

@Data
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

}