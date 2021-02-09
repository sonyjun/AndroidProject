package com.example.viewpager;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CustomPagerAdapter extends androidx.viewpager.widget.PagerAdapter {
    Context context;
    View view;
    public CustomPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //ListView의 getView()와의 차이점은 재활용을 하고 안하고 차이인 것 같음. ListView는 10 몇 개 만들어놓고 그렇잔아.
        //얘는 이전꺼랑 앞에꺼만 만들어 놓으니.. 재활용도 없고.. 보면 getView에는 convertView가 있는데 여긴 없자나. 재활용 할 view를 넘겨주질 않네.
        View view = null;
        if (context != null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.viewpager_page, container, false);
            TextView textView = view.findViewById(R.id.page_textview);
            textView.setText(position + "번째 Content");
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // 본인것과 양쪽에 해당되는 왼쪽,오른쪽 뷰는 유지시켜놓음. visible,invisible 인듯.
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        //키로 관리되는 키 객체와 반환 받은 view를 비교하는 역할. 표시해주려는 뷰가 관리되는 것과 같으면 보여주는 것 같음.
        return view == (View)object;
    }
}
