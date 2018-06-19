package com.ibrahimaboismail.sapoonaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by d_200 on 1/28/2018.
 */

public class AdsActivity extends AppCompatActivity {
     Button laundry,presser;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads);

         Intent intent = getIntent();

         if (intent.getIntExtra("idd",1) == 1){
             FragmentManager fragmentManager = getSupportFragmentManager();
             FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
             fragmentTransaction.replace(R.id.conteinar,new LaundryFragment());
             fragmentTransaction.commit();
         }

        if (intent.getIntExtra("idd",1) == 2){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.conteinar,new PresserFragment());
            fragmentTransaction.commit();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.conteinar,new LaundryFragment());
        fragmentTransaction.commit();

        laundry = findViewById(R.id.laundry);
        presser = findViewById(R.id.presser);

        laundry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.conteinar,new LaundryFragment());
                fragmentTransaction.commit();

            }
        });

        presser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.conteinar,new PresserFragment());
                fragmentTransaction.commit();
            }
        });

    }
}
