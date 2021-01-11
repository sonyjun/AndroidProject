package com.example.listview_multi_item;

import android.graphics.drawable.Drawable;

public class ListViewItem {
    String item1_txt_title;
    String item1_txt_content;

    Drawable item2_drawable;
    String item2_txt_content;

    int type;

    public String getItem1_txt_title() {
        return item1_txt_title;
    }

    public void setItem1_txt_title(String item1_txt_title) {
        this.item1_txt_title = item1_txt_title;
    }

    public String getItem1_txt_content() {
        return item1_txt_content;
    }

    public void setItem1_txt_content(String item1_txt_content) {
        this.item1_txt_content = item1_txt_content;
    }

    public Drawable getItem2_drawable() {
        return item2_drawable;
    }

    public void setItem2_drawable(Drawable item2_drawable) {
        this.item2_drawable = item2_drawable;
    }

    public String getItem2_txt_content() {
        return item2_txt_content;
    }

    public void setItem2_txt_content(String item2_txt_content) {
        this.item2_txt_content = item2_txt_content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
