package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    Button button_createRoom;
    DatabaseReference rootRef;
    RecyclerView recyclerView;
    ChatRoomRecyclerAdapter adapter;
    int count = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_createRoom = (Button) findViewById(R.id.main_btn_createRoom);
        recyclerView = (RecyclerView) findViewById(R.id.main_recyclerview);
        adapter = new ChatRoomRecyclerAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //파이어베이스 데이터베이스의 최상위 루트의 참조를 갖고옴.
        rootRef = database.getReference();
        rootRef.child("Chat").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String title = snapshot.getKey();
                adapter.addItem(new ChatRoomItem(title));
                Log.e("호출","added");
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                Log.e("호출","changed");
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                Log.e("호출","changed");
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                Log.e("호출","moved");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.e("호출","canceled");
            }
        });
        button_createRoom.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                MakeChatRoomDialog dialog = new MakeChatRoomDialog(MainActivity.this);
                dialog.showDialog();
            }
        });
    }
}