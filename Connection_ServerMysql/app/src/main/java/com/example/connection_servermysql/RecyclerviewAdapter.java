package com.example.connection_servermysql;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.RecyclerviewHolder> {
    Context context;
    ArrayList<RecyclerviewItem> arrayList;


    public class RecyclerviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView_name;
        TextView textView_sex;
        TextView textView_age;
        TextView textView_address;

        public RecyclerviewHolder(@NonNull View itemView) {
            super(itemView);
            textView_name = itemView.findViewById(R.id.item_name);
            textView_sex = itemView.findViewById(R.id.item_sex);
            textView_age = itemView.findViewById(R.id.item_age);
            textView_address = itemView.findViewById(R.id.item_address);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            ClickItemDialog clickItemDialog = new ClickItemDialog(context, RecyclerviewAdapter.this, getAdapterPosition());
            clickItemDialog.callDialog(arrayList.get(getAdapterPosition()));
        }
    }

    public RecyclerviewAdapter(Context context, ArrayList<RecyclerviewItem> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.recyclerview_item, parent, false);
        RecyclerviewHolder recyclerviewHolder = new RecyclerviewHolder(view);
        return recyclerviewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerviewHolder holder, int position) {
        holder.textView_name.setText(arrayList.get(position).name);
        holder.textView_sex.setText(arrayList.get(position).sex);
        holder.textView_age.setText(arrayList.get(position).age + "");
        holder.textView_address.setText(arrayList.get(position).address);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void addItem(RecyclerviewItem item) {
        arrayList.add(item);
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        arrayList.remove(position);
        notifyDataSetChanged();
    }
}
