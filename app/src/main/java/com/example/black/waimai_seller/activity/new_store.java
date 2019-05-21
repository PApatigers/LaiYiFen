package com.example.black.waimai_seller.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.util.Base64;

import com.example.black.waimai_seller.R;
import com.example.black.waimai_seller.single_service.singleservice;
import com.example.black.waimai_seller.tool.UriTopath;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class new_store extends Activity {

    private ImageView store_img;
    private EditText store_name,store_add;
    private Button create;
    private String img_path;

    Handler handler = new Handler (){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage (msg);
            if(msg.arg1 == 1){
                Intent intent = new Intent (new_store.this , oldstoreManger.class);
                startActivity (intent);
            }else{
                Toast.makeText (new_store.this,"创建失败",Toast.LENGTH_SHORT).show ();
            }
        }
    };

    @Override
    protected void onCreate(Bundle s){
        super.onCreate (s);
        setContentView (R.layout.create_new_store);

        int hasReadExternalStoragePermission = ContextCompat.checkSelfPermission (getApplication ( ), Manifest.permission.READ_EXTERNAL_STORAGE);
        Log.e ("PERMISION_CODE", hasReadExternalStoragePermission + "***");
        int temp = PackageManager.PERMISSION_GRANTED;
        if (hasReadExternalStoragePermission == PackageManager.PERMISSION_GRANTED) {
            init();
        } else {
            //若没有授权，会弹出一个对话框
            ActivityCompat.requestPermissions (this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (permissions[0].equals (Manifest.permission.READ_EXTERNAL_STORAGE) && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //用户同意授权，执行读取文件的代码
                init ();
            } else {
                //若用户不同意授权，直接暴力退出应用。。
                Toast.makeText (this, "授权失败", Toast.LENGTH_SHORT).show ( );
                finish ( );
            }
        }
    }


    class register_newstore implements Runnable{
        @Override
        public void run(){
            Message message = new Message ();
            if(upload_info ())
                message.arg1 = 1;
            else
                message.arg1 = 0;
            handler.sendMessage (message);
        }
    }

    void init(){
        store_img = findViewById (R.id.store_img);
        store_name = findViewById (R.id.store_name);
        store_add = findViewById (R.id.store_add);
        create = findViewById (R.id.create_store);
        start_find_img ();

        create.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                new Thread (new register_newstore ()).start ();
            }
        });

    }

    //寻找图片
    void start_find_img(){
        store_img.setOnClickListener (new View.OnClickListener ( ) {
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
    //上传
    boolean upload_info(){
        String img_base = fileTobase64 (img_path);
        OkHttpClient client = singleservice.getClient ();
        FormBody formBody = new FormBody.Builder ()
                .add ("store_name" , store_name.getText ().toString ())
                .add ("store_add" , store_add.getText ().toString ())
                .add ("store_img_base64",img_base)
                .build ();
        Request request = new Request.Builder ()
                .url ("http://47.100.202.93/waimai/register_store_info.php")
                .post (formBody)
                .build ();
        Call call = client.newCall (request);

        //同步请求
        try {
            Response response = call.execute ();
            if (response.isSuccessful ()){
                Log.e ("tag",""+response.body ().string ());
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace ( );
        }
        return false;
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

                store_img.setImageURI (uri);
            }

            super.onActivityResult (requestCode , resultCode , data);
        }
    }
}
