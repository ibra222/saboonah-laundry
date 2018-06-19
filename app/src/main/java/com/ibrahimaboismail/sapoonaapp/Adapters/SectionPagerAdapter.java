package com.ibrahimaboismail.sapoonaapp.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by d_200 on 1/29/2018.
 */

public class SectionPagerAdapter  extends FragmentPagerAdapter {
    private static final String TAG = "SectionPagerAdapter";
    private final List<Fragment> mfragmentList = new ArrayList<>();
    public SectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mfragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mfragmentList.size();
    }

    public void addFragment(Fragment fragment){
        mfragmentList.add(fragment);
    }
}
