package com.ibrahimaboismail.sapoonaapp.Home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ibrahimaboismail.sapoonaapp.Adapters.HorizontalAdapter;
import com.ibrahimaboismail.sapoonaapp.Models.Item;
import com.ibrahimaboismail.sapoonaapp.Models.MainItmeRespons;
import com.ibrahimaboismail.sapoonaapp.R;
import com.ibrahimaboismail.sapoonaapp.WebService.WebService;
import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by d_200 on 1/29/2018.
 */

public class PresserHomeFragment extends Fragment {
    private MultiSnapRecyclerView firstRecyclerView,secondRecyclerView,third_recycler_view;
    public PresserHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.presser_home_fragment, container, false);


        firstRecyclerView = view.findViewById(R.id.first_recycler_view);
        secondRecyclerView = view.findViewById(R.id.second_recycler_view);
        third_recycler_view = view.findViewById(R.id.third_recycler_view);

        WebService.getInstance().getApi().mainitem(1).enqueue(new Callback<MainItmeRespons>() {


            @Override
            public void onResponse(Call<MainItmeRespons> call, Response<MainItmeRespons> response) {

                    List<Item> items = response.body().getData();

                    HorizontalAdapter firstAdapter = new HorizontalAdapter(getActivity(),items,Integer.valueOf(response.body().getType().toString()));
                    //  MultiSnapRecyclerView firstRecyclerView = (MultiSnapRecyclerView) view.findViewById(R.id.first_recycler_view);
                    LinearLayoutManager firstManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                    firstRecyclerView.setLayoutManager(firstManager);
                    firstRecyclerView.setAdapter(firstAdapter);

                    HorizontalAdapter secondAdapter = new HorizontalAdapter(getActivity(),items,Integer.valueOf(response.body().getType().toString()));
                    LinearLayoutManager secondManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                    secondRecyclerView.setLayoutManager(secondManager);
                    secondRecyclerView.setAdapter(secondAdapter);

                    HorizontalAdapter thirdAdapter = new HorizontalAdapter(getActivity(),items,Integer.valueOf(response.body().getType().toString()));
                    LinearLayoutManager thirdManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                    third_recycler_view.setLayoutManager(thirdManager);
                    third_recycler_view.setAdapter(thirdAdapter);

            }

            @Override
            public void onFailure(Call<MainItmeRespons> call, Throwable t) {

            }
        });

        return view;
    }


}
