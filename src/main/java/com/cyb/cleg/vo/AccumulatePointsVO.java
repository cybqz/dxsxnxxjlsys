package com.cyb.cleg.vo;

import com.cyb.cleg.domain.AccumulatePoints;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;


@Getter
@Setter
public class AccumulatePointsVO extends AccumulatePoints {

    private String name;

    public AccumulatePointsVO() {}

    public AccumulatePointsVO(String id, String userId, String parameId, Integer points, Date updateDate, String name) {
        super(id, userId, parameId, points, updateDate);
        this.name = name;
    }
}