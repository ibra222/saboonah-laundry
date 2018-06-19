package com.ibrahimaboismail.sapoonaapp.Home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ibrahimaboismail.sapoonaapp.R;

/**
 * Created by d_200 on 1/30/2018.
 */

public class AddCart extends DialogFragment {

    TextView titleitem,count;
    ImageView count_m,count_plus;

    private int countpa = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_to_cart,container,false);

        titleitem = view.findViewById(R.id.titleitem);
        count = view.findViewById(R.id.count);

        count_m = view.findViewById(R.id.count_m);
        count_plus = view.findViewById(R.id.count_plus);

        count_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countpa++;
                count.setText(String.valueOf(countpa));
            }
        });

        count_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countpa--;
                count.setText(String.valueOf(countpa));
            }
        });

        return view;
    }
}
