package com.cyb.talk.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class SharedHelper {

    private Context context;

    public SharedHelper() {
    }

    public SharedHelper(Context context) {
        this.context = context;
    }


    //定义一个保存数据的方法
    public void saveLogin(String username, String passwd) {
        SharedPreferences sp = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("username", username);
        editor.putString("passwd", passwd);
        editor.commit();
        Toast.makeText(context, "登陆成功！", Toast.LENGTH_SHORT).show();
    }

    //定义一个读取SP文件的方法
    public Map<String, String> readLogin() {
        Map<String, String> data = null;
        SharedPreferences sp = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        String username = sp.getString("username", null);
        String password = sp.getString("password", null);
        if(null != username && null != password){
            data = new HashMap<String, String>();
            data.put("username", username);
            data.put("password", password);
            Toast.makeText(context, "自动登陆成功！", Toast.LENGTH_SHORT).show();
        }
        return data;
    }
}
