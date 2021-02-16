package com.example.connection_servermysql;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLConnector {

    public String getJSON_DataFromDB() {
        StringBuilder output = new StringBuilder();
        try {
            java.net.URL url = new URL("http://121.125.42.31:8083/AndroidServer/getPersonData.php");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            if (conn != null) {
                conn.setConnectTimeout(10000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                int resCode = conn.getResponseCode();
                if (resCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream())) ;
                    String line = null;
                    while(true) {
                        line = reader.readLine();
                        if (line == null) {
                            break;
                        }
                        output.append(line + "\n");
                    }

                    reader.close();
                    conn.disconnect();
                }
            }
        } catch(Exception ex) {
            Log.e("SampleHTTP", "Exception in processing response.", ex);
            ex.printStackTrace();
        }

        return output.toString();
    }

    public int send_DataToDB(String name , String sex, int age, String address){
        try {
            java.net.URL url = new URL("http://121.125.42.31:8083/AndroidServer/inputPersonData.php");
            String postData = "name=" + name + "&" + "sex=" + sex + "&" + "age=" + age+ "&" + "address=" + address;
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(5000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(postData.getBytes("UTF-8"));
            outputStream.flush();
            outputStream.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String result = reader.readLine();
            conn.disconnect();
            Log.e("result",result+"  ㄴㅁㅇㄹㄴ");
            if(result.equals("-1")){
                return -1;//에러
            }else{
                return Integer.parseInt(result);//id 리턴
            }
        }
        catch (Exception e) {
            Log.i("Request", "request was failed.");
        }
        return 0;
    }

    public boolean remove_dataFromDB(int id){
        try {
            java.net.URL url = new URL("http://121.125.42.31:8083/AndroidServer/deletePersonData.php");
            String postData = "id=" + id;
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(5000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(postData.getBytes("UTF-8"));
            outputStream.flush();
            outputStream.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String result = reader.readLine();
            conn.disconnect();
            Log.e("result",result+"  ㄴㅁㅇㄹㄴ");
        }
        catch (Exception e) {
            Log.i("Request", "request was failed.");
        }
        return true;
    }
}
