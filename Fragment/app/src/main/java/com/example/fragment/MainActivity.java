package com.example.fragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    static boolean isFragB = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // FragmentManager? : Fragment와 Activity의 중간에서 서로를 이어주는 역할을 한다. 구체적으로 2개의 역할로 구분 가능.
        // 역할 1 : FragmentTransaction을 통해 액티비티에서 이루어진 사용자의 입력에 따라 Fragment의 추가 및 삭제, 교체 등의 작업을 수행할 수 있게 해준다.
        // +) 행해진 트랜잭션의 상태를 백스택에 저장할 수도 있다.
        // 역할 2 : FragmentTransaction이 Fragment를 통채로 다루는 작업이라면, Fragment의 내부 요소에 대한 세부 작업을 도와준다.
        //         Fragment의 내부 요소에 접근이 가능하도록하여, 액티비티에서 특정 이벤트 발생시 Fragment에서 적절한 UI 동작을 할 수 있도록 구현이 가능하다.

        FragmentManager fm = getFragmentManager();// FragmentManager사용을 위해 참조를 획득.
        FragmentTransaction fragmentTransaction = fm.beginTransaction();// DB와 비슷하게 Transaction 작업을 시작하겠다 알리는 것임. 이제 fragment에 대한 추가(add),전환(replace),삭제(remove) 등등의 일련의 작업을 하면 됨.
        fragmentTransaction.add(R.id.main_framelayout_fragBorC, new FragmentB()); // framelayout에 fragmentB를 추가.
        //fragmentTransaction.addToBackStack(null); // addToBackStack() 메소드를 통해 이전 상태로 back key를 통해 돌아갈 수 있다.
        fragmentTransaction.commit(); // transaction 작업이 완료 되엇다고 알림.

        Button btn_change = (Button) findViewById(R.id.main_btn_fragchange);
        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();

                if(isFragB){
                    FragmentC fragmentC = new FragmentC();
                    fragmentTransaction.replace(R.id.main_framelayout_fragBorC, fragmentC);
                    isFragB = false;
                }else{
                    FragmentB fragmentB = new FragmentB();
                    fragmentTransaction.replace(R.id.main_framelayout_fragBorC, fragmentB);
                    isFragB = true;
                }
                fragmentTransaction.commit();
            }
        });
    }
}