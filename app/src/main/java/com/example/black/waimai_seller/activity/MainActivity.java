package com.example.black.waimai_seller.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.support.design.widget.BottomNavigationView;

import com.example.black.waimai_seller.R;
import com.example.black.waimai_seller.adapter.MainPagerAdapter;
import com.example.black.waimai_seller.fragment.MainFragment;
import com.example.black.waimai_seller.fragment.MeFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Fragment> list = new ArrayList<>();
    private BottomNavigationView navigationView;
    FragmentManager fm ;
    ViewPager viewPager;
    FragmentPagerAdapter fragmentPagerAdapter;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener ( ) {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId ( )) {
                case R.id.index:
                    viewPager.setCurrentItem (0);
                    Log.e("tag-se","点击");
                    return true;
                case R.id.user:
                    viewPager.setCurrentItem (1);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle s){
        super.onCreate (s);
        fm = getSupportFragmentManager ();
        setContentView (R.layout.activity_main);
        initFragmentlist();
        fragmentPagerAdapter = new MainPagerAdapter(fm,list);
        initview();
        viewPager.setAdapter (fragmentPagerAdapter);
        viewPager.setCurrentItem (0);
        navigationView = findViewById (R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    void initFragmentlist(){
        Fragment index = new MainFragment();
        Fragment user = new MeFragment();
        list.add (index);
        list.add (user);
    }

    void initview(){
        viewPager = findViewById (R.id.viewpager);
    }
}
