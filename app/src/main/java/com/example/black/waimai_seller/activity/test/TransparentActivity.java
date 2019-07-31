package com.example.black.waimai_seller.activity.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.black.waimai_seller.R;

public class TransparentActivity extends Activity {

    private Button finish;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.v("zhouqi_test","onCreate" + this.getLocalClassName());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transparent);

        finish = findViewById(R.id.finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TransparentActivity.this,TestActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        Log.v("zhouqi_test", "onStart" + this.getLocalClassName());
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.v("zhouqi_test", "onResume" + this.getLocalClassName());
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.v("zhouqi_test", "onPause" + this.getLocalClassName());
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.v("zhouqi_test", "onStop" + this.getLocalClassName());
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.v("zhouqi_test", "onDestory" + this.getLocalClassName());
        super.onDestroy();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.v("zhouqi_test", "onNewIntent" + this.getLocalClassName());
        super.onNewIntent(intent);
    }

    @Override
    protected void onRestart() {
        Log.v("zhouqi_test", "onRestart" + this.getLocalClassName());
        super.onRestart();
    }
}
