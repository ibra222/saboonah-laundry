package com.ibrahimaboismail.sapoonaapp.Adapters;

import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ibrahimaboismail.sapoonaapp.AplayCartActivity;
import com.ibrahimaboismail.sapoonaapp.Models.Item;
import com.ibrahimaboismail.sapoonaapp.Models.MainAddCartResponse;
import com.ibrahimaboismail.sapoonaapp.R;
import com.ibrahimaboismail.sapoonaapp.Utils.Session;
import com.ibrahimaboismail.sapoonaapp.WebService.WebService;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by d_200 on 1/30/2018.
 */

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.ViewHolder> {

    private List<Item> items;
    private Context context;
    private FragmentManager fragmentManager;
    private int co =0;
    private double p = 0;
    private int mType;

    public HorizontalAdapter(Context mC,List items,int type) {
        this.items = items;
        this.context = mC;
        this.mType = type;
    }

    @Override
    public HorizontalAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_horizontal, viewGroup, false);
        return new HorizontalAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HorizontalAdapter.ViewHolder holder, int position) {
        final Item item = items.get(position);
        Picasso.with(context).load("http://192.168.137.60/SaboonahApp/public/images/items/"+ item.getImage()).into(holder.images);
        holder.title.setText(item.getTitle());
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



               // Toast.makeText(context, item.getId().toString(), Toast.LENGTH_SHORT).show();
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.add_to_cart);

                dialog.setTitle("Title...");
                if (mType == 1){
                    p = Double.valueOf(item.getPriceLaundry());
                }
                if (mType == 2){
                    p = Double.valueOf(item.getPricePresser());
                }
                TextView titleitem = dialog.findViewById(R.id.titleitem);
                final TextView pries = dialog.findViewById(R.id.pries);
                pries.setText(String.valueOf(p));
                final TextView total = dialog.findViewById(R.id.total);
                final TextView count = dialog.findViewById(R.id.count);

                ImageView count_m = dialog.findViewById(R.id.count_m);
                count_m.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        co--;
                        count.setText(String.valueOf(co));
                        count.setText(String.valueOf(co));
                        double totalprs = co * p;
                        total.setText(String.valueOf(totalprs));
                    }
                });
                ImageView count_plus = dialog.findViewById(R.id.count_plus);
                count_plus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        co++;
                        count.setText(String.valueOf(co));
                        double totalprs = co * p;
                        total.setText(String.valueOf(totalprs));
                    }
                });

                titleitem.setText(item.getTitle().toString());

                Button aprove_req = dialog.findViewById(R.id.aprove_req);
                aprove_req.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //add to cart

                        WebService.getInstance().getApi().AddItemToCart(item.getId(), Session.getInstance().getUser().getId(),co,2).enqueue(new Callback<MainAddCartResponse>() {
                            @Override
                            public void onResponse(Call<MainAddCartResponse> call, Response<MainAddCartResponse> response) {
                                Intent intent = new Intent(context, AplayCartActivity.class);
                                context.startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<MainAddCartResponse> call, Throwable t) {

                            }
                        });



                        //aprove and delite to cart



                    }
                });

                Button add_to_cart = dialog.findViewById(R.id.add_to_cart);
                add_to_cart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                           // add to cart and dismas dialog

                        WebService.getInstance().getApi().AddItemToCart(item.getId(),Session.getInstance().getUser().getId(),co,2).enqueue(new Callback<MainAddCartResponse>() {
                            @Override
                            public void onResponse(Call<MainAddCartResponse> call, Response<MainAddCartResponse> response) {
                                Toast.makeText(context, response.body().getMessage().get(0).toString(), Toast.LENGTH_SHORT).show();

                                dialog.dismiss();
                            }

                            @Override
                            public void onFailure(Call<MainAddCartResponse> call, Throwable t) {

                            }
                        });
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
       TextView title;
       ImageView images;

        ViewHolder(final View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            images = itemView.findViewById(R.id.images);
        }
    }
}
