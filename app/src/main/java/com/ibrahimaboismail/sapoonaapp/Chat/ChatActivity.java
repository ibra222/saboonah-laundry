package com.ibrahimaboismail.sapoonaapp.Chat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.fourhcode.forhutils.FUtilsValidation;
import com.google.firebase.messaging.FirebaseMessaging;
import com.ibrahimaboismail.sapoonaapp.Adapters.MessagingAdapter;
import com.ibrahimaboismail.sapoonaapp.Models.ChatRoom.MainChatRoomResponse;
import com.ibrahimaboismail.sapoonaapp.Models.Message.MainMessageResponse;
import com.ibrahimaboismail.sapoonaapp.Models.Message.Message;
import com.ibrahimaboismail.sapoonaapp.R;
import com.ibrahimaboismail.sapoonaapp.Utils.Session;
import com.ibrahimaboismail.sapoonaapp.WebService.WebService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by d_200 on 2/13/2018.
 */

public class ChatActivity extends AppCompatActivity {

    private int room_id = 0;
    private int user_id = 0;
    private int type = 1;
    private String username;
    private MessagingAdapter adapter;
    private List<Message> messages;


    RecyclerView recycler_chat;
    EditText et_message;
    ImageView img_send;

    private BroadcastReceiver messageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // get message sent from broadcast
            Message message = intent.getParcelableExtra("msg");

            // check if message is null
            if (message != null) {
                // add message to messages list
                messages.add(message);
                // notify adapter that new message inserted to list in the last position (list size-1)
                adapter.notifyItemInserted(messages.size() - 1);
                // scroll to bottom of recycler show last message
                recycler_chat.scrollToPosition(messages.size() - 1);
            }

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        et_message = findViewById(R.id.et_message);

        recycler_chat = findViewById(R.id.recycler_chat);
        room_id = getIntent().getIntExtra("room_id",0);
        user_id = Session.getInstance().getUser().getId();
        username = Session.getInstance().getUser().getUsername();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler_chat.setLayoutManager(layoutManager);

        FirebaseMessaging.getInstance().subscribeToTopic("room" + room_id);


        //getMessages(room_id);
        img_send = findViewById(R.id.img_send);
        img_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ;
                if (FUtilsValidation.isEmpty(et_message)){

                }else {


                String contents = et_message.getText().toString();
                  room_id = getIntent().getIntExtra("room_id",0);
                  user_id = Session.getInstance().getUser().getId();
                  username = Session.getInstance().getUser().getUsername();
                Message message = new Message();
                message.setUserId(Session.getInstance().getUser().getId());
                message.setType(1);
                message.setRoomId(room_id);
                message.setUserName(username);
                message.setContent(contents);
                messages.add(message);
                adapter.notifyItemInserted(messages.size() - 1);
                recycler_chat.scrollToPosition(messages.size() - 1);
                et_message.setText("");
                addMessage(user_id,room_id,username,contents,type);

                }
            }
        });


    }

    private void getMessages(int room_id){

        WebService.getInstance().getApi().getmessagetoroom(room_id).enqueue(new Callback<MainMessageResponse>() {
            @Override
            public void onResponse(Call<MainMessageResponse> call, Response<MainMessageResponse> response) {
                messages = response.body().getData();

                    adapter = new MessagingAdapter(messages,ChatActivity.this);
                    recycler_chat.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<MainMessageResponse> call, Throwable t) {

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

    @Override
    protected void onResume() {
        super.onResume();

        getMessages(getIntent().getIntExtra("room_id", 0));
//        //addMessage(0,getIntent().getExtras().getInt("room_id"),"admin","مرحبا بك في تطبيق صابونة.",1);
//
//        //  addMessage(0,room_id,"admin","مرحبا بك في تطبيق صابونة.",1);
//        WebService.getInstance().getApi().chekmsehi(getIntent().getExtras().getInt("room_id")).enqueue(new Callback<Chekhasroom>() {
//            @Override
//            public void onResponse(Call<Chekhasroom> call, Response<Chekhasroom> response) {
//                if (response.body().getIshasroom() == false){
//                    addMessage(0,getIntent().getExtras().getInt("room_id"),"admin","مرحبا بك في تطبيق صابونة.",1);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Chekhasroom> call, Throwable t) {
//
//            }
//        });
        // register receiver to handle "" UPDATE CHAT ACTIVITY "" Filter
        registerReceiver(messageReceiver, new IntentFilter("UpdateChateActivity"));

    }

    @Override
    protected void onStop() {
        super.onStop();
        // unregister receiver
        unregisterReceiver(messageReceiver);
    }


}
