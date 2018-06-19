package com.ibrahimaboismail.sapoonaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ibrahimaboismail.sapoonaapp.Home.HomeActivity;
import com.ibrahimaboismail.sapoonaapp.Utils.PrefManager;
import com.ibrahimaboismail.sapoonaapp.Utils.Session;

public class MainActivity extends AppCompatActivity {
    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefManager = new PrefManager(this);
        final Thread timer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {

                    if (Session.getInstance().isUserLoggedIn() == false) {
                        Intent intent1 = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent1);
                    } else {
                        Intent intents = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(intents);
                    }

                }
            }
        });
        timer.start();
    }


    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);



    }
//    @Override
//    public void onResume() {
//        super.onResume();
//        SharedPreferences settings = getSharedPreferences("prefs", 0);
//        boolean firstRun = settings.getBoolean("firstRun", true);
//        if (!firstRun) {
////                Intent intent = new Intent(this, WelcomeActivity.class);
////                startActivity(intent);
////                Log.d("TAG1", "firstRun(false): " + Boolean.valueOf(firstRun).toString());
//        } else {
//
//        }
//    }


//    @Override
//    public void onBackPressed() {
//    }
}
