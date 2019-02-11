package com.example.administrator.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class PointActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point);
        final EditText editTextA = findViewById(R.id.editTextA);
        final EditText editTextB = findViewById(R.id.editTextB);
        final EditText editTextC = findViewById(R.id.editTextC);
        final EditText editTextD = findViewById(R.id.editTextD);
        final TextView textViewResult = findViewById(R.id.textViewResult);
        final ListView listViewPointResults = findViewById(R.id.listViewPointResults);
        final Button btnA = findViewById(R.id.btnA);
        final Button btnRandom = findViewById(R.id.btnRandom);
        final Button btnExit = findViewById(R.id.btnExit);
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = 0, b = 0, c = 0, d = 0;
                try {
                    a = Integer.valueOf(editTextA.getText().toString());
                    b = Integer.valueOf(editTextB.getText().toString());
                    c = Integer.valueOf(editTextC.getText().toString());
                    d = Integer.valueOf(editTextD.getText().toString());
                }catch (Exception e){
                    Toast.makeText(PointActivity.this, "请输入四个整数！",Toast.LENGTH_SHORT).show();
                    return;
                }
                 Point point = new Point(a, b, c, d);
//                textViewResult.setText(point.getResult());
                String[]ret = point.getResults();
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(PointActivity.this,android.R.layout.simple_list_item_1, ret){
                    @Override
                    public View getView(int position, View convertView,ViewGroup parent) {
                        TextView textView = (TextView) super.getView(position, convertView, parent);
                        textView.setGravity(Gravity.CENTER);
                        return textView;
                    }
                };
                listViewPointResults.setAdapter(arrayAdapter);
            }
        });
        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PointActivity.this, "已为您随机生成好四个数！",Toast.LENGTH_SHORT).show();
                int n = 10;
                int a = (int)(Math.random()*n+1);
                int b = (int)(Math.random()*n+1);
                int c = (int)(Math.random()*n+1);
                int d = (int)(Math.random()*n+1);
                int arr[] = new int[]{a, b, c, d};
                Arrays.sort(arr);
                editTextA.setText(String.valueOf(arr[0]));
                editTextB.setText(String.valueOf(arr[1]));
                editTextC.setText(String.valueOf(arr[2]));
                editTextD.setText(String.valueOf(arr[3]));
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }
}
