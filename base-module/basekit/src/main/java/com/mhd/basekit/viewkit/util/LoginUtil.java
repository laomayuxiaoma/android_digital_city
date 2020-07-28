package com.mhd.basekit.viewkit.util;

import com.mhd.basekit.viewkit.util.route.RouteConstant;
import com.mhd.basekit.viewkit.util.route.RouteUtil;

/**
 * @author zhangming
 * @Date 2019/7/1 21:59
 * @Description: 登录相关
 */
public class LoginUtil {

    public static boolean isLogin() {
        if (Common.getUserInfo() == null) {
            RouteUtil.routeSkip(RouteConstant.DATA_LOGIN, new String[][]{});
            return false;
        }
        return true;
    }

}
