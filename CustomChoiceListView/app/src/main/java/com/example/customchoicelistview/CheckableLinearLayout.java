package com.example.customchoicelistview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.LinearLayout;
//예전에 실습을 했었던 checkbox있던 ListView를 만들 땐, ListView와 호환이 가능하도록(ListView의 멤버 함수에 응답할 수 있도록, Checkable을 구현하였음) 만들어진 레이아웃을 골랐던 것임.
//이젠 custom하게 만들었기 때문에, checkable이 구현된 LinearLayout을 만들고, checkbox와 연결될 수 있도록 오버라이딩 함수를 구현해주어야 함.
public class CheckableLinearLayout extends LinearLayout implements Checkable {
    public CheckableLinearLayout(Context context) {
        super(context);
    }

    public CheckableLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CheckableLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setChecked(boolean checked) {
        CheckBox checkBox = (CheckBox) findViewById(R.id.item_checkbox);
        checkBox.setChecked(checked);
    }

    @Override
    public boolean isChecked() {
        CheckBox checkBox = (CheckBox) findViewById(R.id.item_checkbox);
        return checkBox.isChecked();
    }

    @Override
    public void toggle() {
        CheckBox checkBox = (CheckBox) findViewById(R.id.item_checkbox);
        if (checkBox.isChecked()) {
            checkBox.setChecked(false);
        } else {
            checkBox.setChecked(true);
        }
    }
}
