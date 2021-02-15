package com.example.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.main_progressbar);
        textView = (TextView) findViewById(R.id.main_textview);
        button = (Button) findViewById(R.id.main_btn);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                NewAsyncTask asyncTask = new NewAsyncTask(MainActivity.this);
                asyncTask.execute("helloworld");
            }
        });
    }


    class NewAsyncTask extends AsyncTask<String, Long, Boolean> {
        Context context;

        public NewAsyncTask(Context context) {
            super();
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setMax(100);
            progressBar.setProgress(0);
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            Log.e("전달받은 매개변수", strings[0]);
            long i;
            for (i = 1; i <= 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i * 10);
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(aBoolean){
                Toast.makeText(context, "끝", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onProgressUpdate(Long... values) {
            super.onProgressUpdate(values);
            long percent = values[0];
            progressBar.setProgress((int) percent);
            textView.setText((int) percent+"");
        }
    }
}