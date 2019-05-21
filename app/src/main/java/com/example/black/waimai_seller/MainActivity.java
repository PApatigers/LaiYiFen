package com.example.black.waimai_seller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button newstore,oldstore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        newstore = findViewById (R.id.newstore);
        oldstore = findViewById (R.id.oldstore);
        newstore.setOnClickListener (this);
        oldstore.setOnClickListener (this);
    }

    @Override
    public void onClick(View v){
        switch(v.getId ()){
            case R.id.newstore:
                Intent intent1 = new Intent (this,com.example.black.waimai_seller.activity.new_store.class);
                startActivity (intent1);
                break;
            case R.id.oldstore:
                Intent intent2 = new Intent (this,com.example.black.waimai_seller.activity.oldstoreManger.class);
                startActivity (intent2);
                break;
        }
    }
}
