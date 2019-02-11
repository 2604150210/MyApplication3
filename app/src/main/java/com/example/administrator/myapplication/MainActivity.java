package com.example.administrator.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {
    EditText editTextUser = null;
    EditText editTextPassword = null;
    final Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==1){
                Toast.makeText(MainActivity.this, "用户名或密码不正确", Toast.LENGTH_SHORT).show();
            } else if(msg.what==2){
                Toast.makeText(MainActivity.this, "恭喜登录成功！", Toast.LENGTH_SHORT).show();
                /*登陆成功将数据写到本地保存下来，以便下次自动额登陆*/

                Intent i = new Intent(MainActivity.this, IndexActivity.class);
                startActivity(i);
            } else{
                Toast.makeText(MainActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUser = findViewById(R.id.editTextUser);
        editTextPassword = findViewById(R.id.editTextPassword);
        final Button btnGoRegister = findViewById(R.id.btnGoRegister);
        final Button btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Thread t=new Thread(new Runnable() {
                    @Override
                    public void run() {
                        send();
                    }
                });
                t.start();
            }
        });

        btnGoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://oj.ahstu.cc/JudgeOnline/registerpage.php"));
                startActivity(intent);
            }
        });
    }

    private void send() {
        try {
            URL url = new URL("http://git.webturing.com/ServiceProject/login.php");
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();//创建一个HTTP连接
            urlConn.setRequestMethod("POST");//使用POST方式
            urlConn.setDoInput(true);//从连接中写入数据
            urlConn.setDoOutput(true);//从连接中读取数据
            urlConn.setUseCaches(false);//设置禁止缓存
            urlConn.setInstanceFollowRedirects(true);//设置自动执行HTTP重定向
            urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//设置内容格式
            DataOutputStream out = new DataOutputStream(urlConn.getOutputStream());//获取输出流
            String user = editTextUser.getText().toString();
            String password = editTextPassword.getText().toString();
            Log.i("user", user);
            Log.i("password", password);
            String param = String.format("user=%s&password=%s", user, password);
            Log.i("param",param);
            out.writeBytes(param);//将要传输的数据写入数据输出流
            out.flush();
            out.close();
            //判断是否响应成功
            Message m1=handler.obtainMessage();
            m1.what = -1;
            String loginResult = null;
            if (urlConn.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStreamReader in = new InputStreamReader(urlConn.getInputStream());//获得读取的内容
                BufferedReader buffer = new BufferedReader(in);//获取输入流对象
                loginResult = buffer.readLine().trim();
                in.close();
                if (loginResult.equals("2")){
                    m1.what = 2;//登录成功
                }else if(loginResult.equals("1")){
                    m1.what = 1;//用户名或密码错误
                }
            }
            Log.i("result", loginResult);
            handler.sendMessage(m1);
            urlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
