package com.google.firebase.codelab.friendlychat.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

/**
 * Created by ricardo on 6/6/16.
 */
public interface EndPointPushNotifcations {

    @GET("fcm/send")
    Call<ResponseBody> sendPushToTopic(@Body BodyMessage body);
}
