package com.mhd.basekit.viewkit.view.webview.util;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class JsonUtil {
    private Map<String, String> map = new HashMap<>();

    public static JsonUtil initMapUtil() {
        return new JsonUtil();
    }

    public JsonUtil put(String key, String value) {
        map.put(key, value);
        return this;
    }

    public String commit() {
        return getJson(map);
    }

    //map转为json串
    public static String getJson(Object map) {
        if (map instanceof String){
            return (String) map;
        }
        String jsonStr = "";
        Gson gson = new Gson();
        jsonStr = gson.toJson(map);
        if (map instanceof HashMap){
            ((HashMap)map).clear();
        }
        return jsonStr;
    }

    public static <T> T jsonToTarget(T target,String json) {
        try{
            return (T) new Gson().fromJson(json,target.getClass());
        }catch (Exception e){
            return null;
        }
    }

}
