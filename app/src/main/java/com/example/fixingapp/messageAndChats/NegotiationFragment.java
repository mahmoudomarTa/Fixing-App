package com.example.fixingapp.messageAndChats;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fixingapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NegotiationFragment extends Fragment {


    public NegotiationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_negotiation, container, false);
    }

}
