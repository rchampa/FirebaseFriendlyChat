package com.google.firebase.codelab.friendlychat;

import android.app.Application;

public class MyApp extends Application {

    private static MyApp INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }

    public static Application getContext(){
        return INSTANCE;
    }
}
