package com.example.startactivityforresult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.main_btn_add);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ContactActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Intent intent = data;
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {//RESULT_OK : -1 , RESULT_CANCELED : 0
            //resultCode가 없다면, 이 구문은 ContactActivity에서 입력이 잘되던 못되던 실행이 되어짐.
            //resultCode를 통해 잘 들어온 값인지 확인이 필요하다. RESULT_OK는 개발자가 성공적으로 입력이 이루어졌을 때, 표시하는 식별자니까. OK라면 잘 들어온거.
            TextView textView_no = (TextView) findViewById(R.id.main_no);
            TextView textView_name = (TextView) findViewById(R.id.main_name);
            TextView textView_phone = (TextView) findViewById(R.id.main_phone);
            TextView textView_over20 = (TextView) findViewById(R.id.main_age);
            textView_no.setText(intent.getIntExtra("contact_no", 0)+"");
            textView_name.setText(intent.getStringExtra("contact_name"));
            textView_phone.setText(intent.getStringExtra("contact_phone"));
            if(intent.getBooleanExtra("contact_over20",true)){
                textView_over20.setText("20살 넘었습니다.");
            }else{
                textView_over20.setText("20살 아직 안넘었습니다.");
            }
        }
    }
}