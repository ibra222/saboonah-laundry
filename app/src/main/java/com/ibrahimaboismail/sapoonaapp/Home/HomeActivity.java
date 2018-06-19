package com.ibrahimaboismail.sapoonaapp.Home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ibrahimaboismail.sapoonaapp.AboutmainActivity;
import com.ibrahimaboismail.sapoonaapp.Adapters.HomeListAdapter;
import com.ibrahimaboismail.sapoonaapp.Adapters.SectionPagerAdapter;
import com.ibrahimaboismail.sapoonaapp.AdsActivity;
import com.ibrahimaboismail.sapoonaapp.CartShopActivty;
import com.ibrahimaboismail.sapoonaapp.Chat.ChatActivity;
import com.ibrahimaboismail.sapoonaapp.FirebaseInstanceIDService;
import com.ibrahimaboismail.sapoonaapp.FollowusActivity;
import com.ibrahimaboismail.sapoonaapp.Models.Cart.MainCartResponse;
import com.ibrahimaboismail.sapoonaapp.Models.ChatRoom.Chekhasroom;
import com.ibrahimaboismail.sapoonaapp.Models.ChatRoom.MainChatRoomResponse;
import com.ibrahimaboismail.sapoonaapp.Models.ChatRoom.MainGetRoom;
import com.ibrahimaboismail.sapoonaapp.Models.Item;
import com.ibrahimaboismail.sapoonaapp.Models.MainItmeRespons;
import com.ibrahimaboismail.sapoonaapp.MyOrdersActivity;
import com.ibrahimaboismail.sapoonaapp.R;
import com.ibrahimaboismail.sapoonaapp.Utils.PrefManager;
import com.ibrahimaboismail.sapoonaapp.Utils.Session;
import com.ibrahimaboismail.sapoonaapp.WebService.WebService;
import com.yarolegovich.slidingrootnav.SlideGravity;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by d_200 on 1/28/2018.
 */

public class HomeActivity extends AppCompatActivity {

    private FirebaseInstanceIDService firebaseInstanceIDService;
    private static final int ACTIVITY_NUM = 0;
    private Context mContext = HomeActivity.this;
    ImageView close, profilemenu, cartlistic,chat;
    TextView tests;
    TextView myorder, myorders, profile, about, contact, following, ads, loguot;
    RecyclerView recyclerView;
    private PrefManager prefManager;
    private Integer room_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addRoom();

        // Checking for first time launch - before calling setContentView()


        recyclerView = findViewById(R.id.mainlist);

