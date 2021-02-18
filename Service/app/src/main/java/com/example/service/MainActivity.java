package com.example.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button button_startMusic;
    Button button_stopMusic;
    Button button_startService;
    Button button_stopService;
    Button button_bindService;
    Button button_unbindService;
    TestService testService;
    boolean isBind = false;

    ServiceConnection conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_startMusic = (Button) findViewById(R.id.main_btn_musicStart);
        button_stopMusic = (Button) findViewById(R.id.main_btn_musicStop);
        button_startService = (Button) findViewById(R.id.main_btn_serviceStart);
        button_stopService = (Button) findViewById(R.id.main_btn_serviceStop);
        button_bindService = (Button) findViewById(R.id.main_btn_serviceBind);
        button_unbindService = (Button) findViewById(R.id.main_btn_serviceUnbind);
        conn = new ServiceConnection() {
            //서비스 객체랑 연결해주는 역할. 연결이 됐을 경우 서비스의 onBind()의 return값이 service의 인자로 들어옴.
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                TestService.MyBinder binder = (TestService.MyBinder) service;
                testService = binder.getService();
                isBind = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                testService = null;
                isBind = false;
            }
        };

        button_startMusic.setOnClickListener(this);
        button_stopMusic.setOnClickListener(this);
        button_startService.setOnClickListener(this);
        button_stopService.setOnClickListener(this);
        button_bindService.setOnClickListener(this);
        button_unbindService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_btn_musicStart: {
                Intent intent = new Intent(MainActivity.this,MusicService.class);
                startService(intent);
                break;
            }
            case R.id.main_btn_musicStop: {
                Intent intent = new Intent(MainActivity.this,MusicService.class);
                stopService(intent);
                break;
            }
            case R.id.main_btn_serviceStart: {
                Intent intent = new Intent(MainActivity.this, TestService.class);
                startService(intent);
                break;
            }
            case R.id.main_btn_serviceStop: {
                Intent intent = new Intent(MainActivity.this, TestService.class);
                stopService(intent);
                break;
            }
            case R.id.main_btn_serviceBind: {
                Intent intent = new Intent(MainActivity.this, TestService.class);
                if(!isBind) {
                    bindService(intent, conn, BIND_AUTO_CREATE);
                }
                break;
            }
            case R.id.main_btn_serviceUnbind: {
                if(isBind) {
                    isBind = false;
                    unbindService(conn);
                }
                break;
            }
        }
    }
}