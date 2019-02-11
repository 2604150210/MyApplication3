package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class IndexActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        final Button btn2D = findViewById(R.id.btn2D);
        btn2D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IndexActivity.this, DrawActivity.class);
                startActivity(intent);
            }
        });
        final Button btnGoWeibo = findViewById(R.id.btnGoWeibo);
        btnGoWeibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IndexActivity.this, WeiboActivity.class);
                startActivity(intent);
            }
        });
        final Button btnGoPoint = findViewById(R.id.btnGoPoint);
        btnGoPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IndexActivity.this, PointActivity.class);
                startActivity(intent);
            }
        });
    }
}
