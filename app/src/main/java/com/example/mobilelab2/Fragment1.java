package com.example.mobilelab2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Fragment1 extends Fragment {

    TextView textViewDate;
    Button buttonGetDate;

    Fragment1(){
        super(R.layout.fragment1_layout);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment1_layout, container, false);
        textViewDate = rootView.findViewById(R.id.f1_twDate);
        buttonGetDate = rootView.findViewById(R.id.f1_b1Date);

        buttonGetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateFormat dateFormat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Calendar cal = Calendar.getInstance();
                Date currentDate = cal.getTime();
                textViewDate.setText(dateFormat.format(currentDate));
            }
        });

        return rootView;
    }
}
