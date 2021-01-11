package com.example.listviewheaderfooter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.main_listview);
        View footer = getLayoutInflater().inflate(R.layout.footer_layout, null, false);
        View header = getLayoutInflater().inflate(R.layout.header_layout, null, false);
        listView.addFooterView(footer);
        listView.addHeaderView(header);

        TextView txt_title = (TextView) header.findViewById(R.id.header_textview_title);
        TextView txt_content = (TextView) header.findViewById(R.id.header_textview_content);
        Button btn_add = (Button) footer.findViewById(R.id.footer_btn_add);
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adapter);

        txt_title.setText("Header And Footer Test");
        txt_content.setText(arrayList.size()+"개의 항목");
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.add(arrayList.size() + 1+"번째 추가되었습니다.");
                txt_content.setText(arrayList.size()+"개의 항목");
                adapter.notifyDataSetChanged();
            }
        });
    }
}