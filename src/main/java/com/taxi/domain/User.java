package com.taxi.domain;

public class User {
    private long userId;
    private String userName;
    private String userMail;
    private String userPassword;

    public User(long userId, String userName, String userMail, String userPassword) {
        this.userId = userId;
        this.userName = userName;
        this.userMail = userMail;
        this.userPassword = userPassword;
    }

    public User() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
