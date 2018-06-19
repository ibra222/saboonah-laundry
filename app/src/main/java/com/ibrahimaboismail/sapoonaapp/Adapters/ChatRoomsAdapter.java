package com.ibrahimaboismail.sapoonaapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.ibrahimaboismail.sapoonaapp.Models.ChatRoom.Room;

import java.util.List;

/**
 * Created by d_200 on 2/13/2018.
 */

public class ChatRoomsAdapter extends RecyclerView.Adapter<ChatRoomsAdapter.ChatRoomHolder> {

    // define list and context
    private List<Room> chatRooms;
    private Context context;

    // constructor
    public ChatRoomsAdapter(List<Room> chatRooms, Context context) {

        this.chatRooms = chatRooms;
        this.context = context;
    }

    @Override
    public ChatRoomsAdapter.ChatRoomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ChatRoomsAdapter.ChatRoomHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ChatRoomHolder extends RecyclerView.ViewHolder {
        // declare views using butter knife


        public ChatRoomHolder(View itemView) {
            super(itemView);

        }
    }
}
