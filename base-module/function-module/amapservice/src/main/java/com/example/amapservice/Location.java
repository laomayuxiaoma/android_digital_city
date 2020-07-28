package com.example.amapservice;

import android.text.TextUtils;

/**
 * 创建日期：2019/11/29 on 10:30
 * 描述:
 * 作者: zhangming
 */
public class Location {

    public static double latitude;
    public static double longitude;
    public static String city;
    public static String province;
    public static String cityCode;

    public static String selectedCity;
    public static double selectedLatitude;
    public static double selectedLongitude;

    public static Boolean isLocationSucess = false;


    public static boolean isHasLocation = false;


    public static boolean isCurrLocation() {
        if (TextUtils.isEmpty(city)) {
            return false;
        }
        if (TextUtils.isEmpty(selectedCity)) {
            return true;
        }

        if ((city.startsWith(selectedCity) || selectedCity.startsWith(city)) && isLocationSucess) {
            return true;
        }
        return false;
    }

    public static double getmLatitude() {
        if (isCurrLocation() && Location.isLocationSucess){
            return latitude;
        }
        return selectedLatitude;
    }

    public static double getmLongitude() {
        if (isCurrLocation() && Location.isLocationSucess){
            return longitude;
        }
        return selectedLongitude;
    }

    public static String getmName(){
        if (isCurrLocation() && Location.isLocationSucess){
            return "我的位置";
        }
        return "当前位置";
    }

    public static double getPathLatitude() {
        if (Location.isLocationSucess){
            return latitude;
        }
        return selectedLatitude;
    }

    public static double getPathLongitude() {
        if (Location.isLocationSucess){
            return longitude;
        }
        return selectedLongitude;
    }
}
