package com.example.appbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //OptionMenu가 AppBar에 포함되면서,
    //onCreateOptionsMenu() 함수에서 AppBar에 Menu Resource xml을 표시한다.
    // 짐작하건데, AppBar의 오른쪽 부분이 OptionMenu에게 할당된 부분인듯.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_one: {
                Toast.makeText(this,"1번 클릭",Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.action_two: {
                Toast.makeText(this,"2번 클릭",Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.action_three: {
                Toast.makeText(this,"3번 클릭",Toast.LENGTH_SHORT).show();
                return true;
            }
            default :{
                return false;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar ab = getSupportActionBar();
        ab.setIcon(R.drawable.terminal);
        ab.setDisplayUseLogoEnabled(true);
        ab.setDisplayShowHomeEnabled(true);
    }
}