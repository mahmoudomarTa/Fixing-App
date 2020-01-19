package com.example.fixingapp.authentication;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fixingapp.R;
import com.example.fixingapp.adapters.ViewPagerAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class OnBoardingFragment extends Fragment {
    private static ViewPager pager;

    public OnBoardingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_on_boarding, container, false);
        pager = view.findViewById(R.id.onBoardingViewPager);


        OnBoardingFirst first = new OnBoardingFirst();
        OnBoardingSecond second = new OnBoardingSecond();
        OnBoardingThird third = new OnBoardingThird();

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(first);
        fragments.add(second);
        fragments.add(third);


        pager.setAdapter(new ViewPagerAdapter(getActivity().getSupportFragmentManager(),fragments,null));
        return view;
    }

    public static void setCurrentItem(int position){
        pager.setCurrentItem(position);
    }

    public static int getCurrentItem(){
        return pager.getCurrentItem();
    }
}
