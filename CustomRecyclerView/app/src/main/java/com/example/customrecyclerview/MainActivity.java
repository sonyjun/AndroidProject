package com.example.customrecyclerview;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.RecyclerItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("SONY APP");

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_recyclerview);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.memo),"메모","메롱");
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.setting),"세팅","메롱이");
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.trash),"스레기","하하");
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action1:{
                Toast.makeText(this, "action1", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.action2:{
                Toast.makeText(this, "action2", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.action3:{
                Toast.makeText(this, "action3", Toast.LENGTH_SHORT).show();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(int position, View view) {
        Toast.makeText(this, position +"누르셨습니다.", Toast.LENGTH_SHORT).show();
    }
}