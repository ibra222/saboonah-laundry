package com.ibrahimaboismail.sapoonaapp.Home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ibrahimaboismail.sapoonaapp.R;

/**
 * Created by d_200 on 1/29/2018.
 */

public class LPHomeFragment extends Fragment {

    public LPHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.lp_home_fragment, container, false);

        return view;
    }


}
