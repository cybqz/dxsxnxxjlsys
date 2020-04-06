package com.cyb.cleg.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Signin {
    private String id;

    private String userId;

    private Date datetime;

    public Signin(String id, String userId, Date datetime) {
        this.id = id;
        this.userId = userId;
        this.datetime = datetime;
    }

}