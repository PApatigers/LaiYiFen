package com.example.black.waimai_seller.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.black.waimai_seller.R;

public class LoadingView extends FrameLayout {

    private ImageView mLoadingImg;
    private AnimationDrawable animationDrawable;

    public LoadingView(@NonNull Context context) {
        this(context,null);
    }

    public LoadingView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public LoadingView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context,R.layout.view_loading,this);
        initView();
    }

    void initView(){
        mLoadingImg = findViewById(R.id.loading_img);
        animationDrawable = (AnimationDrawable)mLoadingImg.getDrawable();
        animationDrawable.start();
    }

    public void stopAni(){
        this.animationDrawable.stop();
    }

}
