package com.example.black.waimai_seller.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class pager_adapter extends FragmentPagerAdapter {

    private List<Fragment> list = new ArrayList<> ();


    public pager_adapter(FragmentManager fragmentManager , List<Fragment> list){
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
