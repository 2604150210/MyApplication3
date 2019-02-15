package com.example.administrator.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PasswordFormTextActivity extends AppCompatActivity {

    private EditText edit_pawd;
    private Button btnChange;
    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwordfromtext);
        edit_pawd = (EditText) findViewById(R.id.edit_pawd);
        btnChange = (Button) findViewById(R.id.btnChange);
        edit_pawd.setHorizontallyScrolling(true);    //设置EditText不换行
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag == true){
                    edit_pawd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    flag = false;
                    btnChange.setText("密码不可见");
                }else{
                    edit_pawd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    flag = true;
                    btnChange.setText("密码可见");
                }
            }
        });
    }
}