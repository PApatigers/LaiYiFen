package com.example.black.waimai_seller.base;

import android.widget.ImageView;

public class good {

    public  int good_id;
    public String good_name;
    public int inventory;      //库存
    public int sell_mouth_num;
    public double price;
    public String good_image;

    public good(){

    }
    good(String name , int inv , int price){
        this.good_name = name;
        this.inventory = inv;
        this.price = price;
    }

    //补货
    public void add_good(int add_num){
        this.inventory += add_num;
    }

    //修改价格
    public void change_price(double price){
        this.price = price;
    }

    //
    public int get_good_id(){
        return this.good_id;

    }

    public static class Builder{

        int good_id;
        String good_name;
        float good_price;
        int good_inventor;
        String good_img;

        public Builder() {

        }

        public good.Builder setid(int id){
            good_id = id;
            return this;
        }
        public good.Builder setname(String name) {
            good_name = name;
            return this;
        }

        public good.Builder setprice(float price){
            good_price = price;
            return this;
        }

        public good.Builder setinventor(int num){
            good_inventor = num;
            return this;
        }

        public good.Builder setimg(String img){
            good_img = img;
            return this;
        }

        public good builder() {
            return new good(this);
        }


    }

    public good(good.Builder builder) {
        this.good_id = builder.good_id;
        this.good_name = builder.good_name;
        this.price = builder.good_price;
        this.inventory = builder.good_inventor;
        this.good_image = builder.good_img;
    }
}
