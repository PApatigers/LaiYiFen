package com.example.black.waimai_seller.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> list = new ArrayList<>();


    public MainPagerAdapter(FragmentManager fragmentManager , List<Fragment> list){
        super(fragmentManager);
        this.list = list;
    }
    @Override
    public Fragment getItem(int i) {
        return list.get (i);
    }

    @Override
    public int getCount() {
        return list!=null ? list.size () : 0 ;
    }
}
