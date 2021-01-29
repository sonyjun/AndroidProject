package com.example.binaryfile_io;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends Activity implements View.OnClickListener {
    final String fileName = "access.cnt";
    int textCount = 0;
    TextView textView_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView_count = (TextView) findViewById(R.id.textview_count);
        Button btn_inc = (Button) findViewById(R.id.buttonInc);
        Button btn_dec = (Button) findViewById(R.id.buttonDec);
        Button btn_clear = (Button) findViewById(R.id.buttonClear);
        btn_inc.setOnClickListener(this);
        btn_dec.setOnClickListener(this);
        btn_clear.setOnClickListener(this);

        textCount = loadAccessCount();
        textView_count.setText(textCount + "");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonInc: {
                textView_count.setText(++textCount + "");
                saveCount(textCount);
                break;
            }
            case R.id.buttonDec: {
                textView_count.setText(--textCount + "");
                saveCount(textCount);
                break;
            }
            case R.id.buttonClear: {
                textCount = 0;
                textView_count.setText(textCount + "");
                saveCount(textCount);
                break;
            }
        }
    }

    private int loadAccessCount() {
        File file = new File(getFilesDir(), fileName);
        // getFilesDir()은 앱의 기본 파일 저장 디렉터리의 전체 경로를 가져옴.
        System.out.println(file.getAbsolutePath());

        FileInputStream fis = null;
        byte[] buf = new byte[4];
        int count = 0;
        int size = 0;
        if (file.exists()) {
            try {
                fis = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(fis);
                if ((size = bis.read(buf)) != -1) {
                    for (int i = 0; i < size; i++) {
                        count |= (int) (buf[i] << (24 - (i * 8)));
                    }
                }
                bis.close();
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {//해당 파일이 없다. 끝내..
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    private void saveCount(int count) {

        File file = new File(getFilesDir(), fileName);
        // getFilesDir()은 앱의 기본 파일 저장 디렉터리의 전체 경로를 가져옴.
        System.out.println(file.getAbsolutePath());

        FileOutputStream fos = null;
        BufferedOutputStream bis = null;
        byte[] buf = new byte[4];
        if (file.exists()) {
            try {
                fos = new FileOutputStream(file);
                bis = new BufferedOutputStream(fos);
                buf[0] = (byte) ((count >> 24) & 0xFF);
                buf[1] = (byte) ((count >> 16) & 0xFF);
                buf[2] = (byte) ((count >> 8) & 0xFF);
                buf[3] = (byte) (count & 0xFF);
                bis.write(buf);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (fos != null && bis != null) {
                try {
                    bis.close();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {//해당 파일이 없다. 끝내..

        }
    }
}