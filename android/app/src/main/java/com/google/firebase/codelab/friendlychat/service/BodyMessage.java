package com.google.firebase.codelab.friendlychat.service;

/**
 * Created by Ricardo on 26/10/2016.
 */

public class BodyMessage {

    private String to;
    private Data data;

    public BodyMessage(String to, String message) {
        this.to = to;
        this.data.message = message;
    }

    class Data {
        String message;
    }

}
