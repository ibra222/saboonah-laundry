package com.ibrahimaboismail.sapoonaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by d_200 on 1/28/2018.
 */

public class AboutmainActivity extends AppCompatActivity {
    RelativeLayout rel5,rel6;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutmain);

        rel5 = findViewById(R.id.rel5);
        rel5.setClickable(true);
        rel5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutmainActivity.this, AboutalmjaraActivity.class);
                startActivity(intent);
            }
        });
        rel6 = findViewById(R.id.rel6);
        rel6.setClickable(true);
        rel6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutmainActivity.this, SaboonahAboutActivity.class);
                startActivity(intent);
            }
        });


    }
}
