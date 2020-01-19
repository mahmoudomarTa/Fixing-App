package com.example.fixingapp.authentication;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fixingapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OnBoardingFirst extends Fragment {

    private TextView tvSkip;
    private ImageView imgNext;

    public OnBoardingFirst() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_on_boarding_first, container, false);
        tvSkip = view.findViewById(R.id.tvSkip);
        imgNext = view.findViewById(R.id.imgNext);
        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnBoardingFragment.setCurrentItem(2);
            }
        });
        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnBoardingFragment.setCurrentItem(OnBoardingFragment.getCurrentItem()+1);
            }
        });
        return view;
    }

}
