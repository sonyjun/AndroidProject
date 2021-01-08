package com.example.basiclistview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] str = {"A", "B", "C"};//Adapter에 전달되어질 String 배열.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, str);
        // ArrayAdater : 기본적으로 안드로이드 SDK에서 제공되는 Adapter, 배열을 표시해줄 때 사용한다.
        // ArrayAdapter(Context context, int resource, T[] objects)
        // context : 안드로이드 시스템에서 제공되는 어플리케이션 전역 환경 정보에 대한 인터페이스. (Activity를 통해 사용 가능)
        // resource : View로 매핑될 Resource Id. "android.R.layout.simple_list_item_1"은 TextView 위젯으로 구성된 ListView 아이템 리소스 Id.
        // objects : 배열로 선언된 사용자 데이터.

        ListView listView = (ListView) findViewById(R.id.main_listview);
        listView.setAdapter(adapter);
        // ListView는 AdapterView를 상속받아 만들어진다. AdapterView는 Adapter를 이용해 표시되어질 view를 전달받아 화면을 구성한다.
        // ListView 역시 각 항목에 뿌려질 Item(view)을 Adapter에게 요청해야하기 때문에. 연결 과정이 필요.

        // ListView을 구성하는 아이템(view) 항목을 클릭 했을 때 호출되는 메소드 구현.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Listener는 Listview의 아이템(view)에 설정이 되어 있는 상태이므로, 여기서 parent는 listview를 가리킨다.
                Toast.makeText(MainActivity.this, (String)parent.getItemAtPosition(position),Toast.LENGTH_SHORT).show();
            }
        });
    }
}