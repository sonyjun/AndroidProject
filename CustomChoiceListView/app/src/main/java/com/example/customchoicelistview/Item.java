package com.example.customchoicelistview;

import android.graphics.drawable.Drawable;

public class Item {
    Drawable drawable;
    String content;

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
