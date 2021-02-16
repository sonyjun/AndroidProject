package com.example.connection_servermysql;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.regex.Pattern;

public class AddItemDialog extends Dialog {
    Context context;
    EditText editText_name;
    RadioGroup radioGroup_sex;
    EditText editText_age;
    EditText editText_address;
    Button button_cancel;
    Button button_add;
    RecyclerviewAdapter adapter;
    String name;
    String sex;
    String age;
    String address;

    public AddItemDialog(@NonNull Context context, RecyclerviewAdapter adapter) {
        super(context);
        this.context = context;
        this.adapter = adapter;
    }

    public void callDialog() {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_additem);
        editText_name = dialog.findViewById(R.id.dialog_additem_name);
        radioGroup_sex = dialog.findViewById(R.id.dialog_additem_radio);
        editText_age = dialog.findViewById(R.id.dialog_additem_age);
        editText_address = dialog.findViewById(R.id.dialog_additem_address);
        button_add = dialog.findViewById(R.id.dialog_additem_saveBtn);
        button_cancel = dialog.findViewById(R.id.dialog_additem_cancelBtn);
        dialog.show();

        button_add.setOnClickListener(new View.OnClickListener() {
            String pattern = "^[0-9]*$";

            @Override
            public void onClick(View v) {
                 name = editText_name.getText().toString();
                 sex = radioGroup_sex.getCheckedRadioButtonId() == R.id.dialog_additem_male ? "남" : "여";
                 age = editText_age.getText().toString();
                 address = editText_address.getText().toString();
                if (!name.equals("")  && !sex.equals("") && !address.equals("") && !age.equals("")) {
                    InpuAsyncTask inpuAsyncTask = new InpuAsyncTask();
                    inpuAsyncTask.execute(name, sex, age, address);
                    Toast.makeText(context, "추가되었습니다.", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else {
                    Toast.makeText(context, "모든 값을 입력해주세요.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        button_cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    class InpuAsyncTask extends AsyncTask<String, Integer, Integer> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            adapter.addItem(new RecyclerviewItem(integer,name,sex,Integer.parseInt(age),address));
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Integer doInBackground(String... strings) {
            URLConnector urlConnector = new URLConnector();
            int id = urlConnector.send_DataToDB(strings[0], strings[1], Integer.parseInt(strings[2]), strings[3]);
            return id;
        }
    }
}
