package com.example.black.waimai_seller.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.black.waimai_seller.R;
import com.example.black.waimai_seller.single_service.MeGlidItem;

public class MeGlidItemAdapter extends RecyclerView.Adapter <MeGlidItemAdapter.MeItemHolder>{

    private  Context context;
    private  MeGlidItem[] meGlidItems;
    private  MeGlidItem meGlidItem;

    public MeGlidItemAdapter(Context context , MeGlidItem[] meGlidItems){
        this.context = context;
        this.meGlidItems = meGlidItems;

    }

    @NonNull
    @Override
    public MeItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MeItemHolder(LayoutInflater.from(context).inflate(R.layout.me_glid_item , viewGroup , false));
    }

    @Override
    public void onBindViewHolder(MeItemHolder viewHolder, int i) {
        viewHolder.bind(meGlidItems[i],i);
    }

    @Override
    public int getItemCount() {
        return meGlidItems.length;
    }

//    @Override
//    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
//        super.onAttachedToRecyclerView (recyclerView);
//        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager ();
//        if(manager instanceof GridLayoutManager){
//            GridLayoutManager gridLayoutManager = (GridLayoutManager)manager;
//            gridLayoutManager.setSpanSizeLookup (new GridLayoutManager.SpanSizeLookup () {
//                @Override
//                public int getSpanSize(int position) {
//                    return 1;
//                }
//            });
//        }
//    }

    class MeItemHolder extends  RecyclerView.ViewHolder{

        private ImageView mIcon;
        private TextView mTitle;
        public MeItemHolder(View view){
            super(view);
            mTitle = view.findViewById(R.id.meGlidTitle);
            mIcon = view.findViewById(R.id.meGlidIcon);
        }

        void bind(MeGlidItem mItem , int position){
            meGlidItem = mItem;
            mTitle.setText(mItem.gettitle());
        }
    }

}
