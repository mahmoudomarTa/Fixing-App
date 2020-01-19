package com.example.fixingapp.adapters;

import com.example.fixingapp.authentication.OnBoardingFirst;
import com.example.fixingapp.authentication.OnBoardingSecond;
import com.example.fixingapp.authentication.OnBoardingThird;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {


    private ArrayList<Fragment> fragments ;
    private ArrayList<String> names ;

    public ViewPagerAdapter(@NonNull FragmentManager fm, ArrayList<Fragment> fragments, ArrayList<String> names) {
        super(fm);
        this.fragments=fragments;
        this.names=names;

    }



    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return names.get(position);
    }
}
