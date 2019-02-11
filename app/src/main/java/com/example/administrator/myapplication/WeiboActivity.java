package com.example.administrator.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class WeiboActivity extends AppCompatActivity {

    EditText editTextNick = null, editTextContent = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_weibo);
        editTextContent = findViewById(R.id.editTextContent);
        final TextView textViewResult = findViewById(R.id.textViewResult);
        final Button btnSubmit = findViewById(R.id.btnSubmit);
        editTextNick = findViewById(R.id.editTextNick);
        final Handler hander = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (result != null){
                    textViewResult.setText(result);
                    editTextContent.setText("");
                    editTextNick.setText("");
                }
                super.handleMessage(msg);
            }
        };
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextContent.getText().toString().equals("")){
                    Toast.makeText(WeiboActivity.this, "请输入要发表的内容！", Toast.LENGTH_SHORT).show();//显示消息提示
                    return;
                }

                //创建一个新线程，用于发送并去读微博信息
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        send();
                        Message m = hander.obtainMessage();//获取一个Massage
                        hander.sendMessage(m);
                    }
                });
                thread.start();
            }
        });

    }


    String result = "";
    public void send(){
        String target="http://git.webturing.com/jal/index.php";//要访问的URL
        URL url;
        try {
            url = new URL(target);//创建URL对象
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();//创建一个HTTP连接
            urlConnection.setRequestMethod("POST");//指定使用POST请求方式
            urlConnection.setDoInput(true);//向连接中写入数据
            urlConnection.setDoOutput(true);
            urlConnection.setUseCaches(false);
            urlConnection.setInstanceFollowRedirects(true);
            urlConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            DataOutputStream out = new DataOutputStream(urlConnection.getOutputStream());//获取输出流

            String param = "nick="+URLEncoder.encode(editTextNick.getText().toString(), "utf-8") + "&content="+URLEncoder.encode(editTextContent.getText().toString(), "utf-8");
            out.writeBytes(param);
            out.flush();
            out.close();

            //判断是否响应成功
            if(urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStreamReader in = new InputStreamReader(urlConnection.getInputStream());//获得读取的内容
                BufferedReader bf = new BufferedReader(in);//获取输入流对象
                String inputLine = null;
                result = "";
                //通过循环逐行读取输入流中的内容
                while ((inputLine = bf.readLine()) != null){
                    result += inputLine + "\n";
                }
                bf.close();
                in.close();
            }
            urlConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
