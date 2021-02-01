package com.example.progressbar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.main_progressbar);

        //progressBar.setMax(100); // default는 100임
        Button btn = (Button) findViewById(R.id.main_button);
        EditText editText = (EditText) findViewById(R.id.main_edittext);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputStr = editText.getText().toString();
                //if(inputStr.matches("^[0-9]+$")){// 0~9까지의 숫자만 입력가능
                int value = Integer.parseInt(editText.getText().toString());
                if (value >= 0 && value <= 100) {
                    progressBar.setProgress(value);
                } else {
                    Toast.makeText(MainActivity.this, "0~100사이의 범위만 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                //}
                /*else{
                    Toast.makeText(MainActivity.this,"숫자만 입력가능합니다.",Toast.LENGTH_SHORT).show();
                }*/


            }
        });
    }
}