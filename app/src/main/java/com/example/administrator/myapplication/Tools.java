package com.example.administrator.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Base64;
import android.util.Log;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLConnection;
import java.net.URLEncoder;

public class Tools {
    static final private String TAG = "DBtest";
    static final private  String TABLENAME = "user";
    public static int query(Context context, String user, String password) {
        DBHelper helper = new DBHelper(context,"main",null,1);
        SQLiteDatabase db =helper.getReadableDatabase();
        Cursor cursor = db.query("user2", null, null,null, null, null, null);
        int ret = 0;
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                if (cursor.getString(1).equals(user)){
                    ret = 1;
                    if (cursor.getString(2).equals(password)){
                        ret = 2;
                        break;
                    }
                }
                cursor.moveToNext();
            }
        }
        Log.i(TAG, String.valueOf(ret));
        cursor.close();
        db.close();
        return  ret;
    }

    public void mysql(){
        
    }
    public static boolean insert(Context context, String name, String pwd) {
        if(query(context, name,pwd) != 0){
            return false;
        }
        DBHelper helper = new DBHelper(context,"main",null,1);
        SQLiteDatabase db =helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("pwd",pwd);
        db.insert("user2", "id", values);
        db.close();
        return  true;
    }

   public static String base64(String content){
       try {
           content = Base64.encodeToString(content.getBytes("utf-8"), Base64.DEFAULT);
       } catch (UnsupportedEncodingException e) {
           e.printStackTrace();
       }
       content = URLEncoder.encode(content);
        return content;
   }
}
