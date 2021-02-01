package com.example.asset;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.main_text);
        AssetManager am = getResources().getAssets();
        InputStream is = null;
        BufferedReader br = null;
        char[] buf = new char[1024];
        try {
            is = am.open("file.txt");
            br = new BufferedReader(new InputStreamReader(is));
            int size = 0;
            //버퍼 크기 만큼 한꺼번에 읽어올 수 있고,
            if ((size = br.read(buf)) > 0) {//읽은 char 갯수 반환, 비어있다면 -1 반환.
                /*for (int i = 0; i < size; i++) {
                    System.out.print(buf[i]+" ");
                }*/
                String text = new String(buf);
                textView.setText(text);
            }
/*
            System.out.println("fffff");
            // char하나씩 읽어오는 것도 가능.
            int data = 0;
            while((data = br.read()) > 0){
                Log.e("안도",(char)data+"");
            }
*/
            // TODO : use is(InputStream).

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (is != null) {
            try {
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}