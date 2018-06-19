package com.ibrahimaboismail.sapoonaapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.ibrahimaboismail.sapoonaapp.Models.MainRatingReqiste;
import com.ibrahimaboismail.sapoonaapp.Utils.Session;
import com.ibrahimaboismail.sapoonaapp.WebService.WebService;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by d_200 on 4/11/2018.
 */

public class RatingActivity extends AppCompatActivity {
    private RatingBar ratingBar;
    private Context mContext = RatingActivity.this;
    private SweetAlertDialog pDialog;
    Button addreitd;
    EditText note;
    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        Intent intent = getIntent();
        intent.getIntExtra("ordernum",0);

        //Toast.makeText(this, String.valueOf(intent.getIntExtra("ordernum",0)), Toast.LENGTH_SHORT).show();
        ratingBar = findViewById(R.id.ratingBar);
        //ratingBar.getNumStars();
        note = findViewById(R.id.note);
        addreitd = findViewById(R.id.addreitd);
        addreitd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(mContext, note.getText().toString(), Toast.LENGTH_SHORT).show();
               // Toast.makeText(mContext, String.valueOf(ratingBar.getNumStars()), Toast.LENGTH_SHORT).show();
                AddReting(ratingBar.getRating(),note.getText().toString());
                //pDialog.dismiss();
            }
        });
    }

    private void AddReting(float ret, String note){
       // Toast.makeText(mContext, "add", Toast.LENGTH_SHORT).show();
        pDialog = new SweetAlertDialog(mContext, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();
        WebService.getInstance().getApi().addriting(
                getIntent().getIntExtra("ordernum",0),
                Session.getInstance().getUser().getId(),
                Session.getInstance().getUser().getUsername(),
                Session.getInstance().getUser().getMobileNumber(),
                Session.getInstance().getUser().getAddress(),
                ret,
                note,
                Session.getInstance().getUser().getRoot_id()).enqueue(new Callback<MainRatingReqiste>() {
            @Override
            public void onResponse(Call<MainRatingReqiste> call, Response<MainRatingReqiste> response) {
                     pDialog.dismiss();
            }

            @Override
            public void onFailure(Call<MainRatingReqiste> call, Throwable t) {

            }
        });
    }
}
