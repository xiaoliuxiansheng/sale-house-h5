package com.example.wxgzh.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class CoordinatesUtil {
    static String ak="5bSX6lqF6irleeYplWXQVTFYz4tgw2Is";
    public static String loadJSON(String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        return json.toString();
    }

    public static Map<String, Double> getLngAndLat(String address) throws Exception {
        Map<String, Double> map = new HashMap<String, Double>();
        String url = "http://api.map.baidu.com/geocoder/v2/?address=" + address
                + "&output=json&ak="+ak;
        String json = loadJSON(url);
        JSONObject jsonObject = JSON.parseObject(json);

        if (jsonObject.get("status").toString().equals("0")) {
            double lon = jsonObject.getJSONObject("result").getJSONObject("location").getDouble("lng");
            double lat = jsonObject.getJSONObject("result").getJSONObject("location").getDouble("lat");
            map.put("lon", lon);
            map.put("lat", lat);
            System.out.println("经度：" + lon + "---纬度：" + lat);
        } else {
            System.out.println("未找到相匹配的经纬度！");
            throw new Exception();
        }
        return map;
    }

    public static void main(String[] args) throws Exception {

        //经度：113.26422165852385---纬度：23.154872071524395
        getLngAndLat("重庆市渝北区南极凤麟苑");
    }
}
