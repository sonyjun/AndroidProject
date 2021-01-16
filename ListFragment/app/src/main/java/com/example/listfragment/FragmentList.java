package com.example.listfragment;

import android.app.ListFragment;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class FragmentList extends ListFragment {
    //Fragment인데, 자체적으로 ListView를 View로 이미 갖고 있는 Fragment임.
    //View를 따로 생성 할 필요 없음.


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String item = (String)l.getItemAtPosition(position);
        Toast.makeText(getActivity(),item+ "을 선택했습니다",Toast.LENGTH_SHORT).show();
    }
}
