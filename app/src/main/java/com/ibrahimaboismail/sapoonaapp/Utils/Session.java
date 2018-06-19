package com.ibrahimaboismail.sapoonaapp.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.ibrahimaboismail.sapoonaapp.LoginActivity;
import com.ibrahimaboismail.sapoonaapp.Models.User;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by d_200 on 12/19/2017.
 */

public class Session {
    // define single instance
    private static Session instance;
    // define realm
    private Realm realm;
    private Context context ;

    // Session constructor
    private Session() {

       // Realm realm = Realm.getDefaultInstance();
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        realm = Realm.getInstance(realmConfig);
    }

    // get singletone from session
    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    // get new Instance (new Object) from this class
    public static Session newInstance() {
        return new Session();
    }

    // login user take user and add it to realm
    public static void loginUser(final User user) {
        Realm realm;
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        realm = Realm.getInstance(realmConfig);
        if (realm.where(User.class).equalTo("mobileNumber", user.getMobileNumber()).findFirst() == null) {

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealm(user);
                }
            });

        } else {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.delete(User.class);
                }
            });
            loginUser(user);
        }


    }



    // logout
    public static void logout() {
        Realm realm;
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        realm = Realm.getInstance(realmConfig);
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.delete(User.class);
            }
        });
    }

    public boolean isUserLoggedIn() {
        if (realm.where(User.class).findFirst() == null){
            return false;
        }else {
            return true;
        }

    }

    public User getUser() {
        Realm realm;
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        realm = Realm.getInstance(realmConfig);
        return realm.where(User.class).findFirst();
    }

    public static void logoutAndGoToLogin(Activity activity) {
        logout();
        activity.startActivity(new Intent(activity, LoginActivity.class));
        activity.finish();
    }
}
