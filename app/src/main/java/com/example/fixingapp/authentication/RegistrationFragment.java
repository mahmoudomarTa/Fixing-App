package com.example.fixingapp.authentication;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fixingapp.R;
import com.example.fixingapp.adapters.ViewPagerAdapter;
import com.example.fixingapp.utils.Constants;
import com.example.fixingapp.utils.Utils;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationFragment extends Fragment {

    ViewPager registrationViewPager;
    TabLayout signInUpTabLayout;
    public RegistrationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        registrationViewPager = view.findViewById(R.id.registrationViewPager);
        signInUpTabLayout =view.findViewById(R.id.signInUpTabLayout);


        ArrayList<Fragment> fragments = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();
        names.add(getString(R.string.signIn));
        names.add(getString(R.string.signUp));

        if (getArguments()!=null){
            if (getArguments().getString(Constants.TYPE).equals(Utils.USER)){

                SignInUserFragment signInUserFragment = new SignInUserFragment();
                SignUpUser signUpUser = new SignUpUser();

                Bundle bundle = new Bundle();
                bundle.putString(Constants.TYPE,Constants.TYPE_USER);
                signInUserFragment.setArguments(bundle);
                signUpUser.setArguments(bundle);
                fragments.add(signInUserFragment);
                fragments.add(signUpUser);
            }else {
                SignUpWorkerFragment signUpWorkerFragment = new SignUpWorkerFragment();
                SignInWorkerFragment signInWorkerFragment = new SignInWorkerFragment();

                Bundle bundle = new Bundle();
                bundle.putString(Constants.TYPE,Constants.TYPE_WORKER);
                signUpWorkerFragment.setArguments(bundle);
                signInWorkerFragment.setArguments(bundle);

                fragments.add(signInWorkerFragment);
                fragments.add(signUpWorkerFragment);
            }
        }

        registrationViewPager.setAdapter(new ViewPagerAdapter(getActivity().getSupportFragmentManager(),fragments,names));
        signInUpTabLayout.setupWithViewPager(registrationViewPager);
        return view;
    }

}