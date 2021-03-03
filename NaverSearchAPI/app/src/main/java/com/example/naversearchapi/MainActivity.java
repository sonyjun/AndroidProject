package com.example.naversearchapi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button button_search;
    Button button_detail;
    ListView listView;
    ArrayList<String> arrayList;
    ArrayAdapter adapter;
    String jsonContent;
    TextView textView_blank;
    boolean isFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("네이버 API 영화 검색");
        editText = (EditText) findViewById(R.id.main_edittext);
        textView_blank = (TextView) findViewById(R.id. main_textview_blank);
        button_search = (Button) findViewById(R.id.main_btn_search);
        button_detail = (Button) findViewById(R.id.main_btn_detail);
        listView = (ListView) findViewById(R.id.main_listview);
        arrayList = new ArrayList<>();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);
        button_search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String input = editText.getText().toString();
                if (input.equals("")) {
                    Toast.makeText(MainActivity.this, "영화 제목 입력을 해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    if (isFirst == true) {
                        textView_blank.setVisibility(View.GONE);
                        listView.setVisibility(View.VISIBLE);
                        isFirst = false;
                    }
                    SearchAsyncTask s = new SearchAsyncTask();
                    s.execute(input);
                }
            }
        });
        button_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailDialog detailDialog = new DetailDialog(MainActivity.this);
                detailDialog.showDialog(jsonContent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String title ="";
                try {
                    title = URLEncoder.encode(arrayList.get(position), "UTF-8");
                    //네이버에서 UTF-8형태의 쿼리를 통해 검색을 하므로 변경해주어야 함.
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://search.naver.com/search.naver?query=%EC%98%81%ED%99%94+"+title));
                // ' 영화 + 놈놈놈 ' 이런식으로 검색되도록 설정.
                startActivity(intent);
            }
        });
    }


    class SearchAsyncTask extends AsyncTask<String, Integer, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            adapter.notifyDataSetChanged();
            jsonContent = s;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(String... strings) {
            String result = ApiSearchMovie.setting(strings[0]);
            arrayList.clear();
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("items");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject itemObejct = jsonArray.getJSONObject(i);
                    String title = itemObejct.getString("title");
                    title = title.replace("<b>", "").replace("</b>", "");

                    arrayList.add(title);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return result;
        }
    }
}