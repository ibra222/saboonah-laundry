package com.ibrahimaboismail.sapoonaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by d_200 on 2/24/2018.
 */

public class OneAdsActivity extends AppCompatActivity{
     ImageView imgeAds;
     TextView titleads;
     Button laundry,presser;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oneads);

        imgeAds = findViewById(R.id.imgeAds);
        titleads = findViewById(R.id.titleads);
        laundry = findViewById(R.id.laundry);
        presser = findViewById(R.id.presser);

        final Intent intent = getIntent();
        intent.hasExtra("img");
        titleads.setText(intent.getStringExtra("des"));
        Picasso.with(this).load(intent.getStringExtra("img")).into(imgeAds);

        if (intent.getStringExtra("id") == "1" ){
            laundry.setBackgroundResource(R.drawable.fragmintbac);
        }else if (intent.getStringExtra("id") == "2"){
            presser.setBackgroundResource(R.drawable.fragmintbac);
        }

        laundry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OneAdsActivity.this,AdsActivity.class);
                intent.putExtra("idd", 1);
                startActivity(intent);
            }
        });

        presser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OneAdsActivity.this,AdsActivity.class);
                intent.putExtra("idd", 2);
                startActivity(intent);
            }
        });
    }
}
