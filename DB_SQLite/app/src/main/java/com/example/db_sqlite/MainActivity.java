package com.example.db_sqlite;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends Activity {
    SQLiteDatabase sqliteDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqliteDB = init_database(); // db참조를 얻어옴. 없다면 db생성.
        init_tables();// 테이블이 없다면 테이블 생성.
        load_values();// 기존에 데이터 값을 불러와서 뿌려줌.
        Button btn_save = (Button) findViewById(R.id.buttonSave);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save_values();// 입력된 값을 DB에 저장.
            }
        });
        Button btn_clear = (Button) findViewById(R.id.buttonClear);
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete_values();// 입력된 DB를 모두 지우고, 화면에 EditText 항목도 공백처리.
            }
        });
    }

    private SQLiteDatabase init_database() {

        SQLiteDatabase db = null;
        // File file = getDatabasePath("contact.db") ;
        File file = new File(getFilesDir(), "contact.db");

        System.out.println("PATH : " + file.toString());
        try {
            db = SQLiteDatabase.openOrCreateDatabase(file, null);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }

        if (db == null) {
            System.out.println("DB creation failed. " + file.getAbsolutePath());
        }

        return db;
    }

    private void init_tables() {

        if (sqliteDB != null) {
            String sqlCreateTbl = "CREATE TABLE IF NOT EXISTS CONTACT_T (" +
                    "NO " + "INTEGER NOT NULL," +
                    "NAME " + "TEXT," +
                    "PHONE " + "TEXT," +
                    "OVER20 " + "INTEGER" + ")";

            System.out.println(sqlCreateTbl);

            sqliteDB.execSQL(sqlCreateTbl);
        }
    }

    private void load_values() {

        if (sqliteDB != null) {
            String sqlQueryTbl = "SELECT * FROM CONTACT_T";
            Cursor cursor = null;

            // 쿼리 실행
            cursor = sqliteDB.rawQuery(sqlQueryTbl, null);

            if (cursor.moveToNext()) { // 레코드가 존재한다면,
                // no (INTEGER) 값 가져오기.
                int no = cursor.getInt(0);
                EditText editTextNo = (EditText) findViewById(R.id.editTextNo);
                editTextNo.setText(Integer.toString(no));

                // name (TEXT) 값 가져오기
                String name = cursor.getString(1);
                EditText editTextName = (EditText) findViewById(R.id.editTextName);
                editTextName.setText(name);

                // phone (TEXT) 값 가져오기
                String phone = cursor.getString(2);
                EditText editTextPhone = (EditText) findViewById(R.id.editTextPhone);
                editTextPhone.setText(phone);

                // over20 (INTEGER) 값 가져오기.
                int over20 = cursor.getInt(3);
                CheckBox checkBoxOver20 = (CheckBox) findViewById(R.id.checkBoxOver20);
                if (over20 == 0) {
                    checkBoxOver20.setChecked(false);
                } else {
                    checkBoxOver20.setChecked(true);
                }
            }
        }
    }

    private void save_values() {
        if (sqliteDB != null) {

            // delete
            sqliteDB.execSQL("DELETE FROM CONTACT_T");

            EditText editTextNo = (EditText) findViewById(R.id.editTextNo);
            String noText = editTextNo.getText().toString();
            int no = 0;
            if (noText != null && !noText.isEmpty() && noText.matches("^[0-9]+$")) {

                no = Integer.parseInt(noText);

                EditText editTextName = (EditText) findViewById(R.id.editTextName);
                String name = editTextName.getText().toString();

                EditText editTextPhone = (EditText) findViewById(R.id.editTextPhone);
                String phone = editTextPhone.getText().toString();

                CheckBox checkBoxOver20 = (CheckBox) findViewById(R.id.checkBoxOver20);
                boolean isOver20 = checkBoxOver20.isChecked();

                String sqlInsert = "INSERT INTO CONTACT_T " +
                        "(NO, NAME, PHONE, OVER20) VALUES (" +
                        Integer.toString(no) + "," +
                        "'" + name + "'," +
                        "'" + phone + "'," +
                        ((isOver20 == true) ? "1" : "0") + ")";

                System.out.println(sqlInsert);

                sqliteDB.execSQL(sqlInsert);
            } else {
                Toast.makeText(this, "숫자가 아닌 값이 NO 에 있습니다. ", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void delete_values() {
        if (sqliteDB != null) {
            String sqlDelete = "DELETE FROM CONTACT_T" ;

            sqliteDB.execSQL(sqlDelete) ;

            EditText editTextNo = (EditText) findViewById(R.id.editTextNo) ;
            editTextNo.setText("") ;

            EditText editTextName = (EditText) findViewById(R.id.editTextName) ;
            editTextName.setText("") ;

            EditText editTextPhone = (EditText) findViewById(R.id.editTextPhone) ;
            editTextPhone.setText("") ;

            CheckBox checkBoxOver20 = (CheckBox) findViewById(R.id.checkBoxOver20) ;
            checkBoxOver20.setChecked(false) ;
        }
    }
}