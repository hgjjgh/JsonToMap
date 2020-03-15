package com.example.jsontomap;

import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyTask extends AsyncTask<String, Integer, String> {
    private String url;

    public MyTask(String url) {
        this.url = url;
    }

    @Override
    protected String doInBackground(String... params) {
        return getRemoteData();
    }

    private String getRemoteData() {
        HttpURLConnection connection = null;
        StringBuilder inStr = new StringBuilder();
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                inStr.append(line);
            }
        } catch (IOException e) {
            e.getMessage();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return inStr.toString();
    }
}

