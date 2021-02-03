package com.example.navigationdrawer;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.drawerlayout.widget.DrawerLayout;

import java.util.ArrayList;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.main_listview);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("메인화면");
        arrayList.add("A 화면");
        arrayList.add("B 화면");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(this);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        FragmentMain fragmentMain = new FragmentMain();
        fragmentTransaction.add(R.id.main_framelayout, fragmentMain);
        fragmentTransaction.commit();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        if (position == 0) {
            FragmentMain fragmentMain = new FragmentMain();
            fragmentTransaction.replace(R.id.main_framelayout, fragmentMain);
            fragmentTransaction.commit();
        } else if (position == 1) {
            FragmentA fragmentA = new FragmentA();
            fragmentTransaction.replace(R.id.main_framelayout, fragmentA);
            fragmentTransaction.commit();
        } else if (position == 2) {
            FragmentB fragmentB = new FragmentB();
            fragmentTransaction.replace(R.id.main_framelayout, fragmentB);
            fragmentTransaction.commit();
        }
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.main_drawerlayout);
        drawerLayout.closeDrawer(Gravity.LEFT);
    }
}