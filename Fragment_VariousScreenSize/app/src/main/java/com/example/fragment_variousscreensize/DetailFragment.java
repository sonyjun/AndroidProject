package com.example.fragment_variousscreensize;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailFragment extends Fragment {
    TextView textView_title;
    TextView textView_content;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_detail, container, false);
        textView_title = view.findViewById(R.id.activity_fragment_detail_textview_title);
        textView_content = view.findViewById(R.id.activity_fragment_detail_textview_content);
        Bundle bundle = getArguments();
        textView_title.setText(bundle.getString("title"));
        textView_content.setText(bundle.getString("content"));
        return view;
    }
}
