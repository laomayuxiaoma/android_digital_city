package com.mhd.basekit.config;

import android.graphics.Color;
import android.view.View;
import com.mhd.basekit.R;
import java.util.HashMap;

/**
 * Created by 13660 on 2018/11/12.
 */

public abstract class BaseConfig {
    public HashMap<String, Object> hashMap;

    public abstract void makeConfig();


    public BaseConfig() {
        hashMap = new HashMap<>();
        makeConfig();
    }

    public int getColor(String key) {
        try {
            return (int) hashMap.get(key);
        } catch (Exception e) {
            return Color.BLACK;
        }

    }

    public String getContent(String key) {
        try {
            return (String) hashMap.get(key);
        } catch (Exception e) {
            return "我是true";
        }
    }

    public int getViliable(String key) {
        try {
            return (int) hashMap.get(key);
        } catch (Exception e) {
            return View.VISIBLE;
        }
    }

    public int getImage(String key) {
        try {
            return (int) hashMap.get(key);
        } catch (Exception e) {
            return R.mipmap.ic_launcher;
        }
    }

    //异常暂时未null
    public Class getClass(String key) {
        try {
            return (Class) hashMap.get(key);
        } catch (Exception e) {
            return null;
        }
    }

    public Enum getEnum(String key){
        return (Enum) hashMap.get(key);
    }
}
