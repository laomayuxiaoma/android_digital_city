package com.mhd.basekit.viewkit.util.route;

import android.app.Activity;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by 13660 on 2018/10/31.
 */

public class RouteUtil {

    //获取Fragment
    public static Fragment getFragment(String routeName) {
        return (Fragment) ARouter.getInstance().build(routeName).navigation();
    }

    //跳转
    public static void routeSkip(String routeName, String[][] params) {
        routeSkip(routeName, params, 0);
    }

    public static void routeSkip(String routeName, String[][] params, int flag) {
        Postcard postcard = ARouter.getInstance().build(routeName);
        for (String[] value : params) {
            postcard.withString(value[0], value[1]);
        }
        if (flag != 0) {
            postcard.withFlags(flag);
        }
        postcard.navigation();
    }

    //跳转
    public static void routeSkip(String routeName, Object[][] params) {
        routeSkip(routeName, params, 0, null, 0);
    }

    //跳转
    public static void routeSkip(String routeName, Object[][] params, Activity mContext, int requestCode) {
        routeSkip(routeName, params, 0, mContext, requestCode);
    }

    public static void routeSkip(String routeName, Object[][] params, int flag, Activity mContext, int requestCode) {
        Postcard postcard = ARouter.getInstance().build(routeName);
        for (Object[] value : params) {
            if (value[1] instanceof String) {
                postcard.withString((String) value[0], (String) value[1]);
            } else if (value[1] instanceof Boolean) {
                postcard.withBoolean((String) value[0], (boolean) value[1]);
            } else if (value[1] instanceof Integer) {
                postcard.withInt((String) value[0], (int) value[1]);
            } else if (value[1] instanceof Float) {
                postcard.withFloat((String) value[0], (float) value[1]);
            } else if (value[1] instanceof Character) {
                postcard.withChar((String) value[0], (char) value[1]);
            } else if (value[1] instanceof Long) {
                postcard.withLong((String) value[0], (Long) value[1]);
            }
        }
        if (flag != 0) {
            postcard.withFlags(flag);
        }
        if (mContext == null){
            postcard.navigation();
        }else {
            postcard.navigation(mContext,requestCode);
        }
    }


    public static void skipLogin() {
        //routeSkip(RouteConstant.Me_Login,new String[][]{{"code", "3"}});
    }
}
