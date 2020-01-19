package com.example.fixingapp.workerFragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fixingapp.R;
import com.example.fixingapp.adapters.HomeJobsAdapter;
import com.example.fixingapp.firebase.Database;
import com.example.fixingapp.messageAndChats.ChatFragment;
import com.example.fixingapp.messageAndChats.NegotiationFragment;
import com.example.fixingapp.models.Job;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkerHomeFragment extends Fragment {
    AutoCompleteTextView actvStartSearchWorker;
    RecyclerView rvHomeWorker;
    private Database database ;
    public WorkerHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_worker_home, container, false);
        database=new Database(getActivity());
        actvStartSearchWorker = view.findViewById(R.id.actvStartSearchWorker);
        rvHomeWorker=view.findViewById(R.id.rvHomeWorker);


        ImageSpan imageSpan = new ImageSpan(getContext(), R.drawable.ic_search);
        SpannableString spannableString = new SpannableString(getString(R.string.startYourResearch));
        int start = 0;
        int end = 1;
        int flag = 0;
        spannableString.setSpan(imageSpan, start, end, flag);

        actvStartSearchWorker.setHint(spannableString);

        rvHomeWorker.setLayoutManager(new LinearLayoutManager(getContext()));

        //TODO : get data from firebase
        ArrayList<Job> jobs=new ArrayList<>();
        if (database.getAllJobs()!=null){
            jobs=database.getAllJobs();
            Log.d("momo",jobs.toString());
        }else {
            Log.d("momo","jobs is null");
            Toast.makeText(getContext(), "jobs is null", Toast.LENGTH_SHORT).show();
        }
//
//        Job job = new Job("bcvv","vcvvc","mahmoud","hello world!!",1000.0,100,50,211211,null);
//        jobs.add(job);
        final HomeJobsAdapter homeJobsAdapter=new HomeJobsAdapter(jobs,getActivity());
        database.setJobsArrive(new Database.JobsArrive() {
            @Override
            public void onJobsArrive() {
                homeJobsAdapter.notifyDataSetChanged();
            }
        });

        homeJobsAdapter.setOnJobItemCickListener(new HomeJobsAdapter.OnJobItemCickListener() {

            @Override
            public void onLikeClicked(String JobId, ImageView imageView) {
                //imageView.setImageResource(R.drawable.ic_love);
                //TODO : increase likes count
            }

            @Override
            public void onNegotiationClicked(String JobId) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.mainContainerWorker,new NegotiationFragment())
                        .addToBackStack(null)
                        .commit();
            }

            @Override
            public void onShareClicked(String JobId) {

            }
        });
        rvHomeWorker.setAdapter(homeJobsAdapter);

        return view;
    }

}
