package com.ibrahimaboismail.sapoonaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ibrahimaboismail.sapoonaapp.Adapters.OrdersListAdapter;
import com.ibrahimaboismail.sapoonaapp.Home.HomeActivity;
import com.ibrahimaboismail.sapoonaapp.Models.Orders.MainOrdersResponse;
import com.ibrahimaboismail.sapoonaapp.Models.Orders.Order;
import com.ibrahimaboismail.sapoonaapp.Utils.Session;
import com.ibrahimaboismail.sapoonaapp.WebService.WebService;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by d_200 on 2/3/2018.
 */

public class MyOrdersActivity extends AppCompatActivity {
    RecyclerView orderlists;
    TextView totalss;
    Button neworder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorders);

        totalss = findViewById(R.id.totalss);
        orderlists = findViewById(R.id.orderlists);

        neworder = findViewById(R.id.neworder);
        neworder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyOrdersActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        WebService.getInstance().getApi().getorders(Session.getInstance().getUser().getId()).enqueue(new Callback<MainOrdersResponse>() {
            @Override
            public void onResponse(Call<MainOrdersResponse> call, Response<MainOrdersResponse> response) {
                if (response.body().getSuccess() == false){
                    new SweetAlertDialog(MyOrdersActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("هناك شي غير صحيح ؟")
                            .setContentText("الرجاء التاكد من الاتصال بشبكة الانترنت")
                            .show();
                }else{
                    List<Order> orders= response.body().getData();
                    orderlists.setLayoutManager(new LinearLayoutManager(MyOrdersActivity.this));
                    orderlists.setAdapter(new OrdersListAdapter(orders,MyOrdersActivity.this));

                    totalss.setText(String.valueOf(response.body().getTotal()));
                }

            }

            @Override
            public void onFailure(Call<MainOrdersResponse> call, Throwable t) {

            }
        });


    }
}
