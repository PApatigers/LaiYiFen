package com.example.black.waimai_seller.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.black.waimai_seller.base.store;
import com.example.black.waimai_seller.R;
import com.example.black.waimai_seller.tool.imageLoader;

import java.util.List;

public class Store_adapter extends BaseAdapter {

    private List<store> store_list;
    private Context context;
    private LayoutInflater layoutInflater;
    private imageLoader imageshow = new imageLoader ();

    public Store_adapter(Context context, List<store> list){
        this.context = context;
        this.store_list = list;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return store_list.size ();
    }

    @Override
    public Object getItem(int i){
        return store_list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        viewholder holder = null;
        Log.e ("tag-list" , " " + store_list.size () + store_list.get (store_list.size () -1).store_name);

        if(convertView == null){
            holder = new viewholder ();
            convertView = layoutInflater.inflate (R.layout.store_item,null);
            holder.imageView = convertView.findViewById (R.id.old_store_img);
            holder.name = (TextView)convertView.findViewById (R.id.old_store_name);
            holder.add = convertView.findViewById (R.id.old_store_add);
            convertView.setTag (holder);
        }else{
            holder = (viewholder)convertView.getTag ();
        }
        holder.name.setText (store_list.get (position).store_name.toString ());
        holder.add.setText (store_list.get (position).store_add.toString ());
        if(store_list.get (position).store_img != null && holder.imageView != null){
            Log.e ("tag","执行一次" + store_list.get (position).store_img.toString ());
            imageshow.displayImg(store_list.get (position).store_img.toString () , holder.imageView);
        }

        return convertView;
    }

    class  viewholder{
        ImageView imageView;
        TextView name;
        TextView add;
    }
}
