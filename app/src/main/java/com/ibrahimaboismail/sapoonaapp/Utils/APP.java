package com.ibrahimaboismail.sapoonaapp.Utils;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import io.realm.Realm;


/**
 * Created by d_200 on 2/1/2018.
 */

public class APP extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
