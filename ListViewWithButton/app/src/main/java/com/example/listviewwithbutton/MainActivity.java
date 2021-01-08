package com.example.listviewwithbutton;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class MainActivity extends Activity implements ListViewBtnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.main_listview);


        //ListViewAdpater에 있는 addItem함수를 정의해서 값을 전달하는 방법.
        ListViewAdapter adapter1 = new ListViewAdapter();
        for(int i = 0 ; i < 30 ; i++){
            adapter1.addItem(ContextCompat.getDrawable(this, R.drawable.setting), i+"번 아이템");
        }

        //ListViewAdpater에 listener를 전해줘야 할 경우, ArrayList를 DB에서 받아와 통채로 넘거야 할 경우 등등 이렇게 쓰임.
        ArrayList<ListViewItem> arrayList = new ArrayList<ListViewItem>();
        for(int i = 0 ; i < 30 ; i++){
            arrayList.add(new ListViewItem(ContextCompat.getDrawable(this, R.drawable.setting), i+"번 아이템"));
        }
        ListViewAdapter adapter2 = new ListViewAdapter(arrayList,this);//arraylist에 데이터를 담아 넘기고, interface를 통해 구현한 listener를 넘김.
                                                                        // adapter의 btn2가 눌리면 MainActivity의 onListBtnClick이 호출되는 형식.
        //btn2는 listener 전달받아야 호출이 되는 메소드임. listener를 전달 못받으면 아무일 일어나지 않는 쓸모없는 버튼이지.

        listView.setAdapter(adapter2);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("d","dgs");
                Toast.makeText(MainActivity.this, ((ListViewItem) parent.getItemAtPosition(position)).getText(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onListBtnClick(int position) {
        Toast.makeText(MainActivity.this, position + "번째 btn Toast 눌렸습니다.",Toast.LENGTH_SHORT).show();
    }
}