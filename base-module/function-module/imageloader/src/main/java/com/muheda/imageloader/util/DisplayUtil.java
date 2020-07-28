package com.muheda.imageloader.util;

import android.content.Context;

public class DisplayUtil {
    /**
     * dp转px
     */
    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }



    /**
     * px转dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取屏幕的宽度
     */
    public static int getPhoneWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕的高度
     */
    public static int getPhoneheight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }
}
