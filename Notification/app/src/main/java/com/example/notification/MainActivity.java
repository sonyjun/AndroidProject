package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String FIRST_CHANNEL_ID = "channel_1";
    private static final String SECOND_CHANNEL_ID = "channel_2";
    //만들 채널의 이름

    private static final int NOTIFICATION_ID = 1;
    //만들 Notification의 이름

    private NotificationManager notificationManager;
    //채널을 만들고, Notification을 알릴 때 사용.

    private Button button_makeNoti;
    private Button button_startThread;
    private int count = 0;
    private boolean isMakedChannel = false;
    private Thread thread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_makeNoti = (Button) findViewById(R.id.main_btn_makenoti);
        button_startThread = (Button) findViewById(R.id.main_btn_startservice);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        SharedPreferences sp = getSharedPreferences("test",MODE_PRIVATE);
        isMakedChannel = sp.getBoolean("isMakedChannel",false);
        count = sp.getInt("count",0);
        if(!isMakedChannel){
            Log.e("로그","처음돕니다.");
            makeNotificationChannel();
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("isMakedChannel",true);
            editor.commit();
        }else{
            Log.e("로그","채널이 이미 생성되었습니다..");
        }
        button_makeNoti.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sendNotification();
            }
        });
        button_startThread.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        int i = 0;
                        while(i < 10){//5초에 1개씩 10개의 알림을 보낸다.
                            try {
                                Thread.sleep(5000);
                                sendNotification();
                                i++;
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };
                thread = new Thread(runnable);
                thread.start();
            }
        });
    }

    public void makeNotificationChannel() {//매니저를 통해 채널을 만드는 메소드.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {//sdk버전이 오레오(26) 이상이라면
            NotificationChannel notificationChannel1 = new NotificationChannel(FIRST_CHANNEL_ID, "CHANNEL 1", notificationManager.IMPORTANCE_HIGH);
            notificationChannel1.enableLights(true);
            notificationChannel1.setLightColor(Color.GREEN);
            notificationChannel1.enableVibration(true);
            notificationChannel1.setDescription("Notification from Mascot");
            //채널을 정의하고, 다양한 설정들을 해줌.
            notificationManager.createNotificationChannel(notificationChannel1);
            //notificationManager를 통해 채널1 을 생성.


            NotificationChannel notificationChannel2 = new NotificationChannel(SECOND_CHANNEL_ID, "CHANNEL 2", notificationManager.IMPORTANCE_HIGH);
            notificationChannel2.enableLights(true);
            notificationChannel2.setLightColor(Color.GREEN);
            notificationChannel2.enableVibration(true);//진동을 허용함
            notificationChannel2.setDescription("Notification from Mascot");//채널에 대한 설명

            //채널을 정의하고, 다양한 설정들을 해줌.
            notificationManager.createNotificationChannel(notificationChannel2);
            //notificationManager를 통해 채널1 을 생성.
        }
    }

    // notification을 만들어 반환, 채널에 등록도 함.
    public NotificationCompat.Builder getNotificationBuilder() {
        Intent intent = new Intent(MainActivity.this, ClickActivity.class);
        intent.putExtra("count",count);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, NOTIFICATION_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this, FIRST_CHANNEL_ID)
                .setContentTitle("You've been notified!")
                .setContentText(count+"번째의 Notification 입니다.\n 리스너는 들어라!")
                .setSmallIcon(R.drawable.noficiation_icon)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        return notifyBuilder;
    }

    // notification builder를 이용해 만든 notification을 매니저에 알림.
    public void sendNotification() {
        count++;
        SharedPreferences sp = getSharedPreferences("test",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("count",count);
        editor.commit();
        NotificationCompat.Builder notificationBuilder = getNotificationBuilder();
        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build());
    }
}