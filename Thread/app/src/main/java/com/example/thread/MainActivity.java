package com.example.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TextView clockTextView;
    private static Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // mainThread의 흐름에서 이 액티비티의 onCreate()를 호출할 텐데, MainThread 에 무한루프 작업을 할 경우
        // 초기화 작업부분인 onCreate() 함수에서 멈춰버리는 것임.
        // 더 이상 이벤트가 처리 될 수 없음.. (화면이 그려지거나 등등)
        // -> Thread를 생성해야한다. Thread 끼리의 통신 방법이 필요하다.

        // 방법 1.
        // MainThread에 Handler 정의하고, 새로 생성한 Thread에서 Message 만든 후 Handler를 통해 MainThread에 전달.
        // MessageQueue에 Message가 전달되고 Looper를 통해 MessageQueue의 값들이 하나씩 읽어들여져 Handler의 handleMessage() 호출.

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {

                if (msg.what == 0) {
                    Log.e("msg", msg.arg1 + "");
                    Calendar cal = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                    String strTime = sdf.format(cal.getTime());
                    clockTextView = findViewById(R.id.clock);
                    clockTextView.setText(strTime);
                }
            }
        };
        class NewThread extends Thread {
            @Override
            public void run() {
                while (true) {
                    Message message = handler.obtainMessage();
                    message.what = 0;
                    message.arg1 = 1;
                    handler.sendMessage(message);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        NewThread newThread = new NewThread();
        newThread.start();


        // 새로운 스레드를 실행하는 역할은 Thread 클래스의 역할입니다.
        // Runnable은 단지 실행될 코드를 정의하는 run() 메서드를 가지는 인터페이스일 뿐
        // run() 메소드는 코드를 덩어리째 보내주는 느낌이다.

        // 방법 2. ( Runnable을 통한 Thread 생성 후 Runnable를 통한 Thread 통신. )
        // Runnable을 implements하여 Thread 의 매개변수에 전달하여 Thread 생성.(생성된 Thread에서 실행 될 코드를 명시한 Runnable)
        // 생성된 Thread에 Message대신 Runnable 객체를 전달.(MainThread에서 실행 될 코드를 명시한 Runnable)
        /*
        handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                String strTime = sdf.format(cal.getTime());
                clockTextView = findViewById(R.id.clock);
                clockTextView.setText(strTime);
            }
        };
        class NewRunnable implements Runnable {
            @Override
            public void run() {
                while (true) {
                    handler.post(runnable);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        ;
        NewRunnable newRunnable = new NewRunnable();
        Thread t = new Thread(newRunnable);
        t.start();
        */

        // 방법 3. ( Thread를 상속받아 Thread 클래스 생성 후 MainThread에 Runnable 객체 전달.)
        // Runnable이 아닌 Thread를 상속받아 Thread 생성
        // 생성된 Thread에 Message대신 Runnable 객체를 전달.(MainThread에서 실행 될 코드를 명시한 Runnable)
        /*
        handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                String strTime = sdf.format(cal.getTime());
                clockTextView = findViewById(R.id.clock);
                clockTextView.setText(strTime);
            }
        };
        class NewThread extends Thread {
            @Override
            public void run() {
                while (true) {
                    handler.post(runnable);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        NewThread newThread = new NewThread();
        newThread.start();

        */
    }
}