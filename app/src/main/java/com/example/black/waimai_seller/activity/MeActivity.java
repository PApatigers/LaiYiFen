package com.example.black.waimai_seller.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.black.waimai_seller.R;
import com.example.black.waimai_seller.adapter.MeGlidItemAdapter;
import com.example.black.waimai_seller.single_service.MeGlidItem;

public class MeActivity extends Activity {

    private RecyclerView recyclerView;
    private MeGlidItem[] meGlidItems;

    public MeGlidItem[] generateItem(){
        MeGlidItem[] res = new MeGlidItem[]{
                new MeGlidItem("me",R.drawable.back , R.string.app_name),
                new MeGlidItem("no",R.drawable.ic_dashboard_black_24dp,R.string.appbar_scrolling_view_behavior)
        };

        return res;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me);

        initRecvcleView();
    }

    void initRecvcleView(){
        recyclerView = findViewById(R.id.glid_recyview);
        meGlidItems = generateItem();
        MeGlidItemAdapter meGlidItemAdapter = new MeGlidItemAdapter(this,meGlidItems);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2,GridLayoutManager.VERTICAL , false));
        recyclerView.setAdapter(meGlidItemAdapter);
    }
}
