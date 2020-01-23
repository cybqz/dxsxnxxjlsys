package com.cyb.blogserver.domain;

public class Interest {
    private String id;

    private String userId;

    private String interestCode;

    public Interest(String id, String userId, String interestCode) {
        this.id = id;
        this.userId = userId;
        this.interestCode = interestCode;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getInterestCode() {
        return interestCode;
    }
}