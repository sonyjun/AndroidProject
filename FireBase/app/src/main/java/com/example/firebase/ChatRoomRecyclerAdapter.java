package com.example.firebase;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatRoomRecyclerAdapter extends RecyclerView.Adapter<ChatRoomRecyclerAdapter.ChatRoomViewHolder> {
    ArrayList<ChatRoomItem> arrayList = new ArrayList<ChatRoomItem>();
    Context context;

    class ChatRoomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView_roomName;

        public ChatRoomViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_roomName = itemView.findViewById(R.id.chatroom_textview);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String roomName = arrayList.get(getAdapterPosition()).getTitle();
            Intent intent = new Intent(context, ChatActivity.class);
            intent.putExtra("room_name", roomName);
            context.startActivity(intent);
        }
    }

    public ChatRoomRecyclerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ChatRoomRecyclerAdapter.ChatRoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.chatroom_item, parent, false);
        ChatRoomViewHolder chatRoomViewHolder = new ChatRoomViewHolder(view);
        return chatRoomViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatRoomRecyclerAdapter.ChatRoomViewHolder holder, int position) {
        holder.textView_roomName.setText(arrayList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void addItem(ChatRoomItem item) {
        arrayList.add(item);
        notifyDataSetChanged();
    }
}
