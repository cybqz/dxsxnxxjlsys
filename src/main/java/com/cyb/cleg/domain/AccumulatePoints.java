package com.cyb.cleg.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AccumulatePoints {
    public String id;

    public String userId;

    public String parameId;

    public Integer points;

    public Date updateDate;

    public AccumulatePoints() {
    }

    public AccumulatePoints(String id, String userId, String parameId, Integer points, Date updateDate) {
        this.id = id;
        this.userId = userId;
        this.parameId = parameId;
        this.points = points;
        this.updateDate = updateDate;
    }
}