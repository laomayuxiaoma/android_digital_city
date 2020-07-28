package com.example.amapservice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by wangfei on 2019/4/10.
 */
public class LocationManager implements Serializable{

    public static ArrayList<ListenerConfig> managers = new ArrayList<>();
    public long interval;
    public LocationPolicy locationPolicy;
    public int locationNum;
    public OnLocationListener onLocationListener;
    public static ListenerConfig config;
    public LocationManager(Builder builder){
        this.interval=builder.interval;
        this.locationPolicy=builder.locationPolicy;
        this.onLocationListener=builder.onLocationListener;
        this.locationNum = builder.locationNum;
    }

    public OnLocationListener getOnLocationListener(){
        return onLocationListener;
    }

    public long getInterval() {
        return interval;
    }

    public LocationPolicy getLocationPolicy() {
        return locationPolicy;
    }

    public void start(Context context){
        Intent intent=new Intent(context,AMapService.class);
        Bundle bundle=new Bundle();
        ListenerConfig config = new ListenerConfig();
        config.interval = interval;
        config.type = locationPolicy.hashCode();
        config.onLocationListener = onLocationListener;
//        bundle.putSerializable("key", (Serializable) config);
        intent.putExtras(bundle);
        context.stopService(intent);
        context.startService(intent);

    }

    public static class Builder implements Serializable{
        public long interval;
        public LocationPolicy locationPolicy;
        public int locationNum;
        public OnLocationListener onLocationListener;
        public Builder(){
            this.interval=1000;
            locationPolicy=LocationPolicy.ONCE;
            locationNum = 1;
        }

        public Builder setOnLocationListener(OnLocationListener onLocationListener) {
            this.onLocationListener = onLocationListener;
            LocationUtil.managers.remove(onLocationListener);
            LocationUtil.managers.add(onLocationListener);
            return this;
        }

        public Builder setInterval(long interval) {
            this.interval = interval;
            return this;
        }

        public Builder setLocationPolicy(LocationPolicy locationPolicy) {
            this.locationPolicy = locationPolicy;
            return this;
        }

        public LocationManager build(){
            return new LocationManager(this);
        }
    }
}
