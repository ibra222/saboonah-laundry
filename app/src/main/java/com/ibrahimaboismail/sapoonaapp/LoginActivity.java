package com.ibrahimaboismail.sapoonaapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fourhcode.forhutils.FUtilsValidation;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.ibrahimaboismail.sapoonaapp.AdminSelcte.SelcteDryClensonMap;
import com.ibrahimaboismail.sapoonaapp.Home.HomeActivity;
import com.ibrahimaboismail.sapoonaapp.Models.Cart.MainCartResponse;
import com.ibrahimaboismail.sapoonaapp.Models.MainSingupResponse;
import com.ibrahimaboismail.sapoonaapp.Models.User;
import com.ibrahimaboismail.sapoonaapp.Utils.Session;
import com.ibrahimaboismail.sapoonaapp.WebService.WebService;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by d_200 on 1/26/2018.
 */

public class LoginActivity extends AppCompatActivity {

    TextView problem;
    RelativeLayout rel7;
    Button login;
    EditText mobile,password;
    String passwordv;
    int mobilev;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Session.getInstance().isUserLoggedIn() == true) {
            Intent intents = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intents);
        } else {

        }
        setContentView(R.layout.activity_login);


        mobile = findViewById(R.id.mobile);
        password = findViewById(R.id.password);

        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!FUtilsValidation.isEmpty(mobile,"ادخل رقم الجوال")
                    && !FUtilsValidation.isEmpty(password,"ادخل كلمة المرور")){
                mobilev = Integer.valueOf(mobile.getText().toString());
                passwordv = password.getText().toString();
                    final SweetAlertDialog pDialog = new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                    pDialog.setTitleText("Loading");
                    pDialog.setCancelable(false);
                    pDialog.show();
                WebService.getInstance().getApi().login(mobilev, passwordv).enqueue(new Callback<MainSingupResponse>() {
                    @Override
                    public void onResponse(Call<MainSingupResponse> call, Response<MainSingupResponse> response) {
                        if (response.body().getSuccess() == false) {
                            pDialog.dismiss();
                            Toast.makeText(LoginActivity.this, response.body().getMessage().get(0).toString(), Toast.LENGTH_SHORT).show();
                        } else {
                            //  Toast.makeText(LoginActivity.this, response.body().getMessage().get(0).toString(), Toast.LENGTH_SHORT).show();
                            //  Toast.makeText(LoginActivity.this, response.body().getData().getIdNumber().toString(), Toast.LENGTH_SHORT).show();
                            pDialog.dismiss();
                            User user = new User();
                            user = response.body().getData();
//                            patient.setIdNumber(Integer.parseInt(response.body().getData().getIdNumber().toString()));
//                            patient.setEmail(response.body().getData().getEmail());
//                            patient.setPassword(response.body().getData().getPassword());
                            Session.getInstance().loginUser(user);
                            FirebaseMessaging.getInstance().subscribeToTopic("test");
                            FirebaseInstanceId.getInstance().getToken();
                            regusertoken(FirebaseInstanceId.getInstance().getToken(),Session.getInstance().getUser().getId(),Session.getInstance().getUser().getRoot_id());
                            Intent intent = new Intent(LoginActivity.this, SelcteDryClensonMap.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<MainSingupResponse> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "يوجد مشكلة في الخادم", Toast.LENGTH_SHORT).show();
                        pDialog.dismiss();
                    }
                });


            }


            }
        });



        problem = findViewById(R.id.problem);
        problem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,ProblemActivity.class);
                startActivity(intent);
            }
        });

        rel7 = findViewById(R.id.rel7);
        rel7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });
    }

    private void regusertoken(String token, int user_id,int root_id){

        WebService.getInstance().getApi().adds(user_id,token,root_id).enqueue(new Callback<MainCartResponse>() {
            @Override
            public void onResponse(Call<MainCartResponse> call, Response<MainCartResponse> response) {
              //  Toast.makeText(LoginActivity.this, "ok", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<MainCartResponse> call, Throwable t) {

            }
        });

    }

}

