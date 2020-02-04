package com.cyb.talk.util;

import android.util.Log;
import com.cyb.talk.ui.login.LoginViewModel;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class HttpUtil {

    /**
     * post提交数据
     */
    public static JSONObject requestPost(String TAG, String requestURL, Map<String, String> paramsMap) {

        JSONObject result = new JSONObject();
        try {
            //合成参数
            String params = null;
            if(null != paramsMap && paramsMap.size() > 0){
                StringBuilder tempParams = new StringBuilder();
                int pos = 0;
                for (String key : paramsMap.keySet()) {
                    if (pos >0) {
                        tempParams.append("&");
                    }
                    tempParams.append(String.format("%s=%s", key, URLEncoder.encode(paramsMap.get(key), "utf-8")));
                    pos++;
                }
                params = tempParams.toString();
            }

            Log.e(TAG,"request info " + requestURL + "\t" + params);
            result.put("r", LoginViewModel.SEND_START);
            URL url = new URL(requestURL);
            // 打开一个HttpURLConnection连接
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            // 设置连接超时时间
            urlConn.setConnectTimeout(5 * 1000);
            //设置从主机读取数据超时
            urlConn.setReadTimeout(5 * 1000);
            // Post请求必须设置允许输出 默认false
            urlConn.setDoOutput(true);
            //设置请求允许输入 默认是true
            urlConn.setDoInput(true);
            // Post请求不能使用缓存
            urlConn.setUseCaches(false);
            // 设置为Post请求
            urlConn.setRequestMethod("POST");
            //设置本次连接是否自动处理重定向
            urlConn.setInstanceFollowRedirects(true);
            //配置请求Content-Type
            //urlConn.setRequestProperty("Content-Type", "application/json");//post请求不能设置这个
            // 开始连接
            urlConn.connect();

            // 发送请求参数
            PrintWriter dos = new PrintWriter(urlConn.getOutputStream());
            if(null != params){
                dos.write(params);
            }
            dos.flush();
            dos.close();
            // 判断请求是否成功
            int code = urlConn.getResponseCode();
            if (code == 200) {
                // 获取返回的数据
                result = streamToJSONObject(TAG, urlConn.getInputStream());
                Log.e(TAG, "Post方式请求成功，result--->" + result);

            } else {
                result.put("r","code:" + code);
                Log.e(TAG, "Post方式请求失败");
            }
            // 关闭连接
            urlConn.disconnect();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return result;
    }

    /**
     * 将输入流转换成字符串
     *
     * @param is 从网络获取的输入流
     * @param TAG
     * @return
     */
    public static JSONObject streamToJSONObject(String TAG, InputStream is) {
        JSONObject jsonObject = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }

            baos.close();
            is.close();
            byte[] byteArray = baos.toByteArray();
            String s = new String(byteArray);
            if(null != s && !s.equals("")){
                jsonObject = new JSONObject(s);
            }
            return jsonObject;
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            return null;
        }
    }
}
