package com.example.firebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatContentRecyclerAdapter extends RecyclerView.Adapter<ChatContentRecyclerAdapter.ChatContentViewHolder>{
    ArrayList<ChatContentItem> arrayList = new ArrayList<ChatContentItem>();

    class ChatContentViewHolder extends RecyclerView.ViewHolder {
        TextView textView_content;
        public ChatContentViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_content = itemView.findViewById(R.id.chatcontent_textview);
        }
    }

    @NonNull
    @Override
    public ChatContentRecyclerAdapter.ChatContentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.chatcontent_item, parent, false);
        ChatContentViewHolder chatRoomViewHolder = new ChatContentViewHolder(view);
        return chatRoomViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatContentRecyclerAdapter.ChatContentViewHolder holder, int position) {
        holder.textView_content.setText(arrayList.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void addItem(ChatContentItem item){
        arrayList.add(item);
        notifyDataSetChanged();
    }
}
