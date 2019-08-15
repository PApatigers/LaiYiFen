package com.example.black.waimai_seller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.black.waimai_seller.R;
import com.example.black.waimai_seller.activity.test.TestActivity;

public class MainFragment extends Fragment implements View.OnClickListener{

    private View index_view;
    private Button newstore,oldstore;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(index_view == null)
            index_view = inflater.inflate (R.layout.fragment_main,null);

        newstore = index_view.findViewById (R.id.newstore);
        oldstore = index_view.findViewById (R.id.oldstore);
        newstore.setOnClickListener (this);
        oldstore.setOnClickListener (this);
        return index_view;
    }

    @Override
    public void onClick(View v){
        switch(v.getId ()){
            case R.id.newstore:
                Intent intent1 = new Intent (getContext (), TestActivity.class);
                startActivity (intent1);
                break;
            case R.id.oldstore:
                Intent intent2 = new Intent (getContext (),com.example.black.waimai_seller.activity.oldstoreManger.class);
                startActivity (intent2);
                break;
        }
    }
}
