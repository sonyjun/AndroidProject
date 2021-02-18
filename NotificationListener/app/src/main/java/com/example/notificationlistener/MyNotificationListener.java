package com.example.notificationlistener;

import android.app.Notification;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)

// notificationListenerService는 startService를 해주지 않아도,
// 사용자의 권한만 주어지면 자동으로 시작되기 때문에 startService를 구현할 필요는 없습니다.
public class MyNotificationListener extends NotificationListenerService {
    String title;
    String content;
    private final String BROADCAST_MESSAGE = "TestMessage";
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);
        Notification notification = sbn.getNotification();
        Bundle extras = notification.extras;
        title = extras.getString(notification.EXTRA_TITLE);
        content = extras.getString(notification.EXTRA_TEXT);
        if(content.contains("리스너는 들어라")){
            makeBroadCast();
        }
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
    }

    private void makeBroadCast() {
        Intent intent = new Intent(BROADCAST_MESSAGE);//이 메세지를 액션으로 취하여 Intent를 구성.
        intent.putExtra("title", title); // 전달하려는 값 전달 가능.
        intent.putExtra("content", content); // 전달하려는 값 전달 가능.
        sendBroadcast(intent);//모든 앱에게 이 액션을 알림.
    }
}