package com.example.fixingapp.authentication;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.fixingapp.MainActivityWorker;
import com.example.fixingapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReadyFragment extends Fragment {

    Button btnGoToHome;

    public ReadyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ready, container, false);
        btnGoToHome = view.findViewById(R.id.btnGoToHome);
        btnGoToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MainActivityWorker.class));
            }
        });

        return view;
    }

}
