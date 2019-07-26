package com.example.black.waimai_seller.activity;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.example.black.waimai_seller.R;

import java.util.ArrayList;
import java.util.List;

public class GetAppActvity extends Activity {

    private TextView allApp;
    private TextView realApp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PackageManager pm = getApplication().getPackageManager();
        setContentView(R.layout.activity_getapp);

        allApp = findViewById(R.id.allapp);
        realApp = findViewById(R.id.realapp);

        List<PackageInfo> appInfo = pm.getInstalledPackages(0);

        Log.v("AllApp",""+appInfo);
        for(PackageInfo tmp : appInfo){
            String name = tmp.packageName + " " + tmp.applicationInfo.loadLabel(pm).toString();
            allApp.append(name+"\n");
        }
        allApp.append(""+appInfo.size());

        //过滤系统应用和自身
        List<PackageInfo> realAppInfo = new ArrayList<>();

        for(PackageInfo tmp : appInfo){
            boolean flag = (tmp.applicationInfo.flags > 0 && ApplicationInfo.FLAG_SYSTEM > 0);

            if(flag){
                realAppInfo.add(tmp);
            }
        }

        Log.v("RealApp",""+realAppInfo);
        for (PackageInfo tmp : realAppInfo){
            realApp.append(tmp.packageName+" " + tmp.applicationInfo.loadLabel(pm).toString() + "\n");
        }
        realApp.append(""+realAppInfo.size());
    }
}
