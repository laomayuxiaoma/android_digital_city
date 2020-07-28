package com.muheda.mhdsystemkit.sytemUtil;

import android.app.Application;
import android.content.Context;

public class SystemKit {

    public static Application mApplication;

    public static void init(Application application) {
        mApplication = application;
    }

    public static Context getContext() {
        if (mApplication == null) {
            throw new NullPointerException("SystemKit is not init()");
        }
        return mApplication.getApplicationContext();
    }
}
