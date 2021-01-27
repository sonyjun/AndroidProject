package com.example.customlistview_itemfiltering;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.customlistview_itemfiltering.R;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter implements Filterable {
    ArrayList<Item> originArrayList = new ArrayList<Item>();
    ArrayList<Item> filteredArrayList = originArrayList;
    Filter filter;

    @Override
    public int getCount() {
        return filteredArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredArrayList.get(position);
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
        Item item = filteredArrayList.get(position);
        ImageView imageView = convertView.findViewById(R.id.item_imageview);
        TextView textView_name = convertView.findViewById(R.id.item_texview_name);
        TextView textView_explanation = convertView.findViewById(R.id.item_texview_explanation);
        imageView.setImageDrawable(item.getDrawable());
        textView_name.setText(item.getName());
        textView_explanation.setText(item.getExplanation());
        Log.e("여기","getView");
        return convertView;
    }

    public void addItem(Drawable drawable, String name, String explanation) {
        Item item = new Item();
        item.setDrawable(drawable);
        item.setName(name);
        item.setExplanation(explanation);
        originArrayList.add(item);
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new CustomFilter();
        }
        return filter;
    }

    class CustomFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            Log.e("여기","1");
            if (constraint == null || constraint.length() == 0 ) {
                results.values = originArrayList;
                results.count = originArrayList.size();
                Log.e("여기","2");
            } else {
                ArrayList<Item> resultArrayList = new ArrayList<Item>();
                for (Item item : originArrayList) {
                    if (item.getName().toUpperCase().contains(constraint.toString().toUpperCase()) ||
                            item.getExplanation().toUpperCase().contains(constraint.toString().toUpperCase())) {
                        resultArrayList.add(item);
                    }
                }
                results.values = resultArrayList;
                results.count = resultArrayList.size();
                Log.e("여기","3");
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredArrayList = (ArrayList<Item>) results.values;
            if(filteredArrayList.size() > 0){
                notifyDataSetChanged();
            }else{
                notifyDataSetInvalidated();
            }
        }
    }
}
