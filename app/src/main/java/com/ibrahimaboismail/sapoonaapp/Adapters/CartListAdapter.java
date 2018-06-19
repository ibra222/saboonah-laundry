package com.ibrahimaboismail.sapoonaapp.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ibrahimaboismail.sapoonaapp.Models.Cart.Data;
import com.ibrahimaboismail.sapoonaapp.Models.Main;
import com.ibrahimaboismail.sapoonaapp.R;
import com.ibrahimaboismail.sapoonaapp.WebService.WebService;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by d_200 on 1/29/2018.
 */

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.DataListHolder> {

    private List<Data> items;
    private Context context;
    private int co =0;
    private double p = 0;
    private int mType = 0;

    public CartListAdapter(List<com.ibrahimaboismail.sapoonaapp.Models.Cart.Data> item, Context context) {
        items = item;
        this.context = context;
    }

    @Override
    public CartListAdapter.DataListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.shpo_cart_item, parent, false);
        DataListHolder holder = new DataListHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(CartListAdapter.DataListHolder holder, final int position) {
        final Data itemss = items.get(position);
        holder.title.setText(itemss.getItems().getTitle());
        holder.quantity.setText(String.valueOf(itemss.getQuantity()));
        holder.editss.setClickable(true);
        holder.editss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //  Toast.makeText(context, item.getPriceLaundry().toString(), Toast.LENGTH_SHORT).show();
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.edit_to_cartv1);

                final CheckBox jasel = dialog.findViewById(R.id.jasel);
                final CheckBox kaoe = dialog.findViewById(R.id.kaoe);
                final CheckBox alltype = dialog.findViewById(R.id.alltype);
                dialog.setTitle("تعديل الطلب");

                if (itemss.getType() == 1){

                    kaoe.setVisibility(View.GONE);
                    alltype.setVisibility(View.GONE);
                    jasel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (((CheckBox) v).isChecked()){
                                p = Double.valueOf(itemss.getItems().getPriceLaundry());
                                final TextView pries = dialog.findViewById(R.id.pries);
                                pries.setText(String.valueOf(p));
                                mType = 1;
                                // Toast.makeText(context,  String.valueOf(mType), Toast.LENGTH_SHORT).show();

                            }

                        }
                    });
                }
                if (itemss.getType() == 2){

                    jasel.setVisibility(View.GONE);
                    alltype.setVisibility(View.GONE);
                    kaoe.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            p = Double.valueOf(itemss.getItems().getPricePresser());
                            final TextView pries = dialog.findViewById(R.id.pries);
                            pries.setText(String.valueOf(p));
                            mType = 2;
                            //  Toast.makeText(context,  String.valueOf(mType), Toast.LENGTH_SHORT).show();

                        }
                    });
                }

                if (itemss.getType() == 3){

                    kaoe.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            p = Double.valueOf(itemss.getItems().getPricePresser());
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
                                p = Double.valueOf(itemss.getItems().getPriceLaundry());
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
                                p = Double.valueOf(itemss.getItems().getPriceLaundry());
                                Double pp = Double.valueOf(p) + Double.valueOf(itemss.getItems().getPricePresser());
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

                titleitem.setText(itemss.getItems().getTitle().toString());


                Button editorder = dialog.findViewById(R.id.editorder);
                editorder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //add to cart
                        if (jasel.isChecked() || kaoe.isChecked() || alltype.isChecked()){
                             // Toast.makeText(context, "id = "+ itemss.getId().toString()+ "  count = "+ co + mType ,Toast.LENGTH_LONG).show();
                               WebService.getInstance().getApi().edit(itemss.getId(),co,mType).enqueue(new Callback<Main>() {
                                   @Override
                                   public void onResponse(Call<Main> call, Response<Main> response) {
                                       Toast.makeText(context, "jghhjghg", Toast.LENGTH_SHORT).show();
                                       dialog.dismiss();
                                   }

                                   @Override
                                   public void onFailure(Call<Main> call, Throwable t) {
                                       dialog.dismiss();
                                   }
                               });
                               notifyDataSetChanged();
                            notifyItemRangeChanged(position, items.size());
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
                        dialog.dismiss();
                    }
                });
                dialog.show();

            }
        });
        holder.delite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebService.getInstance().getApi().deleit(itemss.getId()).enqueue(new Callback<Main>() {
                    @Override
                    public void onResponse(Call<Main> call, Response<Main> response) {
                        if (response.body().getSuccess() == false) {

                        } else if (response.body().getSuccess() == true) {

                            new SweetAlertDialog(context, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                                    .setTitleText("تم حذف العنصر بنجاح")
                                    .setCustomImage(R.drawable.saok)
                                    .show();

                            removeAt(position);


                        }
                    }

                    @Override
                    public void onFailure(Call<Main> call, Throwable t) {

                    }
                });
            }
        });

        if (itemss.getType() == 1) {
            holder.prise.setText(itemss.getItems().getPriceLaundry());
            Double to = itemss.getQuantity() * Double.valueOf(itemss.getItems().getPriceLaundry().toString());
            holder.total.setText(to.toString());
        }
        if (itemss.getType() == 2) {
            holder.prise.setText(itemss.getItems().getPricePresser());
            Double to = itemss.getQuantity() * Double.valueOf(itemss.getItems().getPricePresser().toString());
            holder.total.setText(to.toString());
        }
        if (itemss.getType() == 3) {
            holder.prise.setText(String.valueOf(Double.valueOf(itemss.getItems().getPriceLaundry().toString()) + Double.valueOf(itemss.getItems().getPricePresser().toString())));
            Double to = itemss.getQuantity() * (Double.valueOf(itemss.getItems().getPriceLaundry().toString()) + Double.valueOf(itemss.getItems().getPriceLaundry().toString()));
            holder.total.setText(to.toString());
        }


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class DataListHolder extends RecyclerView.ViewHolder {
        TextView title, quantity, prise, total;
        RelativeLayout editss, delite;

        public DataListHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            quantity = itemView.findViewById(R.id.quantity);
            prise = itemView.findViewById(R.id.prise);
            total = itemView.findViewById(R.id.total);
            editss = itemView.findViewById(R.id.editss);
            delite = itemView.findViewById(R.id.delite);

        }
    }


    public void removeAt(int position) {
        items.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, items.size());
    }
}


