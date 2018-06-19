package com.ibrahimaboismail.sapoonaapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.ibrahimaboismail.sapoonaapp.Home.HomeActivity;

/**
 * Created by d_200 on 1/28/2018.
 */

public class SaboonahAboutActivity extends AppCompatActivity {

    RelativeLayout facebooks,twwetars,youteops,rel5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saboona_activitty);

        facebooks = findViewById(R.id.youteops);
        facebooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/SABOONAH"));
                startActivity(browserIntent);
            }
        });
        twwetars = findViewById(R.id.twwetars);
        twwetars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/SABOONAH"));
                startActivity(browserIntent);
            }
        });
        youteops = findViewById(R.id.youteops);
        youteops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/SABOONAH"));
                startActivity(browserIntent);
            }
        });

        rel5 = findViewById(R.id.rel5);
        rel5.setClickable(true);
        rel5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntenst = new Intent(SaboonahAboutActivity.this,HomeActivity.class);
                startActivity(browserIntenst);
            }
        });
    }
}
