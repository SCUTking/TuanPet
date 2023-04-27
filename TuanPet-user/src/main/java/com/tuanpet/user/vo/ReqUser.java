package com.tuanpet.user.vo;

import java.io.Serializable;

public class ReqUser implements Serializable {
    Integer userId;
    String token;

    public ReqUser(Integer userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "ReqUser{" +
                "userId=" + userId +
                ", token='" + token + '\'' +
                '}';
    }
}
