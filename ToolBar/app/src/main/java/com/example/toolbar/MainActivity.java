package com.example.toolbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);

        // ActionBar가 ToolBar로 대체되는 것.
        // xml에 추가한 toolbar를 ActionBar(앱바)로 지정.
        setSupportActionBar(toolbar);

        // 위의 구문을 통해 이제 ActionBar가 ToolBar로 대체 되었으므로, ToolBar를 다루는 것과 같음.
        ActionBar ab = getSupportActionBar();
        ab.setTitle("hello world");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_action, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action1:{
                Toast.makeText(MainActivity.this, "action1" ,Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.action2:{
                Toast.makeText(MainActivity.this, "action2" ,Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.action3:{
                Toast.makeText(MainActivity.this, "action3" ,Toast.LENGTH_SHORT).show();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}