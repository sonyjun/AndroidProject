package com.example.listviewwithbutton;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter implements View.OnClickListener {
    int i=0;
    ArrayList<ListViewItem> listViewItemArrayList = new ArrayList<ListViewItem>();
    ListViewBtnClickListener listViewBtnClickListener;
    public ListViewAdapter(ArrayList<ListViewItem> arrayList, ListViewBtnClickListener listener){
        this.listViewItemArrayList = arrayList;
        this.listViewBtnClickListener = listener;
    }

    public ListViewAdapter(){

    }
    @Override
    public int getCount() {
        return listViewItemArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return listViewItemArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {//처음부터 다 부르진 않고, 화면에 표시될 만큼만 호출됨.
        final int pos = position;
        final Context context = parent.getContext();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }
        ImageView imageView = (ImageView) convertView.findViewById(R.id.item_imageview);
        Button editBtn = (Button) convertView.findViewById(R.id.item_btn_edit);
        Button toastBtn = (Button) convertView.findViewById(R.id.item_btn_toast);
        TextView textView = (TextView) convertView.findViewById(R.id.item_textview);

        ListViewItem item = listViewItemArrayList.get(pos);
        imageView.setImageDrawable(item.getDrawable());
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText().toString()+"\n 눌렸음");
            }
        });
        toastBtn.setOnClickListener(this);
        toastBtn.setTag(position);
        textView.setText(item.getText());
        return convertView;
    }

    public void addItem(Drawable drawable, String txt){
        ListViewItem item = new ListViewItem(drawable,txt);
        listViewItemArrayList.add(item);
    }

    @Override
    public void onClick(View v) {
        listViewBtnClickListener.onListBtnClick((int)v.getTag());
        // 생성자를 통해 listener를 전달 받았고, btn2가 눌리면 tag로 설정해두었던 해당 position을 매개변수로하여 listener를 호출한다.
    }
}
