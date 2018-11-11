package com.test.yuan.zktest.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * date:2018/11/10
 * author:y(123)
 * function:
 */
public class HttpUtil {
    private String mUrl;

    public HttpUtil(String url) {
        mUrl = url;
    }

    public String requeatUtil(){
            String result = null;
            try {
                URL url = new URL(mUrl);
                try {
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setConnectTimeout(5000);
                    urlConnection.setReadTimeout(5000);
                    int responseCode = urlConnection.getResponseCode();
                    if(responseCode==200){
                        result = String2string(urlConnection.getInputStream());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return result;
        }

        private String String2string(InputStream inputStream) {
            StringBuffer buffer = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            try {
                for(String temp=reader.readLine();temp!=null;temp=reader.readLine()){
                    buffer.append(temp);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return buffer.toString();
        }
}
