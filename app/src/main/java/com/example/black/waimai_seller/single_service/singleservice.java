package com.example.black.waimai_seller.single_service;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class singleservice {

    private static OkHttpClient okHttpClient;
    private singleservice(){
    }

    public static OkHttpClient getClient(){
        if(okHttpClient == null){
            okHttpClient = new OkHttpClient.Builder ()
                    .connectTimeout(3, TimeUnit.SECONDS)
                    .readTimeout(3, TimeUnit.SECONDS)
                    .build();
        }
        return okHttpClient;
    }
}
