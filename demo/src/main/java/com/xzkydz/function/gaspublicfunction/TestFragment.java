package com.xzkydz.function.gaspublicfunction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class TestFragment extends Fragment {

    private TextView textView;
    private String showStr;

    @SuppressLint("ValidFragment")
    public TestFragment(String showStr) {
        this.showStr = showStr;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test,null);
        textView = view.findViewById(R.id.textview);
        textView.setText(showStr);
        return view;
    }
}
