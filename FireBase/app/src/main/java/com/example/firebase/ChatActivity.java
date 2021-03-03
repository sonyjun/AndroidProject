package com.example.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChatActivity extends AppCompatActivity {

    DatabaseReference rootRef;
    FirebaseDatabase database;
    RecyclerView recyclerView;
    EditText editText;
    Button button_send;
    TextView textView_roomName;
    ChatContentRecyclerAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        recyclerView = (RecyclerView) findViewById(R.id.chat_recyclerview);
        editText = (EditText) findViewById(R.id.chat_edittext);
        button_send = (Button) findViewById(R.id.chat_btn_send);
        textView_roomName = (TextView) findViewById(R.id.chat_textview_roomname);
        adapter = new ChatContentRecyclerAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        Intent intent = getIntent();
        String roomName = intent.getStringExtra("room_name");
        textView_roomName.setText(roomName);
        Log.e("채팅방 이름", roomName + " 뭐야 ");



        // firebase에 데이터 추가 리스너 등록 및 기존 데이터 가져옴
        database = FirebaseDatabase.getInstance();
        rootRef = database.getReference();
        //rootRef.child(roomName).push().setValue("하나 개설");
        rootRef.child("Chat").child(roomName).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.e("채팅 내용", snapshot.getValue(String.class));
                adapter.addItem(new ChatContentItem(snapshot.getValue(String.class)));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        button_send.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String inputStr = editText.getText().toString();
                if(!inputStr.equals("")){
                    rootRef.child("Chat").child(roomName).push().setValue(editText.getText().toString());
                    editText.setText("");
                }else{
                    Toast.makeText(ChatActivity.this, "대화를 입력하세요", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
