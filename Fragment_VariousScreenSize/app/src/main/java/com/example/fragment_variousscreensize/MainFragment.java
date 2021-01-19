package com.example.fragment_variousscreensize;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainFragment extends Fragment {
    ArrayList<String> titleArrayList;
    ArrayList<String> contentArrayList;
    ArrayAdapter<String> arrayAdapter;
    OnItemClickListener onItemClickListener;
    ListView listView;
    public interface OnItemClickListener{
        void onItemClick(int position, String title, String content);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        listView = (ListView) view.findViewById(R.id.fragment_main_listview);
        Button button_add = (Button) view.findViewById(R.id.fragment_main_btn_add);
        EditText editText_title = (EditText) view.findViewById(R.id.fragment_main_edittext_title);
        EditText editText_content = (EditText) view.findViewById(R.id.fragment_main_edittext_content);


        //listview 처리
        titleArrayList = new ArrayList<String>();
        contentArrayList = new ArrayList<String>();
        for(int i = 0 ; i  < 10 ; i ++){
            titleArrayList.add("제목"+i);
            contentArrayList.add("내용"+i);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //MainActivity의 리스너 호출할 거임
                showDetails(position);
            }
        });
        arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, titleArrayList);
        listView.setAdapter(arrayAdapter);

        //editText, button처리
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editText_title.getText().toString().equals("") && !editText_content.getText().toString().equals("")) {
                    titleArrayList.add(editText_title.getText().toString());
                    contentArrayList.add(editText_content.getText().toString());
                    arrayAdapter.notifyDataSetChanged();
                    editText_title.setText("");
                    editText_content.setText("");
                    Toast.makeText(getActivity(),"추가 되었습니다.",Toast.LENGTH_SHORT).show();
                    //현재 보고있는 화면에서는 추가가 되지만, fragment의 view가 새로 그려질 땐 사라질 것임.
                    //왜냐,, 데이터는 원래 DB를 통해 갖고오는게 맞지만, 여기서는 초기화 할 경우 임시로 추가해 준 것이기 때문,
                    //따라서 oncreateView에 DB와 통신을 통해 값을 가져오는 구문을 추가하면 됨.
                }else{
                    Toast.makeText(getActivity(),"제목 또는 내용이 비었습니다.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.fragment_main_linear_addbox);
            linearLayout.setVisibility(View.GONE);
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {// context로 MainAcitivty의 참조를 받아와 캐스팅하여 리스너 객체에 맵핑.
        super.onAttach(context);
        try {
            if (context instanceof Activity) {
                onItemClickListener = (OnItemClickListener) context;
            }
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnTitleSelectedListener");
        }
    }

    // onListItemClick() 함수에서 호출하는 showDetails() 함수.
    public void showDetails(int position) {
        onItemClickListener.onItemClick(position, titleArrayList.get(position), contentArrayList.get(position)) ;
    }


    public void addItem(String title, String content){//MainActivity같은 외부에서 값을 추가할 경우 이 메소드 사용.
        titleArrayList.add(title);
        contentArrayList.add(content);//내용도 같이 보관된다고 가정.
        arrayAdapter.notifyDataSetChanged();
    }
}
