package com.google.firebase.codelab.friendlychat.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by ricardo on 6/6/16.
 */
public interface EndPointPushNotifcations {

    @POST("fcm/send")
    Call<ResponseBody> sendPushToTopic(@Body BodyMessage body);
}
