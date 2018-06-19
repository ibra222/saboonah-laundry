package com.ibrahimaboismail.sapoonaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ibrahimaboismail.sapoonaapp.Adapters.CartListAdapter;
import com.ibrahimaboismail.sapoonaapp.Chat.ChatActivity;
import com.ibrahimaboismail.sapoonaapp.Home.HomeActivity;
import com.ibrahimaboismail.sapoonaapp.Models.Cart.Data;
import com.ibrahimaboismail.sapoonaapp.Models.Cart.MainCartResponse;
import com.ibrahimaboismail.sapoonaapp.Models.ChatRoom.Chekhasroom;
import com.ibrahimaboismail.sapoonaapp.Models.ChatRoom.MainChatRoomResponse;
import com.ibrahimaboismail.sapoonaapp.Utils.Session;
import com.ibrahimaboismail.sapoonaapp.WebService.WebService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by d_200 on 2/1/2018.
 */

public class CartShopActivty extends AppCompatActivity {
     RecyclerView recyclerView;
     Button aplaye;
     TextView totalcart;
     ImageView profilemenu,cartlistic,chat;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        totalcart = findViewById(R.id.totalcart);

        recyclerView = findViewById(R.id.orderlist);
        WebService.getInstance().getApi().cart(Session.getInstance().getUser().getId()).enqueue(new Callback<MainCartResponse>() {
            @Override
            public void onResponse(Call<MainCartResponse> call, Response<MainCartResponse> response) {
             //   Toast.makeText(CartShopActivty.this, response.body().getData().get(0).getItems().getTitle().toString(),
                // Toast.LENGTH_SHORT).show();

                totalcart.setText(String.valueOf(response.body().getTotal()));
                List<Data> AdsLa = response.body().getData();
                recyclerView.setLayoutManager(new LinearLayoutManager(CartShopActivty.this));
                recyclerView.setAdapter(new CartListAdapter(AdsLa,CartShopActivty.this));


            }

            @Override
            public void onFailure(Call<MainCartResponse> call, Throwable t) {

            }
        });


        aplaye = findViewById(R.id.aplaye);
        aplaye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartShopActivty.this,SelcteAddressOnMap.class);
                startActivity(intent);
            }
        });

        chat = findViewById(R.id.chat);
        chat.setOnClickListener(new View.OnClickListener() {
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
                                            Intent intent = new Intent(CartShopActivty.this, ChatActivity.class);
                                            intent.putExtra("room_id", response.body().getData().getId());
                                            startActivity(intent);
                                        }

                                        @Override
                                        public void onFailure(Call<MainChatRoomResponse> call, Throwable t) {

                                        }
                                    });
                        }else {
                            Intent intent = new Intent(CartShopActivty.this, ChatActivity.class);
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

        profilemenu = findViewById(R.id.profilemenu);
        profilemenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CartShopActivty.this, HomeActivity.class);
                startActivity(i);
            }
        });

    }
}
