package com.example.black.waimai_seller.activity;

import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.black.waimai_seller.R;
import com.example.black.waimai_seller.adapter.GoodsAdapter;
import com.example.black.waimai_seller.adapter.goods_adapter;
import com.example.black.waimai_seller.base.good;
import com.example.black.waimai_seller.netUtil.GoodDataUtil;
import com.example.black.waimai_seller.tool.imageLoader;
import com.example.black.waimai_seller.view.LoadingView;

import java.util.List;

public class GoodMangerActivity extends Activity implements View.OnClickListener{

    private ImageView mBack;
    private TextView mTitleStoreName;
    private ImageView mStoreIcon;
    private TextView mStoreName;
    private TextView mStorePV;
    private RecyclerView mKindsMenu;
    private RecyclerView mGoodsMenu;
    private Button mAddKind;
    private TextView mNoGood;
    private LoadingView mLoading;

    private List<good> mGoodList;
    private int storeId;
    private String storeName;
    private GoodsAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goog_menu);

        initView();
        initData();
    }

    void initView(){
        mBack = findViewById(R.id.back);
        mStoreIcon = findViewById(R.id.store_icon);
        mTitleStoreName = findViewById(R.id.title_store_name);
        mStoreName = findViewById(R.id.store_name);
        mStorePV = findViewById(R.id.store_visitor_num);
        mKindsMenu = findViewById(R.id.kinds_menu);
        mGoodsMenu = findViewById(R.id.good_menu);
        mAddKind = findViewById(R.id.add_kind);
        mNoGood = findViewById(R.id.nogood);
        mLoading = findViewById(R.id.loading);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoodMangerActivity.this.finish();
            }
        });

        Intent intent = getIntent();
        storeName = intent.getStringExtra("storeName");
        mTitleStoreName.setText(storeName);
        mStoreName.setText(storeName);
        storeId =Integer.parseInt(intent.getStringExtra("storeId"));
        String storeImgSource = intent.getStringExtra("storeIcon");
        imageLoader.getIns().displayImg(storeImgSource,mStoreIcon);

        mLoading.setVisibility(View.VISIBLE);
        mGoodsMenu.setVisibility(View.GONE);
        mKindsMenu.setVisibility(View.GONE);
        mNoGood.setVisibility(View.GONE);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoading.stopAni();
    }

    void initData(){
        mGoodList = GoodDataUtil.getIns().getGoodData(storeId);
        if(mGoodList != null){
            if (mGoodList.size() != 0){
                mGoodsMenu.setVisibility(View.VISIBLE);
                adapter = new GoodsAdapter(this,mGoodList);
                mGoodsMenu.setAdapter(adapter);
            }else{
                mNoGood.setVisibility(View.VISIBLE);
                mNoGood.setText(R.string.no_good);
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

        }
    }
}
