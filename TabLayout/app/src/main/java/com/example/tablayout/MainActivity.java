package com.example.tablayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 /*     //tabLayout에 새로운 탭을 추가하기 위한 방법에는 2가지. XML에 추가,
        //java코드로 추가.
        //tabLayout의 newTab() 메소드로 새로운 탭을 만듬, addTab()메소드로 탭 추가 해줌.
        TabLayout tabLayout = (TabLayout) findViewById(R.id.main_tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("TAB-1"));
        tabLayout.addTab(tabLayout.newTab().setText("TAB-2"));
        tabLayout.addTab(tabLayout.newTab().setText("TAB-3"));
*/
        //위의 방법 풀어 쓴 것 뿐임.
        TabLayout tabLayout = (TabLayout) findViewById(R.id.main_tablayout);
        TabLayout.Tab tab1 = tabLayout.newTab();
        tab1.setText("TAB-1");
        tab1.setTag(1);
        TabLayout.Tab tab2 = tabLayout.newTab();
        tab2.setText("TAB-2");
        tab2.setTag(2);
        TabLayout.Tab tab3 = tabLayout.newTab();
        tab3.setText("TAB-3");
        tab3.setTag(3);
        tabLayout.addTab(tab1);
        tabLayout.addTab(tab2);
        tabLayout.addTab(tab3);


        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.main_framelayout,new FragmentA());
        fragmentTransaction.commit();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                switch ((int) tab.getTag()) {
                    case 1: {
                        fragmentTransaction.replace(R.id.main_framelayout,new FragmentA());
                        fragmentTransaction.commit();
                        break;
                    }
                    case 2: {
                        fragmentTransaction.replace(R.id.main_framelayout,new FragmentB());
                        fragmentTransaction.commit();
                        break;
                    }
                    case 3: {
                        fragmentTransaction.replace(R.id.main_framelayout,new FragmentC());
                        fragmentTransaction.commit();
                        break;
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}