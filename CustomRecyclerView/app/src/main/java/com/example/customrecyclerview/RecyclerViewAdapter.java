package com.example.customrecyclerview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {
    ArrayList<RecyclerViewItem> arrayList = new ArrayList<RecyclerViewItem>();
    RecyclerItemClickListener recyclerItemClickListener;

    interface RecyclerItemClickListener {
        public void onItemClick(int position, View view);
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView_title;
        TextView textView_content;
        Button button;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_imageview);
            textView_title = itemView.findViewById(R.id.item_title);
            textView_content = itemView.findViewById(R.id.item_content);
            button = itemView.findViewById(R.id.item_btn);
            settingOnClick(itemView);
        }

        public void settingOnClick(View view) {
            view.setOnClickListener(new View.OnClickListener() {
                // Adapter외부 액티비티나 프래그먼트의 리스너를 호출할 경우.
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    recyclerItemClickListener.onItemClick(position, v);
                }
            });
            button.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    textView_content.setText(textView_content.getText().toString()+" 눌렀음.");
                }
            });
        }
    }

    public RecyclerViewAdapter(ArrayList<RecyclerViewItem> arrayList) {
        this.arrayList = arrayList;
    }

    public RecyclerViewAdapter(RecyclerItemClickListener recyclerItemClickListener) {
        this.recyclerItemClickListener = recyclerItemClickListener;
    }

    @Override
    public RecyclerViewAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerViewHolder recyclerViewHolder;
        LayoutInflater layoutInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.recyclerview_item, parent, false);
        recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.RecyclerViewHolder holder, int position) {
        RecyclerViewItem item = arrayList.get(position);
        holder.imageView.setImageDrawable(item.getDrawable());
        holder.textView_title.setText(item.getTitle());
        holder.textView_content.setText(item.getContent());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void addItem(Drawable drawable, String title, String content) {
        RecyclerViewItem recyclerViewItem = new RecyclerViewItem();
        recyclerViewItem.setTitle(title);
        recyclerViewItem.setContent(content);
        recyclerViewItem.setDrawable(drawable);
        arrayList.add(recyclerViewItem);
    }
}
