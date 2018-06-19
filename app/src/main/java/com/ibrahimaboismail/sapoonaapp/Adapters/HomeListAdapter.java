package com.ibrahimaboismail.sapoonaapp.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.ibrahimaboismail.sapoonaapp.Models.Item;
import com.ibrahimaboismail.sapoonaapp.Models.MainAddCartResponse;
import com.ibrahimaboismail.sapoonaapp.R;
import com.ibrahimaboismail.sapoonaapp.SelcteAddressOnMap;
import com.ibrahimaboismail.sapoonaapp.Utils.Session;
import com.ibrahimaboismail.sapoonaapp.WebService.Urls;
import com.ibrahimaboismail.sapoonaapp.WebService.WebService;
import com.squareup.picasso.Picasso;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by d_200 on 1/29/2018.
 */

public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.HomeListHolder> {
    private Urls urls;
    private List<Item> Item;
    private Context context;
    private int co =0;
    private double p = 0;
    private int mType = 0;

    public HomeListAdapter(List<Item> items, Context context) {
        Item = items;
        this.context = context;
    }

    @Override
    public HomeListAdapter.HomeListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_items,parent,false);
        HomeListHolder holder = new HomeListHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(HomeListAdapter.HomeListHolder holder, int position) {
        final Item item = Item.get(position);
        holder.title.setText(item.getTitle());
        holder.butintercart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               //  Toast.makeText(context, item.getPriceLaundry().toString(), Toast.LENGTH_SHORT).show();
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.add_to_cartv1);

                final CheckBox jasel = dialog.findViewById(R.id.jasel);
                final CheckBox kaoe = dialog.findViewById(R.id.kaoe);
                final CheckBox alltype = dialog.findViewById(R.id.alltype);
                dialog.setTitle("Title...");

                if (item.getType() == 1){

                    kaoe.setVisibility(View.GONE);
                    alltype.setVisibility(View.GONE);
                    jasel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (((CheckBox) v).isChecked()){
                                p = Double.valueOf(item.getPriceLaundry());
                                final TextView pries = dialog.findViewById(R.id.pries);
                                pries.setText(String.valueOf(p));
                                mType = 1;
                               // Toast.makeText(context,  String.valueOf(mType), Toast.LENGTH_SHORT).show();

                            }

                        }
                    });
                }
                if (item.getType() == 2){

                    jasel.setVisibility(View.GONE);
                    alltype.setVisibility(View.GONE);
                    kaoe.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            p = Double.valueOf(item.getPricePresser());
                            final TextView pries = dialog.findViewById(R.id.pries);
                            pries.setText(String.valueOf(p));
                            mType = 2;
                          //  Toast.makeText(context,  String.valueOf(mType), Toast.LENGTH_SHORT).show();

                        }
                    });
                }

                if (item.getType() == 3){

                    kaoe.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            p = Double.valueOf(item.getPricePresser());
                            final TextView pries = dialog.findViewById(R.id.pries);
                            pries.setText(String.valueOf(p));
                            mType = 2;
                           // Toast.makeText(context,  String.valueOf(mType), Toast.LENGTH_SHORT).show();

                        }
                    });

                    jasel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (((CheckBox) v).isChecked()){
                                p = Double.valueOf(item.getPriceLaundry());
                                final TextView pries = dialog.findViewById(R.id.pries);
                                pries.setText(String.valueOf(p));
                                mType = 1;
                              //  Toast.makeText(context,  String.valueOf(mType), Toast.LENGTH_SHORT).show();

                            }

                        }
                    });

                    alltype.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (((CheckBox) v).isChecked()){
                                p = Double.valueOf(item.getPriceLaundry());
                                Double pp = Double.valueOf(p) + Double.valueOf(item.getPricePresser());
                                final TextView pries = dialog.findViewById(R.id.pries);
                                pries.setText(String.valueOf(pp));

                                p = pp;
                                mType = 3;
                              //  Toast.makeText(context,  String.valueOf(mType), Toast.LENGTH_SHORT).show();

                            }

                        }
                    });

                }



                TextView titleitem = dialog.findViewById(R.id.titleitem);

                final TextView total = dialog.findViewById(R.id.total);
                final TextView count = dialog.findViewById(R.id.count);

                ImageView count_m = dialog.findViewById(R.id.count_m);
                count_m.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        co--;
                        if (co > 0){
                            count.setText(String.valueOf(co));
                            double totalprs = co * p;
                            total.setText(String.valueOf(totalprs));
                        }else{
                            co = 0;
                        }
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
                        if (jasel.isChecked() || kaoe.isChecked() || alltype.isChecked()){

                            WebService.getInstance().getApi().AddItemToCart(item.getId(), Session.getInstance().getUser().getId(),co,mType).enqueue(new Callback<MainAddCartResponse>() {
                                @Override
                                public void onResponse(Call<MainAddCartResponse> call, Response<MainAddCartResponse> response) {
                                    Intent intent = new Intent(context, SelcteAddressOnMap.class);
                                    context.startActivity(intent);
                                }

                                @Override
                                public void onFailure(Call<MainAddCartResponse> call, Throwable t) {

                                }
                            });

                        }else {
                            new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("هناك شي غير صحيح ؟")
                                    .setContentText("لرجاء اخيار نوع الخدمة")
                                    .show();
                            //Toast.makeText(context, "الرجاء اخيار نوع الخدمة", Toast.LENGTH_SHORT).show();
                        }



                        //aprove and delite to cart



                    }
                });

                Button add_to_cart = dialog.findViewById(R.id.add_to_cart);
                add_to_cart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // add to cart and dismas dialog
                        if (jasel.isChecked() || kaoe.isChecked() || alltype.isChecked()){

                            final SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
                            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                            pDialog.setTitleText("Loading");
                            pDialog.setCancelable(false);
                            pDialog.show();

                            WebService.getInstance().getApi().AddItemToCart(item.getId(),Session.getInstance().getUser().getId(),co,mType).enqueue(new Callback<MainAddCartResponse>() {
                                @Override
                                public void onResponse(Call<MainAddCartResponse> call, Response<MainAddCartResponse> response) {
                                   // Toast.makeText(context, response.body().getMessage().get(0).toString(), Toast.LENGTH_SHORT).show();

                                    dialog.dismiss();
                                    pDialog.dismiss();
                                }

                                @Override
                                public void onFailure(Call<MainAddCartResponse> call, Throwable t) {
                                    new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                                            .setTitleText("هناك شي غير صحيح ؟")
                                            .setContentText("لرجاء اخيار نوع الخدمة")
                                            .show();
                                }
                            });
                        }else {
                            new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("هناك شي غير صحيح ؟")
                                    .setContentText("لرجاء اخيار نوع الخدمة")
                                    .show();
                            //Toast.makeText(context, "الرجاء اخيار نوع الخدمة", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                dialog.show();
            }
        });
        Picasso.with(context).load("http://192.168.1.101/www/newAdmin/SaboonahApp/SaboonahApp/public/images/items/"+ item.getImage()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return Item.size();
    }

     class HomeListHolder extends RecyclerView.ViewHolder{
         TextView title;
         ImageView thumbnail;
         Button butintercart;
        public HomeListHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            butintercart = itemView.findViewById(R.id.butintercart);
        }
    }


}


