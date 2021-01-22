package com.example.listview_sort;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends Activity implements View.OnClickListener {
    ArrayList<ListViewItem> arrayList;
    ListViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.main_listview);
        arrayList = new ArrayList<ListViewItem>();
        adapter = new ListViewAdapter(arrayList);
        adapter.addItem(4, "cccc");
        adapter.addItem(3, "aaaa");
        adapter.addItem(5, "eeee");
        adapter.addItem(2, "ff");
        adapter.addItem(1, "hhh");
        listView.setAdapter(adapter);

        Button button_NoASC = (Button) findViewById(R.id.main_btn_NoASC);
        Button button_NoDESC = (Button) findViewById(R.id.main_btn_NoDESC);
        Button button_TextAsc = (Button) findViewById(R.id.main_btn_TextAsc);
        Button button_TextDesc = (Button) findViewById(R.id.main_btn_TextDesc);
        button_NoASC.setOnClickListener(this);
        button_NoDESC.setOnClickListener(this);
        button_TextAsc.setOnClickListener(this);
        button_TextDesc.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_btn_NoASC: {
                Collections.sort(arrayList, new Comparator<ListViewItem>() {
                    @Override
                    public int compare(ListViewItem o1, ListViewItem o2) {
                        if(o1.no == o2.no){
                            return o1.Text.compareTo(o2.Text);
                        }
                        return o1.no - o2.no;
                    }
                });
                adapter.notifyDataSetChanged();
                break;
            }
            case R.id.main_btn_NoDESC: {
                Collections.sort(arrayList, new Comparator<ListViewItem>() {
                    @Override
                    public int compare(ListViewItem o1, ListViewItem o2) {
                        if(o1.no == o2.no){
                            return o1.Text.compareTo(o2.Text);
                        }
                        return o2.no - o1.no;
                    }
                });
                adapter.notifyDataSetChanged();
                break;
            }
            case R.id.main_btn_TextAsc: {
                Collections.sort(arrayList, new Comparator<ListViewItem>() {
                    @Override
                    public int compare(ListViewItem o1, ListViewItem o2) {
                        return o1.Text.compareTo(o2.Text);
                    }
                });
                adapter.notifyDataSetChanged();
                break;
            }
            case R.id.main_btn_TextDesc: {
                Collections.sort(arrayList, new Comparator<ListViewItem>() {
                    @Override
                    public int compare(ListViewItem o1, ListViewItem o2) {
                        return o2.Text.compareTo(o1.Text);
                    }
                });
                adapter.notifyDataSetChanged();
                break;
            }
        }
    }
}