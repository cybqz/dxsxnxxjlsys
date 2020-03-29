package com.cyb.blogserver.vo;

import com.cyb.blogserver.domain.Team;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TeamVO extends Team {

    private boolean isMyCreated = false;

    private boolean joined = false;

    public TeamVO() {}

    public TeamVO(String id, String userId, String name, Date createDate, boolean isMyCreated, boolean joined) {
        super(id, userId, name, createDate);
        this.isMyCreated = isMyCreated;
        this.joined = joined;
    }
}