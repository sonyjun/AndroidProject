package com.example.imageview_bitmap;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AssetManager assetManager = getResources().getAssets();
        InputStream is = null;
        ImageView imageView = (ImageView) findViewById(R.id.main_imageview);
        try {
            is = assetManager.open("terminal.png");

            imageView.setImageBitmap(BitmapFactory.decodeStream(is));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}