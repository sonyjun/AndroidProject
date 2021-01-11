package com.example.buttonlistener;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView text_content = (TextView) findViewById(R.id.main_txt_content);
        Button btn_red = (Button) findViewById(R.id.main_btn_red);
        Button btn_green = (Button) findViewById(R.id.main_btn_green);
        Button btn_blue = (Button) findViewById(R.id.main_btn_blue);


        //--------------------------------------여러 방법 소개------------------------------------------------
        // 1. setOnClickLister에 익명 클래스를 이용해 바로 listener 객체를 등록하는 방법.
        // class는 없지만 class 이름이 없는 일회용성 class가 implements OnClickLister를 하고 즉석해서 객체를 생성해내서 사용.
        // 주의할 점 : View.OnClickListener 객체를 생성하는 것이 아닌 View.OnClickListener를 상속받는 객체가 생성되는 것.
        /*btn_red.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                text_content.setText("dsf");
            }
        });*/



        // 2. 익명 클래스를 이용해 바로 객체를 생성해서 여러 button이 공유해서 사용하는 경우, lister를 공유해서 사용 가능.
        /*View.OnClickListener lister = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };
        btn_red.setOnClickListener(lister);*/




        // 3. View.OnClickListener를 상속받는 새로운 클래스를 생성, 이 class는 밖에 있던, inner로 있던 상관없음.
        /*class BtnOnClickLister implements View.OnClickListener{
            @Override
            public void onClick(View v) {

            }
        }
        BtnOnClickLister btnOnClickLister = new BtnOnClickLister();
        btn_red.setOnClickListener(btnOnClickLister);




        // 4. MainActivity가 직접 View.OnClickListener를 implements하여 구현하는 경우.
        btn_red.setOnClickListener(this);*/





        // 5. XML에서 Button의 onClick 속성을 이용해 처리할 메소드를 지정해주는 경우.
        //--------------------------------------방법 설명 끝------------------------------------------------



        // 버튼 3개 모두 1번 방법을 이용해 구현하겠습니다.
        btn_red.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                text_content.setBackgroundColor(Color.RED);
            }
        });

        btn_green.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                text_content.setBackgroundColor(Color.GREEN);
            }
        });

        btn_blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_content.setBackgroundColor(Color.BLUE);
            }
        });

    }

    @Override
    public void onClick(View v) {

    }
}