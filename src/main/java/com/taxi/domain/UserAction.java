package com.taxi.domain;

public class UserAction {
    private int id;
    private User user;
    private Action action;

    public UserAction() {
    }

    public UserAction(User user, Action action) {
        this.user = user;
        this.action = action;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
