package com.muheda.mhdsystemkit.sytemUtil.functionutil;

import android.app.Activity;
import android.content.Intent;

import java.io.Serializable;

/**
 * Created by 13660 on 2018/11/15.
 */

public class IntentToActivity {

    /**
     * 无数据跳转
     *
     * @param activity
     * @param cls
     */
    public static void skipActivity(Activity activity,
                                    Class<? extends Activity> cls) {
        skipActivity(activity, cls, 0, null);
    }

    /**
     * 无数据带启动模式的跳转
     *
     * @param activity
     * @param cls
     */
    public static void skipActivity(Activity activity,
                                    Class<? extends Activity> cls, int flag) {
        skipActivity(activity, cls, flag, null);
    }

    /**
     * 带参跳转
     *
     * @param activity
     * @param cls
     * @param stringArray
     */
    public static void skipActivity(Activity activity, Class<? extends Activity> cls, Object[][] stringArray) {
        skipActivity(activity, cls, Intent.FLAG_ACTIVITY_NEW_TASK, stringArray);
    }

    /**
     * 带参数和启动模式跳转
     *
     * @param activity
     * @param cls
     * @param flag
     * @param value
     */
    public static void skipActivity(Activity activity, Class<? extends Activity> cls, int flag, Object[][] value) {
        Intent intent = new Intent(activity, cls);
        if (flag != 0) {
            intent.setFlags(flag);
        }
        if (value != null) {
            for (Object[] objectValue : value) {
                if (objectValue[1] instanceof String) {
                    intent.putExtra((String) (objectValue[0]), ((String) objectValue[1]));
                }
                if (objectValue[1] instanceof Boolean) {
                    intent.putExtra((String) (objectValue[0]), (boolean) objectValue[1]);
                }
                if (objectValue[1] instanceof Integer) {
                    intent.putExtra((String) (objectValue[0]), (int) objectValue[1]);
                }
                if (objectValue[1] instanceof Float) {
                    intent.putExtra((String) (objectValue[0]), (float) objectValue[1]);
                }
                if (objectValue[1] instanceof Double) {
                    intent.putExtra((String) (objectValue[0]), (double) objectValue[1]);
                }
                if (objectValue[1] instanceof Serializable) {
                    intent.putExtra((String) (objectValue[0]), (Serializable) objectValue[1]);
                }
            }
        }
        activity.startActivity(intent);
    }
}
