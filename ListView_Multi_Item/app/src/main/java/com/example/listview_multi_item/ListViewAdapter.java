package com.example.listview_multi_item;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ListViewAdapter extends BaseAdapter {
    ArrayList<ListViewItem> arrayList = new ArrayList<ListViewItem>();
    private static final int ITEM_VIEW_TYPE_STR = 0;
    private static final int ITEM_VIEW_TYPE_IMG = 1;

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public ListViewItem getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListViewItem item = arrayList.get(position);
        Context context = parent.getContext();
        int type = item.getType();
        if (convertView == null) {// null이 아닌경우는 처음에 만들어지는 경우인가??
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            switch (type) {
                case ITEM_VIEW_TYPE_STR: {
                    convertView = inflater.inflate(R.layout.item1_layout, parent, false);
                    TextView textView_title = convertView.findViewById(R.id.item1_textview_title);
                    TextView textView_content = convertView.findViewById(R.id.item1_textview_content);
                    textView_title.setText(item.getItem1_txt_title());
                    textView_content.setText(item.getItem1_txt_content());
                    break;
                }
                case ITEM_VIEW_TYPE_IMG: {
                    convertView = inflater.inflate(R.layout.item2_layout, parent, false);
                    ImageView imageView_icon = convertView.findViewById(R.id.item2_imageview_icon);
                    TextView textView_content = convertView.findViewById(R.id.item2_textview_content);
                    imageView_icon.setImageDrawable(item.getItem2_drawable());
                    textView_content.setText(item.getItem2_txt_content());
                    break;
                }
            }
        }
        return convertView;
    }

    public void addItem(String title, String content) {
        ListViewItem item = new ListViewItem();
        item.setItem1_txt_title(title);
        item.setItem1_txt_content(content);
        item.setType(0);
        arrayList.add(item);
    }

    public void addItem(Drawable drawable, String content) {
        ListViewItem item = new ListViewItem();
        item.setItem2_drawable(drawable);
        item.setItem2_txt_content(content);
        item.setType(1);
        arrayList.add(item);
    }
}
