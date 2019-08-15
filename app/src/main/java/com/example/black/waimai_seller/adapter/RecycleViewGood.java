package com.example.black.waimai_seller.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class RecycleViewGood extends RecyclerView.Adapter {

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class myholder extends RecyclerView.ViewHolder{

        public myholder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
