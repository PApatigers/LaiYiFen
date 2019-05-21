package com.example.black.waimai_seller.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.example.black.waimai_seller.adapter.Store_adapter;
import com.example.black.waimai_seller.base.store;
import com.example.black.waimai_seller.R;

import com.alibaba.fastjson.JSONArray;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class oldstoreManger extends Activity {

    private List<store> store_list;
    private ListView store_listview;
    private Store_adapter adapter;

    Handler handler = new Handler (){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage (msg);

            if(msg.arg1 > 0){
                store_listview.setAdapter (adapter);
            }else {
                Toast.makeText (oldstoreManger.this,"你还未创建店铺",Toast.LENGTH_SHORT).show ();
                oldstoreManger.this.finish ();
            }

        }
    };

    @Override
    protected void onCreate(Bundle s){
        super.onCreate (s);
        setContentView (R.layout.old_store_manger);

        store_list = new ArrayList<> ();
        init();
    }

    void init(){
        store_listview = findViewById (R.id.store_list);
        adapter = new Store_adapter (this,store_list);
        new Thread (){
            @Override
            public void run(){
                getdata();
            }
        }.start ();

        store_listview.setOnItemClickListener (new AdapterView.OnItemClickListener ( ) {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //进入店铺
                in_store(position);
            }
        });
    }

    void getdata(){
        OkHttpClient client = new OkHttpClient ();
        Request request = new Request.Builder()
                .url ("http://47.100.202.93/waimai/get_start_info.php")
                .build ();
        Call call = client.newCall (request);
        try {
            Response response = call.execute ();
            if (response.isSuccessful ()){
                String res = response.body ().string ();
                JSONArray json = JSONArray.parseArray(res);
                Log.e("tag-json",""+ json + " " + json.size ());
                for(int i=0;i<json.size ();i++){
                    JSONObject object = json.getJSONObject (i);
                    store store = new store();
                    store.store_id = Integer.parseInt (object.getString ("store_id"));
                    store.store_name = (String)object.getString("store_name");
                    store.store_img = (String)object.getString ("store_img");
                    store.store_add = (String)object.getString ("store_add");
                    store_list.add (store);

                }

                Message msg = new Message ();
                msg.arg1 = json.size ();
                handler.sendMessage (msg);
            }

        } catch (IOException e) {
            e.printStackTrace ( );
        }
    }

    //进入店铺
    void in_store(int position){
        Intent intent = new Intent (this,goods_manger.class);
        intent.putExtra ("store_id",store_list.get (position).store_id);
        startActivity(intent);
    }

}
