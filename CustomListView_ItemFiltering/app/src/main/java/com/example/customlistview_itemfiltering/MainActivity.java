package com.example.customlistview_itemfiltering;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.core.content.ContextCompat;

import com.example.customlistview_itemfiltering.R;

import java.util.ArrayList;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListViewAdapter listViewAdapter = new ListViewAdapter();
        listViewAdapter.addItem(ContextCompat.getDrawable(this,R.drawable.setting),"setting","this is setting item");
        listViewAdapter.addItem(ContextCompat.getDrawable(this,R.drawable.memo),"memo","this is memo item");
        listViewAdapter.addItem(ContextCompat.getDrawable(this,R.drawable.terminal),"terminal","this is terminal item");
        listViewAdapter.addItem(ContextCompat.getDrawable(this,R.drawable.trash),"trash","this is trash item");
        ListView listView = (ListView) findViewById(R.id.main_listview);
        listView.setAdapter(listViewAdapter);
        EditText editText = (EditText) findViewById(R.id.main_edittext);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String editStr = editText.getText().toString();
                if(editStr.length() == 0){//원래 공백이면 아무일도 일어나지 않는다.
                    listView.clearTextFilter();
                }else{
                    listView.setFilterText(editStr);//대소문자 구분 안함.
                }
            }
        });
    }
}