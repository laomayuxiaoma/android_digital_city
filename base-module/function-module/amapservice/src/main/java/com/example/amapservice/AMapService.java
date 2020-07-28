package com.example.amapservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

/**
 * Created by wangfei on 2019/4/10.
 */
public class AMapService extends Service {
    public LocationManager locationManager;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    private AMapLocationClientOption mLocationOption = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = null;


    @Override
    public void onCreate() {
        super.onCreate();


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mLocationClient != null) {
           mLocationClient.stopLocation();
           mLocationClient.onDestroy();
        }
        mLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        Location.isLocationSucess = true;
                        Location.latitude = aMapLocation.getLatitude();
                        Location.longitude = aMapLocation.getLongitude();
                        Location.city = aMapLocation.getCity();
                        Location.province = aMapLocation.getProvince();
                        Location.cityCode = aMapLocation.getCityCode();
                        Location.isHasLocation = true;

                        if (aMapLocation == null || TextUtils.isEmpty(aMapLocation.getCity())) {
                            return;
                        }
                        for (int i = 0; i < LocationUtil.managers.size();i = 0) {
                            LocationUtil.managers.get(i).getData(aMapLocation);
                            LocationManager.managers.remove(0);
                        }
                        Log.e("TTTTTTTTYYY",aMapLocation.getCity()+"||");
                    } else {
                        //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                        Log.e("AmapError", "location Error, ErrCode:"
                                + aMapLocation.getErrorCode() + ", errInfo:"
                                + aMapLocation.getErrorInfo());
                        if ("10".equals(aMapLocation.getErrorCode())) {
                            Location.isLocationSucess = false;
                            Location.isHasLocation = false;
                        }
                    }
                }
            }
        };
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setNeedAddress(true);
        mLocationOption.setInterval(1000);
        mLocationOption.setKillProcess(true);
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.startLocation();
        return super.onStartCommand(intent,flags,startId);
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public boolean stopService(Intent name) {
        if (mLocationClient != null) {
            mLocationClient.stopLocation();
            mLocationClient.onDestroy();
        }
        return super.stopService(name);
    }
}
