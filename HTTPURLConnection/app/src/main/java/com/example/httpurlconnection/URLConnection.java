package com.example.httpurlconnection;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLConnection {
    public String getResponse(String address) {
        try {
            URL url = new URL(address);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(10000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            int resCode = conn.getResponseCode();
            StringBuilder sb = new StringBuilder();
            if(resCode == HttpURLConnection.HTTP_OK){
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while((line = br.readLine()) != null){
                    sb.append(line);
                }
            }else{
                sb.append("error");
            }
            Log.e("result",sb.toString());
            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public String postResponse(String address, String param1 , String param2) {
        try {
            String postData = "data1="+param1+"&data2="+param2;
            URL url = new URL(address);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(10000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(postData.getBytes("UTF-8"));
            outputStream.flush();
            outputStream.close();

            int resCode = conn.getResponseCode();
            StringBuilder sb = new StringBuilder();
            if(resCode == HttpURLConnection.HTTP_OK){
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while((line = br.readLine()) != null){
                    sb.append(line);
                }
                conn.disconnect();
                br.close();
            }else{
                sb.append("error");
            }

            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}
