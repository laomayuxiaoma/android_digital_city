package com.example.amapservice;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import org.greenrobot.eventbus.EventBus;

/**
 * 创建日期：2019/12/4 on 8:52
 * 描述:
 * 作者: zhangming
 */
public class MLocationManager implements AMapLocationListener {

    private OnLocationListener mapLocationListener;
    private AMapLocationClient mLocationClient = null;
    private AMapLocationClientOption mLocationOption = null;
    private Context context;
    private int count = 3;

    private MLocationManager(){

    }

    public static MLocationManager start(Context context,OnLocationListener mapLocationListener){
        MLocationManager mLocationManager = new MLocationManager();
        mLocationManager.context = context;
        mLocationManager.mapLocationListener = mapLocationListener;
        mLocationManager.init();
        return mLocationManager;
    }

    public static MLocationManager start(Context context){
        MLocationManager mLocationManager = new MLocationManager();
        mLocationManager.context = context;
        mLocationManager.init();
        return mLocationManager;
    }

    private void init(){
        //初始化定位
        mLocationClient = new AMapLocationClient(context);
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setOnceLocation(true);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setNeedAddress(true);
        mLocationOption.setInterval(1000);
        mLocationOption.setKillProcess(true);
        mLocationOption.setLocationCacheEnable(false);
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.startLocation();
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        Location.isHasLocation = true;
        Location.isLocationSucess = true;
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                Location.latitude = aMapLocation.getLatitude();
                Location.longitude = aMapLocation.getLongitude();
                Location.city = aMapLocation.getCity();
                Location.province = aMapLocation.getProvince();
                Location.cityCode = aMapLocation.getCityCode();
                Location.isHasLocation = true;

                if (aMapLocation == null || TextUtils.isEmpty(aMapLocation.getCity())) {
                    return;
                }
                if (mapLocationListener != null){
                    mapLocationListener.getData(aMapLocation);
                    mapLocationListener = null;
                }
                EventBus.getDefault().post("has_permission");
                Log.e("TTTTTTTTYYY",aMapLocation.getCity()+"||");
            } else {
                //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
                if (12 == aMapLocation.getErrorCode()) {
                    EventBus.getDefault().post("no_permission");
                    Location.isLocationSucess = false;
                }
            }
        }
    }
}
