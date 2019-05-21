package com.example.black.waimai_seller.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.black.waimai_seller.R;
import com.example.black.waimai_seller.single_service.singleservice;
import com.example.black.waimai_seller.tool.UriTopath;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.util.Log.e;

public class good_add extends Activity {

    private EditText good_name,good_price,good_inventory;
    private String str_good_name,str_good_price,str_good_inventory;
    private ImageView good_img;
    private Button add_good;
    private int store_id;
    private String img_path;

    Handler handler =  new Handler (){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage (msg);
            if(msg.arg1 == 1){
                Intent intent = new Intent (good_add.this,goods_manger.class);
                startActivity (intent);
            }else{
                Toast.makeText (good_add.this,"上架失败",Toast.LENGTH_SHORT).show ();
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.good_add);

        init();
    }

    void init(){
        good_img = findViewById (R.id.good_img);
        good_name = findViewById (R.id.good_name);
        good_price = findViewById (R.id.good_price);
        good_inventory = findViewById (R.id.inventory);
        add_good = findViewById (R.id.add_good);

        Intent intent = getIntent ();
        store_id = intent.getIntExtra ("store_id",store_id);
        add_good.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                send_good ();
            }
        });

        start_find_img ();
    }

    //寻找图片
    void start_find_img(){
        good_img.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent,1);
            }
        });
    }

    //将图片进行转义
    String fileTobase64(String imgFilePath) {
        byte[] data = null;
        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream (imgFilePath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 对字节数组Base64编码
        String res = Base64.encodeToString(data,Base64.DEFAULT);
        Log.e ("tag-base",res.length () +" ");
        return Base64.encodeToString(data,Base64.DEFAULT);

    }

    //上传新商品
    void send_good(){
        if(!good_name.getText ().toString ().equals (null) && !good_price.getText ().toString ().equals (null) && !good_inventory.getText ().toString ().equals (null)){
            str_good_name = good_name.getText ().toString ();
            str_good_price = good_price.getText ().toString ();
            str_good_inventory = good_inventory.getText ().toString ();

            new Thread(){

                @Override
                public void run(){
                    Message message = new Message ();
                    if(upload ()){
                        message.arg1 = 1;
                    }else{
                        message.arg1 = 0;
                    }
                }
            }.start ();
        }else {
            Toast.makeText (this,"信息未填写完整",Toast.LENGTH_SHORT).show ();
        }
    }

    //网络线程
    Boolean upload(){
        String img = fileTobase64 (img_path);
        OkHttpClient client = singleservice.getClient ();
        FormBody formBody = new FormBody.Builder ()
                .add ("good_name",str_good_name)
                .add ("good_price",str_good_price)
                .add ("good_inventor",str_good_inventory)
                .add("store_id",String.valueOf (store_id))
                .add ("good_img",img)
                .build ();
        Request request = new Request.Builder ()
                .url ("http://47.100.202.93/waimai/add_good.php")
                .post (formBody)
                .build ();
        Call call = client.newCall (request);

        try{
            Response response = call.execute ();
            Log.e("tag",response.body ().string ());
            if(response.isSuccessful ()){
                return true;
            }
        }catch(IOException e){
            e.printStackTrace ();
        }
        return  false;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == 1) {//是否选择，没选择就不会继续

            if(data != null){
                //保留img路径后期上传
                Uri uri = data.getData();//得到uri，后面就是将uri转化成file的过程。
                UriTopath uriTopath = new UriTopath ();
                img_path = uriTopath.getFilePathByUri(this,uri);
                Log.e ("tag-img",img_path + " "+ uri);

                good_img.setImageURI (uri);
            }

            super.onActivityResult (requestCode , resultCode , data);
        }
    }

}
