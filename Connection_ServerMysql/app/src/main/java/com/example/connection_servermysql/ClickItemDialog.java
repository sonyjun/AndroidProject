package com.example.connection_servermysql;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class ClickItemDialog extends Dialog implements DeleteItemDialog.onDeleteListener{
    Context context;
    TextView textView_name;
    TextView textView_sex;
    TextView textView_age;
    TextView textView_address;
    Button button_ok;
    Button button_delete;
    Dialog dialog;
    int position;
    int id;
    RecyclerviewAdapter adapter;
    public ClickItemDialog(@NonNull Context context, RecyclerviewAdapter adapter, int position) {
        super(context);
        this.context = context;
        this.position = position;
        this.adapter = adapter;
    }

    public void callDialog(RecyclerviewItem item) {
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_clickitem);
        textView_name = dialog.findViewById(R.id.dialog_clickitem_name);
        textView_sex = dialog.findViewById(R.id.dialog_clickitem_sex);
        textView_age = dialog.findViewById(R.id.dialog_clickitem_age);
        textView_address = dialog.findViewById(R.id.dialog_clickitem_address);
        button_ok = dialog.findViewById(R.id.dialog_clickitem_okBtn);
        button_delete = dialog.findViewById(R.id.dialog_clickitem_deleteBtn);
        textView_name.setText(item.getName());
        textView_sex.setText(item.getSex());
        textView_age.setText(item.getAge() + "");
        id = item.getId();
        textView_address.setText(item.getAddress());
        dialog.show();

        button_ok.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        button_delete.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                DeleteItemDialog deleteItemDialog = new DeleteItemDialog(context,ClickItemDialog.this);
                deleteItemDialog.callDialog();
            }
        });
    }

    @Override
    public void onDeleteBtnClick(boolean isDel) {
        if(isDel){
            RemoveAsyncTask removeAsyncTask = new RemoveAsyncTask();
            removeAsyncTask.execute(id);
            adapter.removeItem(position);
            dialog.dismiss();
        }
    }

    class RemoveAsyncTask extends AsyncTask<Integer,Integer,Boolean> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Boolean doInBackground(Integer... integers) {
            URLConnector urlConnector = new URLConnector();
            urlConnector.remove_dataFromDB(integers[0]);
            return true;
        }
    }
}
