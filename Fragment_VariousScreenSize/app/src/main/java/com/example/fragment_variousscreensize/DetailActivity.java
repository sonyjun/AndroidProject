package com.example.fragment_variousscreensize;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_detail);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");
        TextView textView_title = (TextView) findViewById(R.id.activity_fragment_detail_textview_title);
        TextView textView_content = (TextView) findViewById(R.id.activity_fragment_detail_textview_content);
        textView_title.setText(title);
        textView_content.setText(content);
    }
}