package com.mhd.basekit.viewkit.util.oldUtil;

import android.app.Application;

/**
 * Created by 13660 on 2019/4/11.
 */

public class BaseInit {
    private static Application mApplication;
    public static void init(Application application){
        mApplication=application;

    }

    public static Application getApplication(){
        return mApplication;
    }
}
