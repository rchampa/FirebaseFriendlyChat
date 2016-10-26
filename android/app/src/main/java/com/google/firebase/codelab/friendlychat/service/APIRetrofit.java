package com.google.firebase.codelab.friendlychat.service;

/**
 * Created by ricardo on 6/6/16.
 */

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Ricardo on 06/10/15.
 */
public class APIRetrofit {

    private long TIMEOUT = 30;
    protected Retrofit retrofit;

    public APIRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://fcm.googleapis.com")
//                .baseUrl("http://192.168.2.1:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient(new HashMap<String, String>()))
                .build();

    }

    public APIRetrofit(Map<String, String> headers) {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://fcm.googleapis.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient(headers))
                .build();

    }


    public OkHttpClient getClient(final Map<String, String> headers) {

        SSLContext sslContext = null;
        SSLSocketFactory sslSocketFactory;
        try{
            // Install the all-trusting trust manager
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, getTrustAllCerts(), new java.security.SecureRandom());
        }
        catch(Exception e){

            try {
                sslContext = SSLContext.getInstance("SSL");
                sslContext.init(null, getTrustAllCerts(), new java.security.SecureRandom());
            }
            catch(Exception e1){

            }
        }

        // Create an ssl socket factory with our all-trusting manager
        sslSocketFactory = sslContext.getSocketFactory();

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder builder = original.newBuilder();
                builder.header("User-Agent", "ParlaMI Android");

                for (String key : headers.keySet()) {
                    builder.header(key, headers.get(key));
                }

                builder.method(original.method(), original.body());
                Request request = builder.build();

                return chain.proceed(request);
            }
        });
        httpClient.connectTimeout(TIMEOUT, TimeUnit.SECONDS);
        httpClient.readTimeout(TIMEOUT, TimeUnit.SECONDS);
        httpClient.writeTimeout(TIMEOUT, TimeUnit.SECONDS);
        httpClient.sslSocketFactory(sslSocketFactory);
        httpClient.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });

        OkHttpClient client = httpClient.build();
        return client;


    }


    public Retrofit getRetrofit() {
        return retrofit;
    }

    public TrustManager[] getTrustAllCerts() {

        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new java.security.cert.X509Certificate[]{};
                    }
                }
        };


        return trustAllCerts;
    }


}
