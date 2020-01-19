package com.example.fixingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.fixingapp.utils.Utils;

public class SplashActivity extends AppCompatActivity {

    private Utils utils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        utils=new Utils(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (utils.isFirst()){
                    Intent intent = new Intent(getApplicationContext(),AuthenticitionActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(getApplicationContext(), MainActivityWorker.class);
                    startActivity(intent);
                }
                finish();
            }
        },2000);
    }
}