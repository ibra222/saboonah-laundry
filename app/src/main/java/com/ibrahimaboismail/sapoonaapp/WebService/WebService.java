package com.ibrahimaboismail.sapoonaapp.WebService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by d_200 on 12/18/2017.
 */

public class WebService {
    private static WebService instance;
    private API api;

    public WebService(){


        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder().client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.1.101/www/newAdmin/SaboonahApp/SaboonahApp/public/api/")
                .build();
        api = retrofit.create(API.class);

    }

    public static WebService getInstance(){
        if (instance == null){
            instance = new WebService();
        }
  
        return instance;
    }

    public API getApi(){
        return api;
    }




}
