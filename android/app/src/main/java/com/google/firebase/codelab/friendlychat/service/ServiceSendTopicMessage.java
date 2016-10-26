package com.google.firebase.codelab.friendlychat.service;

import android.app.IntentService;
import android.content.Intent;

import com.google.firebase.codelab.friendlychat.CodelabPreferences;
import com.google.firebase.codelab.friendlychat.MyFirebaseInstanceIdService;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by ricardo on 6/6/16.
 */
public class ServiceSendTopicMessage extends IntentService {

    public static final String ACTION_OK = "es.ric.ServiceAvatar.OK";
    public static final String ACTION_ERROR = "es.ric.ServiceAvatar.error";
    public static final String ACTION_ENTYTY_NOT_FOUND = "es.ric.ServiceAvatar.entity.not.found.error";

    public static String MESSAGE = "message";



    public ServiceSendTopicMessage() {
        super("ServiceAvatar");
    }

    @Override
    protected void onHandleIntent(Intent myIntent) {

        if (myIntent != null && myIntent.getExtras() != null) {

            String message = myIntent.getExtras().getString(MESSAGE);

            Intent bcIntent = new Intent();

            Map<String,String> headers = new HashMap<>();
            headers.put("Authorization", "key=AIzaSyDpFONgSXBE26WAT_aRAoJw7VBuxK4ZaGI");
            APIFCMNotifications api = new APIFCMNotifications(headers);
            try{

                String topic = "/topics/"+MyFirebaseInstanceIdService.FRIENDLY_ENGAGE_TOPIC;
                Response<ResponseBody> response = api.sendToTopic(new BodyMessage(topic,message));
                if(response.isSuccessful()){

                }

            }
            catch(Exception e){
                bcIntent.setAction(ACTION_ERROR);
            }

//            sendBroadcast(bcIntent);

        }
    }
}
