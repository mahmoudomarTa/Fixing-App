package com.example.fixingapp.authentication;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.fixingapp.R;
import com.example.fixingapp.utils.Constants;
import com.example.fixingapp.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class OnBoardingThird extends Fragment {

    Button btnUser;
    TextView tvWorker;

    public OnBoardingThird() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_on_boarding_third, container, false);
        btnUser = view.findViewById(R.id.btnUser);
        tvWorker = view.findViewById(R.id.tvWorker);

        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistrationFragment registrationFragment = new RegistrationFragment();
                Bundle bundle = new Bundle();
                bundle.putString(Constants.TYPE, Utils.USER);
                registrationFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.authenticationContainer,registrationFragment).commit();
            }
        });
        tvWorker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistrationFragment registrationFragment = new RegistrationFragment();
                Bundle bundle = new Bundle();
                bundle.putString(Constants.TYPE, Utils.WORKER);
                registrationFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.authenticationContainer,registrationFragment).commit();

            }
        });



        return view;
    }

}
