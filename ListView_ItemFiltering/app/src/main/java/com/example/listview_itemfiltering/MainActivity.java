package com.example.listview_itemfiltering;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("hello");
        arrayList.add("how");
        arrayList.add("when");
        arrayList.add("alert");
        arrayList.add("abs");
        arrayList.add("enter");
        arrayList.add("keyboard");
        arrayList.add("text");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayList);
        ListView listView = (ListView) findViewById(R.id.main_listview);
        listView.setAdapter(arrayAdapter);
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