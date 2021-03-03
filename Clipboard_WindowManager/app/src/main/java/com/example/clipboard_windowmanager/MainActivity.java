package com.example.clipboard_windowmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE = 1;
    EditText editText;
    Button button_copy;
    Button button_paste;
    Button button_vib_serviceStart;
    Button button_vib_serviceEnd;
    Button button_window_serviceStart;
    Button button_window_serviceEnd;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.main_editText);
        button_copy = (Button) findViewById(R.id.main_btn_copy);
        button_paste = (Button) findViewById(R.id.main_btn_paste);
        textView = (TextView) findViewById(R.id.main_textView_content);
        button_vib_serviceStart = (Button) findViewById(R.id.main_btn_vib_startService);
        button_vib_serviceEnd = (Button) findViewById(R.id.main_btn_vib_endService);
        button_window_serviceStart = (Button) findViewById(R.id.main_btn_window_startService);
        button_window_serviceEnd = (Button) findViewById(R.id.main_btn_window_endService);
        button_window_serviceStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                checkPermissionAndStart();
            }
        });
        button_window_serviceEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WindowManagerService.class);
                stopService(intent);
            }
        });
        button_copy.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                copyText();
            }
        });
        button_paste.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                paste();
            }
        });
        button_vib_serviceStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ClipBoardService.class);
                startService(intent);
            }
        });
        button_vib_serviceEnd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ClipBoardService.class);
                stopService(intent);
            }
        });
    }

    //클립보드에 클립이 있음. 클립에는 내가 저장한 데이터가 저장됨.

    void copyText() {
        String text = editText.getText().toString();//에디트텍스트에 입력된 값 가져옴
        if (text.length() != 0) {
            //문자열을 클립보드에 넣는수 있는 클립데이터 형태로 포장
            ClipData clip = ClipData.newPlainText("text", text);
            //클립보드 관리자 객체를 가져옴, 클립보드 매니저를 통해 클립보드에 설정.
            ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            cm.setPrimaryClip(clip);//클립보드에 저장하는 부분
            Toast.makeText(this, "복사되었습니다.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "복사할 값을 입력해주세요.", Toast.LENGTH_SHORT).show();
        }

    }

    public void paste() {
        // 클립보드 객체 얻기
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboardManager.hasPrimaryClip() == true) {
            // 클립보드 데이터가 있을 때 처리
            if (clipboardManager.getPrimaryClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN) == false) {
                //클립보드가 텍스트가 아니라면,
                Toast.makeText(this, "복사된 값이 텍스트 형태가 아닙니다.", Toast.LENGTH_SHORT).show();
            } else {
                ClipData data = clipboardManager.getPrimaryClip();
                for (int i = 0; i < data.getItemCount(); i++) {
                    Log.e("clip data list", data.getItemAt(i).getText() + "");
                }
                ClipData.Item item = data.getItemAt(0);
                textView.setText(item.getText());
                Toast.makeText(this, "붙여넣기가 완료 되었습니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void checkPermissionAndStart() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {   // 마시멜로우 이상일 경우
            if (!Settings.canDrawOverlays(this)) {              // 체크
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE);
            } else {
                startService(new Intent(MainActivity.this, WindowManagerService.class));
            }
        } else {
            startService(new Intent(MainActivity.this, WindowManagerService.class));
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE) {
            if (!Settings.canDrawOverlays(this)) {
                // TODO 동의를 얻지 못했을 경우의 처리

            } else {
                startService(new Intent(MainActivity.this, WindowManagerService.class));
            }
        }
    }
}