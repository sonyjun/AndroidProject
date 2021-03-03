package com.example.clipboard_windowmanager;

import android.app.Service;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class WindowManagerService extends Service implements ClipboardManager.OnPrimaryClipChangedListener {
    ClipboardManager clipboardManager;
    WindowManager wm;
    View mView;
    WindowManager.LayoutParams params;
    String clipContent;
    boolean isAttached = false;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        clipboardManager.addPrimaryClipChangedListener(this);
        LayoutInflater inflate = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        wm = (WindowManager) getSystemService(WINDOW_SERVICE);

        params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
        );
        params.gravity = Gravity.BOTTOM;
        mView = inflate.inflate(R.layout.windowmanager_layout, null);
        final Button button_ok = (Button) mView.findViewById(R.id.windowManger_btn_yes);
        final Button button_no = (Button) mView.findViewById(R.id.windowManger_btn_no);
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WindowManagerService.this,PasteResultActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("clip_content",clipContent);
                startActivity(intent);
                wm.removeView(mView);
                isAttached = false;
            }
        });
        button_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wm.removeView(mView);
                isAttached = false;
            }
        });

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(wm != null){
            if(mView != null && isAttached){
                wm.removeView(mView);
            }
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onPrimaryClipChanged() {
        ClipData data = clipboardManager.getPrimaryClip();
        ClipData.Item item = data.getItemAt(0);
        clipContent = item.getText().toString();
        Log.e("service의 content",clipContent);
        if(!isAttached){
            wm.addView(mView, params);
            isAttached = true;
        }
    }
}
