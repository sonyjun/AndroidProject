package com.example.listview_sort;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    ArrayList<ListViewItem> arrayList;

    public ListViewAdapter(ArrayList<ListViewItem> arrayList) {
        this.arrayList = arrayList;
    }

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
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.listview_item, parent, false);
        }
        ListViewItem item = arrayList.get(position);
        TextView textView_no = convertView.findViewById(R.id.item_textview_no);
        TextView textView_text = convertView.findViewById(R.id.item_textview_text);
        textView_no.setText(item.getNo()+"");
        textView_text.setText(item.getText());
        return convertView;
    }

    public void addItem(int no, String text){
        ListViewItem item = new ListViewItem();
        item.setNo(no);
        item.setText(text);
        arrayList.add(item);
    }
}
