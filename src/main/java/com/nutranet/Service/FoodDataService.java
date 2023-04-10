package com.nutranet.Service;

import com.google.gson.*;
import com.nutranet.Model.Entity.FoodDataEntity;
import com.nutranet.Model.Repository.FoodDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
public class FoodDataService<T> {
    @Autowired
    FoodDataRepository foodDataRepos;

    @Value("${API_KEY}")
    private String API_KEY;

    public List getSavedData() {
        List<FoodDataEntity> rs = foodDataRepos.findAll();
        return rs;
    }

    public void saveNewData() {
        BufferedReader reader = null;
        try{
            URL url = new URL("https://apis.data.go.kr/1471000/HtfsTrgetInfoService02/getHtfsInfoList02?numOfRows=100&serviceKey="+API_KEY+"&type=json");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(1000);
            conn.connect();

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            String inputLine = null;
            //PrettyPrinting 옵션을 추가하여 Gson 객체 생성
            Gson gson = new Gson();
            while((inputLine = reader.readLine()) != null){
                JsonObject jsonObject = (JsonObject) JsonParser.parseString(inputLine);
                System.out.println(jsonObject);
                System.out.println(jsonObject.get("header").getAsJsonObject().get("resultCode"));
            }

            reader.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
