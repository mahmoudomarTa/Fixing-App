package com.example.fixingapp.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fixingapp.R;
import com.example.fixingapp.messageAndChats.Conversation;
import com.example.fixingapp.models.User;
import com.example.fixingapp.utils.Utils;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessagesViewHolder> {

    private ArrayList<Conversation> conversations;
    private Activity activity;
    private Utils utils =new Utils(activity);
    private OnChatItemClickListener chatItemClickListener;

    public void setChatItemClickListener(OnChatItemClickListener chatItemClickListener) {
        this.chatItemClickListener = chatItemClickListener;
    }

    public MessagesAdapter(ArrayList<Conversation> conversations, Activity activity) {
        this.conversations = conversations;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MessagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MessagesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesViewHolder holder, final int position) {
        holder.tvLastestMessage.setText(conversations.get(position).getLastMsg().getText());
        holder.tvPersonName.setText(conversations.get(position).getLastMsg().getSenderId());
        if(conversations.get(position).getLastMsg().getSenderId().equals("")){
        holder.tvYou.setVisibility(View.VISIBLE);
        }
        holder.tvLastestMessageTime.setText(utils.setUpMessageTime(conversations.get(position).getLastMsg().getTime()));
        Glide.with(holder.imgPerson).load(conversations.get(position).getSpeakerImg()).into(holder.imgPerson);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatItemClickListener.OnChatItemClicked(conversations.get(position).getSpeakerId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return conversations.size();
    }

    public class MessagesViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPerson;
        TextView tvPersonName,tvLastestMessage,tvYou,tvLastestMessageTime;


        public MessagesViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPerson=itemView.findViewById(R.id.imgPerson);
            tvPersonName = itemView.findViewById(R.id.tvPersonName);
            tvLastestMessage = itemView.findViewById(R.id.tvLastestMessage);
            tvYou = itemView.findViewById(R.id.tvYou);
            tvLastestMessageTime = itemView.findViewById(R.id.tvLastestMessageTime);

        }
    }
    public interface OnChatItemClickListener{
        void OnChatItemClicked(String speakerId);
    }
}
