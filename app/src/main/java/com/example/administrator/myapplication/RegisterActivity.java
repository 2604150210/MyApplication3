package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText editTextUserR = findViewById(R.id.editTextUserR);
        final EditText editTextPasswordR = findViewById(R.id.editTextPasswordR);
        final EditText editTextPassword2R = findViewById(R.id.editTextPassword2R);
        final Button btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextUserR.getText().toString();
                String pwd = editTextPasswordR.getText().toString();
                String pwd2 = editTextPassword2R.getText().toString();
                if (!pwd.equals(pwd2)){
                    Toast.makeText(RegisterActivity.this,"两次密码不一致，请重新输入！",Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean in = Tools.insert(RegisterActivity.this, name, pwd);
                if (in){
                    Toast.makeText(RegisterActivity.this, "注册成功！，即将跳转至首页", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RegisterActivity.this, IndexActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(RegisterActivity.this, "该用户名已存在", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
