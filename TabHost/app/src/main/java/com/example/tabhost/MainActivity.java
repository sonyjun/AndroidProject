package com.example.tabhost;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabHost tabHost = (TabHost) findViewById(R.id.main_tabHost);
        tabHost.setup();

        TabHost.TabSpec ts1 = tabHost.newTabSpec("tab1");
        ts1.setContent(R.id.content1);
        ts1.setIndicator("탭 1");
        tabHost.addTab(ts1);

        TabHost.TabSpec ts2 = tabHost.newTabSpec("tab2");
        ts2.setContent(R.id.content2);
        ts2.setIndicator("탭 2");
        tabHost.addTab(ts2);

        TabHost.TabSpec ts3 = tabHost.newTabSpec("tab3");
        ts3.setContent(R.id.content3);
        ts3.setIndicator("탭 3");
        tabHost.addTab(ts3);

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                Log.e("id",tabId);
            }
        });
    }
}