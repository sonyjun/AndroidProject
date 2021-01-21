package com.example.startactivityforresult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class ContactActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Intent intent = new Intent();
        EditText editText_no = (EditText) findViewById(R.id.contact_edittext_no);
        EditText editText_name = (EditText) findViewById(R.id.contact_edittext_name);
        EditText editText_phone = (EditText) findViewById(R.id.contact_edittext_name);
        CheckBox checkBox_over20 = (CheckBox) findViewById(R.id.contact_checkbox_age);
        Button btn_save = (Button) findViewById(R.id.contact_btn_save);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String no = editText_no.getText().toString();
                if (!no.isEmpty() && no.matches("(^[0-9]*$)")) {
                    String name = editText_name.getText().toString();
                    String phone = editText_phone.getText().toString();
                    boolean checked = checkBox_over20.isChecked();
                    intent.putExtra("contact_no", no);
                    intent.putExtra("contact_name", name);
                    intent.putExtra("contact_phone", phone);
                    intent.putExtra("contact_over20", checked);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(ContactActivity.this, "No 항목에 숫자가 아닌 값이 있습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}