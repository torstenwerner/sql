package com.westernacher.tutorial.querydsl;

import javax.annotation.Generated;

/**
 * Attribute is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class Attribute {

    private String key;

    private String userid;

    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "key = " + key + ", userid = " + userid + ", value = " + value;
    }

}

