package com.example.black.waimai_seller.single_service;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import java.io.Serializable;

public class MeGlidItem implements Serializable {

    /**
     * 网格元素
     */

    @DrawableRes
    private int itemIconRes;

    @StringRes
    private int itemTitleRes;

    public MeGlidItem(String id , int itemIconRes , int itemTitleRes){
        this.itemIconRes = itemIconRes;
        this.itemTitleRes = itemTitleRes;
    }

    public int gettitle(){
        return this.itemTitleRes;
    }
}
