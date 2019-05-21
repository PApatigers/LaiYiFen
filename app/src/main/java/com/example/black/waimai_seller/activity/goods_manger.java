package com.example.black.waimai_seller.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.example.black.waimai_seller.R;
import com.example.black.waimai_seller.adapter.goods_adapter;
import com.example.black.waimai_seller.base.good;
import com.example.black.waimai_seller.single_service.singleservice;

import com.alibaba.fastjson.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class goods_manger extends Activity {

    private ListView good_listview;
    private List<good> good_list;
    private goods_adapter adapter;
    private int store_id;
    private Button add;

    Handler handler = new Handler (){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage (msg);
            if(msg.arg1 == 0){
                Toast.makeText (goods_manger.this,"无商品",Toast.LENGTH_SHORT).show ();
            }else{
                good_listview.setAdapter (adapter);
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.goos_manger);

        init();
    }

    void init(){
        good_listview = findViewById (R.id.goods_list);
        add = findViewById (R.id.add_good);

        good_list = new ArrayList<> ();
        adapter = new goods_adapter (this,good_list);
        Intent intent = getIntent ();
        store_id = intent.getIntExtra ("store_id",store_id);
        Log.e ("tag_store_id",""+store_id);
        new Thread (){
            @Override
            public  void run(){
                getdata(store_id);
            }
        }.start ();

        //进入修改商品信息
        good_listview.setOnItemClickListener (new AdapterView.OnItemClickListener ( ) {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //进入修改商品信息
                in_good(position);
            }
        });

        //进入新增商品页
        add.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                 add_good ();
            }
        });
    }

    void getdata(int store_id){
        Log.e ("tag","jinru");
        OkHttpClient good_client = singleservice.getClient ();
        if(good_client == null)
            Log.e ("tag","null");
        else
            Log.e ("tag","not null");
        if(good_client == null)
            Log.e ("tag","null");
        FormBody formBody = new FormBody.Builder ()
                .add ("store_id",""+store_id)
                .build ();
        Request request = new Request.Builder ()
                .url ("http://47.100.202.93/waimai/get_good_info.php")
                .post (formBody)
                .build ();
        Call call = good_client.newCall (request);

        try{
            Response response = call.execute ();
            Log.e ("tag","jintu"+response.isSuccessful ());
            if(response.isSuccessful ()){
                String res = response.body ().string ();
                Log.e ("tag","反馈" +res);
                if(!res.equals ("kong")){
                    JSONArray json = JSONArray.parseArray (res);
                    JSONObject object = null;
                    for(int i =0;i<json.size ();i++){
                        object = json.getJSONObject (i);
                        good good = new good.Builder ()
                                .setname (object.getString ("good_name"))
                                .setprice (object.getFloat ("good_price"))
                                .setinventor (object.getInteger ("good_inventor"))
                                .setimg (object.getString ("good_img"))
                                .builder ();
                        good_list.add (good);
                    }
                    Log.e("tag-list",good_list.size ()+good_list.get (0).good_name);
                }

                Message msg = new Message ();
                msg.arg1 = good_list.size ();
                handler.sendMessage (msg);
            }


        }catch (IOException e){
            e.printStackTrace ();
        }

    }

    //进入商品信息修改页
    void in_good(int position){
        Intent intent = new Intent (this,good_alter.class);
        intent.putExtra ("good_id",good_list.get (position).get_good_id ());
        startActivity (intent);
    }

    //进入新增商品页
    void add_good(){
        Intent intent = new Intent (this,good_add.class);
        intent.putExtra ("store_id",store_id);
        startActivity (intent);
    }
}
