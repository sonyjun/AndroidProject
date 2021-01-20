package com.example.customchoicelistview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {
    ArrayList<Item> arrayList = new ArrayList<Item>();

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }
        Item item = arrayList.get(position);
        TextView textView = convertView.findViewById(R.id.item_textview);
        ImageView imageView = convertView.findViewById(R.id.item_imageview);
        textView.setText(item.getContent());
        imageView.setImageDrawable(item.getDrawable());
        return convertView;
    }

    public void addItem(Drawable drawable, String content) {
        Item item = new Item();
        item.setContent(content);
        item.setDrawable(drawable);
        arrayList.add(item);
    }
}
