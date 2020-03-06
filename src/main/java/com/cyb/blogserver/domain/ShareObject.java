package com.cyb.blogserver.domain;

import java.math.BigDecimal;
import java.util.Date;

public class ShareObject {
    private String id;

    private String userId;

    private String title;

    private String flag;

    private String discribe;

    private String imgSrc;

    private BigDecimal price;

    private Integer shared;

    private String authorName;

    private Date createDateTime;

    public ShareObject(String id, String userId, String title, String flag, String discribe, String imgSrc, BigDecimal price, Integer shared, String authorName, Date createDateTime) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.flag = flag;
        this.discribe = discribe;
        this.imgSrc = imgSrc;
        this.price = price;
        this.shared = shared;
        this.authorName = authorName;
        this.createDateTime = createDateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getDiscribe() {
        return discribe;
    }

    public void setDiscribe(String discribe) {
        this.discribe = discribe;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getShared() {
        return shared;
    }

    public void setShared(Integer shared) {
        this.shared = shared;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }
}