package com.muheda.mhdsystemkit.sytemUtil.functionutil;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.muheda.mhdsystemkit.sytemUtil.SystemKit;

import java.util.List;

/**
 * Created by 13660 on 2018/11/15.
 */

public class PackageUtil {


    public static String getVersionName(Context mContext) {
        return getCurrentVersion(mContext).versionName;
    }

    public static int getVersion(Context mContext) {
        return getCurrentVersion(mContext).versionCode;
    }

    public static PackageInfo getCurrentVersion(Context mContext) {
        try {
            // 获取packagemanager的实例
            PackageManager packageManager = mContext.getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            return packageManager.getPackageInfo(
                    mContext.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isAppInstall(String appPackage) {
        final PackageManager packageManager = SystemKit.mApplication.getPackageManager();// 获取packagemanager
        List<PackageInfo> packageInfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (packageInfo != null) {
            for (int i = 0; i < packageInfo.size(); i++) {
                String pn = packageInfo.get(i).packageName;
                if (pn.equals(appPackage)) {
                    return true;
                }
            }
        }
        return false;
    }
}
