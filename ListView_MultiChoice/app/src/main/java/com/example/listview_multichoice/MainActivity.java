package com.example.listview_multichoice;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity {
    int itemIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.main_listview);
        Button btn_add = (Button) findViewById(R.id.main_btn_add);
        Button btn_delete = (Button) findViewById(R.id.main_btn_delete);
        Button btn_selectall = (Button) findViewById(R.id.main_btn_selectall);

        ArrayList<String> arrayList = new ArrayList<String>();
        for (itemIndex = 1; itemIndex < 5; itemIndex++) {
            arrayList.add(itemIndex + "번째 아이템");
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, arrayList);
        listView.setAdapter(adapter);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.add(itemIndex++ + "번째 아이템 추가");
                adapter.notifyDataSetChanged();
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SparseBooleanArray sparseBooleanArray = listView.getCheckedItemPositions();
                if (arrayList.size() > 0 && sparseBooleanArray != null) {
                    for (int i = sparseBooleanArray.size() - 1; i >= 0; i--) {
                        //Log.e("삭제될 녀석들 ", sparseBooleanArray.keyAt(i) + "");
                        arrayList.remove(sparseBooleanArray.keyAt(i));
                    }
                    adapter.notifyDataSetChanged();
                    listView.clearChoices();
                }
            }
        });

        btn_selectall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < arrayList.size(); i++) {
                    listView.setItemChecked(i, true);
                }
            }
        });
    }
}