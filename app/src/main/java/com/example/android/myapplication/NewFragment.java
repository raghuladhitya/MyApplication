package com.example.android.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NewFragment extends Fragment {

    public NewFragment(){

    }
    TextView textView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.card_view_activity,container,false);
        textView = (TextView)view.findViewById(R.id.newText);
        textView.setText("Hi! you did it to the best");
        return view;
    }
}
