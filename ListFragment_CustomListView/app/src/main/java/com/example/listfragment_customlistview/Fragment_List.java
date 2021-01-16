package com.example.listfragment_customlistview;

import android.app.ListFragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;

public class Fragment_List extends ListFragment {
    ListAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        adapter = new ListAdapter();
        setListAdapter(adapter);
        adapter.addItem(ContextCompat.getDrawable(getActivity(),R.drawable.setting),"세팅","세팅은 아무렇게나 하세요.");
        adapter.addItem(ContextCompat.getDrawable(getActivity(),R.drawable.terminal),"터미널","터미널이 멋지게 생겻네");
        adapter.addItem(ContextCompat.getDrawable(getActivity(),R.drawable.trash),"쓰레기통","쓰레기를 꼭 비워주세요");
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    public void addItem(Drawable drawable, String title,String content){
        adapter.addItem(drawable,title,content);
    }

    public void update(){
        adapter.notifyDataSetChanged();
    }
}
