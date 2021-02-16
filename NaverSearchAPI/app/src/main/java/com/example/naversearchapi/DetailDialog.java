package com.example.naversearchapi;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class DetailDialog extends Dialog {
    Context context;
    public DetailDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }
    public void showDialog(String content){
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_detailcontent);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView textView = dialog.findViewById(R.id.dialog_detailcontent_textview);
        textView.setMovementMethod(new ScrollingMovementMethod());
        Button button = dialog.findViewById(R.id.dialog_detailcontent_btn);
        textView.setText(content);
        dialog.show();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
