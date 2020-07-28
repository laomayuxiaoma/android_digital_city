package com.muheda.mhdsystemkit.sytemUtil.functionutil;

import android.os.Build;
import android.os.StrictMode;

import java.util.HashMap;
import java.util.Map;

/**
 * 严苛模式(上线时需关闭)
 */
public class StrictModeUtil {

    private static Map<Class, Integer> classInstanceLimit = new HashMap<>();

    public static void initStrictMode() {
        strictMode();
    }

    public static void initStrictMode(Map<Class, Integer> classInstanceLimit) {
        StrictModeUtil.classInstanceLimit = classInstanceLimit;
        strictMode();
    }

    private static void strictMode() {
        StrictMode.setThreadPolicy(detectTP()
                .penaltyLog() //在Logcat 中打印违规异常信息 StrictMode过滤
                .penaltyFlashScreen() //API等级11
                .permitNetwork()
                .penaltyDropBox()
                .build());
        StrictMode.setVmPolicy(detectVm()
                .penaltyLog()
                .penaltyDropBox()
                .build());
    }

    private static StrictMode.ThreadPolicy.Builder detectTP() {
        StrictMode.ThreadPolicy.Builder builder = new StrictMode.ThreadPolicy.Builder();
        builder.detectDiskReads()//监测是否在主线程中读写磁盘
                .detectDiskWrites()//监测是否在主线程中读写磁盘
                .detectCustomSlowCalls();//
        final int targetSdk = Build.VERSION.SDK_INT;
        if (targetSdk >= Build.VERSION_CODES.M) {
            builder.detectResourceMismatches();
        }
        if (targetSdk >= Build.VERSION_CODES.O) {
            builder.detectUnbufferedIo();
        }
        return builder;
    }

    private static StrictMode.VmPolicy.Builder detectVm() {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        builder.detectLeakedSqlLiteObjects()
                .detectActivityLeaks();//监测Activity内存泄漏的情况
//                .detectLeakedClosableObjects()//当使用的资源没有被正确关闭时会触发
//                .setClassInstanceLimit(BaseApplication.class, 1);//设定某个类在内存中实例的上限
        if (classInstanceLimit != null) {
            for (Class key : classInstanceLimit.keySet()) {
                builder.setClassInstanceLimit(key, classInstanceLimit.get(key));
            }
        }

        final int targetSdk = Build.VERSION.SDK_INT;
        if (targetSdk >= Build.VERSION_CODES.O) {
            builder.detectContentUriWithoutPermission();
//            builder.detectUntaggedSockets();
        }
        if (targetSdk >= Build.VERSION_CODES.JELLY_BEAN) {
            builder.detectLeakedRegistrationObjects();//监测BroadcastReceiver 或者 ServiceConnection 注册类对象是否被正确释放
        }
        if (targetSdk >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            builder.detectFileUriExposure();
        }
        return builder;
    }
}
