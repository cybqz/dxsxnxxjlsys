package com.cyb.blogserver.domain;

import lombok.Data;

import java.util.Date;

@Data
public class ShareObject {
    private String id;

    private String userId;

    private String title;

    private String flag;

    private String discribe;

    private String imgSrc;

    private String price;

    private Integer shared;

    private String authorName;

    private Integer hot;

    private Date createDateTime;

}