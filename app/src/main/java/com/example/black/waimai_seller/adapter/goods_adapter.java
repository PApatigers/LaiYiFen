package com.example.black.waimai_seller.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.black.waimai_seller.R;
import com.example.black.waimai_seller.bean.good;
import com.example.black.waimai_seller.bean.store;
import com.example.black.waimai_seller.tool.imageLoader;

import java.util.List;

public class goods_adapter extends BaseAdapter {

    private List<good> good_list;
    private Context context;
    private LayoutInflater layoutInflater;
    private imageLoader imageshow = new imageLoader ();

    public goods_adapter(Context context, List<good> list){
        this.context = context;
        this.good_list = list;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return good_list.size ();
    }

    @Override
    public Object getItem(int i){
        return good_list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        goods_adapter.viewholder holder = null;

        if(convertView == null){
            holder = new goods_adapter.viewholder ();
            convertView = layoutInflater.inflate (R.layout.good_item,null);
            holder.imageView = convertView.findViewById (R.id.good_img);
            holder.name = (TextView)convertView.findViewById (R.id.good_name);
            holder.price = convertView.findViewById (R.id.good_price);
            holder.Inventoe = convertView.findViewById (R.id.inventory);
            convertView.setTag (holder);
        }else{
            holder = (goods_adapter.viewholder)convertView.getTag ();
        }

        if(good_list.get (position).good_name != null){
            holder.name.setText (good_list.get (position).good_name.toString ());
            holder.price.setText (String.valueOf ("单价：" + good_list.get (position).price)+"￥");
            holder.Inventoe.setText (String.valueOf ("库存：" + good_list.get (position).inventory));
            if(good_list.get (position).good_image != null && holder.imageView != null){
                Log.e ("tag","执行一次" + good_list.get (position).good_image.toString ());
                imageshow.displayImg(good_list.get (position).good_image.toString () , holder.imageView);
            }
        }

        return convertView;
    }

    class  viewholder{
        ImageView imageView;
        TextView name;
        TextView price;
        TextView Inventoe;
    }
}
