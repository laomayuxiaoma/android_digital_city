package com.mhd.android_digital_city;

import android.app.Application;

import com.mhd.basekit.viewkit.util.InitBasekit;
import com.muheda.mhdsystemkit.sytemUtil.SystemKit;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SystemKit.init(this);
        InitBasekit.init(this);
    }
}
