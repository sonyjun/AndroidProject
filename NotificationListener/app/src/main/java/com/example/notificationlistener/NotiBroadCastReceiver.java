package com.example.notificationlistener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;


//정적으로 브로드캐스트 리시버 등록할 땐 클래스를 정의.
//하지만 MainActivity같은 컴포넌트의 지역변수같은 걸 접근할 수 없음..
public class NotiBroadCastReceiver extends BroadcastReceiver {
    public NotiBroadCastReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {

    }

    @Override
    public IBinder peekService(Context myContext, Intent service) {
        return super.peekService(myContext, service);
    }
}
