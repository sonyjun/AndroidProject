package com.example.connection_servermysql;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.NonNull;

public class DeleteItemDialog extends Dialog {
    Context context;
    Button button_ok;
    Button button_cancel;
    onDeleteListener mOnDeleteListener;

    interface onDeleteListener {
        public void onDeleteBtnClick(boolean isDel);
    }

    public DeleteItemDialog(@NonNull Context context, onDeleteListener mOnDeleteListener) {
        super(context);
        this.context = context;
        this.mOnDeleteListener = mOnDeleteListener;
    }

    public void callDialog() {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_deleteitem);
        button_ok = dialog.findViewById(R.id.dialog_deleteitem_okBtn);
        button_cancel = dialog.findViewById(R.id.dialog_deleteitem_noBtn);
        dialog.show();
        button_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
                mOnDeleteListener.onDeleteBtnClick(true);
            }
        });
        button_cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
    class deleteAsyncTask extends AsyncTask<String,Integer,Boolean>{

        @Override
        protected Boolean doInBackground(String... strings) {
            return null;
        }

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
    }
}
