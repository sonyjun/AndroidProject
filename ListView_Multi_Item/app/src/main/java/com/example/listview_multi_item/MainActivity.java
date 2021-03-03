package com.example.listview_multi_item;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.main_listview);
        ListViewAdapter adapter = new ListViewAdapter();
        adapter.addItem("안드로이드 프로그래밍","안드로이드 프로그래밍에 대한 내용에 대해 학습합니다.");
        //adapter.addItem(ContextCompat.getDrawable(this,R.drawable.setting),"광고 내용 입니다1");
        adapter.addItem("객체지향 프로그래밍","객체지향 프로그래밍에 대한 내용에 대해 학습합니다.");
        adapter.addItem("웹 프로그래밍","웹 프로그래밍에 대한 내용에 대해 학습합니다.");
        adapter.addItem("객체지향 프로그래밍","객체지향 프로그래밍에 대한 내용에 대해 학습합니다.");
        adapter.addItem("웹 프로그래밍","웹 프로그래밍에 대한 내용에 대해 학습합니다.");
        adapter.addItem("객체지향 프로그래밍","객체지향 프로그래밍에 대한 내용에 대해 학습합니다.");
        adapter.addItem("웹 프로그래밍","웹 프로그래밍에 대한 내용에 대해 학습합니다.");
        adapter.addItem("객체지향 프로그래밍","객체지향 프로그래밍에 대한 내용에 대해 학습합니다.");
        adapter.addItem("웹 프로그래밍","웹 프로그래밍에 대한 내용에 대해 학습합니다.");
        adapter.addItem("객체지향 프로그래밍","객체지향 프로그래밍에 대한 내용에 대해 학습합니다.");
        adapter.addItem("웹 프로그래밍","웹 프로그래밍에 대한 내용에 대해 학습합니다.");
        adapter.addItem("객체지향 프로그래밍","객체지향 프로그래밍에 대한 내용에 대해 학습합니다.");
        adapter.addItem("웹 프로그래밍","웹 프로그래밍에 대한 내용에 대해 학습합니다.");
        adapter.addItem("객체지향 프로그래밍","객체지향 프로그래밍에 대한 내용에 대해 학습합니다.");
        adapter.addItem("웹 프로그래밍","웹 프로그래밍에 대한 내용에 대해 학습합니다.");
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.setting),"광고 내용 입니다3");
        listView.setAdapter(adapter);

    }
}