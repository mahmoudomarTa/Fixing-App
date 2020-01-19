package com.example.fixingapp.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fixingapp.R;
import com.example.fixingapp.models.Job;
import com.example.fixingapp.utils.Utils;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomeJobsAdapter extends RecyclerView.Adapter<HomeJobsAdapter.HomeJobViewHolder> {

    private ArrayList<Job> jobs;
    private OnJobItemCickListener onJobItemCickListener;
    private Activity activity;
    private Utils utils = new Utils(activity);


    public void setOnJobItemCickListener(OnJobItemCickListener onJobItemCickListener) {
        this.onJobItemCickListener = onJobItemCickListener;
    }

    public HomeJobsAdapter(ArrayList<Job> jobs, Activity activity) {
        this.jobs = jobs;
        this.activity = activity;
    }

    @NonNull
    @Override
    public HomeJobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeJobViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.job_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final HomeJobViewHolder holder, final int position) {
        holder.tvCustomerName.setText(jobs.get(position).getCustomerName());
        holder.tvPrice.setText(jobs.get(position).getPrice()+" $");
        holder.tvJobDescription.setText(jobs.get(position).getJobDescription());
        holder.tvNegotiationCount.setText(jobs.get(position).getNegotiationCount()+"");
        holder.tvLikesCount.setText(jobs.get(position).getLikesCount()+"");
        holder.tvTime.setText(utils.setUpJobTime(jobs.get(position).getTime()));
        Glide.with(holder.imgCustomer).load(jobs.get(position).getImgCustomer()).centerCrop().into(holder.imgCustomer);
        holder.imgLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onJobItemCickListener.onLikeClicked(jobs.get(position).getJobId(),holder.imgLike);
            }
        });

        holder.tvLikesCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onJobItemCickListener.onLikeClicked(jobs.get(position).getJobId(),holder.imgLike);
            }
        });

        holder.imgNegotiation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onJobItemCickListener.onNegotiationClicked(jobs.get(position).getJobId());
            }
        });

        holder.tvNegotiationCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onJobItemCickListener.onNegotiationClicked(jobs.get(position).getJobId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    public class HomeJobViewHolder extends RecyclerView.ViewHolder {

        ImageView imgCustomer,imgNegotiation,imgLike,imgShare;
        TextView tvCustomerName,tvPrice,tvJobDescription,tvNegotiationCount,tvLikesCount,tvTime;

        public HomeJobViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCustomer = itemView.findViewById(R.id.imgCustomer);
            imgNegotiation = itemView.findViewById(R.id.imgNegotiation);
            imgLike = itemView.findViewById(R.id.imgLike);
            imgShare = itemView.findViewById(R.id.imgShare);
            tvCustomerName = itemView.findViewById(R.id.tvCustomerName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvJobDescription = itemView.findViewById(R.id.tvJobDescription);
            tvNegotiationCount = itemView.findViewById(R.id.tvNegotiationCount);
            tvLikesCount = itemView.findViewById(R.id.tvLikesCount);
            tvTime= itemView.findViewById(R.id.tvTime);
        }


    }
    public interface OnJobItemCickListener{
        void onLikeClicked(String JobId,ImageView imageView);
        void onNegotiationClicked(String JobId);
        void onShareClicked(String JobId);
    }


}
