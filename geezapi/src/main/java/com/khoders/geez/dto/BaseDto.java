package com.khoders.geez.dto;

public class BaseDto {
    private String id;
    private String valueDate;
    private String userAccountId;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getValueDate() {
        return valueDate;
    }
    public void setValueDate(String valueDate) {
        this.valueDate = valueDate;
    }
    public String getUserAccountId() {
        return userAccountId;
    }
    public void setUserAccountId(String userAccountId) {
        this.userAccountId = userAccountId;
    }
}
