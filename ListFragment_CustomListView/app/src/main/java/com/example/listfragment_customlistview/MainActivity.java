package com.example.listfragment_customlistview;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.core.content.ContextCompat;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getFragmentManager();
        Fragment_List fragment_list = (Fragment_List) fm.findFragmentById(R.id.main_fragment);
        fragment_list.addItem(ContextCompat.getDrawable(this, R.drawable.memo), "메모장", "소중한 순간을 기록하세요");
        fragment_list.update();

        Button button = (Button) findViewById(R.id.main_btn_add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment_list.addItem(ContextCompat.getDrawable(MainActivity.this, R.drawable.memo), "추가", "추가된 항목입니다~");
                fragment_list.update();
            }
        });
    }
}