package com.ibrahimaboismail.sapoonaapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by d_200 on 1/27/2018.
 */

public class SendProblemActivity extends AppCompatActivity {


    private Context context = SendProblemActivity.this;

    ImageView chat;
    Button sendbut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_problem);

        chat = findViewById(R.id.chat);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        sendbut = findViewById(R.id.sendbut);
        sendbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SweetAlertDialog(SendProblemActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                        .setTitleText("رائع!")
                        .setContentText("تم ارسال رسالتك بنجاح \n"+"وسيتم الرد عليك قريبا")
                        .setCustomImage(R.drawable.saok)
                        .show();
            }
        });
    }



}
