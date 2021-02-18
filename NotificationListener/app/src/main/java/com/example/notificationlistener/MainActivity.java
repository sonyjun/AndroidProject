package com.example.notificationlistener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private boolean isPermitted;
    private final String BROADCAST_MESSAGE = "TestMessage";
    private BroadcastReceiver broadcastReceiver;

    //동적 리시버 : 해당 리시버를 등록한 액티비티의 생명주기에 따라갈 수 있음. 앱에 포커스 안가있으면 작동 안하게도 할 수 있음.
    //정적 리시버 : 리시버 해제가 어려움. 앱이 켜져있는 동안 리시버는 계속 작동할 것이다.
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isPermitted = permissionGranted();
        if (!isPermitted) {
            Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
            startActivity(intent);
        }

    }

    private boolean permissionGranted() {//이 앱이 노티피케이션 리스너 서비스 허용을 받았는지 확인.
        Set<String> sets = NotificationManagerCompat.getEnabledListenerPackages(this);
        if (sets != null && sets.contains(getPackageName())) {
            return true;
        } else {
            return false;
        }
    }


    private void registerReceiver() {
        if(broadcastReceiver != null)
            return;

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BROADCAST_MESSAGE);
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String title = intent.getStringExtra("title");
                String content = intent.getStringExtra("content");
                Toast.makeText(MainActivity.this, "제목 : " + title + "\n 내용 : " + content, Toast.LENGTH_SHORT).show();
            }
        };
        this.registerReceiver(this.broadcastReceiver, intentFilter);
    }

    private void unregisterReceiver() {
        if (broadcastReceiver != null) {
            this.unregisterReceiver(broadcastReceiver);
            broadcastReceiver = null;
        }
    }
}