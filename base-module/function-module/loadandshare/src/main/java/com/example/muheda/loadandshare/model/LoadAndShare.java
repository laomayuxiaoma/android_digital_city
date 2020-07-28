package com.example.muheda.loadandshare.model;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

/**
 * Created by 13660 on 2018/11/6.
 */

public class LoadAndShare {
    private String wxAppId;
    static LoadAndShare mLoadAndShare;

    public static void init(Context context) {
        if (mLoadAndShare == null) {
            mLoadAndShare = new LoadAndShare();
        }
        context = context.getApplicationContext();
        ApplicationInfo appInfo = null;
        try {
            appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        if (appInfo != null && appInfo.metaData != null) {
            mLoadAndShare.wxAppId = appInfo.metaData.getString("WEIXIN_ID", "cuowu");

            Log.e("TTTTTTTTTTTTTTT",mLoadAndShare.wxAppId);
        } else {
            throw new IllegalStateException("error load social config");
        }
    }

    public static LoadAndShare getInstance() {
        if (mLoadAndShare == null) {
            mLoadAndShare = new LoadAndShare();
        }
        return mLoadAndShare;
    }

    public static String getWxId() {
        return getInstance().wxAppId;
    }
}
