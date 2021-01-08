package com.example.listviewwithbutton;

import android.graphics.drawable.Drawable;
public class ListViewItem {
    Drawable drawable;
    String text;
    public ListViewItem(Drawable drawable, String text){
        this.text = text;
        this.drawable = drawable;
    }
    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
