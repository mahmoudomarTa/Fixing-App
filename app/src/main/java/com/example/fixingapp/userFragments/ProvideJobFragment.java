package com.example.fixingapp.userFragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.fixingapp.R;
import com.example.fixingapp.firebase.Database;
import com.example.fixingapp.models.Job;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProvideJobFragment extends Fragment {

    private Database database;

    public ProvideJobFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_provide_job, container, false);
        database=new Database(getActivity());

        final Job job = new Job("bcvv","vcvvc","mahmoud","hello world!!",1000.0,100,50,211211,null);
        Button btnProvide = view.findViewById(R.id.btnProvide);
        btnProvide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.provideJob(job);
            }
        });


        return view;
    }

}
