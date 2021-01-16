package com.example.listfragment_customlistview;

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
    ArrayList<ListItem> arrayList = new ArrayList<>();

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
            convertView = inflater.inflate(R.layout.listfargment_item, parent, false);
        }

        ListItem item = arrayList.get(position);

        ImageView imageView = convertView.findViewById(R.id.item_imageview);
        TextView textView_title = convertView.findViewById(R.id.item_textview_title);
        TextView textView_content = convertView.findViewById(R.id.item_textview_content);
        imageView.setImageDrawable(item.getDrawable());
        textView_title.setText(item.getTitle());
        textView_content.setText(item.getContent());
        return convertView;
    }

    public void addItem(Drawable drawable, String title, String content){
        ListItem item = new ListItem();
        item.setContent(content);
        item.setDrawable(drawable);
        item.setTitle(title);
        arrayList.add(item);
    }
}
