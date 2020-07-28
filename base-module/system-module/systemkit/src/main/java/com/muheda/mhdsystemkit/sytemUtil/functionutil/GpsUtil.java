package com.muheda.mhdsystemkit.sytemUtil.functionutil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.provider.Settings;

/**
 * Created by 13660 on 2019/4/2.
 */

public class GpsUtil {
    public static void initGPS(Context context){
        LocationManager locationManager = (LocationManager) context
                .getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager
                .isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)){
            // 转到手机设置界面，用户设置GPS
            Intent intent = new Intent(
                    Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            ((Activity)context).startActivityForResult(intent, 0); // 设置完成后返回到原来的界面
        }
    }
}
