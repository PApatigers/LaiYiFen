package com.example.black.waimai_seller.fragement;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.black.waimai_seller.R;
import com.example.black.waimai_seller.single_service.*;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class good_data extends Fragment {

    private RecyclerView mGoodList;
    private TextView mLoadError;
    private ProgressBar mLoading;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_good_data,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);

        //加载商品信息
        new Thread(new GetGoodDataThread()).start();
    }

    public void initView(View view){
        mGoodList = view.findViewById(R.id.goods);
        mLoadError = view.findViewById(R.id.load_error);
        mLoading = view.findViewById(R.id.loading);
        mLoading.setVisibility(View.VISIBLE);
    }



    class GetGoodDataThread implements Runnable{

        @Override
        public void run() {
            getGoodData();
        }

        void getGoodData(){
            OkHttpClient okHttpClient = com.example.black.waimai_seller.single_service.singleservice.getClient();
            Request request = new Request.Builder()
                    .get()
                    .build();
            Call call = okHttpClient.newCall(request);

            try{
                Response response = call.execute();
                if (response.isRedirect()){

                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
