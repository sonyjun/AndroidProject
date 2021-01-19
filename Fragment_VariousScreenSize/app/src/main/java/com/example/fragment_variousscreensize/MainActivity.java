package com.example.fragment_variousscreensize;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

public class MainActivity extends Activity implements MainFragment.OnItemClickListener{
    MainFragment mainFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //가로 모드라면 생성되어지는 view가 다르기 때문에 해당 뷰를 그려주는 작업을 해주어야 함.
            FragmentManager fm = getFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            DetailFragment detailFragment = new DetailFragment();
            Bundle bundle = new Bundle();
            bundle.putString("title","게시글을 선택해주세요");
            bundle.putString("content","게시글을 선택해주세요");
            detailFragment.setArguments(bundle);
            transaction.add(R.id.main_large_right_framelayout,detailFragment);
            transaction.commit();
        }else{
            //세로 모드라면 현재 이 액티비티 표시되는 fragment가 하나이고 layout resource xml로 지정을 해놓았기 때문에 처리 필요 없음.
        }
    }

    @Override
    public void onItemClick(int position, String title, String content) {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //가로 모드라면 생성되어지는 view가 다르기 때문에 해당 뷰를 그려주는 작업을 해주어야 함.
            FragmentManager fm = getFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            DetailFragment detailFragment = new DetailFragment();
            Bundle bundle = new Bundle();
            bundle.putString("title",title);
            bundle.putString("content",content);
            detailFragment.setArguments(bundle);
            transaction.replace(R.id.main_large_right_framelayout,detailFragment);
            transaction.commit();
        }else{
            Intent intent = new Intent(this,DetailActivity.class);
            intent.putExtra("title",title);
            intent.putExtra("content",content);
            startActivity(intent);
        }
    }
}