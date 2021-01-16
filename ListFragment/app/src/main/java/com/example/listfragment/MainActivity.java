package com.example.listfragment;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import java.util.ArrayList;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Fragment를 Activity위에 추가하는 방법.
        // 1. Layout Resource XML파일에 fragment 태그를 이용해 Fragment class에 바로 매칭시킨다. MainActivity에서 매칭 시켜줄 필요 없음.
        // 2. Fragment가 들어갈 ViewGroup(FrameLayout 등등)을 미리 선언하고, FragmentManager를 이용해 추가한다.
        // activity의 layout과 fragment의 매칭이 필요하다는 기술적 측면은 같음.
        // 하지만 1번 방법은 그 fragment의 데이터가 다루어질 필요가 있다면, findFragmentById함수를 통해 객체로 선언이 되어야 한다.

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        FragmentList fragmentList = new FragmentList();
        fragmentTransaction.add(R.id.main_framelayout,fragmentList);
        fragmentTransaction.commit();
        //activity_main에 미리 정의한 FrameLayout에 ListFragment 객체를 만들어 View로 추가한다.

        //ListFragment도 결국 ListView이기 때문에 Adapter를 만들어 지정해주어야 함.
        ArrayList<String> arrayList1 = new ArrayList<>();
        arrayList1.add("하나");
        arrayList1.add("둘");
        arrayList1.add("셋");
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayList1);
        fragmentList.setListAdapter(arrayAdapter1);



        ArrayList<String> arrayList2 = new ArrayList<>();
        arrayList2.add("넷");
        arrayList2.add("다섯");
        arrayList2.add("여섯");
        FragmentList fragmentList2 = (FragmentList) fm.findFragmentById(R.id.main_fragment);
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayList2);
        fragmentList2.setListAdapter(arrayAdapter2);
    }
}