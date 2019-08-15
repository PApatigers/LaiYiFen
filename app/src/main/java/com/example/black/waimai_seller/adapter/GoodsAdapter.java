package com.example.black.waimai_seller.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.black.waimai_seller.R;
import com.example.black.waimai_seller.base.good;
import com.example.black.waimai_seller.tool.imageLoader;

import java.util.List;
import java.util.zip.Inflater;

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.GoodHolder> {

    private Context mContext;
    private List<good> mGoodList;

    public GoodsAdapter(Context context , List<good> goods){
        this.mContext = context;
        this.mGoodList = goods;
    }

    @NonNull
    @Override
    public GoodHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(mContext, R.layout.good_item,null);
        GoodHolder holder = new GoodHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GoodHolder holder, int position) {
        if(mGoodList.get (position).good_name != null){
            holder.name.setText (mGoodList.get (position).good_name.toString ());
            holder.price.setText (String.valueOf ("单价：" + mGoodList.get (position).price)+"￥");
            holder.Inventoe.setText (String.valueOf ("库存：" + mGoodList.get (position).inventory));
            if(mGoodList.get (position).good_image != null && holder.imageView != null){
                imageLoader.getIns().displayImg(mGoodList.get (position).good_image.toString () , holder.imageView);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mGoodList.size();
    }

    class GoodHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView name;
        TextView price;
        TextView Inventoe;

        public GoodHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById (R.id.good_img);
            name = (TextView)itemView.findViewById (R.id.good_name);
            price = itemView.findViewById (R.id.good_price);
            Inventoe = itemView.findViewById (R.id.inventory);
        }
    }
}
