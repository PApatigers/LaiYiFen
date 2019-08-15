package com.example.black.waimai_seller.tool;

import android.graphics.Bitmap;

import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class memorycache {

    private Map<String , Bitmap> cache =  Collections.synchronizedMap (new HashMap<String, Bitmap> ());
    //此处以url为id保存
    public Bitmap getcache(String id){
        if(!cache.containsKey (id))
            return null;
        Bitmap bitmap = cache.get (id);
        return bitmap;
    }

    public void putcache(String id , Bitmap bitmap){
        cache.put (id,bitmap);
    }

    public void clearcache(){
        cache.clear ();
    }
}
