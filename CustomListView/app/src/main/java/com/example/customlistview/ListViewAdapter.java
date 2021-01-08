package com.example.customlistview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    private ArrayList<ListViewItem> listViewItemArrayList = new ArrayList<ListViewItem>();

    @Override
    public int getCount() {// getCount로 반환되는 아이템의 수 만큼 아래 메소드들을 호출함.
        return listViewItemArrayList.size();
    }

    @Override
    public ListViewItem getItem(int position) {//해당 위치에 있는 아이템 반환
        return listViewItemArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //해당 위치에 표시되어질 view를 만들어서 반환해줌.
        final int pos = position;
        final Context context = parent.getContext();
        if (convertView == null) {
            //view를 만들어 주기 위해서 inflater를 이용해야됨.
            //inflate는 view를 정의하기 위한 xml파일을 이용해 실제 view객체를 만들어주는 행위임.
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);//만들어진 view객체
        }

        //view객체의 멤버함수인 findViewById를 통해 view안에 widget들을 참조.
        ImageView imageView = (ImageView) convertView.findViewById(R.id.item_imageview);
        TextView textView1 = (TextView) convertView.findViewById(R.id.item_textview1);
        TextView textView2 = (TextView) convertView.findViewById(R.id.item_textview2);

        ListViewItem item = listViewItemArrayList.get(position);//요청한 위치의 데이터를 불러옴

        //해당 위젯에 맞게 데이터를 입력 시킴.
        imageView.setImageDrawable(item.getIconDrawable());
        textView1.setText(item.getTxt1());
        textView2.setText(item.getTxt2());

        return convertView;//최종적으로 만든 view를 반환. listview는 반환된 view를 해당 위치에 표시.
    }

    public void addItem(Drawable drawable, String text1, String text2) {
        ListViewItem item = new ListViewItem();
        item.setIconDrawable(drawable);
        item.setTxt1(text1);
        item.setTxt2(text2);
        listViewItemArrayList.add(item);
    }
}
