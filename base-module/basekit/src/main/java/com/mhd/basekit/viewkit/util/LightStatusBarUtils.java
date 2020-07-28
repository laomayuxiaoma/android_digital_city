package com.mhd.basekit.viewkit.util;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.ViewGroup;

import com.muheda.mhdsystemkit.sytemUtil.uiutil.SizeUtils;

/**
 * @author zhangming
 * @Date 2019/7/8 9:48
 * @Description: 状态栏
 */
public class LightStatusBarUtils {
    public static void setLightStatusBar(Activity activity, boolean dark) {
        setLightStatusBar(activity, dark, false);
    }

    public static void setLightStatusBar(Activity activity, boolean dark, boolean isChangeRootColor) {
        com.luck.picture.lib.immersive.LightStatusBarUtils.setLightStatusBar(activity, dark);
        if (isChangeRootColor) {
            setRootViewFitsSystemWindows(activity,true);
        }
    }

    /**
     *  代码实现android:fitsSystemWindows
     *
     * @param activity
     */
    public static void setRootViewFitsSystemWindows(Activity activity, boolean fitSystemWindows) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ViewGroup winContent = (ViewGroup) activity.findViewById(android.R.id.content);
            if (winContent.getChildCount() > 0) {
                ViewGroup rootView = (ViewGroup) winContent.getChildAt(0);
                if (rootView != null) {
                    rootView.setFitsSystemWindows(fitSystemWindows);
                }
            }
        }

    }

    public static int getStatusBarHeight(Context context) {
        // 获得状态栏高度
        try{
            int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
            return context.getResources().getDimensionPixelSize(resourceId);
        }catch (Exception e){
            return SizeUtils.dp2px(20);
        }
    }
}
