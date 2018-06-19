package com.ibrahimaboismail.sapoonaapp;

import android.content.Context;
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

public class AboutalmjaraActivity extends AppCompatActivity {
     RelativeLayout rel5,facebook,twwetar,youteop;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutalmjara);

        facebook = findViewById(R.id.facebook);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/AlMajaraps"));
                startActivity(browserIntent);
            }
        });
        twwetar = findViewById(R.id.twwetar);
        twwetar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/AlMajaraps"));
                startActivity(browserIntent);
            }
        });

        youteop = findViewById(R.id.youteop);
        youteop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/AlMajaraps"));
                startActivity(browserIntent);
            }
        });

        rel5 = findViewById(R.id.rel5);
        rel5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutalmjaraActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

    }

    public static Intent getOpenFacebookIntent(Context context) {

        try {
            context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
            return new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/F8j6+7fiAvK"));
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/AlMajaraps"));
        }
    }
}
