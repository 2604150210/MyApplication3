package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class PagerTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagertest);
        Button btnPagerOne = findViewById(R.id.btnPagerOne);
        btnPagerOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PagerTestActivity.this, PagerOneActivity.class);
                startActivity(intent);
            }
        });
        Button btnPagerTwo = findViewById(R.id.btnPagerTwo);
        btnPagerTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PagerTestActivity.this, PagerTwoActivity.class);
                startActivity(intent);
            }
        });
    }
}