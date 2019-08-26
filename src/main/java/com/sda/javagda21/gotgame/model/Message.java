package com.sda.javagda21.gotgame.model;


import com.sda.javagda21.gotgame.config.AppUser;

public class Message {

    private String value;

    private AppUser user;

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public Message(String message) {
        this.value = message;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Message() {
    }
}
