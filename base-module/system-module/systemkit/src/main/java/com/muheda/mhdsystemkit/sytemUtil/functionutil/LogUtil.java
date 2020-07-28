package com.muheda.mhdsystemkit.sytemUtil.functionutil;

import android.content.Context;
import android.util.Log;

import com.muheda.mhdsystemkit.sytemUtil.SystemKit;

import java.util.Calendar;

/**
 * Created by wf on 2017/9/23.
 */
public class LogUtil {

    public final static String TAG = "muheda";
    //start millis
    public static long startLogTimeInMillis = 0;
    private static boolean debug = true;
    public static boolean Debug = debug;
    public static boolean Info = debug;
    public static boolean Err = debug;

    public static void d(Context context, String message) {
        String tag = context.getClass().getSimpleName();
        d(tag, message);
    }

    /**
     * debug log
     */
    public static void d(String tag, String message) {
        if (Debug) {
            Log.d(tag, message);
        }
    }

    public static void d(Class<?> clazz, String message) {
        String tag = clazz.getSimpleName();
        d(tag, message);
    }

    public static void d(String message) {
        d(TAG, message + "\n\n");
    }

    public static void i(Context context, String message) {
        String tag = context.getClass().getSimpleName();
        i(tag, message);
    }

    /**
     * info log
     */
    public static void i(String tag, String message) {
        if (Info) {
            Log.i(tag, message + "\n\n");
        }
    }

    public static void i(Class<?> clazz, String message) {
        String tag = clazz.getSimpleName();
        i(tag, message);
    }

    public static void i(String message) {
        i(TAG, message);
    }

    public static void e(Context context, String message) {
        String tag = context.getClass().getSimpleName();
        e(tag, message);
    }

    /**
     * error log
     */
    public static void e(String tag, String message) {
        if (Err) {
            Log.e(tag, message);
        }
    }

    public static void e(Class<?> clazz, String message) {
        String tag = clazz.getSimpleName();
        e(tag, message);
    }

    public static void e(String message) {
        e(TAG, message);
    }

    public static void prepareLog() {
        String tag = SystemKit.getContext().getClass().getSimpleName();
        prepareLog(tag);
    }

    /**
     * Recorde log start millis
     */
    public static void prepareLog(String tag) {
        Calendar current = Calendar.getInstance();
        startLogTimeInMillis = current.getTimeInMillis();
        Log.d(tag, "Log prepare：" + startLogTimeInMillis);
    }

    public static void prepareLog(Class<?> clazz) {
        String tag = clazz.getSimpleName();
        prepareLog(tag);
    }

    public static void d(String message, boolean printTime) {
        String tag = SystemKit.getContext().getClass().getSimpleName();
        d(tag, message, printTime);
    }

    /**
     * Add duration
     * link to prepareLog()
     */
    public static void d(String tag, String message, boolean printTime) {
        Calendar current = Calendar.getInstance();
        long endLogTimeInMillis = current.getTimeInMillis();
        Log.d(tag, message + ":" + (endLogTimeInMillis - startLogTimeInMillis) + " ms");
    }

    public static void d(Class<?> clazz, String message, boolean printTime) {
        String tag = clazz.getSimpleName();
        d(tag, message, printTime);
    }

    /**
     * verbose
     */
    public static void debug(boolean d) {
        Debug = d;
    }

    public static void info(boolean i) {
        Info = i;
    }

    public static void error(boolean e) {
        Err = e;
    }

    public static void setVerbose(boolean d, boolean i, boolean e) {
        Debug = d;
        Info = i;
        Err = e;
    }

    public static void openAll() {
        Debug = true;
        Info = true;
        Err = true;
    }

    public static void closeAll() {
        Debug = false;
        Info = false;
        Err = false;
    }
}
