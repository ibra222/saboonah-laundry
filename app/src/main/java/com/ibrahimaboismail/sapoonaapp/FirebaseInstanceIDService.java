package com.ibrahimaboismail.sapoonaapp;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.ibrahimaboismail.sapoonaapp.Models.Cart.MainCartResponse;
import com.ibrahimaboismail.sapoonaapp.Utils.Session;
import com.ibrahimaboismail.sapoonaapp.WebService.WebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by d_200 on 1/7/2018.
 */

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        registerToken(Session.getInstance().getUser().getId(),token,Session.getInstance().getUser().getRoot_id());
    }

    private void registerToken(int user_id,String token,int root_id){
       try {
           WebService.getInstance().getApi().adds(user_id,token,root_id).enqueue(new Callback<MainCartResponse>() {
               @Override
               public void onResponse(Call<MainCartResponse> call, Response<MainCartResponse> response) {
                   //Toast.makeText(SignupActivity.this, "ok", Toast.LENGTH_SHORT).show();
               }

               @Override
               public void onFailure(Call<MainCartResponse> call, Throwable t) {

               }
           });
       }catch (NullPointerException e){

       }


//        OkHttpClient client = new OkHttpClient();
//        RequestBody body = new FormBody.Builder()
//                .add("token",token)
//                .add("user_id",)
//                .add("root_id", String.valueOf(Session.getInstance().getUser().getRoot_id()))
//                .build();
//
//        Request request = new Request.Builder()
//                .url("http://192.168.88.70/SaboonahApp/public/api/notifcation/add")
//                .post(body)
//                .build();
//
//        try {
//            client.newCall(request).execute();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
