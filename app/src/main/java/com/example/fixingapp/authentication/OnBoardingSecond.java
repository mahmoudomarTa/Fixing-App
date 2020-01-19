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
public class OnBoardingSecond extends Fragment {

    private TextView tvSkip2;
    private ImageView imgNext2;

    public OnBoardingSecond() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_on_boarding_second, container, false);
        tvSkip2 = view.findViewById(R.id.tvSkip2);
        imgNext2 = view.findViewById(R.id.imgNext2);
        tvSkip2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnBoardingFragment.setCurrentItem(2);
            }
        });
        imgNext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnBoardingFragment.setCurrentItem(OnBoardingFragment.getCurrentItem()+1);
            }
        });

        return view;
    }

}
