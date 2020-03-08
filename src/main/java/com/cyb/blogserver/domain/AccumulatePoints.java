package com.cyb.blogserver.domain;

import lombok.Data;

import java.util.Date;

@Data
public class AccumulatePoints {
    private String id;

    private String userId;

    private String parameId;

    private Integer points;

    private Date updateDate;

    public AccumulatePoints(String id, String userId, String parameId, Integer points, Date updateDate) {
        this.id = id;
        this.userId = userId;
        this.parameId = parameId;
        this.points = points;
        this.updateDate = updateDate;
    }
}