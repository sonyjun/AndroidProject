package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

// startService : onCreate() -> onStartCommand() -> onDestroy()
// bindService : onCreate() -> onBind() -> onUnbind() -> onDestroy()
public class MusicService extends Service {
    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this,R.raw.music);
        mediaPlayer.setLooping(true);
        Log.e("서비스 테스트","서비스 onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("서비스 테스트","서비스 onStartCommand");
        if(mediaPlayer.isPlaying()){
            Toast.makeText(this, "이미 노래가 실행중입니다.", Toast.LENGTH_SHORT).show();
        }else{
            mediaPlayer.start();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        Log.e("서비스 테스트","서비스 onDestroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("서비스 테스트","서비스 onBind");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("서비스 테스트","서비스 onUnbind");
        return super.onUnbind(intent);
    }
}
