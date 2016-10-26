package com.google.firebase.codelab.friendlychat.service;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by ricardo on 6/6/16.
 */
public class APIFCMNotifications extends APIRetrofit {

    private EndPointPushNotifcations apiService;

    public APIFCMNotifications(Map<String,String> headers){
        super(headers);
        this.apiService =  retrofit.create(EndPointPushNotifcations.class);
    }

    public Response<ResponseBody> sendToTopic(BodyMessage body) throws Exception {
        Call<ResponseBody> call = apiService.sendPushToTopic(body);
        return call.execute();
    }


}
