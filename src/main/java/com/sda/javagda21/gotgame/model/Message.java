package com.sda.javagda21.gotgame.model;


public class Message {

    private String value;

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    private String player;
//    private AppUser user;

//    public AppUser getUser() {
//        return user;
//    }
//
//    public void setUser(AppUser user) {
//        this.user = user;
//    }

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
