package com.example.customchoicelistview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import androidx.core.content.ContextCompat;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.main_listview);
        ListAdapter listAdapter = new ListAdapter();
        listAdapter.addItem(ContextCompat.getDrawable(this,R.drawable.memo),"this is memo");
        listAdapter.addItem(ContextCompat.getDrawable(this,R.drawable.setting),"this is setting");
        listAdapter.addItem(ContextCompat.getDrawable(this,R.drawable.trash),"this is trash");
        listAdapter.addItem(ContextCompat.getDrawable(this,R.drawable.terminal),"this is terminal");
        listView.setAdapter(listAdapter);
    }
}