package org.kodigo.project.ApiPublic;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class WeatherState {

    public static Map<String,Object> jsonToMap(String str){
        Map<String,Object> map = new Gson().fromJson(str,new
                TypeToken<HashMap<String,Object>>() {}.getType());
        return map;
    }

    public String GetweatherStatus(String city) throws IOException {
        String urlApi = "http://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=078f3e50b28bd5406e852a3eddcdfefe";
        StringBuilder result = new StringBuilder();
        StringBuilder weatherStateCity = new StringBuilder();
        URL url = new URL(urlApi);
        URLConnection conn = url.openConnection();
        BufferedReader rd = new BufferedReader(new InputStreamReader (conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null){
            result.append(line);
        }
        rd.close();
        Map<String, Object> respMap = jsonToMap (result.toString());
        Map<String, Object> mainMap = jsonToMap (respMap.get("main").toString());
        Map<String, Object> windMap = jsonToMap (respMap.get("wind").toString());
        weatherStateCity
                .append("Temperature of ")
                .append(mainMap.get("temp"))
                .append(" degrees")
                .append(" and winds with speed of ")
                .append(windMap.get("speed"))
                .append(" km/h");
        return weatherStateCity.toString();
    }
}
