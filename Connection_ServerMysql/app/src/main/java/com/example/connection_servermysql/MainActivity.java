package com.example.connection_servermysql;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    RecyclerView recyclerView;
    RecyclerviewAdapter recyclerviewAdapter;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.main_btn_add);
        progressBar = (ProgressBar) findViewById(R.id.main_progressbar);
        recyclerView = (RecyclerView) findViewById(R.id.main_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<RecyclerviewItem> arrayList = new ArrayList<RecyclerviewItem>();
        recyclerviewAdapter = new RecyclerviewAdapter(this, arrayList);
        recyclerView.setAdapter(recyclerviewAdapter);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.main_swiperefresh);
        AsyncTask_GetPersonData asyncTask_getPersonData = new AsyncTask_GetPersonData();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recyclerviewAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddItemDialog addItemDialog = new AddItemDialog(MainActivity.this, recyclerviewAdapter);
                addItemDialog.callDialog();
            }
        });
        asyncTask_getPersonData.execute();
    }
    class AsyncTask_GetPersonData extends AsyncTask<String, Integer, Boolean> {
        String result;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            swipeRefreshLayout.setVisibility(View.GONE);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            progressBar.setVisibility(View.GONE);
            swipeRefreshLayout.setVisibility(View.VISIBLE);
            Log.e("result", result);
            try {
                JSONObject jsonObject = new JSONObject(result);
                String status = jsonObject.getString("status");
                int rowNum = Integer.parseInt(jsonObject.getString("rownum"));
                JSONArray jsonArray = jsonObject.getJSONArray("result");
                //ArrayList<RecyclerviewItem> getItemArrayList = new ArrayList<RecyclerviewItem>();
                for (int i = 0; i < rowNum; i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    String name = object.getString("name");
                    String sex = object.getString("sex");
                    int age = Integer.parseInt(object.getString("age"));
                    String address = object.getString("address");
                    int id = Integer.parseInt(object.getString("id"));
                    recyclerviewAdapter.addItem(new RecyclerviewItem(id, name, sex, age, address));
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            URLConnector urlConnector = new URLConnector();
            result = urlConnector.getJSON_DataFromDB();
            /*
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            */

            return true;
        }
    }
}