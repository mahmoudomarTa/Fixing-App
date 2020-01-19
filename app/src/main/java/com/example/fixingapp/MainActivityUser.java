package com.example.fixingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.fixingapp.userFragments.ProvideJobFragment;

public class MainActivityUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainContainerUser,new ProvideJobFragment()).commit();
    }
}
