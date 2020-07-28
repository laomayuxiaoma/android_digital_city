package com.mhd.basekit.viewkit.util.wholeCommon;

/**
 * Created by 13660 on 2019/4/1.
 */

public class DataConstant {
    //数据常量
    public static final String ISFRISTUSE = "ISFRISTUSE"; //是否是首次
    public static final String METHODJSBACK = "{\"method\": \"jsBack\"}";
    public static final String WEBCONFIRMTRUE = "{\"confirm\":\"1\"}";
    public static final String WEBCONFIRMFALSE = "{\"confirm\":\"0\"}";
    public static final String WEB_CONFIRM_TRUE = "1";
    public static final String WEB_CONFIRM_FALSE = "0";



    public static final int HOME_WEBVIEW_TYPE_ONE = 1;
    public static final int HOME_WEBVIEW_TYPE_TWO = 2;
    public static final int HOME_WEBVIEW_TYPE_THREE = 3;
    public static final int HOME_WEBVIEW_TYPE_FIVE = 5;

    public static final int TEXT_ZOOM_SIZE = 260;



    public static String qrCode = "";

    public static String getQrCode() {
        return qrCode;
    }

    public static void setQrCode(String qrCode) {
        DataConstant.qrCode = qrCode;
    }
}
