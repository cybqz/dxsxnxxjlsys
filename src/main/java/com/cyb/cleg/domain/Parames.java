package com.cyb.cleg.domain;

import lombok.Data;

@Data
public class Parames {

    private String id;

    private String name;

    private String value;

    private int weight;

    private String group;

    private int order;

    private String remark;

    public Parames(String id, String name, String value, int weight, String group) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.weight = weight;
        this.group = group;
    }

}