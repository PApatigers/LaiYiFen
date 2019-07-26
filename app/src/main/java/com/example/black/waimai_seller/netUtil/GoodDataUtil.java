package com.example.black.waimai_seller.netUtil;

import android.util.Log;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.black.waimai_seller.base.good;
import com.example.black.waimai_seller.single_service.singleservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GoodDataUtil {

    private static GoodDataUtil instance = null;
    private ExecutorService mExecutorService;
    private static OkHttpClient mOkHttpClient;

    private GoodDataUtil(){
        mOkHttpClient = singleservice.getClient();
    };

    public static GoodDataUtil getIns(){
        if(instance == null)
            instance = new GoodDataUtil();
        return instance;
    }


    public static List<good> getGoodData(int storeId) {
        final List<good> mGoodList = new ArrayList<>();
        FormBody formBody = new FormBody.Builder()
                .add("store_id", "" + storeId)
                .build();
        final Request request = new Request.Builder()
                .url(UrlUtil.GOODDATAURL)
                .post(formBody)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = response.body().string();
                Log.v("storetest",body);
                if (!body.equals("kong")) {
                    JSONArray json = JSONArray.parseArray(body);
                    JSONObject object = null;
                    for (int i = 0; i < json.size(); i++) {
                        object = json.getJSONObject(i);
                        good good = new good.Builder()
                                .setname(object.getString("good_name"))
                                .setprice(object.getFloat("good_price"))
                                .setinventor(object.getInteger("good_inventor"))
                                .setimg(object.getString("good_img"))
                                .builder();
                        mGoodList.add(good);
                    }
                }
            }
        });

        return mGoodList;
    }

    void addGood(){}

    void alterGood(){}

    void deleteGood(){}

    Thread t;
}
