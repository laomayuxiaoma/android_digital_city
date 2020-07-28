package com.muheda.mhdsystemkit.sytemUtil.functionutil;


import android.content.Context;

import com.tencent.mmkv.MMKV;

/**
 * Created by wf
 */
public final class MMKVUtil {
    private static MMKV kv;

    private MMKVUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void init(Context context) {
        MMKV.initialize(context);
        kv = MMKV.defaultMMKV();
    }

    public static void putBoolean(String key, Boolean value) {
        kv.encode(key, value);
    }

    public static Boolean getBoolean(String key, Boolean value) {
        return kv.decodeBool(key, value);
    }

    public static void putInt(String key, int value) {
        kv.encode(key, value);
    }

    public static int getInt(String key, int value) {
        return kv.decodeInt(key, value);
    }

    public static void putLong(String key, long value) {
        kv.encode(key, value);
    }

    public static Long getLong(String key, long value) {
        return kv.decodeLong(key, value);
    }

    public static void putFloat(String key, float value) {
        kv.encode(key, value);
    }

    public static Float getFloat(String key, float value) {
        return kv.decodeFloat(key, value);
    }

    public static void putDouble(String key, double value) {
        kv.encode(key, value);
    }

    public static Double getDouble(String key, double value) {
        return kv.decodeDouble(key, value);
    }

    public static void putString(String key, String value) {
        kv.encode(key, value);
    }

    public static String getString(String key, String value) {
        return kv.decodeString(key, value);
    }

    public static void putByte(String key, byte[] value) {
        kv.encode(key, value);
    }

    public static byte[] getByte(String key) {
        return kv.decodeBytes(key);
    }

    public static void deleteKey(String key) {
        kv.removeValueForKey(key);
    }

    public static void deleteKeys(String[] key) {
        kv.removeValuesForKeys(key);
    }


}
