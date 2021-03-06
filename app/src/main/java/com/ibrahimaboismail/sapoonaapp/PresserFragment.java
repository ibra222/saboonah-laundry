package com.ibrahimaboismail.sapoonaapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ibrahimaboismail.sapoonaapp.Adapters.AdsListAdapter;
import com.ibrahimaboismail.sapoonaapp.Models.Ads;
import com.ibrahimaboismail.sapoonaapp.Models.MainAdsResponse;
import com.ibrahimaboismail.sapoonaapp.Utils.Session;
import com.ibrahimaboismail.sapoonaapp.WebService.WebService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by d_200 on 1/28/2018.
 */

public class PresserFragment extends Fragment {
    private RecyclerView recyclerView;
    private Activity activity;

    public PresserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_presser, container, false);

        recyclerView = view.findViewById(R.id.re2);


        WebService.getInstance().getApi().mainads(Session.getInstance().getUser().getRoot_id(),2).enqueue(new Callback<MainAdsResponse>() {
            @Override
            public void onResponse(Call<MainAdsResponse> call, Response<MainAdsResponse> response) {


                List<Ads> AdsLa = response.body().getData();
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
                recyclerView.setAdapter(new AdsListAdapter(AdsLa,getActivity()));

            }

            @Override
            public void onFailure(Call<MainAdsResponse> call, Throwable t) {
                Toast.makeText(activity,"Text!",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }
}
