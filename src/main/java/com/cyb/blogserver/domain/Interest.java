package com.cyb.blogserver.domain;

import lombok.Data;

import java.util.Date;

@Data
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

}