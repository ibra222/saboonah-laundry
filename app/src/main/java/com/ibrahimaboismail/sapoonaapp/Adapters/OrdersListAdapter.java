package com.ibrahimaboismail.sapoonaapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ibrahimaboismail.sapoonaapp.Models.Orders.Order;
import com.ibrahimaboismail.sapoonaapp.R;
import com.ibrahimaboismail.sapoonaapp.RatingActivity;
import com.ramotion.foldingcell.FoldingCell;

import java.util.List;

/**
 * Created by d_200 on 1/29/2018.
 */

public class OrdersListAdapter extends RecyclerView.Adapter<OrdersListAdapter.OrderListHolder> {

    private List<Order> Orders;
    private Context context;

    public OrdersListAdapter(List<Order> orders, Context context) {
        Orders = orders;
        this.context = context;
    }

    @Override
    public OrdersListAdapter.OrderListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_items_layout,parent,false);
        OrderListHolder holder = new OrderListHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(OrdersListAdapter.OrderListHolder holder, int position) {
        final Order Order = Orders.get(position);
        holder.title1.setText(String.valueOf(Order.getOrderNumber()));
        holder.ordernumonhiden.setText(String.valueOf(Order.getOrderNumber()));

        if (Order.getStatus() == 4 ){

            holder.mainLiner.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, RatingActivity.class);
                    intent.putExtra("ordernum",Order.getOrderNumber());
                    intent.putExtra("ordernum",Order.getOrderNumber());
                    context.startActivity(intent);
                }
            });

        }

        if (Order.getStatus() == 1){
            holder.stage1actev.setVisibility(View.VISIBLE);
            holder.stage1unactev.setVisibility(View.GONE);
            holder.stage2actev.setVisibility(View.GONE);
            holder.stage2unactev.setVisibility(View.VISIBLE);
            holder.stage3actev.setVisibility(View.GONE);
            holder.stage3unactev.setVisibility(View.VISIBLE);
        }

        if (Order.getStatus() == 2){
            holder.stage1actev.setVisibility(View.GONE);
            holder.stage1unactev.setVisibility(View.VISIBLE);
            holder.stage2actev.setVisibility(View.VISIBLE);
            holder.stage2unactev.setVisibility(View.GONE);
            holder.stage3actev.setVisibility(View.GONE);
            holder.stage3unactev.setVisibility(View.VISIBLE);
        }

        if (Order.getStatus() == 3){
            holder.stage1actev.setVisibility(View.GONE);
            holder.stage1unactev.setVisibility(View.VISIBLE);
            holder.stage2actev.setVisibility(View.GONE);
            holder.stage2unactev.setVisibility(View.VISIBLE);
            holder.stage3actev.setVisibility(View.VISIBLE);
            holder.stage3unactev.setVisibility(View.GONE);
        }
        if (Order.getStatus() == 4){
            holder.stage1actev.setVisibility(View.GONE);
            holder.stage1unactev.setVisibility(View.VISIBLE);
            holder.stage2actev.setVisibility(View.GONE);
            holder.stage2unactev.setVisibility(View.VISIBLE);
            holder.stage3actev.setVisibility(View.VISIBLE);
            holder.stage3unactev.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return Orders.size();
    }

     class OrderListHolder extends RecyclerView.ViewHolder{
         TextView title1,ordernumonhiden;
         RelativeLayout  stage1actev,stage1unactev,stage2actev,stage2unactev,stage3actev,stage3unactev;
         LinearLayout mainLiner ;

        public OrderListHolder(View itemView) {
            super(itemView);

            title1 = itemView.findViewById(R.id.title1);
            stage1actev = itemView.findViewById(R.id.stage1actev);
            stage1unactev = itemView.findViewById(R.id.stage1unactev);
            stage2actev = itemView.findViewById(R.id.stage2actev);
            stage2unactev = itemView.findViewById(R.id.stage2unactev);
            stage3actev = itemView.findViewById(R.id.stage3actev);
            stage3unactev = itemView.findViewById(R.id.stage3unactev);

            mainLiner= itemView.findViewById(R.id.mainLiner);


            ordernumonhiden = itemView.findViewById(R.id.ordernumonhiden);
            final FoldingCell fc = (FoldingCell) itemView.findViewById(R.id.folding_cell);
            fc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fc.toggle(false);
                }
            });
        }
    }
}


