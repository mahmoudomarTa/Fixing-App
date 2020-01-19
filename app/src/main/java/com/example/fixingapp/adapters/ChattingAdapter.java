package com.example.fixingapp.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fixingapp.R;
import com.example.fixingapp.models.Message;
import com.example.fixingapp.utils.Utils;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChattingAdapter extends RecyclerView.Adapter<ChattingAdapter.CahattingViewHolder> {
    private static final int TYPE_SEND = 1;
    private static final int TYPE_RESEIVED = 2;
    private ArrayList<Message> messages;
    private Activity activity;
    private Utils utils = new Utils(activity);


    public ChattingAdapter(ArrayList<Message> messages, Activity activity) {
        this.messages = messages;
        this.activity = activity;
    }

    @NonNull
    @Override
    public CahattingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        CahattingViewHolder holder = null ;
        if (viewType==TYPE_SEND) {
           holder = new CahattingViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.send_message, parent, false));
        }else if (viewType==TYPE_RESEIVED){
            holder = new CahattingViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.received_message, parent, false));
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CahattingViewHolder holder, int position) {
        holder.tvMsg.setText(messages.get(position).getText());
        holder.tvMsgTime.setText(utils.setUpMessageTime(messages.get(position).getTime()));
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class CahattingViewHolder extends RecyclerView.ViewHolder {
        TextView tvMsg,tvMsgTime;

        public CahattingViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMsg = itemView.findViewById(R.id.tvMsg);
            tvMsgTime = itemView.findViewById(R.id.tvMsgTime);
        }
    }
}