        final SweetAlertDialog pDialog = new SweetAlertDialog(mContext, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();
        WebService.getInstance().getApi().mainitem(getIntent().getIntExtra("root_id",0)).enqueue(new Callback<MainItmeRespons>() {


            @Override
            public void onResponse(Call<MainItmeRespons> call, Response<MainItmeRespons> response) {
                //  Toast.makeText(mContext, "sadfsdf", Toast.LENGTH_SHORT).show();

                  List<Item> items = response.body().getData();
                  recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
                  recyclerView.setAdapter(new HomeListAdapter(items, mContext));

                  pDialog.dismiss();



            }

            @Override
            public void onFailure(Call<MainItmeRespons> call, Throwable t) {
                pDialog.dismiss();
            }
        });


        // setupViewPager();


        final SlidingRootNav mune = new SlidingRootNavBuilder(this)
                .withMenuLayout(R.layout.menu_left_drawer)
                .withGravity(SlideGravity.RIGHT) //If LEFT you can swipe a menu from left to right, if RIGHT - the direction is opposite.
                .withContentClickableWhenMenuOpened(true)
                .inject();

        close = findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mune.closeMenu();
            }
        });

        profilemenu = findViewById(R.id.profilemenu);
        profilemenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mune.openMenu();
            }
        });

        chat = findViewById(R.id.chat);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebService.getInstance().getApi().getRoom(Session.getInstance().getUser().getId()).enqueue(new Callback<MainGetRoom>() {
                    @Override
                    public void onResponse(Call<MainGetRoom> call, Response<MainGetRoom> response) {
                        if (response.body().getSuccess() == false){

                        }else {
                            //Toast.makeText(HomeActivity.this, String.valueOf(response.body().getData().get(0).getId()), Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(mContext, ChatActivity.class);
                            intent.putExtra("room_id", response.body().getData().get(0).getId());
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<MainGetRoom> call, Throwable t) {

                    }
                });
//                Intent intent = new Intent(mContext, ChatActivity.class);
//                intent.putExtra("room_id", room_id);
//                startActivity(intent);
//                WebService.getInstance().getApi().chek(Session.getInstance().getUser().getId()).enqueue(new Callback<Chekhasroom>() {
//                    @Override
//                    public void onResponse(Call<Chekhasroom> call, Response<Chekhasroom> response) {
//                        if (response.body().getIshasroom() == false){
//                            WebService.getInstance().getApi().addroom(Session.getInstance().getUser().getUsername().toString(), "non", Session.getInstance().getUser().getId())
//                                    .enqueue(new Callback<MainChatRoomResponse>() {
//                                        @Override
//                                        public void onResponse(Call<MainChatRoomResponse> call, Response<MainChatRoomResponse> response) {
//                                            Intent intent = new Intent(mContext, ChatActivity.class);
//                                            intent.putExtra("room_id", response.body().getData().getId());
//                                            startActivity(intent);
//                                        }
//
//                                        @Override
//                                        public void onFailure(Call<MainChatRoomResponse> call, Throwable t) {
//
//                                        }
//                                    });
//                        }else {
//                            Intent intent = new Intent(mContext, ChatActivity.class);
//                            intent.putExtra("room_id",response.body().getData().get(0).getId());
//                            startActivity(intent);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<Chekhasroom> call, Throwable t) {
//
//                    }
//                });
            }
        });

        cartlistic = findViewById(R.id.cartlistic);
        cartlistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CartShopActivty.class);
                startActivity(intent);
            }
        });
        setupmenu();
    }

    private void setupmenu() {

        tests = findViewById(R.id.tests);
        myorder = findViewById(R.id.myorder);
        myorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CartShopActivty.class);
                startActivity(intent);
            }
        });

        myorders = findViewById(R.id.myorders);
        myorders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MyOrdersActivity.class);
                startActivity(intent);
            }
        });


        about = findViewById(R.id.about);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AboutmainActivity.class);
                startActivity(intent);
            }
        });
        contact = findViewById(R.id.contact);
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                WebService.getInstance().getApi().getRoom(Session.getInstance().getUser().getId()).enqueue(new Callback<MainGetRoom>() {
                    @Override
                    public void onResponse(Call<MainGetRoom> call, Response<MainGetRoom> response) {
                        if (response.body().getSuccess() == false){

                        }else {
                    //        Toast.makeText(HomeActivity.this, String.valueOf(response.body().getData().get(0).getId()), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(mContext, ChatActivity.class);
                            intent.putExtra("room_id", response.body().getData().get(0).getId());
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<MainGetRoom> call, Throwable t) {

                    }
                });



//                WebService.getInstance().getApi().chek(Session.getInstance().getUser().getId()).enqueue(new Callback<Chekhasroom>() {
//                    @Override
//                    public void onResponse(Call<Chekhasroom> call, Response<Chekhasroom> response) {
//                            if (response.body().getIshasroom() == false){
//                                WebService.getInstance().getApi().addroom(Session.getInstance().getUser().getUsername().toString(), "non", Session.getInstance().getUser().getId())
//                                        .enqueue(new Callback<MainChatRoomResponse>() {
//                                            @Override
//                                            public void onResponse(Call<MainChatRoomResponse> call, Response<MainChatRoomResponse> response) {
//                                                Intent intent = new Intent(mContext, ChatActivity.class);
//                                                intent.putExtra("room_id", response.body().getData().getId());
//                                                startActivity(intent);
//                                            }
//
//                                            @Override
//                                            public void onFailure(Call<MainChatRoomResponse> call, Throwable t) {
//
//                                            }
//                                        });
//                            }else {
//                                Intent intent = new Intent(mContext, ChatActivity.class);
//                                intent.putExtra("room_id",response.body().getData().get(0).getId());
//                                startActivity(intent);
//                            }
//                    }
//
//                    @Override
//                    public void onFailure(Call<Chekhasroom> call, Throwable t) {
//
//                    }
//                });




            }
        });
        following = findViewById(R.id.following);
        following.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, FollowusActivity.class);
                startActivity(intent);
            }
        });
        ads = findViewById(R.id.ads);
        ads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AdsActivity.class);
                startActivity(intent);
            }
        });
        loguot = findViewById(R.id.loguot);
        loguot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Session.logout();
                finish();
            }
        });

    }

    private void setupViewPager() {
        SectionPagerAdapter adapter = new SectionPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PresserHomeFragment());
        adapter.addFragment(new LaundryHomeFragment());
        adapter.addFragment(new LPHomeFragment());
        ViewPager viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("كوي ");
        tabLayout.getTabAt(1).setText("غسيل");
        tabLayout.getTabAt(2).setText("كوي و غسيل");
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    private void regusertoken(String token, int user_id,int root_id) {

        WebService.getInstance().getApi().adds(user_id, token,root_id).enqueue(new Callback<MainCartResponse>() {
            @Override
            public void onResponse(Call<MainCartResponse> call, Response<MainCartResponse> response) {
                //  Toast.makeText(HomeActivity.this, "ok", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<MainCartResponse> call, Throwable t) {

            }
        });

    }




    private void initChat(int room){
        //addMessage(0,getIntent().getExtras().getInt("room_id"),"admin","مرحبا بك في تطبيق صابونة.",1);
        //  addMessage(0,room_id,"admin","مرحبا بك في تطبيق صابونة.",1);
        WebService.getInstance().getApi().chekmsehi(room).enqueue(new Callback<Chekhasroom>() {
            @Override
            public void onResponse(Call<Chekhasroom> call, Response<Chekhasroom> response) {
                if (response.body().getIshasroom() == false){
                    addMessage(Session.getInstance().getUser().getNumberOrders(),room_id,Session.getInstance().getUser().getUsername(),"مرحبا بك في تطبيق ."+Session.getInstance().getUser().getUsername(),1);
                }
            }

            @Override
            public void onFailure(Call<Chekhasroom> call, Throwable t) {

            }
        });

    }

    private void addMessage(int user_id,int room_id, String user_name,String contents,int type){
        WebService.getInstance().getApi().addmessagetoroom(user_id,room_id,user_name,contents,type).enqueue(new Callback<MainChatRoomResponse>() {
            @Override
            public void onResponse(Call<MainChatRoomResponse> call, Response<MainChatRoomResponse> response) {

            }

            @Override
            public void onFailure(Call<MainChatRoomResponse> call, Throwable t) {

            }
        });
    }

    private void addRoom(){
        WebService.getInstance().getApi().chek(Session.getInstance().getUser().getId()).enqueue(new Callback<Chekhasroom>() {
            @Override
            public void onResponse(Call<Chekhasroom> call, Response<Chekhasroom> response) {
                if (response.body().getIshasroom() == false){
                    WebService.getInstance().getApi().addroom(Session.getInstance().getUser().getUsername().toString(), "non", Session.getInstance().getUser().getId())
                            .enqueue(new Callback<MainChatRoomResponse>() {
                                @Override
                                public void onResponse(Call<MainChatRoomResponse> call, Response<MainChatRoomResponse> response) {
                                    room_id = response.body().getData().getId();
                                    initChat(room_id);
                                }

                                @Override
                                public void onFailure(Call<MainChatRoomResponse> call, Throwable t) {

                                }
                            });
                }else {

                }
            }

            @Override
            public void onFailure(Call<Chekhasroom> call, Throwable t) {

            }
        });
    }
}
