package com.example.black.waimai_seller.fragement;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.black.waimai_seller.R;
import com.example.black.waimai_seller.adapter.MeGlidItemAdapter;
import com.example.black.waimai_seller.single_service.MeGlidItem;

public class user extends Fragment {

    private View me;
    private RecyclerView recyclerView;
    private MeGlidItem[] meGlidItems;

    public MeGlidItem[] generateItem(){
        MeGlidItem[] res = new MeGlidItem[]{
                new MeGlidItem("me",R.drawable.back , R.string.app_name),
                new MeGlidItem("no",R.drawable.ic_dashboard_black_24dp,R.string.appbar_scrolling_view_behavior)
        };

        return res;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (me == null){
            me = inflater.inflate(R.layout.me,null);
        }
        initview(me);

        return me;
    }

    void initview(View view){
        recyclerView = view.findViewById(R.id.glid_recyview);
        meGlidItems = generateItem();
        MeGlidItemAdapter meGlidItemAdapter = new MeGlidItemAdapter(getContext(),meGlidItems);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL , false));
        recyclerView.setAdapter(meGlidItemAdapter);
    }
}
