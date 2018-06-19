package com.ibrahimaboismail.sapoonaapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ibrahimaboismail.sapoonaapp.Home.HomeActivity;
import com.ibrahimaboismail.sapoonaapp.Models.Admins.Admin;
import com.ibrahimaboismail.sapoonaapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by d_200 on 1/29/2018.
 */

public class AdminsListAdapter extends RecyclerView.Adapter<AdminsListAdapter.AdsListHolder> {
  //  private Urls urls;
    private List<Admin> admins;
    private Context context;

    public AdminsListAdapter(List<Admin> admin, Context context) {
        admins = admin;
        this.context = context;
    }

    @Override
    public AdminsListAdapter.AdsListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.adminlistitems,parent,false);
        AdsListHolder holder = new AdsListHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(AdminsListAdapter.AdsListHolder holder, int position) {
        final Admin admin = admins.get(position);
        holder.int_name.setText(admin.getName());
        Picasso.with(context).load("http://192.168.1.101/www/newAdmin/SaboonahApp/SaboonahApp/public/images/profile/"+ admin.profile.getImag() ).into(holder.thumbnail);

        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HomeActivity.class);
                intent.putExtra("root_id_from_map", admin.getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return admins.size();
    }

     class AdsListHolder extends RecyclerView.ViewHolder{
         TextView int_name;
         ImageView thumbnail;
        public AdsListHolder(View itemView) {
            super(itemView);

            int_name = itemView.findViewById(R.id.name);
            thumbnail = itemView.findViewById(R.id.images);

        }
    }
}


