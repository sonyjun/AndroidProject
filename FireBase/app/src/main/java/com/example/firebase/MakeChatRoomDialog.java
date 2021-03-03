package com.example.firebase;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashSet;

public class MakeChatRoomDialog extends Dialog {
    Context context;
    Button button_ok;
    Button button_cancel;
    EditText editText;

    DatabaseReference rootRef;
    FirebaseDatabase database;
    public MakeChatRoomDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }
    public void showDialog(){
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_makechatroom);
        button_ok = (Button) dialog.findViewById(R.id.dialog_makechat_btn_ok);
        button_cancel = (Button) dialog.findViewById(R.id.dialog_makechat_btn_cancel);
        editText = (EditText) dialog.findViewById(R.id.dialog_makechat_edit);
        database = FirebaseDatabase.getInstance();
        rootRef = database.getReference();
        HashSet<String> roomList = new HashSet<String>();
        rootRef.child("Chat").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                roomList.add(snapshot.getKey());
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

        button_ok.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ChatActivity.class);
                String inputStr = editText.getText().toString();
                if(!inputStr.equals("")){//만드려는 채팅방 이름이 공백이라면.
                    if(!roomList.contains(inputStr)){//이미 존재하는 채팅방 이름이라면
                        intent.putExtra("room_name",editText.getText().toString());
                        context.startActivity(intent);
                        dialog.dismiss();
                    }else{
                        Toast.makeText(context, "이미 존재하는 채팅방입니다.", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(context, "채팅방 이름을 입력하세요", Toast.LENGTH_SHORT).show();
                }
            }
        });
        button_cancel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
