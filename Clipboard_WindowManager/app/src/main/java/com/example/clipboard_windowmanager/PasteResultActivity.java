package com.example.clipboard_windowmanager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PasteResultActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasteresult);
        textView= (TextView) findViewById(R.id.pasteResult_textView);
        Intent intent = getIntent();
        String clipContent = intent.getStringExtra("clip_content");

        Log.e("activity의 content",clipContent);
        textView.setText(clipContent);
    }
}
