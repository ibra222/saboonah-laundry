package com.ibrahimaboismail.sapoonaapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ibrahimaboismail.sapoonaapp.Models.Ads;
import com.ibrahimaboismail.sapoonaapp.OneAdsActivity;
import com.ibrahimaboismail.sapoonaapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by d_200 on 1/29/2018.
 */

public class AdsListAdapter extends RecyclerView.Adapter<AdsListAdapter.AdsListHolder> {
  //  private Urls urls;
    private List<Ads> Ads;
    private Context context;

    public AdsListAdapter(List<com.ibrahimaboismail.sapoonaapp.Models.Ads> ads, Context context) {
        Ads = ads;
        this.context = context;
    }

    @Override
    public AdsListAdapter.AdsListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.ads_item,parent,false);
        AdsListHolder holder = new AdsListHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(AdsListAdapter.AdsListHolder holder, int position) {
        final Ads ads = Ads.get(position);
        holder.int_name.setText(ads.getTitle());
        Picasso.with(context).load("http://192.168.1.101/www/newAdmin/SaboonahApp/SaboonahApp/public/images/adv/"+ ads.getImag()).into(holder.thumbnail);

        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OneAdsActivity.class);
                intent.putExtra("img","http://192.168.1.101/www/newAdmin/SaboonahApp/SaboonahApp/public/images/adv/"+ ads.getImag());
                intent.putExtra("title", ads.getTitle());
                intent.putExtra("des", ads.getDesc());
                intent.putExtra("id", ads.getType());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return Ads.size();
    }

     class AdsListHolder extends RecyclerView.ViewHolder{
         TextView int_name;
         ImageView thumbnail;
        public AdsListHolder(View itemView) {
            super(itemView);

            int_name = itemView.findViewById(R.id.info_text);
            thumbnail = itemView.findViewById(R.id.thumbnail);

        }
    }
}


