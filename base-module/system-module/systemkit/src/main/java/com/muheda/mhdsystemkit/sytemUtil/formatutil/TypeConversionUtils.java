package com.muheda.mhdsystemkit.sytemUtil.formatutil;

/**
 * @author zhangming
 * @Date 2019/5/21 11:46
 * @Description: int的相关操作帮助类
 */
public class TypeConversionUtils {

    /**
     * 转换为double
     *
     * @param value        需要进行转换的参数
     * @param defaultValue 转换失败时的默认值
     * @return
     */
    public static double convertToDouble(Object value, double defaultValue) {
        if (value == null || "".equals(value.toString().trim())) {
            return defaultValue;
        }
        try {
            return Double.valueOf(value.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 转换为int
     *
     * @param value        需要进行转换的参数
     * @param defaultValue 转换失败时的默认值
     * @return
     */
    public static int convertToInt(Object value, int defaultValue) {
        if (value == null || "".equals(value.toString().trim())) {
            return defaultValue;
        }
        try {
            return Integer.valueOf(value.toString());
        } catch (Exception e) {
            try {
                return Double.valueOf(value.toString()).intValue();
            } catch (Exception el) {
                return defaultValue;
            }
        }
    }

}
