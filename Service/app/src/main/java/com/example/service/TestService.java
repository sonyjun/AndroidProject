package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

// startService : onCreate() -> onStartCommand() -> onDestroy()
// bindService : onCreate() -> onBind() -> onUnbind() -> onDestroy()
public class TestService extends Service {
    IBinder iBinder = new MyBinder();
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("서비스 테스트","서비스 onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("서비스 테스트","서비스 onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("서비스 테스트","서비스 onDestroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("서비스 테스트","서비스 onBind");
        return iBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("서비스 테스트","서비스 onUnbind");
        return super.onUnbind(intent);
    }

    class MyBinder extends Binder{
        public TestService getService(){
            return TestService.this;
        }
    }
}
