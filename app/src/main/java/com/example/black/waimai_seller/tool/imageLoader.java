package com.example.black.waimai_seller.tool;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class imageLoader {


    private Bitmap bitmap ;
    private String str_url;
    private ImageView imageView;
    ExecutorService executorService;
    private Map<ImageView, String> imageViews = Collections.synchronizedMap(new WeakHashMap<ImageView, String> ());

    public imageLoader(){
        executorService= Executors.newFixedThreadPool(5);
    }

    Handler handler = new Handler (){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage (msg);
            if(msg.arg1 == 1 && bitmap!= null){
                //bitmap = yasuo_img (bitmap);
                imageView.setImageBitmap (bitmap);
            }
        }
    };

    public void displayImg(String url, ImageView imageView){
        imageViews.put (imageView,url);

        queueImg(url,imageView);
    }

    void queueImg(String uri , ImageView imageView){
        PhotoToLoad photoToLoad = new PhotoToLoad (uri,imageView);
        PhotoToLoad p=new PhotoToLoad(uri, imageView);
        executorService.submit(new PhotosLoader(p));
    }

     //任务队列
    private class PhotoToLoad
    {
        public String url;
        public ImageView imageView;
        public PhotoToLoad(String u, ImageView i){
            url=u;
            imageView=i;
        }
    }

    class PhotosLoader implements Runnable {
        PhotoToLoad photoToLoad;
        PhotosLoader(PhotoToLoad photoToLoad){
            this.photoToLoad=photoToLoad;
        }

        @Override
        public void run() {
            Bitmap bmp=getBitmap(photoToLoad.url);
            BitmapDisplayer bd = new BitmapDisplayer(bmp, photoToLoad);
            Activity a=(Activity)photoToLoad.imageView.getContext();
            a.runOnUiThread(bd);
        }
    }

    Bitmap getBitmap(String url1){
        try{
            URL url = new URL (url1);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection ();
            httpURLConnection.setRequestMethod("GET");
            if (200 == httpURLConnection.getResponseCode()) {
                InputStream inputStream = httpURLConnection.getInputStream();
                //解析完毕图片对象后进行返回
                bitmap = BitmapFactory.decodeStream(inputStream);
            }
            Log.e ("tag-img","下载的url"+url1);


        }catch (MalformedURLException e){

        }catch (IOException e) {
            e.printStackTrace ( );
        }
        return bitmap;
    }

    //用于显示位图在UI线程
    class BitmapDisplayer implements Runnable
    {
        Bitmap bitmap;
        PhotoToLoad photoToLoad;
        public BitmapDisplayer(Bitmap b, PhotoToLoad p){bitmap=b;photoToLoad=p;}
        public void run()
        {
            photoToLoad.imageView.setImageBitmap(bitmap);
        }
    }

    class img_thread implements Runnable{
        public void run(){

            Message message = new Message ();
            message.arg1 = 1;
            handler.sendMessage (message);

        }
    }

    //对图片进行压缩
    Bitmap yasuo_img(Bitmap bitmap){
        int width = bitmap.getWidth ();
        int height = bitmap.getHeight ();

        Log.e ("tag-wid",""+width+" "+height);

        //获取imageview大小
        ViewGroup.LayoutParams para = imageView.getLayoutParams();
        int w=para.width;
        int h=para.height;

        //确定比例
        float scale_width = w/width;
        float scale_height = h/height;

        Log.e ("tag-sorce",""+w+ " "+h + " " + width + " " + height);

        Matrix matrix = new Matrix ();
        matrix.postScale (scale_width,scale_height);
        Bitmap newBitmap = Bitmap.createBitmap(bitmap,0,0,width, height,matrix,true);
        return newBitmap;
    }


}
