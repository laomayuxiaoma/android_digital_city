package com.muheda.mhdsystemkit.sytemUtil.functionutil;

/**
 * Created by liguoying on 2017/7/4.
 * SharedPreferences 工具类
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.muheda.mhdsystemkit.sytemUtil.SystemKit;

import java.util.ArrayList;
import java.util.List;

public class SPUtil {
    private static SharedPreferences mSharedPreferences;

    private static void init() {
        if (mSharedPreferences == null) {
            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(
                    SystemKit.getContext());
        }
    }

    public static void setInt(String key, int value) {
        if (mSharedPreferences == null) {
            init();
        }
        mSharedPreferences.edit().putInt(key, value).apply();
    }

    public static int getInt(String key) {
        if (mSharedPreferences == null) {
            init();
        }
        return getInt(key, -1);
    }

    public static int getInt(String key, int defaultValue) {
        if (mSharedPreferences == null) {
            init();
        }
        return mSharedPreferences.getInt(key, defaultValue);
    }

    public static void setFloat(String key, float value) {
        if (mSharedPreferences == null) {
            init();
        }
        mSharedPreferences.edit().putFloat(key, value).apply();
    }

    public static Float getFloat(String key) {
        if (mSharedPreferences == null) {
            init();
        }
        return getFloat(key, -1f);
    }

    public static Float getFloat(String key, float defaultValue) {
        if (mSharedPreferences == null) {
            init();
        }
        return mSharedPreferences.getFloat(key, defaultValue);
    }

    public static void setBoolean(String key, boolean value) {
        if (mSharedPreferences == null) {
            init();
        }
        mSharedPreferences.edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(String key) {
        if (mSharedPreferences == null) {
            init();
        }
        return getBoolean(key, false);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        if (mSharedPreferences == null) {
            init();
        }
        return mSharedPreferences.getBoolean(key, defaultValue);
    }

    public static void setString(String key, String value) {
        if (mSharedPreferences == null) {
            init();
        }
        mSharedPreferences.edit().putString(key, value).apply();
    }

    public static String getString(String key) {
        if (mSharedPreferences == null) {
            init();
        }
        return getString(key, "");
    }

    public static String getString(String key, String defaultValue) {
        if (mSharedPreferences == null) {
            init();
        }
        return mSharedPreferences.getString(key, defaultValue);
    }

    public static void remove(String key) {
        if (mSharedPreferences == null) {
            init();
        }
        mSharedPreferences.edit().remove(key).apply();
    }

    public static void clearAll() {
        if (mSharedPreferences == null) {
            init();
        }
        mSharedPreferences.edit().clear().apply();
    }

    public static int getInt(Context context, String key,
                             int defaultValue) {
        if (mSharedPreferences == null) {
            init();
        }
        return getInt(key,defaultValue);
    }

    public static void setSharedStringData(Context context, String key,
                                           String value) {
        if (mSharedPreferences == null) {
            init();
        }
        setString(key,value);
    }

    public static void remove(Context context, String key) {
        if (mSharedPreferences == null) {
            init();
        }
        remove(key);
    }

    public static void clearAll(Context context) {
        if (mSharedPreferences == null) {
            init();
        }
        clearAll();
    }

    public static void putStrListValue(Context context, String key,
                                       List<String> strList) {
        if (null == strList) {
            return;
        }
        // 保存之前先清理已经存在的数据，保证数据的唯一性
        removeStrList(context, key);
        int size = strList.size();
        setInt(key + "size", size);
        for (int i = 0; i < size; i++) {
            setString(key + i, strList.get(i));
        }
    }
    public static void removeStrList(Context context, String key) {
        int size = getInt(context, key + "size", 0);
        if (0 == size) {
            return;
        }
        remove(context, key + "size");
        for (int i = 0; i < size; i++) {
            remove(context, key + i);
        }
    }
    public static List<String> getStrListValue(Context context, String key) {
        List<String> strList = new ArrayList<String>();
        int size = getInt(context, key + "size", 0);
        //Log.d("sp", "" + size);
        for (int i = 0; i < size; i++) {
            strList.add(getString( key + i, null));
        }
        return strList;
    }

}
