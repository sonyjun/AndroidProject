package com.example.drawerlayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.drawerlayout.widget.DrawerLayout;

public class MainActivity extends Activity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_open = (Button) findViewById(R.id.main_btn_open);
        Button btn_close = (Button) findViewById(R.id.main_btn_close);
        CheckBox checkBox = (CheckBox) findViewById(R.id.main_checkbox);

        btn_open.setOnClickListener(this);
        btn_close.setOnClickListener(this);
        checkBox.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.main_drawerlayout);
        switch (v.getId()) {
            case R.id.main_btn_open: {
                if (!drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }
                break;
            }
            case R.id.main_btn_close: {
                if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    drawerLayout.closeDrawer(Gravity.LEFT);
                }
                break;
            }
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.main_drawerlayout);
        if (isChecked == true) {
            if(drawerLayout.isDrawerOpen(Gravity.LEFT)){
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
            }else{
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            }
        } else {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }
    }
}