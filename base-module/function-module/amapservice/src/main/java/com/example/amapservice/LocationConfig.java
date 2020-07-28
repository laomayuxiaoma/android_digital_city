package com.example.amapservice;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

/**
 * 创建日期：2019/11/22 on 18:02
 * 描述: 地图定位配置类
 * 作者: zhangming
 */
public class LocationConfig {

    public LocationManager locationManager;
    public ListenerConfig config;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    private AMapLocationClientOption mLocationOption = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = null;


    public LocationManager getLocationManager() {
        return locationManager;
    }

    public void setLocationManager(LocationManager locationManager) {
        this.locationManager = locationManager;
    }

    public ListenerConfig getConfig() {
        return config;
    }

    public void setConfig(ListenerConfig config) {
        this.config = config;
    }

    public AMapLocationClient getmLocationClient() {
        return mLocationClient;
    }

    public void setmLocationClient(AMapLocationClient mLocationClient) {
        this.mLocationClient = mLocationClient;
    }

    public AMapLocationClientOption getmLocationOption() {
        return mLocationOption;
    }

    public void setmLocationOption(AMapLocationClientOption mLocationOption) {
        this.mLocationOption = mLocationOption;
    }

    public AMapLocationListener getmLocationListener() {
        return mLocationListener;
    }

    public void setmLocationListener(AMapLocationListener mLocationListener) {
        this.mLocationListener = mLocationListener;
    }
}
