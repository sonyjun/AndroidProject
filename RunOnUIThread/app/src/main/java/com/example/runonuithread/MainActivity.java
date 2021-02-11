package com.example.runonuithread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // MainThread에 던져줄 Runnable 객체 생성.
        // Thread를 Runnable을 통해 생성.
        // Runnable을 통해 생성한 Thread에서 던져줄 Runnable 을 runOnUIThread를 통해 전달.
        // runOnUiThread가 전달받은 runnable을 핸들러를 실행함.
        TextView textView = (TextView) findViewById(R.id.main_textview);


        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                String time = sdf.format(calendar.getTime());
                textView.setText(time);
            }
        };

        class NewRunnable implements Runnable {

            @Override
            public void run() {
                while (true) {
                    runOnUiThread(runnable);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        Thread thread = new Thread(new NewRunnable());
        thread.start();
    }
}