package com.example.administrator.myapplication;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

public class DrawActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        FrameLayout frameLayout1 = findViewById(R.id.frameLayout1);
        frameLayout1.addView(new DrawView(DrawActivity.this));
    }

}
