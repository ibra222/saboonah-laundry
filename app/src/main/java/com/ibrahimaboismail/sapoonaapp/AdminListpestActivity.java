package com.ibrahimaboismail.sapoonaapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ibrahimaboismail.sapoonaapp.Adapters.AdminsListAdapter;
import com.ibrahimaboismail.sapoonaapp.Models.Admins.Admin;
import com.ibrahimaboismail.sapoonaapp.Models.Admins.MainAdminDryClensResponse;
import com.ibrahimaboismail.sapoonaapp.WebService.WebService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminListpestActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_listpest);

        recyclerView = findViewById(R.id.adminlist);

        WebService.getInstance().getApi().alluserAdmin().enqueue(new Callback<MainAdminDryClensResponse>() {
            @Override
            public void onResponse(Call<MainAdminDryClensResponse> call, Response<MainAdminDryClensResponse> response) {
                List<Admin> admins = response.body().getData();
                recyclerView.setLayoutManager(new LinearLayoutManager(AdminListpestActivity.this));
                recyclerView.setAdapter(new AdminsListAdapter(admins,AdminListpestActivity.this));
               // Toast.makeText(AdminListpestActivity.this, admins.get(3).profile.id, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<MainAdminDryClensResponse> call, Throwable t) {

            }
        });


    }


}
