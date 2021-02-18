package com.example.notification;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ClickActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click);
        textView = (TextView) findViewById(R.id.click_txt);
        Intent intent = getIntent();
        int count = intent.getIntExtra("count",0);
        textView.setText(count+"번의 Notification이 도착했습니다.");
    }
}
