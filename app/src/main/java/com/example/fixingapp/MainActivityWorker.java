package com.example.fixingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.fixingapp.messageAndChats.MessagesFragment;
import com.example.fixingapp.workerFragments.WorkerHomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivityWorker extends AppCompatActivity {

    WorkerHomeFragment workerHomeFragment;
    MessagesFragment messagesFragment;
    BottomNavigationView bnvWorker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.worker_activity_main);

        workerHomeFragment = new WorkerHomeFragment();
        messagesFragment = new MessagesFragment();

        bnvWorker = findViewById(R.id.bnvWorker);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainContainerWorker, workerHomeFragment)
                .commit();

        bnvWorker.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                    case R.id.home:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.mainContainerWorker, workerHomeFragment)
                                .commit();
                        break;

                    case R.id.proposal:
                        
                        break;

                    case R.id.messages:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.mainContainerWorker, messagesFragment)
                                .commit();
                        break;
                }


                return true;
            }
        });


    }
}
