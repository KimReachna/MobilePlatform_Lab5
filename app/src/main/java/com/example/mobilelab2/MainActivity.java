package com.example.mobilelab2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    Button buttonFragment1;
    Button buttonFragment2;

    FrameLayout container;
    Fragment1 fragment1;
    Fragment2 fragment2;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonFragment1 = findViewById(R.id.ma_bf1);
        buttonFragment2 = findViewById(R.id.ma_bf2);

        fragmentManager = getSupportFragmentManager();
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();

        if(savedInstanceState == null){
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_container_view, new Fragment1(), null);
            fragmentTransaction.commit();
        }

        buttonFragment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container_view, new Fragment1(), null);
                fragmentTransaction.commit();
            }
        });

        buttonFragment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container_view, new Fragment2(), null);
                fragmentTransaction.commit();
            }
        });
    }
}