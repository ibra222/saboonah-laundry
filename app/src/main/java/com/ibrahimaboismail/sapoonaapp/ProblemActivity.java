package com.ibrahimaboismail.sapoonaapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by d_200 on 1/27/2018.
 */

public class ProblemActivity extends AppCompatActivity {

    RelativeLayout rel3,rel4;
    ImageView chat;
    LinearLayout passwordrest;
    private Context context = ProblemActivity.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem);
        setupWidget();
    }


    private void setupWidget(){

        passwordrest = findViewById(R.id.passwordrest);
        passwordrest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SweetAlertDialog(ProblemActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                        .setTitleText("رائع!")
                        .setContentText("ثوانٍ قليلة وستصلك كلمة السر \n" +
                                "برسالة على الجوال")
                        .setCustomImage(R.drawable.passwordrest)
                        .show();
            }
        });

        chat = findViewById(R.id.chat);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rel3 = findViewById(R.id.rel3);
        rel3.setClickable(true);
        rel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,SendProblemActivity.class);
                startActivity(intent);
            }
        });

        rel4 = findViewById(R.id.rel4);
        rel4.setClickable(true);
        rel4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,SignupActivity.class);
                startActivity(intent);
            }
        });

    }
}
