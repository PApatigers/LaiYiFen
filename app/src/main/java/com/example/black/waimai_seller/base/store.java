package com.example.black.waimai_seller.base;

public class store {

    public int store_id;
    public String store_name;
    public String store_add;
    public String store_img;

    public store(){
        this.store_id = 0;
        this.store_name = "";
        this.store_add = "";
        this.store_img = "";
    }
    store(String name , String add , String img){
        this.store_name = name;
        this.store_add = add;
        this.store_img = img;
    }

    public static class Builder{

        //必须参数
        int param1;

        //非必须参数
        int param2 = 0;

        public Builder() {

        }

        public Builder param2(int vaules) {
            param2 = vaules;
            return this;
        }

        public store builder() {
            return new store(this);
        }


    }

    public store(Builder builder) {

    }
}
