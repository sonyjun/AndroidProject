package com.example.listviewcrud;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener {
    int accumulateIndex = 0;
    ArrayList<String> items;
    ArrayAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.main_listview);
        items = new ArrayList<String>();//adapter에 전달될 데이터
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, items);
        //Android SDK에서 제공하는 ArrayAdapter를 single_choice로 제공되는 view를 테마로 하여 view를 구성한다.

        listView.setAdapter(adapter);

        Button btn_add = (Button) findViewById(R.id.main_btn_add);
        Button btn_edit = (Button) findViewById(R.id.main_btn_edit);
        Button btn_delete = (Button) findViewById(R.id.main_btn_delete);
        btn_add.setOnClickListener(this);
        btn_edit.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_btn_add: {
                items.add(accumulateIndex++ + "번째 값 추가");
                adapter.notifyDataSetChanged();// adapter에 값이 추가되었으니 갱신을 하라고 알려줌.
                break;
            }
            case R.id.main_btn_edit: {
                int checkedPos = listView.getCheckedItemPosition();// listview에서 제공하는 함수. 체크되어진 항목의 위치를 반환함.
                if(items.size() > 0 && checkedPos != -1 && checkedPos < items.size()){
                    items.set(checkedPos,items.get(checkedPos)+" 수정됨");
                    adapter.notifyDataSetChanged();
                }
                /*SparseBooleanArray posArr = listView.getCheckedItemPositions();//체크되어진 것들의 갯수만큼 반환, keyAt() 함수로 위치 반환 받아서 활용
                for (int i = 0; i < posArr.size(); i++) {
                    Log.e("값 ","i: "+i+ "  "+posArr.keyAt(i)+"");
                }*/
                break;
            }
            case R.id.main_btn_delete: {
                int checkedPos = listView.getCheckedItemPosition();
                if (items.size() > 0 && checkedPos != -1 && checkedPos < items.size()) {
                    items.remove(checkedPos);
                    adapter.notifyDataSetChanged();
                    listView.clearChoices();
                }
                break;
            }
        }
    }
}