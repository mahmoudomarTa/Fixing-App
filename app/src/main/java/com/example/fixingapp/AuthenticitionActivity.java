package com.example.fixingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.fixingapp.authentication.OnBoardingFragment;

public class AuthenticitionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticition);
        getSupportFragmentManager().beginTransaction().replace(R.id.authenticationContainer,new OnBoardingFragment()).commit();
    }
}
