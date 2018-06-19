package com.ibrahimaboismail.sapoonaapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.fourhcode.forhutils.FUtilsProgress;
import com.fourhcode.forhutils.FUtilsValidation;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ibrahimaboismail.sapoonaapp.Chat.ChatActivity;
import com.ibrahimaboismail.sapoonaapp.Home.HomeActivity;
import com.ibrahimaboismail.sapoonaapp.Models.ChatRoom.Chekhasroom;
import com.ibrahimaboismail.sapoonaapp.Models.ChatRoom.MainChatRoomResponse;
import com.ibrahimaboismail.sapoonaapp.Models.MainAddCartResponse;
import com.ibrahimaboismail.sapoonaapp.Utils.Session;
import com.ibrahimaboismail.sapoonaapp.WebService.WebService;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import android.icu.util.Calendar;

/**
 * Created by d_200 on 2/1/2018.
 */

public class AplayCartActivity extends AppCompatActivity implements
        View.OnClickListener,OnMapReadyCallback {
    private static final String TAG = "AplayCartActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;
    private Context mContext = AplayCartActivity.this;
    TextView timeDelivery, dateDelivery,dateReceipt,timeReceipt;
    Button aplaye,edit;
    RelativeLayout pross;
    private MapView mapView;
    private GoogleMap gmap;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private Context context = AplayCartActivity.this;
    Button map;
    EditText subaddress;
    TextView tests;
    ImageView notifications,cartlistic,chat,profilemenu;
    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aplaye_cart);
        initwed();
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);

        subaddress = findViewById(R.id.subaddress);

        if (isServisesOk()){
           // init();
        }

       // Toast.makeText(context, getIntent().getStringExtra("address").toString(), Toast.LENGTH_LONG).show();
      //  init();
        pross = findViewById(R.id.pross);
        aplaye = findViewById(R.id.aplaye);
        timeDelivery = findViewById(R.id.timeDelivery);
        dateDelivery = findViewById(R.id.dateDelivery);
        dateReceipt  = findViewById(R.id.dateReceipt);
        timeReceipt  = findViewById(R.id.timeReceipt);

        timeDelivery.setOnClickListener(this);
        dateDelivery.setOnClickListener(this);
        dateReceipt.setOnClickListener(this);
        timeReceipt.setOnClickListener(this);
        aplaye.setOnClickListener(this);

    }




    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {

        if (v == dateReceipt) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            dateReceipt.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == dateDelivery) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            dateDelivery.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == timeReceipt) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            timeReceipt.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }

        if (v == timeDelivery) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            timeDelivery.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }

        if(v == aplaye){


                FUtilsValidation.isEmpty(subaddress,"ادخل العنوان الفرعي");


                FUtilsProgress.newProgress(context,pross);
                WebService.getInstance().getApi().approval_request(Session.getInstance().getUser().getId(),Session.getInstance().getUser().getNumberOrders(),getIntent().getStringExtra("address").toString()+subaddress.getText().toString(),
                        timeReceipt.getText().toString()+" "+dateReceipt.getText().toString(),
                        timeDelivery.getText().toString()+" "+dateDelivery.getText().toString() ).enqueue(new Callback<MainAddCartResponse>() {
                    @Override
                    public void onResponse(Call<MainAddCartResponse> call, Response<MainAddCartResponse> response) {
                        Toast.makeText(context, response.body().getMessage().get(0).toString(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AplayCartActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<MainAddCartResponse> call, Throwable t) {
                        Toast.makeText(context, "sdfsdfsd", Toast.LENGTH_SHORT).show();
                    }
                });




        }

        if (v == edit){

            finish();

        }
    }


    private void initwed(){
        tests = findViewById(R.id.tests);
        tests.setVisibility(View.GONE);

        notifications = findViewById(R.id.notifications);
        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebService.getInstance().getApi().chek(Session.getInstance().getUser().getId()).enqueue(new Callback<Chekhasroom>() {
                    @Override
                    public void onResponse(Call<Chekhasroom> call, Response<Chekhasroom> response) {
                        if (response.body().getIshasroom() == false){
                            WebService.getInstance().getApi().addroom(Session.getInstance().getUser().getUsername().toString(), "non", Session.getInstance().getUser().getId())
                                    .enqueue(new Callback<MainChatRoomResponse>() {
                                        @Override
                                        public void onResponse(Call<MainChatRoomResponse> call, Response<MainChatRoomResponse> response) {
                                            Intent intent = new Intent(mContext, ChatActivity.class);
                                            intent.putExtra("room_id", response.body().getData().getId());
                                            startActivity(intent);
                                        }

                                        @Override
                                        public void onFailure(Call<MainChatRoomResponse> call, Throwable t) {

                                        }
                                    });
                        }else {
                            Intent intent = new Intent(mContext, ChatActivity.class);
                            intent.putExtra("room_id",response.body().getData().get(0).getId());
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<Chekhasroom> call, Throwable t) {

                    }
                });
            }
        });

        cartlistic = findViewById(R.id.cartlistic);
        cartlistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,CartShopActivty.class);
                startActivity(intent);
            }
        });

        profilemenu = findViewById(R.id.profilemenu);
        profilemenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,HomeActivity.class);
                startActivity(intent);
            }
        });

    }

    public boolean isServisesOk(){
        Log.d(TAG,"isServisesOk : checkeing google servises version");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(AplayCartActivity .this);

        if (available == ConnectionResult.SUCCESS){
            Log.d(TAG,"isServisesOk: Google Play Services is working ");
            return true;
        }

        else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            Log.d(TAG,"isServisesOk: an error occured but we canfix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(AplayCartActivity.this,available,ERROR_DIALOG_REQUEST);
            dialog.show();
        }else {
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle);
        }

        mapView.onSaveInstanceState(mapViewBundle);
    }
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }
    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }
    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmap = googleMap;
        gmap.setMinZoomPreference(15);
        LatLng ny = new LatLng(getIntent().getDoubleExtra("lat",0.525), getIntent().getDoubleExtra("lon",0.0225));
        gmap.moveCamera(CameraUpdateFactory.newLatLng(ny));
        MarkerOptions options = new MarkerOptions()
                .position(ny)
                .title(getIntent().getStringExtra("address"));
        gmap.addMarker(options);
    }


}
