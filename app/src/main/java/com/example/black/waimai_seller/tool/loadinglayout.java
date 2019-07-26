package com.example.black.waimai_seller.tool;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.example.black.waimai_seller.R;

public class loadinglayout extends FrameLayout {

    private  int emptyview,errorview,loadingview;
    public loadinglayout(Context context) {
        this(context, null);
    }

    public loadinglayout(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public loadinglayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.LoadingLayout, 0, 0);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate ( );
    }
}
