package com.example.jsontomap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private String title,descriptionFilterHtml,mapToString;
    private TextView textView,textView2,textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getExhibition();

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);


        textView.setText(title);
        textView2.setText(descriptionFilterHtml);
        textView3.setText(mapToString);
    }

    private void getExhibition() {

        String url = "https://cloud.culture.tw/frontsite/trans/SearchShowAction.do?method=doFindTypeJ&category=6";
        try {
            String jsonString = "";

            //取進來的值為政府openData https://data.gov.tw/dataset/6012 展覽資訊
            //值為jsonArray 裡面包含多個jsonObject展覽物件 我這邊只取第一個 取多個就用array跑for迴圈取出 放到recyclerview
            jsonString = new MyTask(url).execute().get();

            //1.jsonObject
            //1.1 url取值
            JSONArray jsonArray = new JSONArray(jsonString);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            //1.2 json轉map
            HashMap<String, String> changeToMap = new HashMap<>();
            changeToMap.put("title",jsonObject.getString("title"));
            changeToMap.put("descriptionFilterHtml",jsonObject.getString("descriptionFilterHtml"));
            title = changeToMap.get("title");
            descriptionFilterHtml = changeToMap.get("descriptionFilterHtml");
            //1.3 map轉json
            mapToString = new JSONObject(changeToMap).toString();

//            //2.JsonArray to Map
//            JSONArray jsonArray = new JSONArray(jsonString);
//            JSONObject jsonObject;
//            HashMap<String, String> changeToMap;
//            List<HashMap<String,String>> res = new ArrayList<>();
//            //我這邊只取兩次
//            for (int i = 0; i <= 1; i++) {
//                jsonObject = jsonArray.getJSONObject(i);
//                changeToMap = new HashMap<>();
//                changeToMap.put("title", title = jsonObject.getString("title"));
//                changeToMap.put("descriptionFilterHtml", jsonObject.getString("descriptionFilterHtml"));
//                res.add(changeToMap);
//            }
//            //Map To JSONArray
//            mapToString = new JSONArray(res).toString();

        } catch (ExecutionException | InterruptedException | JSONException e) {
            e.printStackTrace();
        }
    }
}
