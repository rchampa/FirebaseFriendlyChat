package com.google.firebase.codelab.friendlychat;

/**
 * Created by Ricardo on 25/10/2016.
 */

public class User {

    private String uuid;
    private String token;

    public User() {
    }
    public User(String uuid, String token) {
        this.uuid = uuid;
        this.token = token;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
