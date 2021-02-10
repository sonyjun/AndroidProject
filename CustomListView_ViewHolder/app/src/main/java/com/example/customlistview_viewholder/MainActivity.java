package com.example.customlistview_viewholder;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.main_listview);
        ListViewAdapter adapter = new ListViewAdapter();
        // custom한 adpater를 만들어줌.

        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.setting), "위 텍스트 1", "아래 텍스트 1");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.setting), "위 텍스트 2", "아래 텍스트 2");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.setting), "위 텍스트 3", "아래 텍스트 3");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.setting), "위 텍스트 4", "아래 텍스트 4");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.setting), "위 텍스트 5", "아래 텍스트 5");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.setting), "위 텍스트 6", "아래 텍스트 6");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.setting), "위 텍스트 7", "아래 텍스트 7");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.setting), "위 텍스트 8", "아래 텍스트 8");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.setting), "위 텍스트 9", "아래 텍스트 9");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.setting), "위 텍스트 10", "아래 텍스트 10");
        //adpater의 addItem 메소드를 호출해서 데이터를 추가.

        listView.setAdapter(adapter);//listview와 adapter를 연결
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //parent : ListView 자체에 대한 참조.
                //view : 클릭이 발생한 View에 대한 참조.
                //position : 클릭된 아이템의 position.
                //id : 클릭된 아이템의 id (adapter의 getId 인듯?)
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position); //adapter의 getItem이 호출되는 거겟네.
                Toast.makeText(MainActivity.this, item.getTxt1() + ", " + item.getTxt2(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}