package com.muheda.mhdsystemkit.sytemUtil.functionutil;

import android.graphics.Color;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.muheda.mhdsystemkit.sytemUtil.SystemKit;

/**
 * @author wangfei
 * @date 2019/8/30.
 */
public class ColorUtils {

    /**
     * 获取色值
     *
     * @param id
     * @return
     */
    public static int getColor(@ColorRes int id) {
        return ContextCompat.getColor(SystemKit.getContext(), id);
    }

    /**
     * 色串转色值
     *
     * @param colorString
     * @return
     */
    public static int string2Int(@NonNull String colorString) {
        return Color.parseColor(colorString);
    }

    /**
     * 色值转 RGB 串
     *
     * @param colorInt
     * @return
     */
    public static String int2RgbString(@ColorInt int colorInt) {
        colorInt = colorInt & 0x00ffffff;
        String color = Integer.toHexString(colorInt);
        while (color.length() < 6) {
            color = "0" + color;
        }
        return "#" + color;
    }

    /**
     * 颜色值转 ARGB 串
     *
     * @param colorInt
     * @return
     */
    public static String int2ArgbString(@ColorInt final int colorInt) {
        String color = Integer.toHexString(colorInt);
        while (color.length() < 6) {
            color = "0" + color;
        }
        while (color.length() < 8) {
            color = "f" + color;
        }
        return "#" + color;
    }

    /**
     * 获取随机颜色值
     *
     * @return
     */
    public static int getRandomColor() {
        return getRandomColor(true);
    }

    /**
     * 获取随机色值
     *
     * @param supportAlpha 是否支持透明度
     * @return
     */

    public static int getRandomColor(final boolean supportAlpha) {
        int high = supportAlpha ? (int) (Math.random() * 0x100) << 24 : 0xFF000000;
        return high | (int) (Math.random() * 0x1000000);
    }
}
